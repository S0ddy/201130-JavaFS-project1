const url = "http://localhost:8080/project-1/manager/";

document.getElementById("all").addEventListener('click', showAllReimb);
document.getElementById("manager-submit").addEventListener('click', changeStatus);
document.getElementById("in-process").addEventListener('click', inProcess);
document.getElementById("approved").addEventListener('click', approved);
document.getElementById("denied").addEventListener('click', denied);
document.getElementById("logout").addEventListener('click', logout);

async function logout() {
    response = await fetch(url + "logout", { credentials: 'include' });
    window.location.href = 'http://localhost:8080/project-1/';
}

async function denied() {
    document.getElementById("change-status-success").style.display = "none";
    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("manager-table-row").style.display = "block";

        let obj = {
            'status': 3
        }

        let response = await fetch(url + 'reimb-by-status', {
                    method: "POST",
                    body: JSON.stringify(obj),
                    credentials: "include"
                });

        if (response.status === 200) {
            document.getElementById("manager-table-body").innerHTML = "";
            let data = await response.json();           

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function approved() {
    document.getElementById("change-status-success").style.display = "none";
    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("manager-table-row").style.display = "block";

        let obj = {
            'status': 2
        }

        let response = await fetch(url + 'reimb-by-status', {
                    method: "POST",
                    body: JSON.stringify(obj),
                    credentials: "include"
                });

        if (response.status === 200) {
            document.getElementById("manager-table-body").innerHTML = "";
            let data = await response.json();           

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function inProcess() {

    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("change-status-success").style.display = "none";
        document.getElementById("manager-table-row").style.display = "block";

        let obj = {
            'status': 1
        }

        let response = await fetch(url + 'reimb-by-status', {
                    method: "POST",
                    body: JSON.stringify(obj),
                    credentials: "include"
                });

        if (response.status === 200) {
            document.getElementById("manager-table-body").innerHTML = "";
            let data = await response.json();

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function changeStatus() {

    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {

        let listOfObjects = [];
        let el = document.getElementsByClassName("form-control");

        for (let i = 0; i < el.length; i++) {
            if (el[i].value != 'Select type') {
                let singleObj = {};
                singleObj['id'] = Number(el[i].id);
                if (el[i].value == 'Deny') {
                    singleObj['status'] = 3;
                } else if (el[i].value == 'Approve') {
                    singleObj['status'] = 2;
                }

                let response = await fetch(url + 'change-status', {
                    method: "POST",
                    body: JSON.stringify(singleObj),
                    credentials: "include"
                });

                if (response.status === 200) {
                    document.getElementById("manager-table-row").style.display = "none";
                    document.getElementById("change-status-success").style.display = "block";
                } else {
                    document.getElementById("manager-table-body").innerHTML = ":(";
                }
            }
        }


    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }

}

async function showAllReimb() {
    document.getElementById("change-status-success").style.display = "none";
    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("manager-table-row").style.display = "block";

        response = await fetch(url + "all-reimb", { credentials: 'include' });

        if (response.status === 200) {
            document.getElementById("manager-table-body").innerHTML = "";
            let data = await response.json();

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function showTable(data) {
    document.getElementById("change-status-success").style.display = "none";
    for (let reimb of data) {
        let row = document.createElement("tr");

        let cell = document.createElement("td");
        cell.id = reimb.id;
        cell.innerHTML = reimb.id;
        row.appendChild(cell);

        let cell2 = document.createElement("td");
        cell2.innerHTML = reimb.amount;
        row.appendChild(cell2);

        let cell3 = document.createElement("td");
        let submitted = new Date(reimb.submitted);
        cell3.innerHTML = submitted.toDateString();
        row.appendChild(cell3);

        let cell4 = document.createElement("td");
        if (reimb.resolved != null) {
            let resolved = new Date(reimb.resolved);
            cell4.innerHTML = resolved.toDateString();
        }
        else {
            cell4.innerHTML = "";
        }
        row.appendChild(cell4);

        let cell5 = document.createElement("td");
        cell5.innerHTML = reimb.description;
        row.appendChild(cell5);

        let cell6 = document.createElement("td");
        if (reimb.resolved)
            cell6.innerHTML = reimb.resolver;
        else
            cell6.innerHTML = "";
        row.appendChild(cell6);

        let cell7 = document.createElement("td");
        let status;
        switch (reimb.status) {
            case 1:
                status = "in process";
                break;
            case 2:
                status = "approved";
                break;
            case 3:
                status = "rejected";
                break;
        }
        cell7.innerHTML = status;
        row.appendChild(cell7);

        let cell8 = document.createElement("td");
        let type;
        switch (reimb.type) {
            case 1:
                type = "Associate Trabel Expense";
                break;
            case 2:
                type = "Certification";
                break;
            case 3:
                type = "Relocation to Training";
                break;
            case 3:
                type = "Software Engineer - Travel Expenses";
                break;
        }
        cell8.innerHTML = type;
        row.appendChild(cell8);

        let cell9 = document.createElement("td");
        if (reimb.status == 1) {
            cell9.innerHTML = `<select id="${reimb.id}" class="form-control"><option selected>Select type</option><option>Approve</option><option>Deny</option></select>`;
        } else {
            cell9.innerHTML = '';
        }

        row.appendChild(cell9);

        document.getElementById("manager-table-body").appendChild(row);

    }
}
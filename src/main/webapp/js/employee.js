const url = "http://localhost:8080/project-1/employee/";

document.getElementById("submit-request").addEventListener('click', showSubmitForm);
document.getElementById("my-history").addEventListener('click', showHistory);
document.getElementById("pending-request").addEventListener('click', showPending);

async function showPending() {

    hideAll();

    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("emplTableRow").style.display = "block";

        response = await fetch(url + "pending", { credentials: 'include' });

        if (response.status === 200) {
            console.log(response);
            document.getElementById("emplTableBody").innerHTML = "";
            let data = await response.json();

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function showHistory() {

    hideAll();

    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("emplTableRow").style.display = "block";

        response = await fetch(url + "history", { credentials: 'include' });

        if (response.status === 200) {
            console.log(response);
            document.getElementById("emplTableBody").innerHTML = "";
            let data = await response.json();

            await showTable(data);
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function showSubmitForm() {

    hideAll()

    let resp = await fetch(url + 'check-session', { credentials: "include" });
    if (resp.status === 200) {
        document.getElementById("submitRequestForm").style.display = "block";
        document.getElementById("submitForm").addEventListener('click', submitForm);
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}
async function submitForm() {
    let checkSession = await fetch(url + 'check-session', { credentials: "include" });

    if (checkSession.status === 200) {
        let embType = document.getElementById("selectType").value;
        let embAmount = document.getElementById("amount").value;
        let embComment = document.getElementById("comment").value;

        if (embType === 'Select type' || embAmount === '' || embComment === '') {
            document.getElementById("reimbFormStatus").innerText = "All fields are required*";
        } else if (isNaN(embAmount) || embAmount < 1) {
            document.getElementById("reimbFormStatus").innerText = "Please input correct reimbursement amount*";
        } else {

            switch (embType) {
                case 'Associate Trabel Expense':
                    embType2 = 1;
                    break;
                case 'Certification':
                    embType2 = 2;
                    break;
                case 'Relocation to Training':
                    embType2 = 3;
                    break;
                case 'Software Engineer - Travel Expenses':
                    embType2 = 4;
                    break;
            }

            console.log(embType);
            console.log(embType2);

            let reimbursement = {
                type: embType2,
                amount: embAmount,
                description: embComment
            };
            let resp = await fetch(url + 'create-reimbursement', {
                method: "POST",
                body: JSON.stringify(reimbursement),
                credentials: "include"
                //Credentials: "include" will ensure that they cookie is captured,
                //future fetch request will also require this value in order to send the cooke back.
            });

            if (resp.status === 200) {
                document.getElementById("submitRequestForm").style.display = "none";
                document.getElementById("formSent").style.display = "block";
            } else {
                document.getElementById("reimbFormStatus").innerText = "Something went wrong... Try again";
            }
        }
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function hideAll() {
    document.getElementById("emplTableRow").style.display = "none";
    document.getElementById("submitRequestForm").style.display = "none";
}

async function showTable(data) {
    for (let reimb of data) {
        console.log(reimb);
        let row = document.createElement("tr");

        let cell = document.createElement("td");
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

        document.getElementById("emplTableBody").appendChild(row);

    }
}
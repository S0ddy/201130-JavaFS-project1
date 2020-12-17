const url = "http://localhost:8080/project-1/employee/";

document.getElementById("submit-request").addEventListener('click', showSubmitForm);

async function showSubmitForm() {
    // console.log(document.getElementById("selectType").value);
    // document.getElementById("avbody").innerHTML("");

    // let resp = await fetch(url + 'check-session', {
    //     method: "GET",
    //     credentials: "include"
    //     //Credentials: "include" will ensure that they cookie is captured,
    //     //future fetch request will also require this value in order to send the cooke back.
    // });

    let resp = await fetch(url + 'check-session', {credentials: "include"});

    console.log(resp);
    console.log(resp.status);
    if (resp.status === 200) {

        document.getElementById("testButton").addEventListener('click', submitForm);

        


        
    } else {
        window.location.href = 'http://localhost:8080/project-1/';
    }
}

async function submitForm() {

    let type = document.getElementById("selectType").value;
    console.log(type);

}



const url = "http://localhost:8080/project-1/";

document.getElementById("loginbtn").addEventListener('click', loginFunc);

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username: usern,
        password: userp
    };

    let resp = await fetch(url + 'login', {
        method: "POST",
        body: JSON.stringify(user),
        credentials: "include"
        //Credentials: "include" will ensure that they cookie is captured,
        //future fetch request will also require this value in order to send the cooke back.
    });

    if(resp.status===200) {
        document.getElementById("loginStatus").innerText = "You have logged in";
        await assembleFunc();
    }else{
        document.getElementById("loginStatus").innerText = "Login failed! Reload th page of the computer";
    }

}
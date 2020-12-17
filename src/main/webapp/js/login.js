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

    if (resp.status === 200) {
        let data = await resp.json();
        if (data == "Employee") {
            window.location.href = 'employee.html';
        } else if (data == "Manager") {
            window.location.href = 'manager.html';
        }
        console.log(data);


    } else {
        document.getElementById("loginStatus").innerText = "Login failed! Try again";
    }

}
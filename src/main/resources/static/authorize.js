Authorize();

function Authorize() {
    fetch('http://localhost:8080/user/authorizedUser')
        .then(response => response.json())
        .then(user => {
            let f = listRoles(user);
            document.getElementById("header").textContent = user.username + ' с ролью: ' + f;

            if (!(f.includes("ROLE_ADMIN"))) {
                document.getElementById("adminContent").remove();
                document.getElementById("user-tab").classList.add("active");
                document.getElementById("admin").remove()
                document.getElementById("user").classList.add("show", "active")
            } else {
                showAllUsers()
                document.getElementById("admin-tab").classList.add("active");
                document.getElementById("admin").classList.add("show", "active");
            }

        });
}
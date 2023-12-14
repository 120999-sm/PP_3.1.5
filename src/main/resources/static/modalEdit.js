function getModalEdit(id) {

    fetch('http://localhost:8080/admin/userById/' + id)
        .then(response => response.json())
        .then(user => {

            let adminSelect = "";
            let userSelect = "";

            for (let i = 0; i < user.roles.length; i++) {
                if (user.roles[i].name.includes("ADMIN")) {
                    adminSelect = "selected";
                } else if (user.roles[i].name.includes("USER")) {
                    userSelect = "selected";
                }
            }

            let modal = document.getElementById('modalWindow');

            modal.innerHTML =
                `<div id="modalEdit"
     class="modal fade" tabindex="-1" role="dialog"
     aria-labelledby="TitleModalLabel" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="TitleModalLabel">Edit user</h5>
            </div>
            <div class="modal-body bg-white">
                <form id="formEditUser" style="width: 200px;"
                       class="form-signin mx-auto font-weight-bold text-center">
                    <p>
                        <label>id</label>
                        <input class="form-control form-control-sm" type="text"
                               id="editID" name="id" value="` + user.id + `" readonly>
                    </p>
                    <p>
                        <label>Имя</label>
                        <input class="form-control form-control-sm" type="text"
                               id="editFirstName" value="` + user.firstName + `"
                               placeholder="First name" required>
                    </p>
                    <p>
                        <label>Фамилия</label>
                        <input class="form-control form-control-sm" type="text"
                               id="editLastName" value="` + user.lastName + `" 
                               placeholder="Last name" required>
                    </p>
                    <p>
                        <label>Возраст</label>
                        <input class="form-control form-control-sm" type="number"
                               id="editAge" value="` + user.age + `" 
                               placeholder="Age" required>
                    </p>
                    <p>
                        <label>Имя пользователя</label>
                        <input class="form-control form-control-sm" type="text"
                               id="editUsername" value="` + user.username + `"
                               placeholder="Email" required>
                    </p>
                    <p>
                        <label>Пароль</label>
                        <input class="form-control form-control-sm" type="password"
                               id="editPassword" placeholder="Password">
                    </p>
                    <p>
                        <label>Роль</label>
                        <select id="editRoles" name="roles" multiple size="2" required 
                               class="form-control form-control-sm">
                            <option value="ROLE_ADMIN" ` + adminSelect + `>ADMIN</option>
                            <option value="ROLE_USER" ` + userSelect + `>USER</option>
                        </select>
                    </p>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Закрыть</button>
                <button class="btn btn-primary" data-bs-dismiss="modal"
                        onclick="editUser()">Изменить</button>
            </div>
        </div>
    </div>
</div>`;

            $("#modalEdit").modal('show');

        });
}
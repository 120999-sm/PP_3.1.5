function editUser() {

    let form = window.formEditUser.editRoles;
    let new_Roles = [];

    for (let i = 0; i < form.length; i++) {
        let option = form.options[i];
        if (option.selected) {
            new_Roles.push(option.value);
        }
    }

    let id = window.formEditUser.editID.value;

    fetch('http://localhost:8080/admin/update', {
        method: 'PUT',
        body: JSON.stringify({
            id: window.formEditUser.editID.value,
            firstName: window.formEditUser.editFirstName.value,
            lastName: window.formEditUser.editLastName.value,
            age: window.formEditUser.editAge.value,
            username: window.formEditUser.editUsername.value,
            password: window.formEditUser.editPassword.value,
            roles: new_Roles
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            $('#' + id).replaceWith('<tr id=' + id + '>' +
                '<td>' + id + '</td>' +
                '<td>' + window.formEditUser.editFirstName.value + '</td>' +
                '<td>' + window.formEditUser.editLastName.value + '</td>' +
                '<td>' + window.formEditUser.editAge.value + '</td>' +
                '<td>' + window.formEditUser.editUsername.value + '</td>' +
                '<td>' + listRoles(user) + '</td>' +
                '<td> <button type="button" onclick="getModalEdit(' + id + ')" class="btn btn-info btn-sm ">Изменить</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + id + ')" class="btn btn-danger btn-sm">Удалить</button> </td>' +
                '</tr>');
        });
}
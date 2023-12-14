function newUser() {
    let form = window.formNewUser.newRoles;
    let new_Roles = [];

    for (let i = 0; i < form.length; i++) {
        let option = form.options[i];
        if (option.selected) {
            new_Roles.push(option.value);
        }
    }

    fetch('http://localhost:8080/admin/create', {
        method: 'POST',
        body: JSON.stringify({
            firstName: window.formNewUser.newFirstName.value,
            lastName: window.formNewUser.newLastName.value,
            age: window.formNewUser.newAge.value,
            username: window.formNewUser.newUsername.value,
            password: window.formNewUser.newPassword.value,
            roles: new_Roles
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => response.json())
        .then(user => {
            $('#tBody tr:last').after('<tr id=' + user.id + '>' +
                '<td>' + user.id + '</td>' +
                '<td>' + window.formNewUser.newFirstName.value + '</td>' +
                '<td>' + window.formNewUser.newLastName.value + '</td>' +
                '<td>' + window.formNewUser.newAge.value + '</td>' +
                '<td>' + window.formNewUser.newUsername.value + '</td>' +
                '<td>' + listRoles(user) + '</td>' +
                '<td> <button type="button btn-info" onclick="getModalEdit(' + user.id + ')" class="btn btn-info btn-sm">Изменить</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + user.id + ')" class="btn btn-danger btn-sm">Удалить</button> </td>' +
                '</tr>');

            window.formNewUser.newFirstName.value = "";
            window.formNewUser.newLastName.value = "";
            window.formNewUser.newAge.value = "";
            window.formNewUser.newUsername.value = "";
            window.formNewUser.newPassword.value = "";
            window.formNewUser.newRoles.value = "";

            $('#NewUserCreated').modal('show');
        });
}
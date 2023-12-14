function listRoles(user) {

    let roles = "";
    for (let i = 0; i < user.roles.length; i++) {
        roles += " " + user.roles[i].name;
    }

    return roles;
}
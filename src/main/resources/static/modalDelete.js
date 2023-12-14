function getModalDelete(id) {

    fetch('http://localhost:8080/admin/userById/' + id)
        .then(response => response.json())
        .then(user => {
            let adminSelect = "";
            let userSelect = "";

            for (let i = 0; i < user.roles.length; i++) {
                if (user.roles[i].name.includes("ADMIN")) {
                    adminSelect = "selected";
                }
                if (user.roles[i].name.includes("USER")) {
                    userSelect = "selected";
                }
            }

            let modal = document.getElementById('modalWindow');

            modal.innerHTML =
                `<div id="modalDelete"
      class="modal fade" tabindex="-1" role="dialog" 
      aria-labelledby="TitleModalLabel" aria-hidden="true"  
      data-backdrop="static" data-keyboard="false"> 
     <div class="modal-dialog modal-dialog-scrollable"> 
         <div class="modal-content"> 
             <div class="modal-header"> 
                 <h5 class="modal-title" id="TitleModalLabel">Удалить пользователя</h5> 
             </div> 
             <div class="modal-body bg-white"> 
                 <form id="formEditUser" style="width: 200px;"  
                        class="form-signin mx-auto font-weight-bold text-center"> 
                     <p> 
                         <label>id</label> 
                         <input class="form-control form-control-sm" type="text" 
                                name="id" value="` + user.id + `" readonly> 
                     </p> 
                     <p> 
                         <label>Имя</label> 
                         <input class="form-control form-control-sm" type="text" 
                                value="` + user.name + `" readonly> 
                     </p> 
                     <p> 
                         <label>Фамилия</label> 
                         <input class="form-control form-control-sm" type="text" 
                                value="` + user.lastName + `" readonly> 
                     </p> 
                     <p> 
                         <label>Возраст</label> 
                         <input class="form-control form-control-sm" type="number" 
                                value="` + user.age + `" readonly> 
                     </p> 
                     <p> 
                         <label>Имя пользователя</label> 
                         <input class="form-control form-control-sm" type="text" 
                                value="` + user.username + `" readonly> 
                     </p> 
                     <p> 
                         <label>Роль</label> 
                         <select class="form-control form-control-sm" multiple size="2" readonly> 
                             <option value="ADMIN"` + adminSelect + ` >ADMIN</option> 
                             <option value="USER"` + userSelect + `>USER</option> 
                         </select> 
                     </p> 
                 </form> 
             </div> 
             <div class="modal-footer"> 
                 <button type="button" class="btn btn-secondary" 
                         data-bs-dismiss="modal">Закрыть</button> 
                 <button class="btn btn-danger" data-bs-dismiss="modal" 
                         onclick="deleteUser(` + user.id + `)">Удалить</button> 
             </div> 
         </div> 
     </div> 
 </div>`;

            $("#modalDelete").modal('show');

        });
}
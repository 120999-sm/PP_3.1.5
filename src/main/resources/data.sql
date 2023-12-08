insert into roles (role) value ('ROLE_ADMIN');
insert into roles (role) value ('ROLE_USER');

insert into users (email, first_name, last_name, password, user_name) VALUES ('admin@admin.com','admin', 'admin', 'admin', 'admin' );
insert into user_roles (users_id, roles_id) VALUES (1,1);

insert into users (email, first_name, last_name, password, user_name) VALUES ('user@user.com','user', 'user', 'user', 'user' );
insert into user_roles (users_id, roles_id) VALUES (2,2);
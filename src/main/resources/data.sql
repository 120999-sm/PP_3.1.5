insert into roles (role) value ('ROLE_ADMIN');
insert into roles (role) value ('ROLE_USER');

insert into users (username, last_name, age,email, password) VALUES ('admin', 'admin', 11 ,'admin@admin.com', 'admin');
insert into users_roles (user_id, role_id) VALUES (1,1);

insert into users (username, last_name, age,email, password) VALUES ('user', 'user', 10 ,'user@user.com', 'user');
insert into users_roles (user_id, role_id) VALUES (2,2);
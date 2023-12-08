drop table if exists roles;
drop table if exists user_roles;
drop table if exists users;

create table roles (id bigint not null auto_increment, role varchar(255), primary key (id)) engine=InnoDB;
create table user_roles (users_id bigint not null, roles_id bigint not null) engine=InnoDB;
create table users (id bigint not null auto_increment, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), user_name varchar(255), primary key (id)) engine=InnoDB;
alter table user_roles add constraint FKdbv8tdyltxa1qjmfnj9oboxse foreign key (roles_id) references roles (id);
alter table user_roles add constraint FKoovdgg7vvr1hb8vw6ivcrv3tb foreign key (users_id) references users (id);
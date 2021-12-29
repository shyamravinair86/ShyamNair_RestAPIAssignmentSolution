/*insert into roles(name) values('ADMIN');
insert into roles(name) values('USER');*/

insert into users(username, password) values('admin', '$2a$12$Y218JAdcdpx.HCrNU2Lqw.GxB2lTlWoqSANorfWhzTkidCOzaIXim');
insert into users(username, password) values('user', '$2a$12$I1lFPYKnt46irkRbkEQuwesX8Dae57IvUVZ3aGoKZ6Yc.O3Rw5kMS');

/*insert into users_roles(user_id, role_id) values(1, 1);
insert into users_roles(user_id, role_id) values(2, 2);

insert into employees(user_id, first_name, last_name, email) values(1, 'Shyam', 'Nair', 'snair@gl.com');
insert into employees(user_id, first_name, last_name, email) values(2, 'Shankar', 'Nair', 'shnair@gl.com');
insert into employees(user_id, first_name, last_name, email) values(3, 'Iyswarya', 'Lakshmi', 'iyshu@gl.com');*/

insert into roles (name) values('ADMIN');
insert into roles (name) values('USER');

insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 2);

insert into employees (employee_id, first_name, last_name, email) values (1, 'Shyam', 'Nair', 'snair@gmail.com');
insert into employees (employee_id, first_name, last_name, email) values (2, 'Shankar', 'Nair', 'shnair@gmail.com');
insert into employees (employee_id, first_name, last_name, email) values (3, 'Iyswarya', 'Lakshmi', 'iyshu@gmail.com');
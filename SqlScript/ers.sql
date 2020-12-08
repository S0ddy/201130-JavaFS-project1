--CREATE schema ers_schema


show search_path;
set search_path to ers_schema;

create table ers_reimbursement_status (
	reimb_status_id SERIAL primary key,
	reimb_status VARCHAR(10) not null
);

create table ers_reimbursement_type (
	reimb_type_id SERIAL primary key,
	reimb_type VARCHAR(10) not null
);

create table ers_user_roles (
	ers_user_role_id SERIAL primary key,
	user_role VARCHAR(10) not null
);

create table ers_users (
	ers_users_id SERIAL primary key,
	ers_username VARCHAR(50) unique not null,
	ers_password VARCHAR(50) not null,
	ers_first_name VARCHAR(100) not null,
	ers_last_name VARCHAR(100) not null,
	user_email VARCHAR(100) unique not null,
	user_role_id INTEGER references ers_user_roles(ers_user_role_id) not null
);

create table ers_reimbursement (
	reimb_id SERIAL primary key,
	reimb_amount numeric (8, 2) check (reimb_amount > 0),
	reimb_submitted DATE not null,
	reimb_resolved DATE,
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,
	reimb_author INTEGER references ers_users(ers_users_id) not null,
	reimb_resolver INTEGER references ers_users(ers_users_id),
	reimb_status_id INTEGER references ers_reimbursement_status(reimb_status_id),
	reimb_type_id INTEGER references ers_reimbursement_type(reimb_type_id)
);

insert into ers_user_roles (ers_user_role_id, user_role) values (1, 'Employee')
insert into ers_user_roles (ers_user_role_id, user_role) values (2, 'Manager')

insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (1, 'esherar0', 'd1ZxS6SlrK', 'Erhart', 'Sherar', 'esherar0@360.cn', 2);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (2, 'doakton1', 'LV21bps', 'Debby', 'Oakton', 'doakton1@europa.eu', 2);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (3, 'mgianettini2', 'YmzRcw', 'Mallory', 'Gianettini', 'mgianettini2@yolasite.com', 2);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (4, 'cjemison3', 'aiq2by0', 'Cassaundra', 'Jemison', 'cjemison3@tamu.edu', 2);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (5, 'fdametti4', 'dHy2SZVE', 'Faulkner', 'Dametti', 'fdametti4@amazonaws.com', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (6, 'wpaolacci5', 'gdUOj7coH', 'Ware', 'Paolacci', 'wpaolacci5@ebay.co.uk', 2);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (7, 'thardwidge6', 'gT6pAeH8oXia', 'Terri', 'Hardwidge', 'thardwidge6@toplist.cz', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (8, 'amcgoldrick7', 'zBtSBXDr3z', 'Augie', 'McGoldrick', 'amcgoldrick7@ycombinator.com', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (9, 'flaflin8', 'Tmlf8VFc7', 'Fifine', 'Laflin', 'flaflin8@tumblr.com', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (10, 'brussel9', 'oWtul7N', 'Baudoin', 'Russel', 'brussel9@huffingtonpost.com', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (11, 'employee', 'pass', 'alex', 'employee', 'employee@gmail.com', 1);
insert into ers_users (ers_users_id, ers_username, ers_password, ers_first_name, ers_last_name, user_email, user_role_id) values (12, 'manager', 'pass', 'alex', 'manager', 'manager@manager.com', 2);

select * from ers_schema.ers_users;

--Select all users
SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id 

--Select all employees
SELECT ers_users_id, ers_users.ers_username, ers_users.ers_password, ers_users.ers_first_name, ers_users.ers_last_name, ers_users.user_email, ers_user_roles.user_role FROM ers_schema.ers_users INNER JOIN ers_user_roles ON ers_users.user_role_id = ers_user_roles.ers_user_role_id where ers_users.user_role_id = 1





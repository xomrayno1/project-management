insert into employee(email,first_name,last_Name,address,create_date,update_date) values ('xomrayno5@gmail.com','Nguyen','Chi Tam','DH - Phu Yen','2020-11-29','2020-11-29')
insert into employee(email,first_name,last_Name,address,create_date,update_date) values ('xomrayno1@gmail.com','Tran','Van A','DH - Phu Yen','2020-11-29','2020-11-29')
insert into employee(email,first_name,last_Name,address,create_date,update_date) values ('xomrayno2@gmail.com','Tran','Van B','DH - Phu Yen','2020-11-29','2020-11-29')
insert into employee(email,first_name,last_Name,address,create_date,update_date) values ('xomrayno3@gmail.com','Nguyen','Van C','DH - Phu Yen','2020-11-29','2020-11-29')

insert into project(NAME,PLANNED_START_DATE,PLANNED_END_DATE,CREATE_DATE,UPDATE_DATE) values ('Create e-commerce website online','2020-12-05','2021-02-25','2020-12-01','2020-12-01')
insert into project(NAME,PLANNED_START_DATE,PLANNED_END_DATE,CREATE_DATE,UPDATE_DATE) values ('Create maintenance system management','2020-12-05','2021-02-25','2020-12-01','2020-12-01')
insert into project(NAME,PLANNED_START_DATE,PLANNED_END_DATE,CREATE_DATE,UPDATE_DATE) values ('Create management school','2020-12-05','2021-02-25','2020-12-01','2020-12-01')
insert into project(NAME,PLANNED_START_DATE,PLANNED_END_DATE,CREATE_DATE,UPDATE_DATE) values ('Create management student','2020-12-05','2021-02-25','2020-12-01','2020-12-01')


insert into client_partner(NAME,address,CREATE_DATE,UPDATE_DATE) values ('CTY 1','AB1- B1','2020-12-01','2020-12-01')
insert into client_partner(NAME,address,CREATE_DATE,UPDATE_DATE) values ('CTY 2','AB2- B2','2020-12-01','2020-12-01')
insert into client_partner(NAME,address,CREATE_DATE,UPDATE_DATE) values ('CTY 3','AB3- B1','2020-12-01','2020-12-01')
insert into client_partner(NAME,address,CREATE_DATE,UPDATE_DATE) values ('TH 1','BD2- B2','2020-12-01','2020-12-01')


insert into user_account(username,password) values ('xomrayno1','$2a$10$v1p/RkNseUsGqLJ.NStVde2g1fTtcdpxKVSCOHXLxR8VTCycJfsoC')
insert into user_account(username,password) values ('xomrayno2','$2a$10$v1p/RkNseUsGqLJ.NStVde2g1fTtcdpxKVSCOHXLxR8VTCycJfsoC')
insert into user_account(username,password) values ('admin','$2a$10$AzGT/5JHtV4e.qC76BZDG.UoQj00NpfCCUEiUcjhTwoerJ/e8Nzk.')

insert into role(NAME) values('ROLE_USER')
insert into role(NAME) values('ROLE_ADMIN')
insert into role(NAME) values('ROLE_SUPERADMIN')

insert into ACCOUNT_ROLE (ACCOUNT_ID,ROLE_ID) values(1,1)
insert into ACCOUNT_ROLE (ACCOUNT_ID,ROLE_ID) values(2,2)
insert into ACCOUNT_ROLE (ACCOUNT_ID,ROLE_ID) values(3,3)



insert into project_manager(account_id,project_id) values (2,1);
insert into project_manager(account_id,project_id) values (2,2);
insert into project_manager(account_id,project_id) values (2,3);
insert into project_manager(account_id,project_id) values (2,4);
 
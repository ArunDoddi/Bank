create database bank;
use bank;
create table account(username varchar(10), password varchar(10));
alter table account add Constraint primary key(username);
desc account;
select * from account;

create database sdu;
use sdu;
create table dal (dal_id int NOT NULL AUTO_INCREMENT , dal_name varchar(30), flag int not null DEFAULT 1, PRIMARY KEY (dal_id));
alter table dal AUTO_INCREMENT=1;

insert into dal VALUES(1, 'rajma',1);
insert into dal values(2, 'sabut maa',1);
insert into dal values(3, 'rongi',1);
insert into dal values(4, 'kale chane',1);
insert into dal values(5, 'kabuli chana',1);
insert into dal values(6, 'sabut moong',1);
insert into dal values(7, 'masoor',1);

create table godown (
	godown_id INT primary key AUTO_INCREMENT,
    godown_name varchar(30) not null,
    capacity numeric(5) default null,
    flag int not null default 1,
    created_on datetime not null default now(),
    updated_on datetime not null default now() );
    
insert into godown values (1,'g1',1000,1,now(), now());
insert into godown values (2, 'g2', 3000);
insert into godown VALUes (1, 'g1', 500);
insert into godown VALUES (2, 'g2', 700);
insert into godown values (3, ' sdu cold store', 10000);
insert into godown values (4, 'gupta cold store', 10000);
insert into godown values (5, 'honda cold store', 10000);
alter table godown add flag numeric(1) not null DEFAULT 1;
alter table godown alter capacity SET DEFAULT null;

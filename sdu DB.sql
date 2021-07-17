
create database sdu;
use sdu;
create table dal (
	dal_id int NOT NULL AUTO_INCREMENT,
    dal_name varchar(30),
    status char not null DEFAULT 'a',
    PRIMARY KEY (dal_id));
    
alter table dal AUTO_INCREMENT=1;

insert into dal VALUES(1, 'rajma','a');
insert into dal values(2, 'sabut maa','a');
insert into dal values(3, 'rongi','a');
insert into dal values(4, 'kale chane','a');
insert into dal values(5, 'kabuli chana','a');
insert into dal values(6, 'sabut moong','a');
insert into dal values(7, 'masoor','a');

create table godown (
	godown_id INT primary key AUTO_INCREMENT,
    godown_name varchar(30) not null,
    capacity numeric(7) default null,
    status char not null default 'a');
    
insert into godown values (1,'g1',1000,now(),'a');
insert into godown (godown_id, godown_name, capacity) values (2, 'g2', 3000);
insert into godown (godown_id, godown_name, capacity) VALUes (3, 'g3', 500);
insert into godown (godown_id, godown_name, capacity) VALUES (4, 'g4', 00);
insert into godown (godown_id, godown_name, capacity) values (5, ' sdu cold store', 10000);
insert into godown (godown_id, godown_name, capacity) values (6, 'gupta cold store', 10000);
insert into godown (godown_id, godown_name, capacity) values (7, 'honda cold store', 10000);

-- alter table godown add flag numeric(1) not null DEFAULT 1;
-- alter table godown alter capacity SET DEFAULT null;

create table stock_in (
	stock_in_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dal_id INT NOT NULL,
    godown_id INT NOT NULL,
    quantity INT NOT NULL,
    dated DATETIME NOT NULL DEFAULT now(),
    remarks VARCHAR(100),
    status CHAR NOT NULL DEFAULT 'a',
    FOREIGN KEY (dal_id) REFERENCES dal(dal_id),
    FOREIGN KEY (godown_id) REFERENCES godown(godown_id)
    );

create TABLE stock_transfer (
	stock_transfer_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    stock_in_id INT NOT NULL,
    godown_id INT NOT NULL,
    quantity INT NOT NULL,
    dated DATETIME NOT NULL DEFAULT now(),
    remarks varchar(50),
    status CHAR NOT NULL DEFAULT 'a',
    FOREIGN KEY (stock_in_id) REFERENCES stock_in (stock_in_id),
    FOREIGN KEY (godown_id) REFERENCES godown(godown_id)
);

create TABLE stock_out (
	stock_out_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    stock_in_id INT NOT NULL,
    quantity INT NOT NULL,
    dated DATETIME NOT NULL DEFAULT now(),
    status CHAR NOT NULL DEFAULT 'a',
    FOREIGN KEY (stock_in_id) REFERENCES stock_in (stock_in_id)
);

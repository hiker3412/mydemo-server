drop table user ;
create table user
(
    id int auto_increment primary key ,
    username varchar(100) not null unique ,
    password varchar(100) not null
);


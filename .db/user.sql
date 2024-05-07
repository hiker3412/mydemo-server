drop table user_authority;
drop table user;

create table user
(
    id       int auto_increment primary key,
    username varchar(50) not null unique ,
    password varchar(500) not null,
    enabled boolean not null default true,
    create_time datetime,
    update_time datetime
);


create table user_authority
(
    id  int auto_increment primary key,
    userid int not null,
    authority varchar(50) not null,
    create_time datetime,
    update_time datetime,
    constraint fk_authority_user foreign key (userid) references user (id),
    constraint idx unique (userid,authority)
);

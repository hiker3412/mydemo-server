drop table user;

create table user
(
    id       int auto_increment primary key,
    username varchar(50) not null unique ,
    password varchar(500) not null,
    enabled boolean not null default true
);

create table authority
(
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_user foreign key (username) references user (username)
);
create unique index ix_auth_username on authority (username, authority);

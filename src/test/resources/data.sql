create table board
(
    no            bigint auto_increment
        primary key,
    user_id       varchar(16)                         not null,
    title         varchar(100)                        null,
    content       longtext                            null,
    hit           bigint                              null,
    register_time timestamp default CURRENT_TIMESTAMP null
);

create index user_id
    on board (user_id);

create table members
(
    no            bigint auto_increment
        primary key,
    user_id       varchar(16)                         not null,
    user_name     varchar(20)                         not null,
    user_password varchar(255)                        not null,
    email_id      varchar(20)                         null,
    email_domain  varchar(45)                         null,
    join_date     timestamp default CURRENT_TIMESTAMP null,
    constraint user_id
        unique (user_id)
);
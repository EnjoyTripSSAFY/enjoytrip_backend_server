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


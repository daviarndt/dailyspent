create table user(
    user_id bigint not null auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(100) not null unique,
    is_active bool not null,

    primary key (user_id)
);
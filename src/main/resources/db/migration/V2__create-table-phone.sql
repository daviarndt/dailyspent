create table phone (
    phone_id bigint not null auto_increment,
    user_id bigint,
    country_code varchar(4) not null,
    phone_number varchar(20) not null,

    primary key (phone_id),
    foreign key (user_id) references user(user_id)
);
create table expense (
    expense_id bigint not null auto_increment,
    user_id bigint,
    amount decimal(10,2) not null,
    category varchar(50) not null,
    created_date datetime not null,

    primary key (expense_id),
    foreign key (user_id) references user(user_id),
    unique key uk_category_user (category, user_id)
);
create table train_prices
(
    train_price_id bigserial primary key,
    train_info_id  int references trains_info not null,
    cost           real,
    comfort_type   text,
    seat_type_id   int references seat_types  not null
);

create table train_prices
(
    train_price_id bigserial primary key,
    train_info_id  int references trains_info,
    cost           real,
    comfort_type   text
);

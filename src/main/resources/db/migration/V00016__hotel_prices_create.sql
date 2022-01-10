create table hotel_prices
(
    hotel_price_id bigserial primary key,
    hotel_info_id  int references hotels_info,
    cost           real,
    comfort_type   text
);

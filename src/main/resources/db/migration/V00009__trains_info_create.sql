create table trains_info
(
    train_info_id     bigserial primary key,
    departure_city_id bigint not null references geo_names,
    arrival_city_id   bigint not null references geo_names,
    train_number      text,
    travel_time       bigint,
    last_update       date   not null default current_date,
    unique (departure_city_id, arrival_city_id, train_number)
);
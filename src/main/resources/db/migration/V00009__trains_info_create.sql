create table trains_info
(
    train_info_id bigserial,
    departure_city_id bigint not null references geo_names,
    arrival_city_id bigint not null references geo_names,
    travel_time bigint,
    seat_type_id int not null default 0 references seat_types,
    cost bigint
);
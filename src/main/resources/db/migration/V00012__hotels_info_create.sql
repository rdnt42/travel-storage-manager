create table hotels_info
(
    hotel_info_id  bigserial primary key,
    city_id        bigint not null references geo_names,
    stars          int    not null default 0,
    cost           bigint,
    is_actual_data bool            default false not null
);
create table hotels_info
(
    hotel_info_id int primary key,
    hotel_name    text,
    city_id       bigint not null references geo_names,
    stars         int    not null default 0,
    last_update   date   not null default current_date
);
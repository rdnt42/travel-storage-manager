create table if not exists tutu_stations
(
    station_id   bigint primary key,
    station_name text not null,
    geo_name_id bigint not null references geo_names
);
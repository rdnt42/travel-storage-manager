create table tutu_routes
(
    tutu_route_id        bigserial primary key,
    departure_station_id int not null references tutu_stations,
    arrival_station_id   int not null references tutu_stations,
    unique (departure_station_id, arrival_station_id)
);
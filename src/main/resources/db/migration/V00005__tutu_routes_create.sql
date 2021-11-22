create table if not exists tutu_routes
(
    departure_station_id int not null references tutu_stations,
    arrival_station_id   int not null references tutu_stations,
    primary key (departure_station_id, arrival_station_id)
);
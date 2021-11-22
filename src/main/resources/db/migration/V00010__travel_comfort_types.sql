create table travel_comfort_types
(
    travel_comfort_type_id int
        constraint travel_comfort_type_id_pk
            primary key,
    travel_comfort_type_name text not null
);


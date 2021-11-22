create table seat_types
(
    seat_type_id int
        constraint seat_types_pk
            primary key,
    seat_type_name text not null
);


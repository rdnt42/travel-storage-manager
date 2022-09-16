alter table travel_dev.public.hotel_prices
    add column comfort_type_id int
        references comfort_types
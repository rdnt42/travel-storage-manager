alter table hotel_prices
    alter column comfort_type_id
        set not null;

alter table hotel_prices
    drop comfort_type;


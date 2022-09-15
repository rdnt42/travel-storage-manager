alter table train_prices
    add column comfort_type_id int
        references comfort_types
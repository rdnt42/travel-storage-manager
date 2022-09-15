alter table train_prices
    alter column comfort_type_id
        set not null;

alter table train_prices
    drop comfort_type;


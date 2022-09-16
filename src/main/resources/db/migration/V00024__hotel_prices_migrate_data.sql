update hotel_prices t
set comfort_type_id = (
    case
        when comfort_type = 'COMFORT_TYPE_CHEAP' then 1
        when comfort_type = 'COMFORT_TYPE_COMFORT' then 2
        when comfort_type = 'COMFORT_TYPE_LUXURY' then 3
        end
    );
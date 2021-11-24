create table geo_names
(
    geo_name_id  bigint primary key,
    geo_name     text not null,
    geo_name_ru  text not null,
    latitude     float,
    longitude    float,
    country_code text not null,
    timezone     text
);
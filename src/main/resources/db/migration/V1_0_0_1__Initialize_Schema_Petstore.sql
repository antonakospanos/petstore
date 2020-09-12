create table if not exists pets
(
    id bigserial not null constraint pets_pkey primary key,
    breed varchar,
    description varchar,
    external_id uuid,
    name varchar,
    price bigint,
    publication_date timestamp,
    species varchar
);
create table if not exists users
(
    id bigserial not null constraint users_pkey primary key,
    address varchar,
    cellphone varchar,
    city varchar,
    email varchar,
    external_id uuid,
    name varchar,
    password varchar,
    username varchar
);
create table if not exists sales
(
    id bigserial not null constraint sales_pkey primary key,
    date timestamp,
    delivery varchar,
    external_id uuid,
    payment varchar,
    remarks varchar,
    user_id bigint constraint fk5bgaw8g0rrbqdvafq36g58smk references users,
    pet_id bigint constraint fkho9yl8ecqw77jlb1f7g94tbk8 references pets
);

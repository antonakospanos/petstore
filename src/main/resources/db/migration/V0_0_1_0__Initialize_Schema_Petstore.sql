create table pet (
	id bigserial not null
		constraint pet_pkey
			primary key,

	breed varchar(255),
	description varchar(255),
	external_id uuid,
	name varchar(255),
	price bigint,
	publication_date timestamp,
	species varchar(255)
);

create table "user" (
	id bigserial not null
		constraint user_pkey
			primary key,

	address varchar(255),
	cellphone varchar(255),
	city varchar(255),
	email varchar(255),
	external_id uuid,
	name varchar(255),
	password varchar(255),
	username varchar(255)
);

create table sale(
	id bigserial not null
		constraint sale_pkey
			primary key,

	date timestamp,
	delivery varchar(255),
	external_id uuid,
	payment varchar(255),
	remarks varchar(255),

	user_id bigint
		constraint fkck1t4noryw58a6jcju0pmj38
			references "user",
	pet_id bigint
		constraint fkk06ypffcsw99ohx5ul5hk5o7t
			references pet
);
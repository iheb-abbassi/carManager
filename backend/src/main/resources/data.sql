/**
 * CREATE Script for init of DB
 * Note: Plain-text passwords used here for development seed data only.
 * In production, passwords must be hashed (e.g. BCrypt).
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'POINT(9.5 55.954)', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'POINT(9.5 55.954)', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

insert into car (id, date_created, deleted, license_plate, seat_count, convertible, engine_type, rating)
values (1, now(), false, 'DE007',3, true, 'ELECTRIC',0);
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, engine_type, rating)
values (2, now(), false, 'DE777',4, true, 'GAS',0);
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, engine_type, rating)
values (3, now(), false, 'DE111',4, true, 'DIESEL',0);

-- Reset identity counters to avoid primary key conflicts with new inserts
ALTER TABLE driver ALTER COLUMN id RESTART WITH 9;
ALTER TABLE car ALTER COLUMN id RESTART WITH 4;

DROP TABLE IF EXISTS package_tag;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS complaint;
DROP TABLE IF EXISTS customer_address;
DROP TABLE IF EXISTS vehicle_address;
DROP TABLE IF EXISTS branch;
DROP TABLE IF EXISTS distributionCenter;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS courier CASCADE;
DROP TABLE IF EXISTS logisticUnit CASCADE;
DROP TABLE IF EXISTS address CASCADE;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS tag;
DROP TABLE IF EXISTS storage CASCADE;
DROP TABLE IF EXISTS registeredCustomer CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS vehicle CASCADE;
DROP TABLE IF EXISTS package CASCADE;


CREATE TABLE users
(
    userID    SERIAL NOT NULL,
    name      varchar(64),
    birthDate date,
    type      varchar(2),
    PRIMARY KEY (userID)
);

CREATE TABLE customer
(
    userID int NOT NULL,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES users (userID)
);

CREATE TABLE staff
(
    userID      int          NOT NULL,
    email       varchar(320) NOT NULL,
    password    varchar(64)  NOT NULL,
    phoneNumber varchar(15)  NOT NULL,
    salary      int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES users (userID),
    UNIQUE (email),
    UNIQUE (phoneNumber)
);

CREATE TABLE admin
(
    userID int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES users (userID)
);


create table registeredCustomer
(
    userID      int,
    email       varchar(320),
    password    varchar(64),
    phoneNumber varchar(15),
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES customer (userID),
    UNIQUE (email),
    UNIQUE (phoneNumber)
);

create table address
(
    addressID   SERIAL,
    country     varchar(255),
    city        varchar(255),
    district    varchar(255),
    zipcode     varchar(20),
    addressInfo varchar(255),
    PRIMARY KEY (addressID)
);

create table logisticUnit
(
    logisticUnitID SERIAL,
    name           varchar(30),
    addressID      int,
    FOREIGN KEY (addressID) REFERENCES address (addressID),
    PRIMARY KEY (logisticUnitID)
);

create table courier
(
    userID         int,
    status         varchar(10),
    logisticUnitID int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES staff (userID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit (logisticUnitID)

);

create table employee
(
    userID         int,
    logisticUnitID int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES staff (userID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit (logisticUnitID)
);

create table vehicle
(
    licensePlate  varchar(10),
    status        int,
    maxWeight     int,
    currentWeight int,
    courierID     int,
    addressID     int,

    PRIMARY KEY (licensePlate),
    FOREIGN KEY (courierID) REFERENCES courier (userID),
    FOREIGN KEY (addressID) REFERENCES address (addressID)
);

create table storage
(
    storageID      SERIAL,
    maxVolume      int NOT NULL,
    currentVolume  int,
    logisticUnitID int,
    PRIMARY KEY (storageID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit (logisticUnitID)
);

create table package
(
    packageID         SERIAL,
    weight            int,
    volume            int,
    status            varchar(255),
    senderAddressID   int,
    receiverAddressID int,
    licensePlate      varchar(10),
    senderID          int,
    receiverID        int,
    storageID         int,
    courierID         int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (senderAddressID) REFERENCES address (addressID),
    FOREIGN KEY (receiverAddressID) REFERENCES address (addressID),
    FOREIGN KEY (licensePlate) REFERENCES vehicle (licensePlate),
    FOREIGN KEY (receiverID) REFERENCES customer (userID),
    FOREIGN KEY (senderID) REFERENCES registeredCustomer (userID),
    FOREIGN KEY (storageID) REFERENCES storage (storageID),
    FOREIGN KEY (courierID) REFERENCES courier (userID)
);

create table tag
(
    tag varchar(30),
    PRIMARY KEY (tag)
);

create table package_tag
(
    packageID int,
    tag       varchar(30),
    PRIMARY KEY (packageID, tag),
    FOREIGN KEY (packageID) REFERENCES package (packageID),
    FOREIGN KEY (tag) REFERENCES tag (tag)
);

create table payment
(
    price     int,
    type      varchar(255),
    status    varchar(255),
    packageID int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (packageID) REFERENCES package (packageID) ON DELETE CASCADE
);

create table complaint
(
    complaintID SERIAL,
    userID      int,
    packageID   int,
    type        int,
    message     varchar(255),
    PRIMARY KEY (complaintID),
    FOREIGN KEY (userID) REFERENCES registeredCustomer (userID),
    FOREIGN KEY (packageID) REFERENCES package (packageID)
);

create table step
(
    stepID        SERIAL,
    receiveDate   date NOT NULL,
    packageID     int,
    prevAddressId int,
    nextAddressId int,
    processType   int,
    PRIMARY KEY (stepID),
    FOREIGN KEY (packageID) REFERENCES package (packageID),
    FOREIGN KEY (prevAddressID) REFERENCES address (addressID),
    FOREIGN KEY (nextAddressID) REFERENCES address (addressID)
);

create table distributionCenter
(
    logisticUnitID int,
    PRIMARY KEY (logisticUnitID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit (logisticUnitID)
);

create table branch
(
    logisticUnitID       int,
    distributionCenterID int,
    PRIMARY KEY (logisticUnitID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit (logisticUnitID),
    FOREIGN KEY (distributionCenterID) REFERENCES
        distributionCenter (logisticUnitID)
);

create table customer_address
(
    customerID int,
    addressID  int,
    PRIMARY KEY (addressID),
    FOREIGN KEY (customerID) REFERENCES customer (userID),
    FOREIGN KEY (addressID) REFERENCES address (addressID)
);

-- FILLING SAMPLES
-- **********************************************************
ALTER SEQUENCE address_addressid_seq RESTART WITH 1;

insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'İzmir', 'Ovacik', '35890', '480 2nd Pass');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Samsun', 'Yeşilköy', '55530', '4 Little Fleur Circle');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Manisa', 'Yeniköy', '45600', '77 Hayes Center');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Samsun', 'Yeşilköy', '55530', '9733 Grim Plaza');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Trabzon', 'Yeniköy', '61300', '6 Mcguire Park');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Samsun', 'Yeşilköy', '55530', '57 Bonner Court');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Samsun', 'Yeşilköy', '55530', '979 Lindbergh Alley');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'İzmir', 'Ovacik', '35890', '6 4th Trail');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'İzmir', 'Ovacik', '35890', '2 Saint Paul Street');
insert into address (country, city, district, zipcode, addressinfo)
values ('Turkey', 'Samsun', 'Yeşilköy', '55530', '1018 Blaine Plaza');

ALTER SEQUENCE logisticunit_logisticunitid_seq RESTART WITH 1;

INSERT INTO logisticUnit(name, addressid)
values ('Karsiyaka Distrubiton Center', 1);

INSERT INTO distributionCenter(logisticunitid)
values (currval('logisticunit_logisticunitid_seq'));

INSERT INTO logisticUnit(name, addressid)
values (' Karsiyaka Branch', 2);

INSERT INTO branch(logisticUnitID, distributionCenterID)
values (currval('logisticunit_logisticunitid_seq'), 1);

ALTER SEQUENCE storage_storageid_seq RESTART WITH 1;
INSERT INTO storage(maxVolume, currentVolume, logisticUnitID)
values (5000000, 200000, 1);

INSERT INTO storage(maxVolume, currentVolume, logisticUnitID)
values (20000, 10000, currval('logisticunit_logisticunitid_seq'));

INSERT INTO storage(maxVolume, currentVolume, logisticUnitID)
values (20000, 5000, currval('logisticunit_logisticunitid_seq'));

INSERT INTO storage(maxVolume, currentVolume, logisticUnitID)
values (20000, 1000, currval('logisticunit_logisticunitid_seq'));

INSERT INTO users(name, birthdate, type)
values ('Can', '2001-05-14', 'E');
INSERT INTO staff
values (currval('users_userid_seq'), 'can@hotmail.com', '123', '05076009363', 44444);
INSERT INTO employee
values (currval('users_userid_seq'), 2);

INSERT INTO users(name, birthdate, type)
values ('Emre', '2001-05-14', 'C');
INSERT INTO staff
values (currval('users_userid_seq'), 'emre@hotmail.com', '123', '0521545451', 50);
INSERT INTO courier
values (currval('users_userid_seq'), null, 2);

INSERT INTO users(name, birthdate, type)
values ('Furkan G', '2001-05-14', 'A');
INSERT INTO staff
values (currval('users_userid_seq'), 'güzelant@hotmail.com', '123', '0537623235', 50000);
INSERT INTO admin
values (currval('users_userid_seq'));

INSERT INTO users(name, birthdate, type)
values ('Bengisu', '2001-05-14', 'RC');
INSERT INTO customer
values (currval('users_userid_seq'));
INSERT INTO registeredCustomer
values (currval('users_userid_seq'), 'bengisu@hotmail.com', '123', '06505519');

INSERT INTO customer_address(customerID, addressID)
values (currval('users_userid_seq'), 3);

INSERT INTO users(name, birthdate, type)
values ('Mahmut', '2001-05-14', 'C');
INSERT INTO customer
values (currval('users_userid_seq'));

INSERT INTO customer_address(customerID, addressID)
values (currval('users_userid_seq'), 4);


ALTER SEQUENCE package_packageid_seq RESTART WITH 1;

INSERT INTO package(weight,
                    volume,
                    status,
                    senderaddressid,
                    receiveraddressid,
                    licenseplate,
                    senderid,
                    receiverid,
                    storageid,
                    courierid)
values (500, 1500, null, 3, 4, null, 4, 5, null, null);

INSERT INTO package(weight,
                    volume,
                    status,
                    senderaddressid,
                    receiveraddressid,
                    licenseplate,
                    senderid,
                    receiverid,
                    storageid,
                    courierid)
values (50, 40, null, 3, 4, null, 4, 5, null, null);

INSERT INTO tag(tag)
values ('Fragile');

INSERT INTO tag(tag)
values ('Flammable');

INSERT INTO tag(tag)
values ('Urgent');

INSERT INTO tag(tag)
values ('Food');

INSERT INTO package_tag(packageID, tag)
values (currval('package_packageid_seq'),'Fragile');

INSERT INTO payment(price, type, status, packageID)
values (100,0,0,currval('package_packageid_seq'));

INSERT INTO step(receivedate, packageid, prevAddressid, nextAddressid, processtype)
values ('2022-12-10', currval('package_packageid_seq'), 3, 2, 0);

INSERT INTO step(receiveDate, packageID, prevAddressId, nextAddressId, processType)
values ('2022-12-11', currval('package_packageid_seq'), 2, 1, 1);

UPDATE package
SET storageid = 1
WHERE packageid = currval('package_packageid_seq');

INSERT INTO vehicle(licensePlate, status, maxWeight, currentWeight, courierID, addressID)
values ('06 DEE 463', 0, 500, 100, 2, 2);

UPDATE package
SET licensePlate = '06 DEE 463',
    status       = 3
WHERE packageid = currval('package_packageid_seq');

INSERT INTO complaint(userID, packageID, type, message)
values (4, currval('package_packageid_seq'), 2, 'Paketim nerde, noluyor yahu !?');


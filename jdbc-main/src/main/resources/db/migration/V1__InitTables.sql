CREATE TABLE users(
    userID    SERIAL NOT NULL,
    name      varchar(64),
    birthDate date,
    PRIMARY KEY (userID)
);

CREATE TABLE customer(
    userID int NOT NULL,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES users (userID)
);

CREATE TABLE staff(
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

CREATE TABLE admin(
    userID int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES users (userID)
);


create table registeredCustomer(
    userID      int,
    email       varchar(320),
    password    varchar(64),
    phoneNumber varchar(15),
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES customer (userID),
    UNIQUE (email),
    UNIQUE (phoneNumber)
);

create table courier(
    userID       		    int,
    status           		varchar(10),
    logisticUnitID		    int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES staff(userID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID)

);

create table employee(
    userID      			int,
    logisticUnitID			int,
    PRIMARY KEY (userID),
    FOREIGN KEY (userID) REFERENCES staff(userID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID)
);

create table vehicle(
                        licensePlate 		varchar(10),
                        status 				varchar(10),
                        maxWeight 			int,
                        currentWeight		int,
                        PRIMARY KEY (licensePlate)
);

create table package(
    packageID      	        SERIAL,
    weight 			        int,
    volume 			        int,
    type 				    varchar(255),
    status 				    varchar(255),
    senderAddressID		    int,
    receiverAddressID		int,
    licensePlate			int,
    senderID			    int,
    receiverID			    int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (senderAddressID)    REFERENCES address(addressID),
    FOREIGN KEY (receiverAddressID)  REFERENCES address(addressID),
    FOREIGN KEY (licensePlate) 	     REFERENCES vehicle(licensePlate),
    FOREIGN KEY (receiverID) 	     REFERENCES customer(userID),
    FOREIGN KEY (senderID)	    	 REFERENCES   registeredCustomer(userID)
);

create table address(
    addressID				SERIAL,
    country				    varchar(255),
    city					varchar(255),
    district				varchar(255),
    zipcode				    int,
    addressInfo				varchar(255),
    PRIMARY KEY (addressID)
);

create table tag(
    tag 				varchar(30),
    PRIMARY KEY (tag)
);

create table package_tag(
    packageID				        int,
    tag 					        varchar(30),
    PRIMARY KEY (packageID, tag),
    FOREIGN KEY (packageID) REFERENCES package(packageID),
    FOREIGN KEY (tag) REFERENCES tag(tag)
);

create table payment(
    price 				int,
    type 				varchar(255),
    status 				varchar(255),
    packageID 			int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (packageID) REFERENCES package(packageID) ON DELETE CASCADE
);

create table customer_complaint(
    complaintID				SERIAL,
    userID					int,
    packageID				int,
    type					varchar(255),
    message				    varchar(255),
    PRIMARY KEY (complaintID),
    FOREIGN KEY (userID) REFERENCES registeredCustomer(userID),
    FOREIGN KEY (packageID) REFERENCES package(packageID)
);

create table step(
     stepID    		        SERIAL,
     receiveDate		    date NOT NULL,
     packageID		        int,
     prevAddressId 	        int,
     nextAddressId	        int,
     processType		    varchar(32),
     PRIMARY KEY (stepID),
     FOREIGN KEY (packageID) REFERENCES package(packageID),
     FOREIGN KEY (prevAddressID) REFERENCES address(addressID),
     FOREIGN KEY (nextAddressID) REFERENCES address(addressID)
);

create table storage(
    storageID 					SERIAL,
    maxVolume					int NOT NULL,
    currentVolume				int,
    PRIMARY KEY (storageID)
);

create table logisticUnit(
    logisticUnitID		int,
    name			    varchar(30),
    PRIMARY KEY (logisticUnitID)
);

create table branch(
    logisticUnitID				int,
    distributionCenterID 		int,
    PRIMARY KEY (logisticUnitID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID),
    FOREIGN KEY (distributionCenterID) REFERENCES
    distributionCenter(logisticUnitID)
);

create table distributionCenter(
     logisticUnitID		int,
     PRIMARY KEY (logisticUnitID),
     FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID)
);

create table vehicle_address(
    licensePlate			varchar(10),
    addressID			    int,
    PRIMARY KEY (licensePlate),
    FOREIGN KEY (licensePlate) REFERENCES vehicle(licensePlate),
    FOREIGN KEY (addressID) REFERENCES address(addressID)
);

create table deliver(
    packageID			int,
    userID				int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (packageID) REFERENCES package(packageID),
    FOREIGN KEY (userID) REFERENCES courier(userID)
);

create table customer_address(
     customerID				int,
     addressID				int,
     PRIMARY KEY (addressID),
     FOREIGN KEY (customerID) REFERENCES customer(userID),
     FOREIGN KEY (addressID) REFERENCES address(addressID)
);

create table package_storage(
    packageID				int,
    storageID				int,
    PRIMARY KEY (packageID),
    FOREIGN KEY (packageID) REFERENCES package(packageID),
    FOREIGN KEY (storageID) REFERENCES storage(storageID)
);

create table logisticUnit_address(
    logisticUnitID			int,
    addressID			int,
    PRIMARY KEY (logisticUnitID),
    FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID),
    FOREIGN KEY (addressID) REFERENCES address(addressID)
);

create table logisticUnit_storage(
     storageID			    int,
     logisticUnitID			int,
     PRIMARY KEY (storageID),
     FOREIGN KEY (storageID) REFERENCES storage(storageID),
     FOREIGN KEY (logisticUnitID) REFERENCES logisticUnit(logisticUnitID)
);

create table courier_vehicle(
    courierID				    int,
    licensePlate				varchar(10) UNIQUE,
    PRIMARY KEY (courierID),
    FOREIGN KEY (courierID) REFERENCES courier(userID),
    FOREIGN KEY (licensePlate) REFERENCES vehicle(licensePlate)
);












DROP TABLE customer;
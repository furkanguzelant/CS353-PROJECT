CREATE TABLE users
(
    userID    SERIAL NOT NULL,
    name      varchar(64),
    birthDate date,
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


DROP TABLE customer;
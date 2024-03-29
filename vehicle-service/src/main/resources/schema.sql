
DROP TABLE IF EXISTS VEHICLE ;
DROP TABLE IF EXISTS PING_SIGNAL ;



create table VEHICLE (
    ID int NOT NULL AUTO_INCREMENT,
    VEHICLE_ID varchar(17) NOT NULL ,
    REGISTERATION_NUMBER varchar(6) NOT NULL UNIQUE,
    CUSTOMER_ID int,
    STATUS VARCHAR(10),
    LAST_PING_DATE TIMESTAMP,
    PRIMARY KEY (ID),
);

create table PING_SIGNAL (
    ID int NOT NULL AUTO_INCREMENT,
    REGISTERATION_NUMBER varchar(10) NOT NULL,
    PING_DATE TIMESTAMP,
    PRIMARY KEY (ID),
);

DROP TABLE IF EXISTS  CUSTOMER ;

CREATE TABLE CUSTOMER (
  ID BIGINT(16) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) NOT NULL UNIQUE,
  ADDRESS_LINE varchar(150) NOT NULL,
  COUNTRY varchar(50) NOT NULL,
  STATE varchar(50) NOT NULL,
  CITY varchar(50) NOT NULL,
  POSTAL_CODE varchar(6) NOT NULL,
  PRIMARY KEY (ID)
);

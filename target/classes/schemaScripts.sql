DROP TABLE employees_proj_0;
CREATE TABLE employees_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
creditcard numeric(8,0),
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

DROP TABLE customers_proj_0;
CREATE TABLE customers_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

DROP TABLE vehicles_proj_0;
CREATE TABLE vehicles_proj_0(
make VARCHAR NOT NULL,
model VARCHAR NOT NULL,
year NUMERIC(8,0),
mileage NUMERIC(8,1),
condition VARCHAR,
bid NUMERIC(8,2) NOT NULL,
highestOffer NUMERIC(8,2),
vin VARCHAR,
highestBidderOrOwner VARCHAR,
monthlyPayment NUMERIC(8,2),
principle NUMERIC(8,2),
paymentDuration INTEGER,
pended BOOLEAN,
PRIMARY KEY(vin)
);

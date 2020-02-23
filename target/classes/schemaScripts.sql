DROP TABLE employees_proj_0;
CREATE TABLE employees_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE NOT NULL,
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

DROP TABLE customers_proj_0;
CREATE TABLE customers_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE NOT NULL,
creditcard numeric(16,0),
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

DROP TABLE vehicles_proj_0;
CREATE TABLE vehicles_proj_0(
make VARCHAR NOT NULL,
model VARCHAR NOT NULL,
year NUMERIC(4,0),
mileage NUMERIC(10,1),
condition VARCHAR,
bid NUMERIC(10,2) NOT NULL,
highestOffer NUMERIC(10,2),
vin VARCHAR,
highestBidderOrOwner VARCHAR,
monthlyPayment NUMERIC(10,2),
principle NUMERIC(10,2),
paymentDuration INTEGER,
pended BOOLEAN,
PRIMARY KEY(vin)
);

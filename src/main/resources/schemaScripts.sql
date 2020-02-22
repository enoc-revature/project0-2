CREATE TABLE employees(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
creditcard numeric(8,0),
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

CREATE TABLE employees(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

CREATE TABLE vehicles(
make VARCHAR,
model VARCHAR,
year NUMERIC(8,0),
mileage NUMERIC(8,1),
condition VARCHAR,
bid NUMERIC(8,2),
highestOffer NUMERIC(8,2),
vin VARCHAR,
highestBidderOrOwner VARCHAR,
monthlyPayment NUMERIC(8,2),
principle NUMERIC(8,2),
paymentDuration INTEGER,
pended BOOLEAN,
PRIMARY KEY(vin)
);

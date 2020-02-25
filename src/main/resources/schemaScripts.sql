-- SCHEMAS

DROP TABLE employees_proj_0;
CREATE TABLE employees_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR NOT NULL,
userid VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(userid)
);

DROP TABLE customers_proj_0;
CREATE TABLE customers_proj_0(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR NOT NULL,
creditcard char(16) NOT NULL,
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



-- Populate Tables
INSERT INTO employees_proj_0
(firstName,lastName,address,email,userid,password)
VALUES
	('John','Smith','123 Ave.','john.smith@email.com','J1324','pa$$word');
					
INSERT INTO customers_proj_0
(firstName,lastName,address,email,creditcard,userid,password)
VALUES
	('Tracey','Adams',null,'tracey.adams@email.com','0123456789012345','TA5432','NewCarSmell');
						
INSERT INTO vehicles_proj_0
(make,model,year,mileage,vin,bid,highestOffer,highestBidderOrOwner,monthlyPayment,principle,paymentDuration,pended)
VALUES
	('Toyota','Yaris',2007,107003.1,'JVP3462',1999.99,null,null,null,null,null,null),
	('Toyota','Camry',2019,12059.0,'MP20394',5999.99,null,null,null,null,null,null),
	('Saturn','Aura',1999,185040.5,'3239350',1449.99,null,null,null,null,null,null),
	('Dodge','Charger',2012,124500.3,'GTO2039',3999.00,null,null,null,null,null,null),
	('Honda','Civic',2017,52148.8,'FP02934',1999.99,null,null,null,null,null,null);
					
				
				
select * from employees_proj_0;
select * from customers_proj_0;
select * from vehicles_proj_0;		
				


--TRUNCATE TABLE employees_proj_0;
--TRUNCATE TABLE customers_proj_0;
--TRUNCATE TABLE vehicles_proj_0;

				
				
				
				
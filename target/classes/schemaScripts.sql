CREATE TABLE employees(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
creditcard numeric(8,0),
id VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE employees(
firstName VARCHAR,
lastName VARCHAR,
address VARCHAR,
email VARCHAR UNIQUE,
id VARCHAR,
password VARCHAR NOT NULL,
PRIMARY KEY(id)
);

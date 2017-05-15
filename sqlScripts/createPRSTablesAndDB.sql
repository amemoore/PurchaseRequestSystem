-- create and select the database
DROP DATABASE IF EXISTS prs;
CREATE DATABASE prs;
USE prs;

-- create the vendor table
CREATE TABLE vendors (
  Id		     int			PRIMARY KEY  AUTO_INCREMENT,
  Code	     	 VARCHAR(50)     NOT NULL,
  Name 			 VARCHAR(25)    NOT NULL,
  Address	     VARCHAR(255)   NOT NULL,
  City           VARCHAR(25)    NOT NULL,
  State    		 VARCHAR(2)     NOT NULL,
  Zip		     VARCHAR(15)     NOT NULL,
  Phone			 VARCHAR(15)    NOT NULL,
  Email			 VARCHAR(75)    NOT NULL,
  Preapproved	 BIT(1)		    NOT Null
  );
  
CREATE TABLE products (
  Id		     int			PRIMARY KEY  AUTO_INCREMENT,
  Name 			 VARCHAR(25)    NOT NULL,
  PartNumber	 VARCHAR(75)    NOT NULL,
  Price          DOUBLE		    NOT NULL,
  Unit    		 VARCHAR(25)     NOT NULL,
  VendorId		 int		    NOT NULL,
  PhotoPath      VARCHAR(75)    NOT NULL,
  foreign key (VendorId) References vendors(Id)
  );
CREATE TABLE users (
  Id		     int			 PRIMARY KEY  AUTO_INCREMENT,
  UserName 		 VARCHAR(25)     NOT NULL,
  Password	     VARCHAR(255)    NOT NULL,
  FirstName      VARCHAR(25)     NOT NULL,
  LastName       VARCHAR(25)     NOT NULL,
  Phone		     VARCHAR(12)     NOT NULL,
  Email          VARCHAR(75)     NOT NULL,
  Manager		 BIT(1)	         NOT NULL
);

CREATE TABLE requests (
  Id		     	int			    PRIMARY KEY  AUTO_INCREMENT,
  Description 		VARCHAR(255)    NOT NULL,
  Justification	    VARCHAR(255)    NOT NULL,
  DateNeeded      	date		    NOT NULL,
  UserId       		int             NOT NULL,
  DeliveryMode		VARCHAR(25)     NOT NULL,
  DocAttached       BIT(1)     		NOT NULL,
  Status		 	BIT(1)	        NOT NULL,
  Total         	DOUBLE          NOT NULL,
  SubmittedDate		DATE	        NOT NULL,
  foreign key (UserId) References users(id)
  );
  
  CREATE TABLE lineitems (
  Id			int		PRIMARY KEY  AUTO_INCREMENT,
  RequestId		int		NOT NULL,
  ProductId		INT		NOT NULL,
  Quantity		INT		NOT NULL,
  foreign key (RequestId) References requests(id),
  foreign key(ProductId) References products(id)
  );
  
  INSERT INTO Vendors VALUES
(1,'bull-025637', 'Bull''s Eye Supplies', '501 Kemper Rd', 'Springdale', 'OH', '45328', '513-568-4422', 'help@bullseye.com', true);  
  INSERT INTO Vendors VALUES
(2,'59722-5565', 'Office Mate', '23545 Pining Rd', 'Evendale', 'OH', '49847', '513-779-5551', 'office@officemate.com', true);  
  INSERT INTO Vendors VALUES
(3,'2-39489', 'Value Office Supply', '49 Pleasant Ridge Rd', 'Pleasant Ridge', 'OH', '45664', '513-555-2233', 'valuesupply@gmail.com', false);  
  
GRANT SELECT, INSERT, DELETE, UPDATE
ON prs.*
TO prs_user@localhost
IDENTIFIED BY 'sesame';  
  
  
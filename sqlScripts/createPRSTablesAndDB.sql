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
  Preapproved	 BIT(1)		    NOT Null,
  constraint vcode unique (code)
  );

CREATE TABLE products (
  Id		     int			PRIMARY KEY  AUTO_INCREMENT,
  Name 			 VARCHAR(25)    NOT NULL,
  PartNumber	 VARCHAR(75)    NOT NULL,
  Price          DOUBLE		    NOT NULL,
  Unit    		 VARCHAR(25)     NOT NULL,
  VendorId		 int		    NOT NULL,
  PhotoPath      VARCHAR(75)    NOT NULL,
  foreign key (VendorId) References vendors(Id),
  constraint part_vendor unique (PartNumber, VendorID)
  );
CREATE TABLE users (
  Id		     int			 PRIMARY KEY  AUTO_INCREMENT,
  UserName 		 VARCHAR(25)     NOT NULL,
  Password	     VARCHAR(255)    NOT NULL,
  FirstName      VARCHAR(25)     NOT NULL,
  LastName       VARCHAR(25)     NOT NULL,
  Phone		     VARCHAR(12)     NOT NULL,
  Email          VARCHAR(75)     NOT NULL,
  Manager		 BIT(1)	         NOT NULL,
  constraint uname unique (username)
);

CREATE TABLE requests (
  Id		     	int			    PRIMARY KEY  AUTO_INCREMENT,
  Description 		VARCHAR(255)    NOT NULL,
  Justification	    VARCHAR(255)    NOT NULL,
  DateNeeded      	date		    NOT NULL,
  UserId       		int             NOT NULL,
  DeliveryMode		VARCHAR(25)     NOT NULL,
  DocAttached       BIT(1)     		NOT NULL,
  Status		 	varchar(10)	    NOT NULL,
  Total         	DOUBLE          NOT NULL,
  SubmittedDate		DATE	        NOT NULL,
  foreign key (UserId) References users(Id)
  );
  
  CREATE TABLE lineitems (
  Id			int		PRIMARY KEY  AUTO_INCREMENT,
  RequestId		int		NOT NULL,
  ProductId		INT		NOT NULL,
  Quantity		INT		NOT NULL,
  foreign key (RequestId) References requests(Id),
  foreign key(ProductId) References products(Id),
  constraint req_pdt unique (RequestId, ProductId)
  );
  
  INSERT INTO Vendors VALUES
(1,'bull-025637', 'Bull''s Eye Supplies', '501 Kemper Rd', 'Springdale', 'OH', '45328', '513-568-4422', 'help@bullseye.com', true);  
  INSERT INTO Vendors VALUES
(2,'59722-5565', 'Office Mate Store', '23545 Pining Rd', 'Evendale', 'OH', '49847', '513-779-5551', 'office@officemate.com', true);  
  INSERT INTO Vendors VALUES
(3,'2-39489', 'Value Office Supply', '49 Pleasant Ridge Rd', 'Pleasant Ridge', 'OH', '45664', '513-555-2233', 'valuesupply@gmail.com', false);  

INSERT INTO products VALUES 
(1, 'Multi-Use Copy Paper', "114961", 25.99, '1 box/8 reams', 1, "path 1");
INSERT INTO products VALUES
(2, 'Pic Ballpoint, Black Ink', "442901", 5.99, '60/box', 1, "path 2");
INSERT INTO products VALUES
(3, 'Fabric Back Black Chair', "556243", 179.99, '1 chair', 1, "path 3");
INSERT INTO products VALUES
(4, 'Multipurpose Paper', "196517", 55.99, '1 box/10 reams', 2, "path 4");
INSERT INTO products VALUES
(5, 'Pic Ballpoints, Black Ink', "664011", 5.99, '60/box', 2, "path 5");
INSERT INTO products VALUES
(6, 'WorkPro Mesh Chair', "510830", 289.99, '1 chair', 2, "path 3");
INSERT INTO products VALUES
(7, 'Multipurpose Office Paper', "295874", 15.99, '1 box/8 reams', 3, "path 4");
INSERT INTO products VALUES
(8, 'RoundTip Ballpoints Black', "329865", 3.99, '60/box', 3, "path 5");
INSERT INTO products VALUES
(9, 'Black Office Chair', "111956", 79.99, '1 chair', 3, "path 3");

  
GRANT SELECT, INSERT, DELETE, UPDATE
ON prs.*
TO prs_user@localhost
IDENTIFIED BY 'sesame';  
  
  
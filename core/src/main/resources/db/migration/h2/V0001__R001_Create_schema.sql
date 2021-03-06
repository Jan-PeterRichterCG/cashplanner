-- This is the SQL script for setting up the DDL for the h2 database
-- In a typical project you would only distinguish between main and test for flyway SQLs
-- However, in this sample application we provde support for multiple databases in parallel
-- You can simply choose the DB of your choice by setting spring.profiles.active=XXX in config/application.properties
-- Assuming that the preconfigured user exists with according credentials using the included SQLs

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1000000;

CREATE TABLE AccountingEntry(
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  dateOfBookkeepingEntry DATE,
  ValueDate DATE,
  PostingText VARCHAR(255),
  Currency VARCHAR (3) NOT NULL,
  Amount NUMERIC (19,2) NOT NULL,
  CONSTRAINT PK_AccountEntry PRIMARY KEY(id)
);

-- *** Entity Product as an example ***
--CREATE TABLE Product(
--  dType VARCHAR(31) NOT NULL,
--  id BIGINT NOT NULL AUTO_INCREMENT,
--  modificationCounter INTEGER NOT NULL,
--  description VARCHAR(255),
--  name VARCHAR(255),
--  alcoholic BOOLEAN,
--  pictureId BIGINT,
--  CONSTRAINT PK_Product PRIMARY KEY(id)
--);

--CREATE TABLE Product_AUD(
--  revType TINYINT,
--  description VARCHAR(255),
--  name VARCHAR(255),
--  pictureId BIGINT,
--  alcoholic BOOLEAN,
--  dType VARCHAR(31) NOT NULL,
--  id BIGINT NOT NULL,
--  rev BIGINT NOT NULL
--);


-- *** BinaryObject (BLOBs) ***
CREATE TABLE BinaryObject (
  id BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  data BLOB(2147483647),
  size BIGINT NOT NULL,
  mimeType VARCHAR(255),
  PRIMARY KEY (ID)
);

-- *** RevInfo (Commit log for envers audit trail) ***
CREATE TABLE RevInfo(
  id BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  timestamp BIGINT NOT NULL,
  userLogin VARCHAR(255)
);
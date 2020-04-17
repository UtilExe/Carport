DROP DATABASE `carport`;
CREATE SCHEMA `carport`;
USE `carport`;

CREATE TABLE `carport`.`carport_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NOT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );
-- Indsæt data i tabellen:
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 240);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 750);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 240);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 780);
  
CREATE TABLE `carport`.`roof_data` (
  `roof_degree` INT NULL,
  `roof_material` VARCHAR(45) NULL
  );
-- indsæt hældningsdata:
INSERT INTO roof_data (`roof_degree`) VALUES (15);
INSERT INTO roof_data (`roof_degree`) VALUES (20);
INSERT INTO roof_data (`roof_degree`) VALUES (25);
INSERT INTO roof_data (`roof_degree`) VALUES (30);
INSERT INTO roof_data (`roof_degree`) VALUES (35);
INSERT INTO roof_data (`roof_degree`) VALUES (40);
INSERT INTO roof_data (`roof_degree`) VALUES (45);

-- indsæt materiale:
INSERT INTO roof_data (`roof_material`) VALUES ('Betontagsten');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7');
  
CREATE TABLE `carport`.`shed_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );
-- Indsæt data i tabellen:
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 210);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 720);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 150);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 690);

  
  CREATE TABLE `carport`.`cust_order` (
  `orderID` INT NOT NULL AUTO_INCREMENT,
  `carport_height` INT NOT NULL,
  `carport_length` INT NOT NULL,
  `carport_width` INT NOT NULL,
  `hasShed` TINYINT NULL,
  `shedIsFullWidth` TINYINT NULL,
  `shedLength` INT NULL,
  `roofIsFlat` TINYINT NULL,
  `roof_degree` INT NULL,
  `roof_material` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`orderID`));
  
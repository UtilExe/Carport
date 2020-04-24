DROP DATABASE IF EXISTS `carport`;
CREATE SCHEMA `carport`;
USE `carport`;

CREATE TABLE `carport`.`carport_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NOT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );
-- Indsæt data i tabellen:
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 240);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 270);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 300);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 330);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 360);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 390);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 420);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 450);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 480);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 510);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 540);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 570);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 600);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 630);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 660);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 690);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 720);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('bredde', 750);

INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 240);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 270);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 300);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 330);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 360);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 390);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 420);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 450);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 480);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 510);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 540);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 570);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 600);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 630);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 660);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 690);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 720);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 750);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('længde', 780);

INSERT INTO carport_measures (`description`, `measures`) VALUES ('højde', 300);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('højde', 320);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('højde', 340);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('højde', 360);
INSERT INTO carport_measures (`description`, `measures`) VALUES ('højde', 380);

  
CREATE TABLE `carport`.`roof_data` (
  `roof_pitch` INT NULL,
  `roof_material` VARCHAR(45) NULL
  );
-- indsæt hældningsdata:
INSERT INTO roof_data (`roof_pitch`) VALUES (15);
INSERT INTO roof_data (`roof_pitch`) VALUES (20);
INSERT INTO roof_data (`roof_pitch`) VALUES (25);
INSERT INTO roof_data (`roof_pitch`) VALUES (30);
INSERT INTO roof_data (`roof_pitch`) VALUES (35);
INSERT INTO roof_data (`roof_pitch`) VALUES (40);
INSERT INTO roof_data (`roof_pitch`) VALUES (45);

-- indsæt materiale:
INSERT INTO roof_data (`roof_material`) VALUES ('Betontagsten - Rød');
INSERT INTO roof_data (`roof_material`) VALUES ('Betontagsten - Teglrød');
INSERT INTO roof_data (`roof_material`) VALUES ('Betontagsten - Brun');
INSERT INTO roof_data (`roof_material`) VALUES ('Betontagsten - Sort');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6 - Grå');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6 - Sort');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6 - Mokka (brun)');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6 - Rødbrun');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B6 - Teglrød');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Grå');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Sort');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Mokka (brun)');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Rødbrun');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Teglrød');
INSERT INTO roof_data (`roof_material`) VALUES ('Eternittag B7 - Rødflammet');
  
CREATE TABLE `carport`.`shed_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );
-- Indsæt data i tabellen:
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 210);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 240);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 270);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 300);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 330);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 360);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 390);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 420);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 450);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 480);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 510);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 540);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 570);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 600);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 630);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 660);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 690);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 720);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 150);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 180);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 210);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 240);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 270);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 300);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 330);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 360);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 390);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 420);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 450);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 480);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 510);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 540);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 570);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 600);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 630);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 660);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 690);

CREATE TABLE `carport`.`users` (
  `name` VARCHAR(16) NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `mobilNr` INT(8) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `saldo` INT(6) NOT NULL DEFAULT 500,
  PRIMARY KEY (`email`));
  
INSERT INTO `users` (`name`, `email`, `password`, `mobilNr`, `saldo`) VALUES ('Admin', 'admin@admin.com', 'admin', 10101010, 10000);

  
  CREATE TABLE `carport`.`cust_order` (
  `orderID` INT NOT NULL AUTO_INCREMENT,
  `carport_length` INT NOT NULL,
  `carport_width` INT NOT NULL,
  `carport_height` INT NOT NULL,
  `hasShed` TINYINT NULL,
  `shedWidth` INT NULL,
  `shedLength` INT NULL,
  `roofIsFlat` TINYINT NULL,
  `roof_pitch` INT NULL,
  `roof_material` VARCHAR(45) NULL,
  `price` INT NULL,
  PRIMARY KEY (`orderID`));
  
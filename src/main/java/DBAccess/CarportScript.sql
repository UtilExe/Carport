DROP DATABASE IF EXISTS `carport`;
CREATE SCHEMA `carport`;
USE `carport`;

CREATE TABLE `carport`.`carport_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NOT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );
  
CREATE TABLE `carport`.`roof_data` (
  `roof_pitch` INT NULL,
  `roof_material` VARCHAR(45) NULL
  );
  
CREATE TABLE `carport`.`shed_measures` (
  `description` VARCHAR(45) NOT NULL,
  `measures` INT NULL,
  `units` VARCHAR(45) DEFAULT 'cm'
  );

CREATE TABLE `carport`.`users` (
  `name` VARCHAR(16) NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `mobilNr` INT(8) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `saldo` INT(6) NOT NULL DEFAULT 500,
  PRIMARY KEY (`email`));
  
INSERT INTO `users` (`name`, `email`, `password`, `mobilNr`, `saldo`) VALUES ('Admin', 'admin@admin.com', 'admin', 10101010, 10000);

CREATE TABLE `carport`.`material_list` (
`ID` INT AUTO_INCREMENT,
`category` VARCHAR(45) NOT NULL,
`price_unit` int(8) NOT NUll,
`amount_pr_unit` VARCHAR(45) NULL,
`unit` VARCHAR(45) NOT NULL,
`type` VARCHAR(45) NOT NULL,
`description` VARCHAR(45) NOT NULL,
PRIMARY KEY (`ID`));
  
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

-- Indsæt hældningsdata:
INSERT INTO roof_data (`roof_pitch`) VALUES (15);
INSERT INTO roof_data (`roof_pitch`) VALUES (20);
INSERT INTO roof_data (`roof_pitch`) VALUES (25);
INSERT INTO roof_data (`roof_pitch`) VALUES (30);
INSERT INTO roof_data (`roof_pitch`) VALUES (35);
INSERT INTO roof_data (`roof_pitch`) VALUES (40);
INSERT INTO roof_data (`roof_pitch`) VALUES (45);

-- Indsæt materiale:
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

-- Indsæt materiale i listen
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'brædt', '25x200 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'brædt', '25x125 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'brædt', '19x100 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'lægte', '38x73 mm., ubh.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'reglar', '45x95 mm., ub.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'spærtræ', '45x195 mm., ubh.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '2', 'cm', 'stolpe', '97x97 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('træ og tagplader', '250', 'stk', 'trapezplade', '100x400 cm., blåtonet');


INSERT INTO material_list (`category`, `price_unit`,`amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '109', '100, stk', 'pakke', 'bundskruer', 'bundskruer');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '87', '10, meter', 'rulle', 'hulbånd', '1x20 mm.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '32', 'stk', 'beslag', '190 mm, universal');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '200', '200, stk', 'pakke', 'skruer', '4.5x60mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '220', '200, stk', 'pakke', 'skruer', '4.5x70 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '155', '150, stk', 'pakke', 'skruer', '4x50 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '230', '250, stk', 'pakke', 'beslagskruer', '4x50 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '3', '1', 'stk', 'bræddebolt', '10x120 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '4', '1', 'stk', 'firkantskiver', '40x40x11 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '110', '1', 'sæt', 'stalddørsgreb', '50x75 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '35', '1', 'stk', 't-hængsel', '390 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type`, `description`) VALUES ('beslag og skruer', '3', '1', 'stk', 'vinkelbeslag', '35 mm.');







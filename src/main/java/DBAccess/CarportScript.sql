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

CREATE TABLE `carport`.`material_type` (
`typeID` INT AUTO_INCREMENT,
`type_name` VARCHAR(45) NOT NULL,
PRIMARY KEY (`typeID`));

CREATE TABLE `carport`.`material_list` (
`productID` INT AUTO_INCREMENT,
`category` VARCHAR(45) NOT NULL,
`price_unit` float(8) NOT NUll,
`amount_pr_unit` VARCHAR(45) NULL,
`unit` VARCHAR(45) NOT NULL,
`type_id` INT NOT NULL,
`description` VARCHAR(45) NOT NULL,
PRIMARY KEY (`productID`),
FOREIGN KEY (`type_id`)
REFERENCES `carport`.`material_type`(`typeID`));

  CREATE TABLE `carport`.`cust_order` (
  `orderID` INT NOT NULL AUTO_INCREMENT,
  `carport_length` INT NOT NULL,
  `carport_width` INT NOT NULL,
  `carport_height` INT NOT NULL,
  `hasShed` TINYINT NULL,
  `shedWidth` INT NULL,
  `shedLength` INT NULL,
  `hasPitch` TINYINT NULL,
  `roof_pitch` INT NULL,
  `roof_material` VARCHAR(45) NULL,
  `price` INT NULL,
  `approved` TINYINT NOT NULL DEFAULT 0,
  `tlf_number` INT NOT NULL,
  PRIMARY KEY (`orderID`));
  
  CREATE TABLE `carport`.`orderlines` (
  `orderlines_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`orderlines_id`),
  FOREIGN KEY (`order_id`)
  REFERENCES `carport`.`cust_order`(`orderID`),
  FOREIGN KEY (`product_id`)
  REFERENCES `carport`.`material_list`(`productID`));
  
  CREATE TABLE `carport`.`storage` (
  `storage_id` INT NOT NULL AUTO_INCREMENT,
  `product_id` INT NOT NULL,
  `length_cm` INT NOT NULL,
  PRIMARY KEY (`storage_id`),
  FOREIGN KEY (`product_id`)
  REFERENCES `carport`.`material_list`(`productID`));

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
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 170);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 200);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 230);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 260);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 290);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 320);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 350);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 380);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 410);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 440);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 470);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 500);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 530);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 560);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 590);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 620);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 650);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('bredde', 680);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 145);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 175);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 205);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 235);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 265);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 295);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 325);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 355);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 385);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 415);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 445);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 475);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 505);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 535);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 565);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 595);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 625);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 655);
INSERT INTO shed_measures (`description`, `measures`) VALUES ('længde', 685);

INSERT INTO material_type (`type_name`) VALUES ('brædt');
INSERT INTO material_type (`type_name`) VALUES ('lægte');
INSERT INTO material_type (`type_name`) VALUES ('reglar');
INSERT INTO material_type (`type_name`) VALUES ('spærtræ');
INSERT INTO material_type (`type_name`) VALUES ('stolpe');
INSERT INTO material_type (`type_name`) VALUES ('trapezplade');
INSERT INTO material_type (`type_name`) VALUES ('bundskruer');
INSERT INTO material_type (`type_name`) VALUES ('hulbånd');
INSERT INTO material_type (`type_name`) VALUES ('beslag');
INSERT INTO material_type (`type_name`) VALUES ('skruer');
INSERT INTO material_type (`type_name`) VALUES ('beslagskruer');
INSERT INTO material_type (`type_name`) VALUES ('bræddebolt');
INSERT INTO material_type (`type_name`) VALUES ('firkantskiver');
INSERT INTO material_type (`type_name`) VALUES ('stalddørsgreb');
INSERT INTO material_type (`type_name`) VALUES ('t-hængsel');
INSERT INTO material_type (`type_name`) VALUES ('vinkelbeslag');

-- Indsæt materiale i listen
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '1', '25x200 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '1', '25x125 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '1', '19x100 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '2', '38x73 mm., ubh.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '3', '45x95 mm., ub.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '4', '45x195 mm., ubh.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '0.05', 'cm', '5', '97x97 mm., trykimp.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '50', 'stk', '6', '1000x4000 mm., blåtonet');

INSERT INTO material_list (`category`, `price_unit`,`amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '109', '200, stk', 'pakke', '7', 'bundskruer');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '87', '10, meter', 'rulle', '8', '1x20 mm.');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '32', 'stk', '9', '190 mm, universal');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '200', '200, stk', 'pakke', '10', '4.5x60mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '220', '200, stk', 'pakke', '10', '4.5x70 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '155', '150, stk', 'pakke', '10', '4x50 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '230', '250, stk', 'pakke', '11', '4x50 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '3', '1', 'stk', '12', '10x120 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '4', '1', 'stk', '13', '40x40x11 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '110', '1', 'sæt', '14', '50x75 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '35', '1', 'stk', '15', '390 mm.');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '3', '1', 'stk', '16', '35 mm.');

INSERT INTO material_type (`type_name`) VALUES ('dobbelt');
INSERT INTO material_type (`type_name`) VALUES ('rygsten');
INSERT INTO material_type (`type_name`) VALUES ('toplægte holder');
INSERT INTO material_type (`type_name`) VALUES ('rygstensbeslag');
INSERT INTO material_type (`type_name`) VALUES ('tagstens bindere og nakkekroge');
INSERT INTO material_type (`type_name`) VALUES ('spær');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('tagpakken', '10', 'stk', '17', 'B & C -s');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('tagpakken', '10', 'stk', '18', 'B & C');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('tagpakken', '10', 'stk', '19', 'B & C');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('tagpakken', '10', 'stk', '20', 'B & C');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('tagpakken', '10', 'pakke', '21', 'B & C');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '5', 'sæt', '22', 'fædigskåret');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '2', 'sæt', '1', '25x50 mm., trykimp');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '35', 'stk', '2', '38x73 mm., taglægte');
INSERT INTO material_list (`category`, `price_unit`, `unit`, `type_id`, `description`) VALUES ('træ og tagplader', '35', 'stk', '2', '38x73 mm., taglægte');
INSERT INTO material_list (`category`, `price_unit`, `amount_pr_unit`, `unit`, `type_id`, `description`) VALUES ('beslag og skruer', '60', '100, stk', 'pakke', '10', '5x100 mm.');

INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (1, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (2, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (3, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (4, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (5, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (6, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (7, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (22, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (26, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (27, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (28, 600);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 250);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 300);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 350);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 400);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 450);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 500);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 550);
INSERT INTO storage (`product_id`, `length_cm`) VALUES (29, 600);
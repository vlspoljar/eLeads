CREATE DATABASE eleads COLLATE utf8_bin;

CREATE TABLE `eleads`.`engine_type` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(20) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE utf8_bin;
INSERT INTO `engine_type` (`id`, `name`) VALUES ('1', 'Petrol');
INSERT INTO `engine_type` (`id`, `name`) VALUES ('2', 'Diesel');
INSERT INTO `engine_type` (`id`, `name`) VALUES ('3', 'Hybrid');
INSERT INTO `engine_type` (`id`, `name`) VALUES ('4', 'Electric');

CREATE TABLE `eleads`.`car_brand` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE utf8_bin;
INSERT INTO `car_brand` (`id`, `name`) VALUES ('1', 'BMW');
INSERT INTO `car_brand` (`id`, `name`) VALUES ('2', 'KIA');;
INSERT INTO `car_brand` (`id`, `name`) VALUES ('3', 'Peugeot');
INSERT INTO `car_brand` (`id`, `name`) VALUES ('4', 'Toyota');
INSERT INTO `car_brand` (`id`, `name`) VALUES ('5', 'Volvo');

CREATE TABLE `eleads`.`car_model` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE utf8_bin;
INSERT INTO `car_model` (`id`, `name`) VALUES ('1', 'i3');
INSERT INTO `car_model` (`id`, `name`) VALUES ('2', 'M5');
INSERT INTO `car_model` (`id`, `name`) VALUES ('3', 'X6');
INSERT INTO `car_model` (`id`, `name`) VALUES ('4', 'Ceed');
INSERT INTO `car_model` (`id`, `name`) VALUES ('5', 'Sportage');
INSERT INTO `car_model` (`id`, `name`) VALUES ('6', 'Soul');
INSERT INTO `car_model` (`id`, `name`) VALUES ('7', '208');
INSERT INTO `car_model` (`id`, `name`) VALUES ('8', '308');
INSERT INTO `car_model` (`id`, `name`) VALUES ('9', '508');
INSERT INTO `car_model` (`id`, `name`) VALUES ('10', 'Avensis');
INSERT INTO `car_model` (`id`, `name`) VALUES ('11', 'Corolla');
INSERT INTO `car_model` (`id`, `name`) VALUES ('12', 'Yaris');
INSERT INTO `car_model` (`id`, `name`) VALUES ('13', 'V40');
INSERT INTO `car_model` (`id`, `name`) VALUES ('14', 'V50');
INSERT INTO `car_model` (`id`, `name`) VALUES ('15', 'V60');

CREATE TABLE `eleads`.`car` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `num_doors` INT(10) NULL , `color` VARCHAR(20) NULL , `engine_type_id` INT(11) UNSIGNED NOT NULL , `car_brand_id` INT(11) UNSIGNED NOT NULL , `car_model_id` INT(11) UNSIGNED NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE utf8_bin;
ALTER TABLE `car` ADD CONSTRAINT `car_fk_1` FOREIGN KEY (`engine_type_id`) REFERENCES `eleads`.`engine_type` ( `id` );
ALTER TABLE `car` ADD CONSTRAINT `car_fk_2` FOREIGN KEY (`car_brand_id`) REFERENCES `eleads`.`car_brand` ( `id` );
ALTER TABLE `car` ADD CONSTRAINT `car_fk_3` FOREIGN KEY (`car_model_id`) REFERENCES `eleads`.`car_model` ( `id` );
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('1', '4', 'White', '4', '1', '1');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('2', '5', 'Blue', '4', '1', '1');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('3', '5', 'Blue', '1', '1', '2');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('4', '5', 'Grey', '1', '1', '2');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('5', '5', 'Black', '2', '1', '3');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('6', '5', 'White', '2', '1', '3');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('7', '5', 'Red', '1', '2', '4');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('8', '5', 'Blue', '1', '2', '4');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('9', '5', 'Blue', '2', '2', '5');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('10', '5', 'Orange', '1', '2', '5');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('11', '5', 'Blue', '4', '2', '6');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('12', '5', 'Red', '1', '3', '7');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('13', '5', 'Grey', '1', '3', '7');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('14', '5', 'White', '2', '3', '8');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('15', '5', 'Blue', '2', '3', '8');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('16', '4', 'Black', '2', '3', '9');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('17', '5', 'White', '2', '3', '9');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('18', '4', 'Black', '2', '4', '10');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('19', '4', 'Grey', '2', '4', '10');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('20', '5', 'White', '3', '4', '11');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('21', '5', 'Grey', '1', '4', '11');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('22', '5', 'White', '3', '4', '12');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('23', '5', 'Red', '1', '4', '12');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('24', '5', 'Black', '2', '5', '13');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('25', '5', 'Blue', '2', '5', '13');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('26', '5', 'White', '2', '5', '14');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('27', '5', 'Grey', '2', '5', '14');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('28', '5', 'Black', '3', '5', '15');
INSERT INTO `car` (`id`, `num_doors`, `color`, `engine_type_id`, `car_brand_id`, `car_model_id`) VALUES ('29', '5', 'Orange', '2', '5', '15');

CREATE TABLE `eleads`.`lead` ( `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT , `first_name` VARCHAR(50) NOT NULL , `last_name` VARCHAR(50) NOT NULL , `city` VARCHAR(50) NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE utf8_bin;

CREATE TABLE `eleads`.`lead_car` ( `lead_id` INT(11) UNSIGNED NOT NULL , `car_id` INT(11) UNSIGNED NOT NULL , PRIMARY KEY (`lead_id` , `car_id`) , CONSTRAINT `lead_car_fk_1` FOREIGN KEY (`lead_id`) REFERENCES `eleads`.`lead` ( `id` ) , CONSTRAINT `lead_car_fk_2` FOREIGN KEY (`car_id`) REFERENCES `eleads`.`car` ( `id` )) ENGINE = InnoDB DEFAULT CHARSET=utf8 COLLATE utf8_bin;
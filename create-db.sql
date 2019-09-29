DROP SCHEMA IF EXISTS `schendule`;

CREATE SCHEMA `schendule`;

use `schendule`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL ,
  `alias` varchar(128) DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf16;


DROP TABLE IF EXISTS `workDay`;

CREATE TABLE `workDay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(45) DEFAULT NULL,
  `dailyDriver` int(11) DEFAULT NULL,
  `allDayWatcher` int(11) DEFAULT NULL,
  `hollidayWatcher` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf16;

INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (54,'ЕГВ');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (130,'ПАИ');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (56,'КНВ');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (1,'ПИЕ');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (2,'ГАМ');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (3,'ХРА');
INSERT INTO `schendule`.`person` (`id`,`alias`) VALUES (4,'РВВ');




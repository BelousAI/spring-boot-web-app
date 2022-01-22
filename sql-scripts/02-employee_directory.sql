CREATE DATABASE  IF NOT EXISTS `employee_directory`;
USE `employee_directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Data for table `employee`
--

INSERT INTO `employee` (first_name, last_name, email)
VALUES
    ('Leslie','Andrews','leslie@gmail.com'),
    ('Emma','Baumgarten','emma@gmail.com'),
    ('Avani','Gupta','avani@gmail.com'),
    ('Yuri','Petrov','yuri@gmail.com'),
    ('Juan','Vega','juan@gmail.com');
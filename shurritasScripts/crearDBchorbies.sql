drop database if exists `Acme-CnG`;
create database `Acme-CnG`;
drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';
create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
grant select, insert, update, delete 
	on `Acme-CnG`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `Acme-CnG`.* to 'acme-manager'@'%';
# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Base de datos: Acme-CnG
# Tiempo de Generación: 2017-03-25 18:46:04 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla Actor_Comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Actor_Comment`;

CREATE TABLE `Actor_Comment` (
  `Actor_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_mh2yd5cn0exjbhtq7t99j4cas` (`comment_id`),
  CONSTRAINT `FK_mh2yd5cn0exjbhtq7t99j4cas` FOREIGN KEY (`comment_id`) REFERENCES `Comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Actor_Comment` WRITE;
/*!40000 ALTER TABLE `Actor_Comment` DISABLE KEYS */;

INSERT INTO `Actor_Comment` (`Actor_id`, `comment_id`)
VALUES
	(529,530),
	(534,535),
	(537,538),
	(539,540),
	(545,546);

/*!40000 ALTER TABLE `Actor_Comment` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Administrator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `commentsPosted` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;

INSERT INTO `Administrator` (`id`, `version`, `commentsPosted`, `email`, `name`, `phone`, `surname`, `userAccount_id`)
VALUES
	(513,0,0,'Administrator1Email@gmail.com','Administrator1Name','+23-6234456456','Administrator1Surname',493),
	(515,0,0,'Administrator2Email@gmail.com','Administrator2Name','+23-534745767','Administrator2Surname',494),
	(516,0,0,'Administrator3Email@gmail.com','Administrator3Name','+23-32464536754','Administrator3Surname',495),
	(517,0,0,'Administrator4Email@gmail.com','Administrator4Name','+23-32464536754','Administrator4Surname',496),
	(518,0,0,'Administrator5Email@gmail.com','Administrator5Name','+23-32464536754','Administrator5Surname',497);

/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Application
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Application`;

CREATE TABLE `Application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `requestStatus` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a34im8lhnwidymhu4f4uihsuc` (`owner_id`),
  CONSTRAINT `FK_a34im8lhnwidymhu4f4uihsuc` FOREIGN KEY (`owner_id`) REFERENCES `Customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Application` WRITE;
/*!40000 ALTER TABLE `Application` DISABLE KEYS */;

INSERT INTO `Application` (`id`, `version`, `requestStatus`, `owner_id`)
VALUES
	(507,1,0,545),
	(508,1,0,545),
	(509,1,2,529),
	(510,1,1,529),
	(511,1,1,529),
	(512,1,1,545);

/*!40000 ALTER TABLE `Application` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Banner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Banner`;

CREATE TABLE `Banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Banner` WRITE;
/*!40000 ALTER TABLE `Banner` DISABLE KEYS */;

INSERT INTO `Banner` (`id`, `version`, `url`)
VALUES
	(503,0,'http://www.ambientum.com/img_boletin/home/7-puntos-amovens.jpg'),
	(504,0,'http://blog.nasafcu.com/wp-content/uploads/2016/02/car_sharing_1.jpg'),
	(505,0,'http://www.altmedia.net.au/wp-content/uploads/2014/11/Silver_yaris_in_pod_car_share_bay_lower_res.jpeg'),
	(506,0,'http://www.businesstravellogue.com/files/2007/07/car-share.jpg');

/*!40000 ALTER TABLE `Banner` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Comment`;

CREATE TABLE `Comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `numberOfStars` int(11) NOT NULL,
  `objectiveId` int(11) NOT NULL,
  `postedMoment` datetime DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;

INSERT INTO `Comment` (`id`, `version`, `banned`, `numberOfStars`, `objectiveId`, `postedMoment`, `text`, `title`, `owner_id`)
VALUES
	(530,0,b'0',2,70,'2017-04-23 23:00:12','text2','title2',529),
	(535,0,b'0',2,72,'2017-04-23 23:00:12','text1','title1',534),
	(538,0,b'0',1,64,'2017-04-23 23:00:12','text3','title3',537),
	(540,0,b'0',4,68,'2017-04-23 23:00:12','text4','title4',539),
	(544,0,b'0',2,610,'2017-04-23 23:00:12','text7','title7',534),
	(546,0,b'0',4,60,'2017-04-23 23:00:12','text5','title5',545),
	(549,0,b'0',4,495,'2017-04-23 23:00:12','text6','title6',545);

/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Customer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Customer`;

CREATE TABLE `Customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `commentsPosted` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;

INSERT INTO `Customer` (`id`, `version`, `commentsPosted`, `email`, `name`, `phone`, `surname`, `userAccount_id`)
VALUES
	(529,0,1,'Customer2Email@gmail.com','Customer2Name','+23-324523452345','Customer2Surname',499),
	(534,0,2,'Customer3Email@gmail.com','Customer3Name','+23-34564563456','Customer3Surname',500),
	(537,0,1,'Customer4Email@gmail.com','Customer4Name','+23-34564563456','Customer4Surname',501),
	(539,0,1,'Customer5Email@gmail.com','Customer5Name','+23-34564563456','Customer5Surname',502),
	(545,0,2,'Customer1Email@gmail.com','Customer1Name','+23-4536435655','Customer1Surname',498);

/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Finder
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Finder`;

CREATE TABLE `Finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `destinationAddress` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `numberOfFinderResults` int(11) DEFAULT NULL,
  `originAddress` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Finder` WRITE;
/*!40000 ALTER TABLE `Finder` DISABLE KEYS */;

INSERT INTO `Finder` (`id`, `version`, `description`, `destinationAddress`, `keyword`, `numberOfFinderResults`, `originAddress`, `title`)
VALUES
	(541,0,'3 plazas libres','sevilla','sevilla',3,'granada','De vuelta a sevilla'),
	(542,0,'murcianicos','Murcia','murcia',NULL,'Huesca','Para murcia que nos vamos'),
	(543,0,'3 plazas libres','Sevilla','merida sevilla',NULL,'Mérida','De Mérida a sevilla');

/*!40000 ALTER TABLE `Finder` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla hibernate_sequences
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;

INSERT INTO `hibernate_sequences` (`sequence_name`, `sequence_next_hi_value`)
VALUES
	('DomainEntity',7);

/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Message
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Message`;

CREATE TABLE `Message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `sentDate` date DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;

INSERT INTO `Message` (`id`, `version`, `body`, `sentDate`, `subject`, `receiver_id`, `sender_id`)
VALUES
	(514,1,'text10','2017-01-23','title10',515,513),
	(519,1,'text1','2016-04-23','title1',529,545),
	(520,1,'text2','2016-04-23','title2',529,545),
	(521,1,'text3','2016-04-23','title3',534,529),
	(522,1,'text4','2016-04-23','title4',537,534),
	(523,1,'text5','2016-04-23','title5',534,545),
	(524,1,'text6','2016-04-23','title6',537,545),
	(525,1,'text7','2016-04-23','title7',539,545),
	(526,1,'text8','2016-04-23','title8',534,539),
	(527,1,'text9','2016-04-23','title9',529,545),
	(528,1,'text10','2017-01-23','title10',545,529);

/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Message_attachments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Message_attachments`;

CREATE TABLE `Message_attachments` (
  `Message_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_9xow20lsikl69n9tfi4iw6q6v` (`Message_id`),
  CONSTRAINT `FK_9xow20lsikl69n9tfi4iw6q6v` FOREIGN KEY (`Message_id`) REFERENCES `Message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Message_attachments` WRITE;
/*!40000 ALTER TABLE `Message_attachments` DISABLE KEYS */;

INSERT INTO `Message_attachments` (`Message_id`, `attachments`)
VALUES
	(514,'sdfasdfsdfsdfsd'),
	(519,'sdfasdfsdfsdfsd'),
	(520,'sdfasdfsdfsdfsd'),
	(521,'sdfasdfsdfsdfsd'),
	(522,'sdfasdfsdfsdfsd'),
	(523,'sdfasdfsdfsdfsd'),
	(524,'sdfasdfsdfsdfsd'),
	(525,'sdfasdfsdfsdfsd'),
	(526,'sdfasdfsdfsdfsd'),
	(527,'sdfasdfsdfsdfsd'),
	(528,'sdfasdfsdfsdfsd');

/*!40000 ALTER TABLE `Message_attachments` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Offer
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Offer`;

CREATE TABLE `Offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `coordXL` char(1) NOT NULL,
  `coordXValue` double DEFAULT NULL,
  `coordYL` char(1) NOT NULL,
  `coordYValue` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `destinationAddress` varchar(255) DEFAULT NULL,
  `originAddress` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tripDate` date DEFAULT NULL,
  `ownerO_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cl20kre8tfuhd9q6foxrkcgbr` (`ownerO_id`),
  CONSTRAINT `FK_cl20kre8tfuhd9q6foxrkcgbr` FOREIGN KEY (`ownerO_id`) REFERENCES `Customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Offer` WRITE;
/*!40000 ALTER TABLE `Offer` DISABLE KEYS */;

INSERT INTO `Offer` (`id`, `version`, `banned`, `coordXL`, `coordXValue`, `coordYL`, `coordYValue`, `description`, `destinationAddress`, `originAddress`, `title`, `tripDate`, `ownerO_id`)
VALUES
	(531,0,b'0','\0',NULL,'\0',NULL,'description1','destination1','origen1','title1','2017-05-23',529),
	(532,0,b'0','\0',NULL,'\0',NULL,'description1','destination1','origen1','title1','2017-05-23',529),
	(547,0,b'0','\0',NULL,'\0',NULL,'description1','destination1','origen1','title1','2017-05-23',545);

/*!40000 ALTER TABLE `Offer` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Request`;

CREATE TABLE `Request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banned` bit(1) NOT NULL,
  `coordXL` char(1) NOT NULL,
  `coordXValue` double DEFAULT NULL,
  `coordYL` char(1) NOT NULL,
  `coordYValue` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `destinationAddress` varchar(255) DEFAULT NULL,
  `originAddress` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `tripDate` date DEFAULT NULL,
  `ownerR_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_65330p4212t47i1nkn961q0dr` (`ownerR_id`),
  CONSTRAINT `FK_65330p4212t47i1nkn961q0dr` FOREIGN KEY (`ownerR_id`) REFERENCES `Customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Request` WRITE;
/*!40000 ALTER TABLE `Request` DISABLE KEYS */;

INSERT INTO `Request` (`id`, `version`, `banned`, `coordXL`, `coordXValue`, `coordYL`, `coordYValue`, `description`, `destinationAddress`, `originAddress`, `title`, `tripDate`, `ownerR_id`)
VALUES
	(533,0,b'1','\0',NULL,'\0',NULL,'description2','destination2','origen2','title2','2017-05-23',529),
	(536,0,b'0','\0',NULL,'\0',NULL,'description3','destination1','origen3','title3','2017-04-13',534),
	(548,0,b'0','\0',NULL,'\0',NULL,'description1','destination1','origen1','title1','2017-04-23',529);

/*!40000 ALTER TABLE `Request` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Trip_Application
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Trip_Application`;

CREATE TABLE `Trip_Application` (
  `Trip_id` int(11) NOT NULL,
  `applications_id` int(11) NOT NULL,
  UNIQUE KEY `UK_s0ydxq9ry5k5rtunyf00tlekh` (`applications_id`),
  CONSTRAINT `FK_s0ydxq9ry5k5rtunyf00tlekh` FOREIGN KEY (`applications_id`) REFERENCES `Application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Trip_Application` WRITE;
/*!40000 ALTER TABLE `Trip_Application` DISABLE KEYS */;

INSERT INTO `Trip_Application` (`Trip_id`, `applications_id`)
VALUES
	(548,507),
	(533,508),
	(536,509),
	(547,510),
	(531,511),
	(532,512);

/*!40000 ALTER TABLE `Trip_Application` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Trip_Comment
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Trip_Comment`;

CREATE TABLE `Trip_Comment` (
  `Trip_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  UNIQUE KEY `UK_6ysyd2ct5fos28wyhh8713vf7` (`comment_id`),
  CONSTRAINT `FK_6ysyd2ct5fos28wyhh8713vf7` FOREIGN KEY (`comment_id`) REFERENCES `Comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Trip_Comment` WRITE;
/*!40000 ALTER TABLE `Trip_Comment` DISABLE KEYS */;

INSERT INTO `Trip_Comment` (`Trip_id`, `comment_id`)
VALUES
	(547,544),
	(548,549);

/*!40000 ALTER TABLE `Trip_Comment` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla UserAccount
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserAccount`;

CREATE TABLE `UserAccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `UserAccount` WRITE;
/*!40000 ALTER TABLE `UserAccount` DISABLE KEYS */;

INSERT INTO `UserAccount` (`id`, `version`, `password`, `username`)
VALUES
	(493,0,'d5cee333abe432891a0de57d0ee38713','administrator1'),
	(494,0,'82954495ff7e2a735ed2192c35b2cd00','administrator2'),
	(495,0,'d1849d15043d73506d6a7523a1661e2d','administrator3'),
	(496,0,'3ce013337481d5af81e21bf6791a574e','administrator4'),
	(497,0,'f2ec5fdcc58bcb002270a11f6bb2a9b3','administrator5'),
	(498,0,'ffbc4675f864e0e9aab8bdf7a0437010','customer1'),
	(499,0,'5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),
	(500,0,'033f7f6121501ae98285ad77f216d5e7','customer3'),
	(501,0,'55feb130be438e686ad6a80d12dd8f44','customer4'),
	(502,0,'9e8486cdd435beda9a60806dd334d964','customer5');

/*!40000 ALTER TABLE `UserAccount` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla UserAccount_authorities
# ------------------------------------------------------------

DROP TABLE IF EXISTS `UserAccount_authorities`;

CREATE TABLE `UserAccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `UserAccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `UserAccount_authorities` WRITE;
/*!40000 ALTER TABLE `UserAccount_authorities` DISABLE KEYS */;

INSERT INTO `UserAccount_authorities` (`UserAccount_id`, `authority`)
VALUES
	(493,'ADMINISTRATOR'),
	(494,'ADMINISTRATOR'),
	(495,'ADMINISTRATOR'),
	(496,'ADMINISTRATOR'),
	(497,'ADMINISTRATOR'),
	(498,'CUSTOMER'),
	(499,'CUSTOMER'),
	(500,'CUSTOMER'),
	(501,'CUSTOMER'),
	(502,'CUSTOMER');

/*!40000 ALTER TABLE `UserAccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


commit;

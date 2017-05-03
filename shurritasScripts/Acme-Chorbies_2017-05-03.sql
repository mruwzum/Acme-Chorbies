start transaction;

drop database if exists `Acme-Chorbies`;
create database `Acme-Chorbies`;
use `Acme-Chorbies`;
drop user 'acme-user'@'%';
drop user 'acme-manager'@'%';
create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';
create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';
grant select, insert, update, delete
  on `Acme-Chorbies`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter,
        create temporary tables, lock tables, create view, create routine,
        alter routine, execute, trigger, show view
    on `Acme-Chorbies`.* to 'acme-manager'@'%';
# ************************************************************
# Sequel Pro SQL dump
# Versión 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.53)
# Base de datos: Acme-Chorbies
# Tiempo de Generación: 2017-05-03 16:47:14 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Volcado de tabla Administrator
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
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

INSERT INTO `Administrator` (`id`, `version`, `email`, `name`, `phone`, `surname`, `userAccount_id`)
VALUES
	(116,0,'Administrator1Email@gmail.com','Administrator1Name','+23-6234456456','Administrator1Surname',87),
	(117,0,'Administrator2Email@gmail.com','Administrator2Name','+23-534745767','Administrator2Surname',88),
	(118,0,'Administrator3Email@gmail.com','Administrator3Name','+23-32464536754','Administrator3Surname',89),
	(119,0,'Administrator4Email@gmail.com','Administrator4Name','+23-32464536754','Administrator4Surname',90);

/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
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
	(102,0,'http://i.imgur.com/UY1x8KM.png'),
	(103,0,'http://i.imgur.com/LtQYadW.png'),
	(104,0,'http://i.imgur.com/eViE38q.png');

/*!40000 ALTER TABLE `Banner` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Chirp
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Chirp`;

CREATE TABLE `Chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Chirp` WRITE;
/*!40000 ALTER TABLE `Chirp` DISABLE KEYS */;

INSERT INTO `Chirp` (`id`, `version`, `message`, `moment`, `subject`, `receiver_id`, `sender_id`)
VALUES
	(127,1,'text1','2016-04-23','title1',152,142),
	(128,1,'text2','2016-04-23','title2',159,152),
	(129,1,'text3','2016-04-23','title3',152,159),
	(130,1,'text4','2016-04-23','title4',160,156),
	(131,1,'text5','2016-04-23','title5',160,152),
	(132,1,'text6','2016-04-23','title6',142,159),
	(133,1,'text7','2016-04-23','title7',156,159),
	(134,1,'text8','2016-04-23','title8',152,156),
	(135,1,'text9','2016-04-23','title9',160,152),
	(136,1,'text10','2017-01-23','title10',142,160),
	(137,1,'text10','2017-01-23','title10',159,152),
	(162,0,'text10','2017-01-23','title10',144,141),
	(163,0,'text10','2017-01-23','title10',142,139),
	(164,0,'text10','2017-01-23','title10',152,141),
	(165,0,'text10','2017-01-23','title10',156,139);

/*!40000 ALTER TABLE `Chirp` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Chirp_attachments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Chirp_attachments`;

CREATE TABLE `Chirp_attachments` (
  `Chirp_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_19xst1rktyonkumt1r20fe0gh` (`Chirp_id`),
  CONSTRAINT `FK_19xst1rktyonkumt1r20fe0gh` FOREIGN KEY (`Chirp_id`) REFERENCES `Chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Chirp_attachments` WRITE;
/*!40000 ALTER TABLE `Chirp_attachments` DISABLE KEYS */;

INSERT INTO `Chirp_attachments` (`Chirp_id`, `attachments`)
VALUES
	(127,'https://pi.tedcdn.com/r/pf.tedcdn.com/images/playlists/in_the_mood_for_love_1200x627.jpg'),
	(128,'https://static.pexels.com/photos/6371/red-love-heart-valentines.jpg'),
	(129,'https://www.psychologies.co.uk/sites/default/files/field/image/Life%20Lab%20love%20languages.jpg'),
	(130,'http://wishesmessages.com/wp-content/uploads/2013/04/Heart-i-love-you-never-leave-me-message-640x480.jpg'),
	(131,'http://www.clipartkid.com/images/31/love-you-clipart-clipart-panda-free-clipart-images-Nrbxqi-clipart.png'),
	(132,'https://www.psychologies.co.uk/sites/default/files/field/image/Life%20Lab%20love%20languages.jpg'),
	(133,'http://sites.psu.edu/siowfa15/wp-content/uploads/sites/29639/2015/09/love-4.jpg'),
	(134,'https://pb-assets.tedcdn.com/system/baubles/files/000/000/958/original/topics_trh_love.jpg'),
	(135,'https://www.psychologies.co.uk/sites/default/files/field/image/Life%20Lab%20love%20languages.jpg'),
	(136,'https://www.hopespeak.com/blog/wp-content/uploads/2015/05/Without-saying-I-love-you.jpg'),
	(137,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif'),
	(162,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif'),
	(163,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif'),
	(164,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif'),
	(165,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif');

/*!40000 ALTER TABLE `Chirp_attachments` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ChirpMultiple
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ChirpMultiple`;

CREATE TABLE `ChirpMultiple` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `message` varchar(255) DEFAULT NULL,
  `moment` date DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_akst13xh43wwhplexole7g4fx` (`sender_id`),
  CONSTRAINT `FK_akst13xh43wwhplexole7g4fx` FOREIGN KEY (`sender_id`) REFERENCES `Manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ChirpMultiple` WRITE;
/*!40000 ALTER TABLE `ChirpMultiple` DISABLE KEYS */;

INSERT INTO `ChirpMultiple` (`id`, `version`, `message`, `moment`, `subject`, `sender_id`)
VALUES
	(138,0,'text10','2017-01-23','title10',139),
	(161,0,'text10','2017-01-23','title10',141);

/*!40000 ALTER TABLE `ChirpMultiple` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ChirpMultiple_attachments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ChirpMultiple_attachments`;

CREATE TABLE `ChirpMultiple_attachments` (
  `ChirpMultiple_id` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_7ptg1mw0o7ab3i4sxewgh9c92` (`ChirpMultiple_id`),
  CONSTRAINT `FK_7ptg1mw0o7ab3i4sxewgh9c92` FOREIGN KEY (`ChirpMultiple_id`) REFERENCES `ChirpMultiple` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ChirpMultiple_attachments` WRITE;
/*!40000 ALTER TABLE `ChirpMultiple_attachments` DISABLE KEYS */;

INSERT INTO `ChirpMultiple_attachments` (`ChirpMultiple_id`, `attachments`)
VALUES
	(138,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif'),
	(161,'http://www.desicomments.com/wp-content/uploads/Animated-Image-Of-Open-Love-Envelope.gif');

/*!40000 ALTER TABLE `ChirpMultiple_attachments` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla ChirpMultiple_Chorbi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ChirpMultiple_Chorbi`;

CREATE TABLE `ChirpMultiple_Chorbi` (
  `ChirpMultiple_id` int(11) NOT NULL,
  `receivers_id` int(11) NOT NULL,
  KEY `FK_6o6b3c7oltfsuh99yp1idmjwe` (`receivers_id`),
  KEY `FK_bp2dhlkcpvoy1q960huxhnw61` (`ChirpMultiple_id`),
  CONSTRAINT `FK_bp2dhlkcpvoy1q960huxhnw61` FOREIGN KEY (`ChirpMultiple_id`) REFERENCES `ChirpMultiple` (`id`),
  CONSTRAINT `FK_6o6b3c7oltfsuh99yp1idmjwe` FOREIGN KEY (`receivers_id`) REFERENCES `Chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `ChirpMultiple_Chorbi` WRITE;
/*!40000 ALTER TABLE `ChirpMultiple_Chorbi` DISABLE KEYS */;

INSERT INTO `ChirpMultiple_Chorbi` (`ChirpMultiple_id`, `receivers_id`)
VALUES
	(138,160),
	(138,159),
	(161,144),
	(161,142);

/*!40000 ALTER TABLE `ChirpMultiple_Chorbi` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Chorbi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Chorbi`;

CREATE TABLE `Chorbi` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `banned` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `genre` int(11) DEFAULT NULL,
  `numberOfStars` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `relationship` int(11) DEFAULT NULL,
  `signUpDate` datetime DEFAULT NULL,
  `totalFeeToPay` int(11) NOT NULL,
  `coordinate_id` int(11) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  KEY `FK_a0hy8coyr45cnqa31ocmih7fv` (`coordinate_id`),
  KEY `FK_ooohivehsja6eu3cxe201pe7v` (`creditCard_id`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_a0hy8coyr45cnqa31ocmih7fv` FOREIGN KEY (`coordinate_id`) REFERENCES `Coordinate` (`id`),
  CONSTRAINT `FK_ooohivehsja6eu3cxe201pe7v` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Chorbi` WRITE;
/*!40000 ALTER TABLE `Chorbi` DISABLE KEYS */;

INSERT INTO `Chorbi` (`id`, `version`, `email`, `name`, `phone`, `surname`, `userAccount_id`, `age`, `banned`, `description`, `genre`, `numberOfStars`, `picture`, `relationship`, `signUpDate`, `totalFeeToPay`, `coordinate_id`, `creditCard_id`)
VALUES
	(142,0,'Chorbi1Email@gmail.com','Chorbi1Name','+23-6234456456','Chorbi1Surname',91,32,b'0','Chorbi 1 description +23-32464536754 with an contact email pepe@gmail.com ',0,4,'http://men-dating.letsbond.com/media/articles/320/tips-for-a-good-online-dating-profile_big.jpg',2,'2016-01-23 23:00:12',0,120,105),
	(144,0,'daviddelatorre00@gmail.com','peeri','+34-648208840','peri',96,21,b'0','Ingeniero informático dispuesto a conocer a una chica que sepa de Java',0,0,'https://pbs.twimg.com/media/BVSJa4wIAAAksTV.jpg',2,'2016-09-23 23:00:12',0,120,110),
	(145,0,'ma.antolinbermudez@gmail.com','Miguel Ángel','+23-626225021','Antolín Bermúdez',97,25,b'0',' Solo se que no se nada',0,0,'https://pbs.twimg.com/profile_images/753519498980798464/sru1pCKN_400x400.jpg',0,'2017-01-23 23:00:12',0,120,NULL),
	(152,0,'Chorbi2Email@gmail.com','Chorbi2Name','+23-534745767','Chorbi2Surname',92,27,b'0','Chorbi description',1,3,'http://www.wonderslist.com/wp-content/uploads/2015/10/Doutzen-Kroes-Most-Beautiful-Dutch-Woman.jpg',2,'2016-11-23 23:00:12',0,121,106),
	(156,0,'Chorbi3Email@gmail.com','Chorbi3Name','+23-32464536754','Chorbi3Surname',93,45,b'0','Chorbi  description call me now!! +23-32464536754',0,5,'http://pngimg.com/uploads/man/man_PNG6534.png',1,'2015-01-23 23:00:12',0,122,107),
	(159,0,'Chorbi4Email@gmail.com','Chorbi4Name','+23-32464536754','Chorbi4Surname',94,24,b'0','Chorbi  description call me now!! +23-32464536754 ',0,2,'https://www.nationaleombudsman.nl/uploads/afbeelding/2012%20Jonge%20man.jpg',0,'2016-01-12 23:00:12',0,123,108),
	(160,0,'Chorbi4Email@gmail.com','Chorbi5Name','+23-32464536754','Chorbi5Surname',95,27,b'0','Chorbi description',1,1,'http://si.wsj.net/public/resources/images/WW-AA663A_SANDB_M_20150925152829.jpg',1,'2016-09-12 13:00:12',0,124,109);

/*!40000 ALTER TABLE `Chorbi` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Coordinate
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Coordinate`;

CREATE TABLE `Coordinate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Coordinate` WRITE;
/*!40000 ALTER TABLE `Coordinate` DISABLE KEYS */;

INSERT INTO `Coordinate` (`id`, `version`, `city`, `country`, `province`, `state`)
VALUES
	(120,0,'Sevilla','Spain','Sevilla','Andalucía'),
	(121,0,'Córdoba','Spain','Córdoba','Andalucía'),
	(122,0,'Granada','Spain','Granada','Andalucía'),
	(123,0,'Almería','Spain','Almería','Andalucía'),
	(124,0,'Huelva','Spain','Huelva','Andalucía'),
	(125,0,'Cádiz','Spain','Cádiz','Andalucía'),
	(126,0,'Málaga','Spain','Málaga','Andalucía');

/*!40000 ALTER TABLE `Coordinate` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla CreditCard
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CreditCard`;

CREATE TABLE `CreditCard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `CVV` varchar(255) DEFAULT NULL,
  `brand` int(11) DEFAULT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holder` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `valid` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `CreditCard` WRITE;
/*!40000 ALTER TABLE `CreditCard` DISABLE KEYS */;

INSERT INTO `CreditCard` (`id`, `version`, `CVV`, `brand`, `expirationMonth`, `expirationYear`, `holder`, `number`, `valid`)
VALUES
	(105,0,'432',0,2,2018,'chorbi1','54275498043695577',b'1'),
	(106,0,'112',3,6,2016,'chorbi2','54275498043695577',b'0'),
	(107,0,'222',2,3,2018,'chorbi3','54275498043695577',b'1'),
	(108,0,'007',4,8,2030,'chorbi4','54275498043695577',NULL),
	(109,0,'235',1,8,2017,'chorbi5','54275498043695577',NULL),
	(110,0,'235',1,8,2020,'David de la torre','54275498043695577',NULL),
	(111,0,'235',1,8,2020,'Miguel Angel Antolin','54275498043695577',NULL),
	(112,0,'235',1,8,2020,'Manager2','54275498043695577',NULL),
	(113,0,'235',1,8,2016,'Manager3','54275498043695577',NULL),
	(114,0,'235',1,8,2020,'Manager4','54275498043695577',NULL),
	(115,0,'235',1,8,2022,'Manager1','54275498043695577',NULL);

/*!40000 ALTER TABLE `CreditCard` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Event
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Event`;

CREATE TABLE `Event` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `numberOfSeats` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pj0001ugwkswndw45svt01gp9` (`owner_id`),
  CONSTRAINT `FK_pj0001ugwkswndw45svt01gp9` FOREIGN KEY (`owner_id`) REFERENCES `Manager` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;

INSERT INTO `Event` (`id`, `version`, `date`, `description`, `numberOfSeats`, `picture`, `title`, `owner_id`)
VALUES
	(140,0,'2017-03-23','descrition 1',10,'https://www.star.com.au/sydney/sites/default/files/thumbnails/image/The%20Star%20-%20The%20Event%20Centre%20-%20Banquet%20setup%20view.JPG','title1',141),
	(143,0,'2017-05-22','descrition 0',10,'https://www.star.com.au/sydney/sites/default/files/thumbnails/image/The%20Star%20-%20The%20Event%20Centre%20-%20Banquet%20setup%20view.JPG','title0',139),
	(153,0,'2017-04-30','descrition 2',100,'https://www.star.com.au/sydney/sites/default/files/thumbnails/image/The%20Star%20-%20The%20Event%20Centre%20-%20Banquet%20setup%20view.JPG','title2',154),
	(155,0,'2017-02-12','descrition 3',200,'https://www.star.com.au/sydney/sites/default/files/thumbnails/image/The%20Star%20-%20The%20Event%20Centre%20-%20Banquet%20setup%20view.JPG','title3',154),
	(172,0,'2017-05-12','descrition 5',10,'https://www.star.com.au/sydney/sites/default/files/thumbnails/image/The%20Star%20-%20The%20Event%20Centre%20-%20Banquet%20setup%20view.JPG','title5',139);

/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Event_ChirpMultiple
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Event_ChirpMultiple`;

CREATE TABLE `Event_ChirpMultiple` (
  `Event_id` int(11) NOT NULL,
  `announcements_id` int(11) NOT NULL,
  KEY `FK_229ccadc8lnb32nw3v6w25dld` (`announcements_id`),
  KEY `FK_f7vc065a6m1ld18v3hjb929kg` (`Event_id`),
  CONSTRAINT `FK_f7vc065a6m1ld18v3hjb929kg` FOREIGN KEY (`Event_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `FK_229ccadc8lnb32nw3v6w25dld` FOREIGN KEY (`announcements_id`) REFERENCES `ChirpMultiple` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Event_ChirpMultiple` WRITE;
/*!40000 ALTER TABLE `Event_ChirpMultiple` DISABLE KEYS */;

INSERT INTO `Event_ChirpMultiple` (`Event_id`, `announcements_id`)
VALUES
	(143,138);

/*!40000 ALTER TABLE `Event_ChirpMultiple` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Event_Chorbi
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Event_Chorbi`;

CREATE TABLE `Event_Chorbi` (
  `eventsToGo_id` int(11) NOT NULL,
  `partakers_id` int(11) NOT NULL,
  KEY `FK_4u5nopdd577x5t0ah740rej2h` (`partakers_id`),
  KEY `FK_5u0w106155d5vf09mr6ds6qq8` (`eventsToGo_id`),
  CONSTRAINT `FK_5u0w106155d5vf09mr6ds6qq8` FOREIGN KEY (`eventsToGo_id`) REFERENCES `Event` (`id`),
  CONSTRAINT `FK_4u5nopdd577x5t0ah740rej2h` FOREIGN KEY (`partakers_id`) REFERENCES `Chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Event_Chorbi` WRITE;
/*!40000 ALTER TABLE `Event_Chorbi` DISABLE KEYS */;

INSERT INTO `Event_Chorbi` (`eventsToGo_id`, `partakers_id`)
VALUES
	(140,142),
	(140,152),
	(143,142),
	(143,144),
	(143,145),
	(153,152),
	(153,156),
	(153,159);

/*!40000 ALTER TABLE `Event_Chorbi` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Fee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Fee`;

CREATE TABLE `Fee` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `feeValue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Fee` WRITE;
/*!40000 ALTER TABLE `Fee` DISABLE KEYS */;

INSERT INTO `Fee` (`id`, `version`, `feeValue`)
VALUES
	(169,0,1),
	(170,0,1);

/*!40000 ALTER TABLE `Fee` ENABLE KEYS */;
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
	('DomainEntity',1);

/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Liked
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Liked`;

CREATE TABLE `Liked` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` date DEFAULT NULL,
  `numberOfStarts` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bxhau131p5re1nayk03kl93y6` (`receiver_id`),
  KEY `FK_93pyqts2b7ar1lce12a9hfmfy` (`sender_id`),
  CONSTRAINT `FK_93pyqts2b7ar1lce12a9hfmfy` FOREIGN KEY (`sender_id`) REFERENCES `Chorbi` (`id`),
  CONSTRAINT `FK_bxhau131p5re1nayk03kl93y6` FOREIGN KEY (`receiver_id`) REFERENCES `Chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Liked` WRITE;
/*!40000 ALTER TABLE `Liked` DISABLE KEYS */;

INSERT INTO `Liked` (`id`, `version`, `moment`, `numberOfStarts`, `text`, `receiver_id`, `sender_id`)
VALUES
	(146,1,'2017-03-29',2,'Woow!',142,152),
	(147,1,'2017-03-29',2,'Woow!',142,160),
	(148,1,'2017-04-01',0,'Woow!',142,156),
	(149,1,'2017-03-28',3,'Woow tuuuuuuu ;)!',152,142),
	(150,1,'2017-03-29',1,'Woow!',160,142),
	(151,1,'2017-03-31',3,'Woow!',156,142),
	(157,1,'2017-03-30',2,'Woow!',156,159),
	(158,1,'2017-03-31',2,':3',159,156);

/*!40000 ALTER TABLE `Liked` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Manager
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Manager`;

CREATE TABLE `Manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `company` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `totalFee` double DEFAULT NULL,
  `vatNumber` varchar(255) DEFAULT NULL,
  `creditCard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  KEY `FK_giivdifqwawxhepiwjes4redt` (`creditCard_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `UserAccount` (`id`),
  CONSTRAINT `FK_giivdifqwawxhepiwjes4redt` FOREIGN KEY (`creditCard_id`) REFERENCES `CreditCard` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Manager` WRITE;
/*!40000 ALTER TABLE `Manager` DISABLE KEYS */;

INSERT INTO `Manager` (`id`, `version`, `email`, `name`, `phone`, `surname`, `userAccount_id`, `company`, `fee`, `totalFee`, `vatNumber`, `creditCard_id`)
VALUES
	(139,0,'Manager1Email@gmail.com','Manager1Name','+23-58439640202','Manager1Surname',98,'Bankinter',1,10,'56663452R',115),
	(141,0,'Manager1Email@gmail.com','Manager1Name','+23-58439640202','Manager1Surname',99,'Santander',2,16,'42211457P',112),
	(154,0,'Manager1Email@gmail.com','Manager1Name','+23-58439640202','Manager1Surname',100,'Durex',5,100,'75558978U',113),
	(171,0,'Manager1Email@gmail.com','Manager1Name','+23-58439640202','Manager1Surname',101,'Control',10,1000,'82225334R',114);

/*!40000 ALTER TABLE `Manager` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla Search
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Search`;

CREATE TABLE `Search` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `creationDate` datetime DEFAULT NULL,
  `genre` int(11) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `relationship` int(11) DEFAULT NULL,
  `coordinate_id` int(11) DEFAULT NULL,
  `owner_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2takf0ibrsvd16v0dxd2jaslx` (`coordinate_id`),
  KEY `FK_682epyxwtuxnynnmxdcpdkwfr` (`owner_id`),
  CONSTRAINT `FK_682epyxwtuxnynnmxdcpdkwfr` FOREIGN KEY (`owner_id`) REFERENCES `Chorbi` (`id`),
  CONSTRAINT `FK_2takf0ibrsvd16v0dxd2jaslx` FOREIGN KEY (`coordinate_id`) REFERENCES `Coordinate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Search` WRITE;
/*!40000 ALTER TABLE `Search` DISABLE KEYS */;

INSERT INTO `Search` (`id`, `version`, `age`, `creationDate`, `genre`, `keyword`, `relationship`, `coordinate_id`, `owner_id`)
VALUES
	(166,0,26,'2017-03-29 00:00:00',0,'Sevilla',0,120,142),
	(167,0,30,'2017-04-01 00:00:00',1,'LOVE',2,120,152);

/*!40000 ALTER TABLE `Search` ENABLE KEYS */;
UNLOCK TABLES;


# Volcado de tabla SearchCache
# ------------------------------------------------------------

DROP TABLE IF EXISTS `SearchCache`;

CREATE TABLE `SearchCache` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cacheValue` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `SearchCache` WRITE;
/*!40000 ALTER TABLE `SearchCache` DISABLE KEYS */;

INSERT INTO `SearchCache` (`id`, `version`, `cacheValue`)
VALUES
	(168,0,12);

/*!40000 ALTER TABLE `SearchCache` ENABLE KEYS */;
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
	(87,0,'d5cee333abe432891a0de57d0ee38713','administrator1'),
	(88,0,'82954495ff7e2a735ed2192c35b2cd00','administrator2'),
	(89,0,'d1849d15043d73506d6a7523a1661e2d','administrator3'),
	(90,0,'3ce013337481d5af81e21bf6791a574e','administrator4'),
	(91,0,'3daa859a294cdefb20a84840240a76f5','chorbi1'),
	(92,0,'0c8746de81268518ff83490301db8652','chorbi2'),
	(93,0,'a2da05a88eead7e64593826cafc6a7a7','chorbi3'),
	(94,0,'a09dd233386632e297a7f4f461989563','chorbi4'),
	(95,0,'7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),
	(96,0,'12881aa458b6d90866d9c06732b00b64','shurrita'),
	(97,0,'12881aa458b6d90866d9c06732b00b64','mruwzum'),
	(98,0,'c240642ddef994358c96da82c0361a58','manager1'),
	(99,0,'8df5127cd164b5bc2d2b78410a7eea0c','manager2'),
	(100,0,'2d3a5db4a2a9717b43698520a8de57d0','manager3'),
	(101,0,'e1ec6fc941af3ba79a4ac5242dd39735','manager4');

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
	(87,'ADMINISTRATOR'),
	(88,'ADMINISTRATOR'),
	(89,'ADMINISTRATOR'),
	(90,'ADMINISTRATOR'),
	(91,'CHORBI'),
	(92,'CHORBI'),
	(93,'CHORBI'),
	(94,'CHORBI'),
	(95,'CHORBI'),
	(96,'CHORBI'),
	(97,'CHORBI'),
	(98,'MANAGER'),
	(99,'MANAGER'),
	(100,'MANAGER'),
	(101,'MANAGER');

/*!40000 ALTER TABLE `UserAccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
commit;
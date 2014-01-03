-- mysql -u username schemaName < sriptBackup.sql 
-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: MolkkyDB
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Equipes`
--

DROP TABLE IF EXISTS `Equipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Equipes` (
  `idEquipe` int(11) NOT NULL AUTO_INCREMENT,
  `idMembre1` int(11) NOT NULL,
  `idMembre2` int(11) NOT NULL,
  `numero_equipe` int(11) DEFAULT NULL,
  `idPartie` int(11) NOT NULL,
  PRIMARY KEY (`idEquipe`),
  KEY `fk_Equipes_Members1` (`idMembre1`),
  KEY `fk_Equipes_Members2` (`idMembre2`),
  KEY `fk_Equipes_Parties1` (`idPartie`),
  CONSTRAINT `fk_Equipes_Members1` FOREIGN KEY (`idMembre1`) REFERENCES `Members` (`idMember`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipes_Members2` FOREIGN KEY (`idMembre2`) REFERENCES `Members` (`idMember`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Equipes_Parties1` FOREIGN KEY (`idPartie`) REFERENCES `Parties` (`idPartie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Equipes`
--

LOCK TABLES `Equipes` WRITE;
/*!40000 ALTER TABLE `Equipes` DISABLE KEYS */;
INSERT INTO `Equipes` VALUES (9,27,65,1,1),(10,39,38,2,1),(12,33,46,3,1),(13,30,29,4,1),(14,66,67,5,1),(15,1,2,6,1),(16,68,69,7,1),(17,70,71,8,1),(18,72,73,9,1),(19,74,75,10,1),(20,76,15,11,1),(21,77,78,12,1),(22,18,8,13,1),(23,79,80,14,1),(24,81,82,15,1),(25,83,84,16,1),(26,85,86,17,1),(27,87,88,18,1),(28,89,90,19,1),(29,91,92,20,1),(30,93,94,21,1),(31,95,96,22,1),(32,97,98,23,1),(33,99,100,24,1),(34,101,102,25,1),(35,9,10,26,1),(36,103,104,27,1),(37,12,11,28,1),(38,32,31,29,1),(39,105,106,30,1),(40,35,34,31,1),(41,107,108,32,1),(42,109,110,33,1),(43,58,111,34,1),(44,112,113,35,1),(45,20,19,36,1),(46,114,55,37,1),(47,41,42,38,1),(48,115,116,39,1),(49,117,118,40,1),(50,26,37,41,1),(51,119,120,42,1),(52,121,122,43,1),(53,56,123,44,1),(54,24,40,45,1);
/*!40000 ALTER TABLE `Equipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Groups`
--

DROP TABLE IF EXISTS `Groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Groups` (
  `idGroup` int(11) NOT NULL AUTO_INCREMENT,
  `numero_Group` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `idMember` int(11) NOT NULL,
  PRIMARY KEY (`idGroup`),
  KEY `fk_Groups_Members1` (`idMember`),
  CONSTRAINT `fk_Groups_Members1` FOREIGN KEY (`idMember`) REFERENCES `Members` (`idMember`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Groups`
--

LOCK TABLES `Groups` WRITE;
/*!40000 ALTER TABLE `Groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `Groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Members`
--

DROP TABLE IF EXISTS `Members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Members` (
  `idMember` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `nom` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `email` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `anniversaire` date DEFAULT NULL,
  `pseudonyme` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  PRIMARY KEY (`idMember`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Members`
--

LOCK TABLES `Members` WRITE;
/*!40000 ALTER TABLE `Members` DISABLE KEYS */;
INSERT INTO `Members` VALUES (1,'MASSIMO','PACINI',NULL,NULL,NULL),(2,'ANNE-LYSE','VERHOLEN',NULL,NULL,NULL),(3,'PASCAL','LAMBIN',NULL,NULL,NULL),(4,'BENOIT','FLASSE',NULL,NULL,NULL),(5,'MARIE CARMEN','MIRAMONTES',NULL,NULL,NULL),(6,'LAURENT','VAN EYCKE',NULL,NULL,NULL),(7,'ROBERT','DIDIER',NULL,NULL,NULL),(8,'TINTIN','DEMATTEIS',NULL,NULL,NULL),(9,'REGIS','PRUVOST',NULL,NULL,NULL),(10,'NICOLAS','PRUVOST',NULL,NULL,NULL),(11,'LAURENT','DELPLANCQ',NULL,NULL,NULL),(12,'MARCELIN','DELPLANCQ',NULL,NULL,NULL),(13,'SANTIAGO','LUNA',NULL,NULL,NULL),(14,'DAVID','MAYEUR',NULL,NULL,NULL),(15,'JACQUELINE','MARTIN',NULL,NULL,NULL),(16,'GENEVIEVE','FRANCOIS',NULL,NULL,NULL),(17,'FLORIAN','PLUMET',NULL,NULL,NULL),(18,'TINO','SIAS',NULL,NULL,NULL),(19,'JEAN-LUC','WASTIEL',NULL,NULL,NULL),(20,'FRANCOIS','WASTIEL',NULL,NULL,NULL),(21,'ETIENNE','VAN DAMME',NULL,NULL,NULL),(22,'JEANINE','COIFFARD',NULL,NULL,NULL),(23,'ANTOINE','QUESTIAUX',NULL,NULL,NULL),(24,'MATHIEU','GEORGERIN',NULL,NULL,NULL),(25,'MONIQUE','NAMUR',NULL,NULL,NULL),(26,'PAUL','HARVENGT',NULL,NULL,NULL),(27,'ARNAUD','DELATTRE',NULL,NULL,NULL),(28,'DONOVAN','HAVEZ',NULL,NULL,NULL),(29,'SEBASTIEN','LAMBERT',NULL,NULL,NULL),(30,'BERTRAND','BOUDART',NULL,NULL,NULL),(31,'MICHEL','COQUELET',NULL,NULL,NULL),(32,'EMILIEN','DELPLANCQ',NULL,NULL,NULL),(33,'SABINE','CERISIER',NULL,NULL,NULL),(34,'CHRISTINE','TACQ',NULL,NULL,NULL),(35,'ANDRE','MILS',NULL,NULL,NULL),(36,'ESTELLE','DUBOIS',NULL,NULL,NULL),(37,'CAROLE','HONOREZ',NULL,NULL,NULL),(38,'RACHEL','DELFOSSE',NULL,NULL,NULL),(39,'ALEXANDRE','DELFOSSE',NULL,NULL,NULL),(40,'XAVIER','FLASSE',NULL,NULL,NULL),(41,'VINCENT','PETILLON',NULL,NULL,NULL),(42,'FREDERIC','STIEVENARD',NULL,NULL,NULL),(43,'ALEXIS','PLUMET',NULL,NULL,NULL),(44,'ANNA','QUESTIAUX',NULL,NULL,NULL),(45,'MICHAEL','VAN AELST',NULL,NULL,NULL),(46,'CHARLES','DELFOSSE',NULL,NULL,NULL),(47,'KIERAN','LANE',NULL,NULL,NULL),(48,'VIRGINIE','DUBOIS',NULL,NULL,NULL),(49,'MARIE','HARVENGT',NULL,NULL,NULL),(50,'FLAVIE','WASTIEL',NULL,NULL,NULL),(51,'VICTOR','FACCHINETTI',NULL,NULL,NULL),(52,'STEVE','PLUMET',NULL,NULL,NULL),(53,'PASCAL','FIEVEZ',NULL,NULL,NULL),(54,'BERNARD','BALTHAZART',NULL,NULL,NULL),(55,'GINETTE','BERTIAUX',NULL,NULL,NULL),(56,'ANAIS','HARVENGT',NULL,NULL,NULL),(57,'THERESE','MILS',NULL,NULL,NULL),(58,'MICHELE','CAMBIER',NULL,NULL,NULL),(59,'EVA','QUESTIAUX',NULL,NULL,NULL),(60,'MONA','HARVENGT',NULL,NULL,NULL),(61,'ARLETTE','BENOIT',NULL,NULL,NULL),(62,'FLORIAN','PAULUS',NULL,NULL,NULL),(63,'SIMON','MALCORPS',NULL,NULL,NULL),(64,'SONIA','DECRESSAC',NULL,NULL,NULL),(65,'HUGO','TIMMERMAN',NULL,NULL,NULL),(66,'JEAN-CLAUDE','DESSORT',NULL,NULL,NULL),(67,'HUGO','MOREAU',NULL,NULL,NULL),(68,'JULIE','DUPONT',NULL,NULL,NULL),(69,'LAURANNE','CORDIER',NULL,NULL,NULL),(70,'PAULINE','VACHAUDEZ',NULL,NULL,NULL),(71,'PIERRE','GASTOUT',NULL,NULL,NULL),(72,'PATRICK','DIEU',NULL,NULL,NULL),(73,'JEAN-CLAUDE','VINCENT',NULL,NULL,NULL),(74,'ANTOINE','CAOCHIES',NULL,NULL,NULL),(75,'QUENTIN','GASTOUT',NULL,NULL,NULL),(76,'SOPHIE','HOUSSIEAU',NULL,NULL,NULL),(77,'GILLES','DEFRAENE',NULL,NULL,NULL),(78,'OLIVIER','URBAIN',NULL,NULL,NULL),(79,'DIDIER','ROBERT',NULL,NULL,NULL),(80,'VINCENT','MOTTE',NULL,NULL,NULL),(81,'ZELIE','JENARD',NULL,NULL,NULL),(82,'ANTONA','JENARD',NULL,NULL,NULL),(83,'ALAIN','JENARD',NULL,NULL,NULL),(84,'VIRGINIE','GHIOT',NULL,NULL,NULL),(85,'AURORE','DEHON',NULL,NULL,NULL),(86,'ANTHONY','KENNIS',NULL,NULL,NULL),(87,'FREDINE','BERTIAUX',NULL,NULL,NULL),(88,'LOUISE','BUKO',NULL,NULL,NULL),(89,'MARTIN','BOUKO',NULL,NULL,NULL),(90,'FABRICE','CANNIZZARO',NULL,NULL,NULL),(91,'TCHETCHENE','X',NULL,NULL,NULL),(92,'BUCK','X',NULL,NULL,NULL),(93,'BRIGITTE','DELGRANGE',NULL,NULL,NULL),(94,'FERNAND','MICHEL',NULL,NULL,NULL),(95,'YVONNE','DION',NULL,NULL,NULL),(96,'VIRGINIE','FLAMENT',NULL,NULL,NULL),(97,'JULIEN','BERTIAUX',NULL,NULL,NULL),(98,'JEAN-BAPTISTE','GLINEUR',NULL,NULL,NULL),(99,'SIMON','FLASSE',NULL,NULL,NULL),(100,'FLORENCE','HANNA',NULL,NULL,NULL),(101,'FLORINE','CROMBOIS',NULL,NULL,NULL),(102,'HELENE','DEHON',NULL,NULL,NULL),(103,'YVES','DRUART',NULL,NULL,NULL),(104,'BENOIT','BRASSEUR',NULL,NULL,NULL),(105,'LOUIS','PAGE',NULL,NULL,NULL),(106,'CLEMENT','DURIEUX',NULL,NULL,NULL),(107,'GAEL','BRONSART',NULL,NULL,NULL),(108,'PHILIPPE','URBAIN',NULL,NULL,NULL),(109,'COLETTE','VAN EYCKE',NULL,NULL,NULL),(110,'FREDDY','ROUX',NULL,NULL,NULL),(111,'THERESE','DORDAIN',NULL,NULL,NULL),(112,'JEAN-LUC','URBAIN',NULL,NULL,NULL),(113,'SYLVIE','HERMAL',NULL,NULL,NULL),(114,'FLAVIE','WASTIEL',NULL,NULL,NULL),(115,'JUSTINE','WASTIEL',NULL,NULL,NULL),(116,'PAULINE','WASTIEL',NULL,NULL,NULL),(117,'CATHERINE','CHANOINE',NULL,NULL,NULL),(118,'ESTELLE','DUBOIS',NULL,NULL,NULL),(119,'JONATHAN','DOMMERY',NULL,NULL,NULL),(120,'MANU','SOHIER',NULL,NULL,NULL),(121,'INGRID','IERONIMO',NULL,NULL,NULL),(122,'MARJORIE','BELOULL',NULL,NULL,NULL),(123,'MAYA','CHANOINE',NULL,NULL,NULL);
/*!40000 ALTER TABLE `Members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Parties`
--

DROP TABLE IF EXISTS `Parties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Parties` (
  `idPartie` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `lieu` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  `idTournoi` int(11) NOT NULL,
  PRIMARY KEY (`idPartie`),
  KEY `fk_Partie_Tournois1` (`idTournoi`),
  CONSTRAINT `fk_Partie_Tournois1` FOREIGN KEY (`idTournoi`) REFERENCES `Tournois` (`idTournoi`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Parties`
--

LOCK TABLES `Parties` WRITE;
/*!40000 ALTER TABLE `Parties` DISABLE KEYS */;
INSERT INTO `Parties` VALUES (1,'2013-10-26','Erquennes',1);
/*!40000 ALTER TABLE `Parties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Scores`
--

DROP TABLE IF EXISTS `Scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Scores` (
  `idScore` int(11) NOT NULL AUTO_INCREMENT,
  `idPartie` int(11) NOT NULL,
  `idMember` int(11) NOT NULL,
  `score` int(11) DEFAULT '0',
  `manche` int(11) DEFAULT NULL,
  PRIMARY KEY (`idScore`),
  KEY `fk_Manche_has_Member_Member1` (`idMember`),
  KEY `fk_Scores_1` (`idPartie`),
  CONSTRAINT `fk_Manche_has_Member_Member1` FOREIGN KEY (`idMember`) REFERENCES `Members` (`idMember`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Scores_1` FOREIGN KEY (`idPartie`) REFERENCES `Parties` (`idPartie`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=650 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Scores`
--

LOCK TABLES `Scores` WRITE;
/*!40000 ALTER TABLE `Scores` DISABLE KEYS */;
INSERT INTO `Scores` VALUES (290,1,1,50,1),(291,1,1,48,2),(292,1,1,47,3),(293,1,1,36,4),(294,1,2,50,1),(295,1,2,48,2),(296,1,2,47,3),(297,1,2,36,4),(298,1,8,43,1),(299,1,8,37,2),(300,1,8,39,3),(301,1,8,0,4),(302,1,9,34,1),(303,1,9,50,2),(304,1,9,0,3),(305,1,9,50,4),(306,1,10,34,1),(307,1,10,50,2),(308,1,10,41,3),(309,1,10,50,4),(310,1,11,48,1),(311,1,11,50,2),(312,1,11,38,3),(313,1,11,24,4),(314,1,12,48,1),(315,1,12,50,2),(316,1,12,38,3),(317,1,12,24,4),(318,1,15,16,1),(319,1,15,18,2),(320,1,15,26,3),(321,1,15,36,4),(322,1,18,0,1),(323,1,18,37,2),(324,1,18,39,3),(325,1,18,39,4),(326,1,19,33,1),(327,1,19,44,2),(328,1,19,29,3),(329,1,19,50,4),(330,1,20,33,1),(331,1,20,44,2),(332,1,20,29,3),(333,1,20,50,4),(334,1,24,49,1),(335,1,24,50,2),(336,1,24,50,3),(337,1,24,50,4),(338,1,26,36,1),(339,1,26,36,2),(340,1,26,25,3),(341,1,26,21,4),(342,1,27,26,1),(343,1,27,28,2),(344,1,27,13,3),(345,1,27,40,4),(346,1,29,50,1),(347,1,29,37,2),(348,1,29,35,3),(349,1,29,31,4),(350,1,30,50,1),(351,1,30,37,2),(352,1,30,35,3),(353,1,30,31,4),(354,1,31,40,1),(355,1,31,50,2),(356,1,31,47,3),(357,1,31,38,4),(358,1,32,40,1),(359,1,32,50,2),(360,1,32,47,3),(361,1,32,38,4),(362,1,33,40,1),(363,1,33,45,2),(364,1,33,40,3),(365,1,33,50,4),(366,1,34,50,1),(367,1,34,30,2),(368,1,34,45,3),(369,1,34,45,4),(370,1,35,50,1),(371,1,35,30,2),(372,1,35,45,3),(373,1,35,45,4),(374,1,37,36,1),(375,1,37,36,2),(376,1,37,25,3),(377,1,37,21,4),(378,1,38,27,1),(379,1,38,36,2),(380,1,38,23,3),(381,1,38,38,4),(382,1,39,27,1),(383,1,39,36,2),(384,1,39,23,3),(385,1,39,38,4),(386,1,40,49,1),(387,1,40,50,2),(388,1,40,50,3),(389,1,40,50,4),(390,1,41,2,1),(391,1,41,39,2),(392,1,41,47,3),(393,1,41,25,4),(394,1,42,0,1),(395,1,42,39,2),(396,1,42,47,3),(397,1,42,25,4),(398,1,46,40,1),(399,1,46,45,2),(400,1,46,40,3),(401,1,46,50,4),(402,1,55,17,1),(403,1,55,16,2),(404,1,55,31,3),(405,1,55,0,4),(406,1,56,6,1),(407,1,56,0,2),(408,1,56,20,3),(409,1,56,17,4),(410,1,58,50,1),(411,1,58,12,2),(412,1,58,14,3),(413,1,58,29,4),(414,1,65,26,1),(415,1,65,28,2),(416,1,65,13,3),(417,1,65,40,4),(418,1,66,34,1),(419,1,66,42,2),(420,1,66,39,3),(421,1,66,48,4),(422,1,67,34,1),(423,1,67,42,2),(424,1,67,39,3),(425,1,67,48,4),(426,1,68,17,1),(427,1,68,17,2),(428,1,68,41,3),(429,1,68,46,4),(430,1,69,17,1),(431,1,69,17,2),(432,1,69,41,3),(433,1,69,46,4),(434,1,70,19,1),(435,1,70,35,2),(436,1,70,29,3),(437,1,70,42,4),(438,1,71,19,1),(439,1,71,35,2),(440,1,71,29,3),(441,1,71,42,4),(442,1,72,29,1),(443,1,72,50,2),(444,1,72,36,3),(445,1,72,46,4),(446,1,73,29,1),(447,1,73,50,2),(448,1,73,36,3),(449,1,73,0,4),(450,1,122,22,1),(451,1,122,33,2),(452,1,122,21,3),(453,1,122,23,4),(454,1,87,24,1),(455,1,87,44,2),(456,1,87,10,3),(457,1,87,38,4),(458,1,97,50,1),(459,1,97,21,2),(460,1,97,39,3),(461,1,97,50,4),(462,1,89,38,1),(463,1,89,41,2),(464,1,89,50,3),(465,1,89,46,4),(466,1,104,38,1),(467,1,104,38,2),(468,1,104,24,3),(469,1,104,39,4),(470,1,107,19,1),(471,1,107,15,2),(472,1,107,25,3),(473,1,107,17,4),(474,1,88,24,1),(475,1,88,44,2),(476,1,88,10,3),(477,1,88,38,4),(478,1,90,38,1),(479,1,90,41,2),(480,1,90,50,3),(481,1,90,46,4),(482,1,74,50,1),(483,1,74,43,2),(484,1,74,50,3),(485,1,74,45,4),(486,1,117,32,1),(487,1,117,27,2),(488,1,117,38,3),(489,1,117,24,4),(490,1,123,6,1),(491,1,123,13,2),(492,1,123,20,3),(493,1,123,17,4),(494,1,101,21,1),(495,1,101,39,2),(496,1,101,38,3),(497,1,101,32,4),(498,1,77,50,1),(499,1,77,42,2),(500,1,77,50,3),(501,1,77,36,4),(502,1,85,48,1),(503,1,85,37,2),(504,1,85,17,3),(505,1,85,39,4),(506,1,102,21,1),(507,1,102,39,2),(508,1,102,38,3),(509,1,102,32,4),(510,1,93,27,1),(511,1,93,50,2),(512,1,93,50,3),(513,1,93,31,4),(514,1,95,20,1),(515,1,95,24,2),(516,1,95,8,3),(517,1,95,36,4),(518,1,119,19,1),(519,1,119,22,2),(520,1,119,2,3),(521,1,119,23,4),(522,1,111,50,1),(523,1,111,12,2),(524,1,111,14,3),(525,1,111,29,4),(526,1,103,38,1),(527,1,103,38,2),(528,1,103,24,3),(529,1,103,39,4),(530,1,118,32,1),(531,1,118,27,2),(532,1,118,38,3),(533,1,118,24,4),(534,1,106,21,1),(535,1,106,33,2),(536,1,106,42,3),(537,1,106,10,4),(538,1,96,20,1),(539,1,96,24,2),(540,1,96,8,3),(541,1,96,36,4),(542,1,99,33,1),(543,1,99,20,2),(544,1,99,27,3),(545,1,99,30,4),(546,1,75,50,1),(547,1,75,43,2),(548,1,75,50,3),(549,1,75,45,4),(550,1,84,50,1),(551,1,84,50,2),(552,1,84,41,3),(553,1,84,50,4),(554,1,98,50,1),(555,1,98,21,2),(556,1,98,39,3),(557,1,98,50,4),(558,1,100,33,1),(559,1,100,20,2),(560,1,100,0,3),(561,1,100,30,4),(562,1,113,0,1),(563,1,113,29,2),(564,1,113,24,3),(565,1,113,15,4),(566,1,76,16,1),(567,1,76,18,2),(568,1,76,26,3),(569,1,76,36,4),(570,1,121,22,1),(571,1,121,33,2),(572,1,121,21,3),(573,1,121,23,4),(574,1,81,22,1),(575,1,81,19,2),(576,1,81,50,3),(577,1,81,50,4),(578,1,82,22,1),(579,1,82,19,2),(580,1,82,50,3),(581,1,82,50,4),(582,1,83,50,1),(583,1,83,50,2),(584,1,83,41,3),(585,1,83,50,4),(586,1,86,48,1),(587,1,86,37,2),(588,1,86,17,3),(589,1,86,39,4),(590,1,94,27,1),(591,1,94,50,2),(592,1,94,50,3),(593,1,94,31,4),(594,1,80,50,1),(595,1,80,50,2),(596,1,80,50,3),(597,1,80,50,4),(598,1,105,21,1),(599,1,105,33,2),(600,1,105,42,3),(601,1,105,10,4),(602,1,79,50,1),(603,1,79,50,2),(604,1,79,50,3),(605,1,79,50,4),(606,1,110,41,1),(607,1,110,55,2),(608,1,110,50,3),(609,1,110,46,4),(610,1,120,0,1),(611,1,120,22,2),(612,1,120,2,3),(613,1,120,23,4),(614,1,78,50,1),(615,1,78,42,2),(616,1,78,50,3),(617,1,78,36,4),(618,1,108,19,1),(619,1,108,15,2),(620,1,108,0,3),(621,1,108,0,4),(622,1,112,27,1),(623,1,112,29,2),(624,1,112,24,3),(625,1,112,15,4),(626,1,109,41,1),(627,1,109,55,2),(628,1,109,50,3),(629,1,109,46,4),(630,1,114,17,1),(631,1,114,16,2),(632,1,114,31,3),(633,1,114,15,4),(634,1,115,26,1),(635,1,115,8,2),(636,1,115,8,3),(637,1,115,32,4),(638,1,116,26,1),(639,1,116,8,2),(640,1,116,8,3),(641,1,116,32,4),(642,1,92,39,1),(643,1,92,37,2),(644,1,92,41,3),(645,1,92,50,4),(646,1,91,39,1),(647,1,91,37,2),(648,1,91,41,3),(649,1,91,50,4);
/*!40000 ALTER TABLE `Scores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `ScoresPartiesview`
--

DROP TABLE IF EXISTS `ScoresPartiesview`;
/*!50001 DROP VIEW IF EXISTS `ScoresPartiesview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ScoresPartiesview` (
  `score` tinyint NOT NULL,
  `idMember` tinyint NOT NULL,
  `idPartie` tinyint NOT NULL,
  `idTournoi` tinyint NOT NULL,
  `prenom` tinyint NOT NULL,
  `nom` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `ScoresTournoiview`
--

DROP TABLE IF EXISTS `ScoresTournoiview`;
/*!50001 DROP VIEW IF EXISTS `ScoresTournoiview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `ScoresTournoiview` (
  `score` tinyint NOT NULL,
  `idMember` tinyint NOT NULL,
  `idPartie` tinyint NOT NULL,
  `idTournoi` tinyint NOT NULL,
  `prenom` tinyint NOT NULL,
  `nom` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `Tournois`
--

DROP TABLE IF EXISTS `Tournois`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tournois` (
  `idTournoi` int(11) NOT NULL AUTO_INCREMENT,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `nom` varchar(45) COLLATE latin1_bin DEFAULT NULL,
  PRIMARY KEY (`idTournoi`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tournois`
--

LOCK TABLES `Tournois` WRITE;
/*!40000 ALTER TABLE `Tournois` DISABLE KEYS */;
INSERT INTO `Tournois` VALUES (1,'2013-10-26','2014-06-21','Tournoi 2013-2014');
/*!40000 ALTER TABLE `Tournois` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_lien_Equipes`
--

DROP TABLE IF EXISTS `groups_lien_Equipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_lien_Equipes` (
  `idGroup` int(11) NOT NULL,
  `idEquipe` int(11) NOT NULL,
  PRIMARY KEY (`idGroup`,`idEquipe`),
  KEY `fk_group_has_Equipes_Equipes1` (`idEquipe`),
  KEY `fk_group_has_Equipes_group1` (`idGroup`),
  CONSTRAINT `fk_group_has_Equipes_Equipes1` FOREIGN KEY (`idEquipe`) REFERENCES `Equipes` (`idEquipe`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_group_has_Equipes_group1` FOREIGN KEY (`idGroup`) REFERENCES `Groups` (`idGroup`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_lien_Equipes`
--

LOCK TABLES `groups_lien_Equipes` WRITE;
/*!40000 ALTER TABLE `groups_lien_Equipes` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups_lien_Equipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `ScoresPartiesview`
--

/*!50001 DROP TABLE IF EXISTS `ScoresPartiesview`*/;
/*!50001 DROP VIEW IF EXISTS `ScoresPartiesview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ScoresPartiesview` AS select sum(`s`.`score`) AS `score`,`s`.`idMember` AS `idMember`,`s`.`idPartie` AS `idPartie`,`p`.`idTournoi` AS `idTournoi`,`m`.`prenom` AS `prenom`,`m`.`nom` AS `nom` from ((`Scores` `s` join `Members` `m`) join `Parties` `p`) where ((`s`.`idPartie` = `p`.`idPartie`) and (`s`.`idMember` = `m`.`idMember`)) group by `s`.`idPartie`,`s`.`idMember` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `ScoresTournoiview`
--

/*!50001 DROP TABLE IF EXISTS `ScoresTournoiview`*/;
/*!50001 DROP VIEW IF EXISTS `ScoresTournoiview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `ScoresTournoiview` AS select sum(`ScoresPartiesview`.`score`) AS `score`,`ScoresPartiesview`.`idMember` AS `idMember`,`ScoresPartiesview`.`idPartie` AS `idPartie`,`ScoresPartiesview`.`idTournoi` AS `idTournoi`,`ScoresPartiesview`.`prenom` AS `prenom`,`ScoresPartiesview`.`nom` AS `nom` from `ScoresPartiesview` group by `ScoresPartiesview`.`idMember`,`ScoresPartiesview`.`idTournoi` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-01-03 21:38:51

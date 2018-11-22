-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: auto_pecas
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (18,'Vendedor');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_p`
--

DROP TABLE IF EXISTS `categoria_p`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categoria_p` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_p`
--

LOCK TABLES `categoria_p` WRITE;
/*!40000 ALTER TABLE `categoria_p` DISABLE KEYS */;
INSERT INTO `categoria_p` VALUES (14,'DDSD');
/*!40000 ALTER TABLE `categoria_p` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientef`
--

DROP TABLE IF EXISTS `clientef`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientef` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `endereco` varchar(70) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientef`
--

LOCK TABLES `clientef` WRITE;
/*!40000 ALTER TABLE `clientef` DISABLE KEYS */;
INSERT INTO `clientef` VALUES (52,'sad','126.293.136-38','(43)43433-4343','3433434','asd','sad','AM');
/*!40000 ALTER TABLE `clientef` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientej`
--

DROP TABLE IF EXISTS `clientej`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `clientej` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `razao` varchar(70) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `endereco` varchar(70) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cnpj` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientej`
--

LOCK TABLES `clientej` WRITE;
/*!40000 ALTER TABLE `clientej` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientej` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedor_p`
--

DROP TABLE IF EXISTS `fornecedor_p`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `fornecedor_p` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `end` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `estado` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor_p`
--

LOCK TABLES `fornecedor_p` WRITE;
/*!40000 ALTER TABLE `fornecedor_p` DISABLE KEYS */;
INSERT INTO `fornecedor_p` VALUES (11,'SAD','DSA','(43)44343-4343','AL');
/*!40000 ALTER TABLE `fornecedor_p` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) DEFAULT NULL,
  `rg` varchar(45) DEFAULT NULL,
  `cpf` varchar(45) DEFAULT NULL,
  `senha` varchar(4) DEFAULT NULL,
  `celular` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `admin` varchar(45) DEFAULT NULL,
  `cargo_id` int(11) NOT NULL,
  `endereco` varchar(70) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cargo_id_idx` (`cargo_id`),
  CONSTRAINT `fk_cargo_id` FOREIGN KEY (`cargo_id`) REFERENCES `cargo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_vendaf`
--

DROP TABLE IF EXISTS `itens_vendaf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `itens_vendaf` (
  `id` int(11) NOT NULL,
  `id_vendaf` int(11) NOT NULL,
  `id_prod` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `preco_prod` float DEFAULT NULL,
  `total_prod` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_vendaf_idx` (`id_vendaf`),
  KEY `fk_produto_idx` (`id_prod`),
  CONSTRAINT `fk_produto` FOREIGN KEY (`id_prod`) REFERENCES `produto` (`id`),
  CONSTRAINT `fk_vendaf` FOREIGN KEY (`id_vendaf`) REFERENCES `vendasf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_vendaf`
--

LOCK TABLES `itens_vendaf` WRITE;
/*!40000 ALTER TABLE `itens_vendaf` DISABLE KEYS */;
/*!40000 ALTER TABLE `itens_vendaf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) DEFAULT NULL,
  `categoria_id` int(11) NOT NULL,
  `fornecedor_id` int(11) NOT NULL,
  `pc_compra` float DEFAULT NULL,
  `pc_venda` float DEFAULT NULL,
  `qntd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fornecedor_id_idx` (`fornecedor_id`),
  KEY `fk_categoria_p_idx` (`categoria_id`),
  CONSTRAINT `fk_categoria_p` FOREIGN KEY (`categoria_id`) REFERENCES `categoria_p` (`id`),
  CONSTRAINT `fk_fornecedor_p` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor_p` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (24,'12313',14,11,135,135,2);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendasf`
--

DROP TABLE IF EXISTS `vendasf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendasf` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_id` int(11) NOT NULL,
  `funcionario_id` int(11) NOT NULL,
  `horario` datetime DEFAULT NULL,
  `valortotalv` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_venda_funcionario_idx` (`funcionario_id`),
  KEY `fk_venda_cliente1_idx` (`cliente_id`),
  CONSTRAINT `fk_venda_cliente1` FOREIGN KEY (`cliente_id`) REFERENCES `clientef` (`id`),
  CONSTRAINT `fk_venda_funcionario` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendasf`
--

LOCK TABLES `vendasf` WRITE;
/*!40000 ALTER TABLE `vendasf` DISABLE KEYS */;
/*!40000 ALTER TABLE `vendasf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-22 18:06:18

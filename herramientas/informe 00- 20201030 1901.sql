USE dbtps;

--
-- Definition of table `informe_00`
--

DROP TABLE IF EXISTS `informe_00`;
CREATE TABLE `informe_00` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `variable` varchar(50) COLLATE latin1_general_ci DEFAULT NULL,
  `importe` double DEFAULT '0',
  `cantidad` double DEFAULT '0',
  `tipo` varchar(3) COLLATE latin1_general_ci DEFAULT NULL,
  `codigo` int(11) DEFAULT NULL,
  `cajero` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Dumping data for table `informe_00`
--

/*!40000 ALTER TABLE `informe_00` DISABLE KEYS */;
INSERT INTO `informe_00` (`id`,`variable`,`importe`,`cantidad`,`tipo`,`codigo`,`cajero`) VALUES 
 (1,'ingresos_ventas_1',150245.35,50,'M',1,5),
 (2,'ingresos_ventas_2',15540.939,8,'M',2,5),
 (3,'ingresos_ventas_3',4238.686,1,'M',3,5),
 (4,'ingresos_ventas_4',1270.15,1,'M',4,5),
 (8,'ingresos_fondos_fijos',1000,1,'FF',NULL,5),
 (9,'egresos_retiros_1',11000,1,'R',1,5),
 (10,'egresos_retiros_ff',1000,1,'RFF',NULL,5),
 (11,'egresos_pagos',0,0,'PA',NULL,5),
 (12,'egresos_vueltos',0,0,'VU',NULL,5),
 (13,'infromacion_anulaciones',-1412.9,1,'AN',NULL,5),
 (14,'informacion_tickets_anulados',0,25,'TC',NULL,5),
 (15,'informacion_nro_tickets',0,59,'NT',NULL,5),
 (16,'informacion_facturas_a',0,0,'FA',NULL,5),
 (17,'informacion_facturas_b',165089.459,56,'FB',NULL,5),
 (18,'informacion_facturas_elect_a',1966.98,2,'FEA',NULL,5),
 (19,'informacion_facturas_elect_b',4238.686,1,'FEB',NULL,5),
 (20,'informacion_nc_elect_a',0,0,'NEA',NULL,5),
 (21,'informacion_nc_elect_b',0,0,'NEB',NULL,5),
 (22,'informacion_nc_a',0,0,'NCA',NULL,5),
 (23,'informacion_nc_b',0,0,'NCB',NULL,5);

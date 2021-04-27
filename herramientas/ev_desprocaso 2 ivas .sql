
USE dbtps;

--
DROP TABLE IF EXISTS `ev_despro`;
CREATE TABLE `ev_despro` (
  `STATUS` tinyint(4) default NULL,
  `ID_EVENTO` int(11) default NULL,
  `POSICION` smallint(6) default NULL,
  `TIPO_MOTIVO` tinyint(4) default NULL,
  `ID_MOTIVO` smallint(6) default NULL,
  `IMPORTE` double default NULL,
  `CODIGO_BARRA` varchar(16) default NULL,
  `COD_ARTICULO` int(11) default NULL,
  `COD_SUCURSAL` smallint(6) default NULL,
  `FECHA_TICKET` smallint(6) default NULL,
  `CAJA_Z` int(11) default NULL,
  `CAJA` smallint(6) default NULL,
  `NRO_Z` int(11) default NULL,
  `NO_ES_DESCUENTO` char(1) default NULL,
  `IVA` double default NULL,
  `FIN` tinyint(4) default NULL,
  KEY `x` (`ID_EVENTO`,`TIPO_MOTIVO`,`ID_MOTIVO`,`POSICION`),
  KEY `principal` (`ID_EVENTO`,`POSICION`,`TIPO_MOTIVO`,`ID_MOTIVO`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ev_despro`
--


INSERT INTO `ev_despro` (`STATUS`,`ID_EVENTO`,`POSICION`,`TIPO_MOTIVO`,`ID_MOTIVO`,`IMPORTE`,`CODIGO_BARRA`,`COD_ARTICULO`,`COD_SUCURSAL`,`FECHA_TICKET`,`CAJA_Z`,`CAJA`,`NRO_Z`,`NO_ES_DESCUENTO`,`IVA`,`FIN`) VALUES 
 (0,9,1,3,2,10899.3374693151,'0000000000659',659,2,21141,300041,3,41,'\0',1144.43043427809,0),
 (0,9,2,3,3,435.145523322941,'7790400030358',874,2,21141,300041,3,41,'\0',91.3805598978176,0),
 (0,9,3,3,4,68.9471183355416,'0000000002962',2962,2,21141,300041,3,41,'\0',14.4788948504637,0);



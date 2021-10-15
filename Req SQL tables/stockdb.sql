CREATE DATABASE `stock` ;

CREATE TABLE `customer` (
  `idcustomer` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phoneno` varchar(255) NOT NULL,
  PRIMARY KEY (`idcustomer`),
  UNIQUE KEY `idcustomer_UNIQUE` (`idcustomer`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order` (
  `orderid` int NOT NULL AUTO_INCREMENT,
  `quantity` int NOT NULL,
  `item` varchar(255) NOT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `stock`.`product` (
  `productid` INT NOT NULL,
  `productname` VARCHAR(255) NOT NULL,
  `price` INT NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`productid`)
  ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

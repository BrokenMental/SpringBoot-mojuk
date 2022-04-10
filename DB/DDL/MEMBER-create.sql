-- MOJUK.`MEMBER` definition

CREATE TABLE `MEMBER` (
    `IDX` int(11) NOT NULL AUTO_INCREMENT,
    `NAME` varchar(20) DEFAULT NULL,
    `CONTACT` varchar(15) DEFAULT NULL,
    `EMAIL` varchar(50) DEFAULT NULL,
    `ENTRANCENUMBER` varchar(10) DEFAULT NULL,
    `BIRTHDAY` date DEFAULT '1900-01-01',
    `SEX` tinyint(4) DEFAULT 0,
    `REGDT` datetime DEFAULT current_timestamp(),
    PRIMARY KEY (`IDX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
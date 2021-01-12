-- -----------------------
-- DB create -------------
-- -----------------------

CREATE database if NOT EXISTS `db1` default character set utf8mb4 collate utf8mb4_unicode_ci;

CREATE database if NOT EXISTS `db2` default character set utf8mb4 collate utf8mb4_unicode_ci;

use db1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
                       `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
                       `content` varchar(32) DEFAULT NULL COMMENT '日志内容',
                       `des` varchar(32) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES (1, 'db1-log', 'db1-log-desc');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


use db2;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
                       `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
                       `content` varchar(32) DEFAULT NULL COMMENT '日志内容',
                       `des` varchar(32) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` VALUES (1, 'db2-log', 'db2-log-desc');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;


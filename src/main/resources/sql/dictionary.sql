/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : faceverify2.0

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 15/08/2019 10:22:07
*/

SET NAMES utf8mb4;
set autocommit = 0;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xdc_t_dictionary
-- ----------------------------
# DROP TABLE IF EXISTS `xdc_t_dictionary`;
CREATE TABLE IF NOT EXISTS `xdc_t_dictionary`  (
  `id` bigint(20) NOT NULL,
  `dictionary_chinese` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dictionary_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `meaning` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `numerical` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xdc_t_dictionary
-- ----------------------------
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (1, '单号规则', 'oddRule', '时间年月日时分秒毫秒', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (2, '单号规则', 'oddRule', '随机', 2);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (3, '单号规则', 'oddRule', '自增长', 3);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (4, '单号规则', 'oddRule', '时间年月日+自增长', 4);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (5, '节点类型', 'nodeType', '开始节点', 0);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (6, '节点类型', 'nodeType', '结束节点', -1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (7, '节点类型', 'nodeType', '一般节点', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (8, '节点类型', 'nodeType', '会签节点', 2);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (9, '节点类型', 'nodeType', '条件判断节点', 3);
# INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (10, '节点类型', 'nodeType', '查阅节点', 4);
# INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (11, '节点类型', 'nodeType', '子流程节点', 5);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (12, '条件分发', 'ConditionsDistribution', '是', 1);
INSERT IGNORE INTO `xdc_t_dictionary` (`id`, `dictionary_chinese`, `dictionary_class`, `meaning`, `numerical`) VALUES (13, '节点类型', 'ConditionsDistribution', '否', 2);











commit;
set autocommit = 1;
SET FOREIGN_KEY_CHECKS = 1;

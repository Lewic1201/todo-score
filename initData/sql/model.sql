CREATE TABLE `option` (
`id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
`sort_id` int(11) NULL DEFAULT NULL COMMENT '顺序标志',
`content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`score` int(11) NULL DEFAULT NULL,
`subject_id` int(11) NULL DEFAULT NULL,
`status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) ,
INDEX `fk_option_id_subject_id` (`subject_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 9
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `score_statistics` (
`id` int(11) NOT NULL,
`user_id` int(11) NULL DEFAULT NULL,
`scores` int(255) NULL DEFAULT NULL,
`create_time` datetime NULL DEFAULT NULL,
PRIMARY KEY (`id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 0
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `subject` (
`id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT COMMENT '任务编号',
`content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
`status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务状态',
`cycle_type` int(255) NULL DEFAULT NULL COMMENT '循环周期类型\r\n1.每天;\r\n2.每周;\r\n3.每月;\r\n4.每年;\r\n5.工作日;\r\n6.非工作日;',
`super_id` int(11) NULL DEFAULT NULL COMMENT '该主题的父主题',
`description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`expiration_time` datetime NULL DEFAULT NULL,
`create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`delete_time` datetime NULL DEFAULT NULL,
PRIMARY KEY (`id`) ,
INDEX `fk_subject_super_id_id` (`super_id` ASC) USING BTREE
)
ENGINE = InnoDB
AUTO_INCREMENT = 11
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `user` (
`id` int(11) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,
`username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`role` int(1) NULL DEFAULT NULL,
`status` int(1) NULL DEFAULT NULL,
`create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 2
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `user_info` (
`id` int(11) NOT NULL,
`name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
`sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
`signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
`create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 0
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `workday` (
`id` int(5) NOT NULL AUTO_INCREMENT,
`type` int(2) NULL DEFAULT NULL COMMENT '类型',
`day` date NULL DEFAULT NULL,
`is_workday` tinyint(1) NULL DEFAULT NULL,
`remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
PRIMARY KEY (`id`) 
)
ENGINE = InnoDB
AUTO_INCREMENT = 34
AVG_ROW_LENGTH = 0
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
KEY_BLOCK_SIZE = 0
MAX_ROWS = 0
MIN_ROWS = 0
ROW_FORMAT = Dynamic;
CREATE TABLE `task` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`content` varchar(255) NULL,
`score` varchar(255) NULL,
`status` int(1) NULL,
`description` varchar(255) NULL,
`cycle_type_id` int(11) NULL,
`create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
`update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`id`) 
);
CREATE TABLE `cycle_type` (
`id` int(11) NOT NULL,
`start_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`times` int(11) NULL,
`end_time` datetime NULL ON UPDATE CURRENT_TIMESTAMP,
`workday` int(1) NOT NULL COMMENT '0不设置;1跳过节假日;2跳过工作日',
`cron_expression` varchar(32) NULL,
PRIMARY KEY (`id`) 
);

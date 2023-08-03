/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : spring_security_jdbc

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 10/07/2023 17:06:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
INSERT INTO `persistent_logins` (`username`, `series`, `token`, `last_used`) VALUES ('admin', 'Pb3JKOzcb6qFl20phTBPSg==', 'PnjhoIeML7wib8/4UvN5Bg==', '2023-05-12 05:34:25');
COMMIT;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '权限名称',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请求地址',
  `method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求方法',
  `description` varchar(200) DEFAULT NULL COMMENT '备注',
  `status` int DEFAULT '1' COMMENT '状态 1 启用0 禁用',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '软删除',
  `created_by` varchar(36) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `updated_by` varchar(36) DEFAULT NULL COMMENT '最后更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '最后更新时间戳',
  PRIMARY KEY (`id`,`created_by`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (1, '查看角色', 'role:detail', '/api/role/{id:\\d+}', 'GET', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (2, '角色列表', 'role:page', '/api/role/page', 'GET', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (3, '新增角色', 'role:add', '/api/role', 'POST', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (4, '修改角色', 'role:update', '/api/role', 'PUT', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (5, '设置权限', 'role:setPermission', '/api/role/setPermission', 'PUT', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (6, '删除角色', 'role:delete', '/api/role', 'DELETE', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (7, '查看用户', 'user:detail', '/api/user/{id:\\d+}', 'GET', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (8, '用户列表', 'user:page', '/api/user/page', 'GET', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (9, '新增用户', 'user:add', '/api/user', 'POST', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (10, '修改用户', 'user:update', '/api/user', 'PUT', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (11, '设置角色', 'user:setRole', '/api/user/setRole', 'PUT', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (12, '修改密码', 'user:updatePassword', '/api/user/updatePassword', 'PUT', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
INSERT INTO `tb_permission` (`id`, `name`, `perms`, `url`, `method`, `description`, `status`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (13, '删除用户', 'user:delete', '/api/user', 'DELETE', NULL, 1, b'0', '0', '2023-07-10 17:02:45', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色类型',
  `description` varchar(200) DEFAULT NULL COMMENT '备注',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '软删除',
  `created_by` varchar(36) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `updated_by` varchar(36) DEFAULT NULL COMMENT '最后更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '最后更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` (`id`, `name`, `type`, `description`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (1, 'admin', 'SUPER_ADMIN', '超级管理员', b'0', 'system', '2023-05-11 22:51:54', 'system', '2023-05-11 22:54:46');
INSERT INTO `tb_role` (`id`, `name`, `type`, `description`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (2, 'sale', 'BUSINESS', '销售', b'0', 'system', '2023-05-11 22:51:54', 'system', '2023-05-11 22:54:46');
COMMIT;

-- ----------------------------
-- Table structure for tb_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_permission`;
CREATE TABLE `tb_role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_id` bigint NOT NULL COMMENT '角色 ID',
  `permission_id` bigint NOT NULL COMMENT '权限 ID',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '软删除',
  `created_by` varchar(36) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `updated_by` varchar(36) DEFAULT NULL COMMENT '最后更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '最后更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色权限表';

-- ----------------------------
-- Records of tb_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (1, 1, 1, b'0', '0', '2023-05-12 13:41:46', NULL, NULL);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (2, 1, 2, b'0', '0', '2023-07-10 10:55:27', NULL, NULL);
INSERT INTO `tb_role_permission` (`id`, `role_id`, `permission_id`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (3, 1, 3, b'0', '0', '2023-07-10 10:55:42', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `phone` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '软删除',
  `created_by` varchar(36) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `updated_by` varchar(36) DEFAULT NULL COMMENT '最后更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '最后更新时间戳',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`id`, `username`, `password`, `phone`, `email`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (1, 'admin', '$2a$10$JXwxyllXqteRptsmDUHMZ.QRfC.gbclr8XL6yRY2yI7yXa9B40ji.', '13800000002', 'admin@163.com', b'0', 'system', '2023-05-04 22:55:49', 'admin', '2023-05-05 02:30:46');
INSERT INTO `tb_user` (`id`, `username`, `password`, `phone`, `email`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (2, 'chen', '$2a$10$Lyzb/G06sE7GICFSLu1KQu7ryYoPOWHupXYUWh3fb.SzjUT7GxRKq', '13800000001', 'admin@163.com1', b'0', 'system', '2023-05-05 02:36:27', 'admin', '2023-05-05 02:38:45');
COMMIT;

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint NOT NULL COMMENT '用户 ID',
  `role_id` bigint NOT NULL COMMENT '角色 ID',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '软删除',
  `created_by` varchar(36) NOT NULL COMMENT '创建人',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间戳',
  `updated_by` varchar(36) DEFAULT NULL COMMENT '最后更新人',
  `updated_date` datetime DEFAULT NULL COMMENT '最后更新时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (1, 1, 1, b'0', 'admin', '2023-05-05 02:49:13', NULL, NULL);
INSERT INTO `tb_user_role` (`id`, `user_id`, `role_id`, `deleted`, `created_by`, `created_date`, `updated_by`, `updated_date`) VALUES (2, 2, 2, b'0', 'admin', '2023-05-05 02:49:13', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

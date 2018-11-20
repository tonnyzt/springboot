
DROP TABLE IF EXISTS `risk_control_service_system_cfg`;
CREATE TABLE `risk_control_service_system_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) NOT NULL COMMENT '配置的键',
  `value` varchar(1000) NOT NULL COMMENT '配置的值',
  `remark` varchar(200) NOT NULL COMMENT '配置的备注',
  `add_time` bigint(20) NOT NULL DEFAULT '0',
  `update_time` bigint(20) NOT NULL DEFAULT '0',
  `add_user` bigint(20) NOT NULL DEFAULT '0',
  `update_user` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

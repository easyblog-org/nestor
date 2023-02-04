#系统SQL文件，每个版本的SQL变更建议都写在对应版本SQL文件中，SQL文件命名建议：项目名称-{版本}，比如easyboot-cli-V1

CREATE TABLE IF NOT EXISTS `message_template`
(
    `id`               bigint(20)    NOT NULL AUTO_INCREMENT,
    `template_code`    varchar(36) COMMENT '工单ID',
    `name`             varchar(100)  NOT NULL DEFAULT '' COMMENT '标题',
    `msg_status`       tinyint(4)    NOT NULL DEFAULT '0' COMMENT '当前消息状态：1.新建 2.停用 3.启用 4.等待发送 5.发送中 6.发送成功 7.发送失败',
    `expect_push_time` varchar(100) COMMENT '期望发送时间：0:立即发送 1:定时任务 2:周期任务:cron表达式',
    `id_type`          tinyint(4)    NOT NULL DEFAULT '0' COMMENT '消息的发送ID类型：1. userId 2.手机号 3.openId 4.email 5.企业微信userId',
    `send_channel`     tinyint(4)    NOT NULL DEFAULT '0' COMMENT '消息发送渠道：1.SMS 2.IM 3.Email 4.Email 5.公众号',
    `msg_type`         tinyint(4)    NOT NULL DEFAULT '0' COMMENT '1.通知类消息 2.营销类消息 3.验证码类消息',
    `shield_type`      tinyint(4)    NOT NULL DEFAULT '0' COMMENT '1.夜间不屏蔽 2.夜间屏蔽 3.夜间屏蔽(次日早上9点发送)',
    `msg_content`      varchar(4096) NOT NULL DEFAULT '' COMMENT '消息内容 占位符用$.var表示',
    `deleted`          bit           NOT NULL DEFAULT 0 COMMENT '是否删除：0.不删除 1.删除',
    `create_time`      timestamp     NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`      timestamp     NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_channel` (`send_channel`),
    KEY `idx_tc` (`template_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息模板信息';

CREATE TABLE IF NOT EXISTS  `business_message_record`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT,
    `business_id`      varchar(36)  NOT NULL DEFAULT 0 COMMENT '业务id',
    `business_module`  varchar(128) NOT NULL DEFAULT '' COMMENT '业务模块',
    `business_event`   varchar(36)  NOT NULL DEFAULT 'DEFAULT_EVENT' COMMENT '业务消息事件',
    `business_message` json         NOT NULL COMMENT '业务消息',
    `status`           tinyint(4)   NOT NULL DEFAULT 0 COMMENT '消息发送状态：-1 发送失败；0 待发送；1 发送成功；2 时间注册',
    `retry_times`      int          NOT NULL DEFAULT 0 COMMENT '消息发送重试次数',
    `fail_reason`      varchar(256) NOT NULL DEFAULT '' COMMENT '消息发送失败原因',
    `deleted`          bit          NOT NULL DEFAULT 0 COMMENT '是否删除：0.不删除 1.删除',
    `create_time`      timestamp    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`      timestamp    NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_event` (`business_event`),
    KEY `business_module` (`business_module`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息通知表';

CREATE TABLE IF NOT EXISTS  `message_config_rule`
(
    `id`              bigint(20)   NOT NULL AUTO_INCREMENT,
    `business_module` varchar(128) NOT NULL DEFAULT '' COMMENT '业务模块',
    `business_event`  varchar(36)  NOT NULL DEFAULT 'DEFAULT_EVENT' COMMENT '业务消息事件',
    `template_code`   varchar(36)  NOT NULL DEFAULT '' COMMENT '模板Code',
    `group`           varchar(36)  NOT NULL DEFAULT '' COMMENT '消息分组',
    `priority`        int          NOT NULL DEFAULT 0 COMMENT '优先级',
    `channel`         tinyint(4)   NOT NULL DEFAULT 1 COMMENT '消息发送渠道：1 SMS 2 邮箱 3 微信',
    `config_ids`      varchar(256) NOT NULL DEFAULT 1 COMMENT '消息发送参数配置',
    `deleted`         bit          NOT NULL DEFAULT 0 COMMENT '是否删除：0.不删除 1.删除',
    `create_time`     timestamp    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`     timestamp    NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_event` (`business_event`),
    KEY `business_module` (`business_module`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息通知规则表';

CREATE TABLE IF NOT EXISTS  `message_config`
(
    `id`                       bigint(20)  NOT NULL AUTO_INCREMENT,
    `code`                     varchar(8)  NOT NULL DEFAULT 0 COMMENT 'code',
    `type`                     varchar(36) NOT NULL DEFAULT '' COMMENT '参数类型：receiver/content ',
    `name`                     varchar(64) NOT NULL DEFAULT '' COMMENT '模板参数名',
    `template_value_config_id` varchar(64) NOT NULL DEFAULT '' COMMENT '模板参数取值方式配置',
    `deleted`                  bit         NOT NULL DEFAULT 0 COMMENT '是否删除：0.不删除 1.删除',
    `create_time`              timestamp   NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time`              timestamp   NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_code` (`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='消息通知参数配置';

CREATE TABLE IF NOT EXISTS  `template_value_config`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT,
    `type`        tinyint(4)   NOT NULL DEFAULT 1 COMMENT '取值方式：1.直接值；2 json 直接值（需解析）；3 接口直接值；4 接口 json直接值（需解析）',
    `expression`  varchar(256) NOT NULL DEFAULT '' COMMENT '取值表达式',
    `url`         varchar(256) NOT NULL DEFAULT '' COMMENT '模板参数取值方式：1 直接值；',
    `deleted`     bit          NOT NULL DEFAULT 0 COMMENT '是否删除：0.不删除 1.删除',
    `create_time` timestamp    NOT NULL DEFAULT current_timestamp COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT current_timestamp on update current_timestamp COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='模板参数取值方式配置';
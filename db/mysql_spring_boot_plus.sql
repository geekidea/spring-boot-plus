-- ----------------------------
-- Table structure for foo_bar
-- ----------------------------
DROP TABLE IF EXISTS foo_bar;
CREATE TABLE foo_bar
(
    id          bigint(0)   NOT NULL COMMENT '主键',
    name        varchar(20) NOT NULL COMMENT '名称',
    foo         varchar(100)         DEFAULT NULL COMMENT 'Foo',
    bar         varchar(100)         DEFAULT NULL COMMENT 'Bar',
    remark      varchar(200)         DEFAULT NULL COMMENT '备注',
    status      tinyint(1)  NOT NULL DEFAULT 1 COMMENT '状态，0：禁用，1：启用',
    create_id   bigint(0)            DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)            DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = 'FooBar'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of foo_bar
-- ----------------------------
INSERT INTO foo_bar
VALUES (1, 'FooBar', 'Foo', 'Bar', NULL, 1, 1, '2023-07-01 21:01:10', NULL, NULL);

-- ----------------------------
-- Table structure for generator_column
-- ----------------------------
DROP TABLE IF EXISTS generator_column;
CREATE TABLE generator_column
(
    id                    bigint(0)    NOT NULL COMMENT '主键',
    table_name            varchar(200) NOT NULL COMMENT '表名称',
    column_name           varchar(200) NOT NULL COMMENT '字段名称',
    column_comment        varchar(200)          DEFAULT NULL COMMENT '字段注释',
    column_custom_comment varchar(200)          DEFAULT NULL COMMENT '自定义注释',
    data_type             varchar(100)          DEFAULT NULL COMMENT '数据类型',
    column_type           varchar(100)          DEFAULT NULL COMMENT '字段类型',
    column_length         int(0)                DEFAULT NULL COMMENT '列长度',
    exists_length         tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否存在长度',
    column_sort           int(0)       NOT NULL DEFAULT 0 COMMENT '列顺序',
    property_name         varchar(200) NOT NULL COMMENT '字段名称',
    property_type         varchar(100)          DEFAULT NULL COMMENT '属性类型',
    is_pk                 tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否主键',
    is_required           tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否必填',
    is_default_value      tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否有默认值 1：有，0：无',
    is_validate           tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否校验 0：不校验，1：校验',
    is_form               tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否表单字段',
    is_list               tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否列表字段',
    is_query              tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否条件查询',
    query_type            tinyint(0)            DEFAULT NULL COMMENT '查询方式 1：等于，2：不等于，3：大于，4：大于等于，5：小于，6：小于等于，7：like，8：日期范围，9：日期时间范围',
    form_type             tinyint(0)            DEFAULT NULL COMMENT '表单类型 1：单行文本，2：多行文本，3：数字框，4：单选框，5：复选框，6：下拉框，7：日期，8：日期时间，9：时间，10：文件上传，11：富文本',
    dict_type             varchar(200)          DEFAULT NULL COMMENT '字典类型编码',
    option_json           varchar(1000)         DEFAULT NULL COMMENT '枚举类型json',
    create_id             bigint(0)             DEFAULT NULL COMMENT '创建人ID',
    create_time           datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id             bigint(0)             DEFAULT NULL COMMENT '修改人ID',
    update_time           datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '生成代码列'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for generator_table
-- ----------------------------
DROP TABLE IF EXISTS generator_table;
CREATE TABLE generator_table
(
    id                        bigint(0)    NOT NULL COMMENT '主键',
    table_name                varchar(200) NOT NULL COMMENT '表名称',
    table_comment             varchar(200)          DEFAULT NULL COMMENT '表注释',
    class_name                varchar(200)          DEFAULT NULL COMMENT '类名称',
    module_name               varchar(100)          DEFAULT NULL COMMENT '模块名称',
    package_name              varchar(200)          DEFAULT NULL COMMENT '包名称',
    author                    varchar(100)          DEFAULT NULL COMMENT '作者',
    id_type                   varchar(32)           DEFAULT NULL COMMENT '生成ID类型枚举',
    generator_backend         tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否生成后端 1：是，0：否',
    generator_app_backend     tinyint(1)            DEFAULT 0 COMMENT '是否生成App后端代码 1：是，0：否',
    generator_frontend        tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否生成前端 1：是，0：否',
    validate_field            tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否校验字段 1：是，0：否',
    enable_log                tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否启用日志注解  1：是，0：否',
    enable_permission         tinyint(1)   NOT NULL DEFAULT 1 COMMENT '是否启用权限编码 1：是，0：否',
    request_mapping_style     int(0)       NOT NULL DEFAULT 1 COMMENT '请求映射风格 1：默认，2：restful，3：全部小写，4：中横线，5：下划线',
    default_order_column_name varchar(100)          DEFAULT NULL COMMENT '默认排序列名称',
    parent_menu_id            bigint(0)             DEFAULT NULL COMMENT '上级菜单ID',
    form_layout               tinyint(0)   NOT NULL DEFAULT 2 COMMENT '表单布局方式 1：一行一列，2：一行两列',
    generator_type            tinyint(0)   NOT NULL DEFAULT 1 COMMENT '生成方式 1：zip压缩包，2：自定义路径',
    custom_backend_path       varchar(300)          DEFAULT NULL COMMENT '自定义生成后端路径',
    custom_frontend_path      varchar(300)          DEFAULT NULL COMMENT '自定义生成前端路径',
    show_default_query        tinyint(1)            DEFAULT NULL COMMENT '是否显示默认查询条件 1：是，0：否',
    only_generator_entity     tinyint(1)            DEFAULT NULL COMMENT '是否只生成实体类 1：是，0：否',
    create_id                 bigint(0)             DEFAULT NULL COMMENT '创建人ID',
    create_time               datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id                 bigint(0)             DEFAULT NULL COMMENT '修改人ID',
    update_time               datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX generator_table_name (table_name) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '生成代码表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config
(
    id           bigint(0)    NOT NULL COMMENT '主键',
    config_name  varchar(100) NOT NULL COMMENT '配置名称',
    config_key   varchar(100) NOT NULL COMMENT '配置key',
    config_value varchar(200) NOT NULL COMMENT '配置值',
    is_system    tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否是系统字典类型',
    status       tinyint(1)   NOT NULL DEFAULT 1 COMMENT '状态 1：正常，0：禁用',
    remark       varchar(200)          DEFAULT NULL COMMENT '备注',
    create_id    bigint(0)             DEFAULT NULL COMMENT '创建人ID',
    update_id    bigint(0)             DEFAULT NULL COMMENT '修改人ID',
    create_time  datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time  datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX sys_config_config_key_uindex (config_key) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统配置'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS sys_dict;
CREATE TABLE sys_dict
(
    id          bigint(0)    NOT NULL COMMENT '主键',
    value       varchar(200) NOT NULL COMMENT '字典值',
    label       varchar(100) NOT NULL COMMENT '字典名称',
    dict_code   varchar(100) NOT NULL COMMENT '字典类型code',
    status      tinyint(1)   NOT NULL DEFAULT 1 COMMENT '状态 1：启用，0：禁用',
    sort        int(0)       NOT NULL DEFAULT 0 COMMENT '排序值',
    remark      varchar(200)          DEFAULT NULL COMMENT '备注',
    create_id   bigint(0)             DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)             DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX sys_dict_dict_code_value_uindex (dict_code, value) USING BTREE,
    INDEX sys_dict_dict_type_index (dict_code) USING BTREE,
    INDEX sys_dict_label_index (label) USING BTREE,
    INDEX sys_dict_value_index (value) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典数据'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO sys_dict
VALUES (489964068171781, '1', '1', '489941097336837', 1, 1, '1', 1, '2023-12-05 14:08:55', NULL, NULL);
INSERT INTO sys_dict
VALUES (489964253274117, '2', '2', '489941097336837', 1, 2, '2', 1, '2023-12-05 14:09:41', NULL, NULL);
INSERT INTO sys_dict
VALUES (489964289384453, '3', '3', '489941097336837', 1, 3, '3', 1, '2023-12-05 14:09:49', NULL, NULL);
INSERT INTO sys_dict
VALUES (489964579540997, '4', '4', '489941097336837', 1, 4, '4', 1, '2023-12-05 14:11:00', NULL, NULL);
INSERT INTO sys_dict
VALUES (489964663173125, '5', '5', '489941097336837', 1, 5, '5', 1, '2023-12-05 14:11:21', NULL, NULL);
INSERT INTO sys_dict
VALUES (489964722814981, '6', '6', '489941097336837', 1, 6, '6', 1, '2023-12-05 14:11:35', NULL, NULL);
INSERT INTO sys_dict
VALUES (489965510180869, '7', '7', '489941097336837', 1, 7, '7', 1, '2023-12-05 14:14:47', NULL, NULL);
INSERT INTO sys_dict
VALUES (489965622493189, '8', '8', '489941097336837', 1, 8, '8', 1, '2023-12-05 14:15:15', NULL, NULL);
INSERT INTO sys_dict
VALUES (489965709664261, '9', '9', '489941097336837', 1, 9, '9', 1, '2023-12-05 14:15:36', NULL, NULL);
INSERT INTO sys_dict
VALUES (489965730222085, '102', '102', '489941097336837', 0, 102, '102', 1, '2023-12-05 14:15:41', NULL,
        '2023-12-05 14:48:20');
INSERT INTO sys_dict
VALUES (490997344141317, '1', '1', '1', 1, 2, '1', 1, '2023-12-08 12:13:20', NULL, NULL);
INSERT INTO sys_dict
VALUES (491478883053573, 'zfb', '支付宝', '491478519701509', 1, 111, '11111111111111', 1, '2023-12-09 20:52:43', NULL,
        NULL);
INSERT INTO sys_dict
VALUES (492217300738053, 'test', '测试', 'test', 1, 0, 'cs', 1, '2023-12-11 22:57:21', 1, '2024-01-12 23:16:21');
INSERT INTO sys_dict
VALUES (498426711265285, 'test1', '测试1', 'test_dict_type', 1, 0, NULL, 1, '2023-12-29 12:03:31', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS sys_dict_type;
CREATE TABLE sys_dict_type
(
    id          bigint(0)    NOT NULL COMMENT '主键',
    code        varchar(100) NOT NULL COMMENT '字典类型编码',
    name        varchar(100) NOT NULL COMMENT '字典类型名称',
    is_system   tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否是系统字典类型',
    remark      varchar(200)          DEFAULT NULL COMMENT '备注',
    create_id   bigint(0)             DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)             DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)           DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX sys_dict_type_code_uindex (code) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '字典类型'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO sys_dict_type
VALUES (1, 'vip_level', 'VIP等级11', 1, '会员等级 1：普通会员，2：黄金会员，3：铂金会员，4：钻石会员', 1, '2023-11-25 22:17:51', NULL,
        '2023-12-09 20:47:48');
INSERT INTO sys_dict_type
VALUES (489941097336837, 'test', '测试', 1, '测试', 1, '2023-12-05 12:35:27', NULL, NULL);
INSERT INTO sys_dict_type
VALUES (491478519701509, 'pay_way11', '支付方式111', 1, '支付方式备注。。。1111', 1, '2023-12-09 20:51:15', NULL,
        '2023-12-09 20:51:54');
INSERT INTO sys_dict_type
VALUES (492524988080133, 'qqq', '1', 1, 'qqqqqqqqqq', 1, '2023-12-12 19:49:20', NULL, NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS sys_file;
CREATE TABLE sys_file
(
    id                 bigint(0)  NOT NULL COMMENT '主键',
    trace_id           varchar(32)    DEFAULT NULL COMMENT '日志链路ID',
    server_type        tinyint(0) NOT NULL COMMENT '服务类型 1：本地服务，2：阿里云OSS',
    upload_type        varchar(32)    DEFAULT NULL COMMENT '上传类型',
    dir_name           varchar(100)   DEFAULT NULL COMMENT '目录名称',
    original_file_name varchar(200)   DEFAULT NULL COMMENT '源文件名称',
    file_name          varchar(200)   DEFAULT NULL COMMENT '生成的文件名称',
    content_type       varchar(200)   DEFAULT NULL COMMENT '文件内容类型',
    extension          varchar(32)    DEFAULT NULL COMMENT '文件后缀',
    size               bigint(0)      DEFAULT NULL COMMENT '文件大小',
    size_mb            decimal(10, 2) DEFAULT NULL COMMENT '文件大小MB',
    url                varchar(500)   DEFAULT NULL COMMENT '访问的URL',
    system_type        tinyint(0)     DEFAULT NULL COMMENT '系统类型 1：管理端，2：移动端',
    user_id            bigint(0)      DEFAULT NULL COMMENT '用户ID',
    file_path          varchar(500)   DEFAULT NULL COMMENT '本地文件服务时的物流文件位置',
    file_type          int(0)         DEFAULT NULL COMMENT '文件类型 1：图片，2：音视频，3：文档，4：文件',
    ip                 varchar(32)    DEFAULT NULL COMMENT 'IP地址',
    ip_area            varchar(100)   DEFAULT NULL COMMENT 'IP区域',
    create_id          bigint(0)      DEFAULT NULL COMMENT '创建人ID',
    create_time        datetime(0)    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id          bigint(0)      DEFAULT NULL COMMENT '修改人ID',
    update_time        datetime(0)    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统文件'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO sys_file
VALUES (492564876013573, '492564820004869', 2, 'any', '202312', 'logo.png', '20231212223125492564821676037.png',
        'image/png', 'png', 8046, 0.01,
        'https://geekidea.oss-cn-chengdu.aliyuncs.com/boot/202312/20231212223125492564821676037.png', 1, 1, NULL, 1,
        '127.0.0.1', '内网IP', 1, '2023-12-12 22:31:38', NULL, NULL);
INSERT INTO sys_file
VALUES (507346623787013, '507346619826181', 2, 'any', '202401', '144.png', '20240123165843507346622103557.png',
        'image/png', 'png', 4288, 0.00,
        'https://geekidea.oss-cn-chengdu.aliyuncs.com/boot/202401/20240123165843507346622103557.png', 1, 1, NULL, 1,
        '127.0.0.1', '内网IP', 1, '2024-01-23 16:58:44', NULL, '2024-01-23 16:58:44');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS sys_log;
CREATE TABLE sys_log
(
    id                bigint(0) NOT NULL COMMENT '主键',
    trace_id          varchar(36)        DEFAULT NULL COMMENT '日志链路ID',
    request_time      varchar(30)        DEFAULT NULL COMMENT '请求时间',
    request_url       varchar(1000)      DEFAULT NULL COMMENT '全路径',
    permission_code   varchar(200)       DEFAULT NULL COMMENT '权限编码',
    log_name          varchar(200)       DEFAULT NULL COMMENT '日志名称',
    request_method    varchar(10)        DEFAULT NULL COMMENT '请求方式，GET/POST',
    content_type      varchar(200)       DEFAULT NULL COMMENT '内容类型',
    is_request_body   tinyint(1)         DEFAULT NULL COMMENT '是否是JSON请求映射参数',
    token             varchar(100)       DEFAULT NULL COMMENT 'token',
    module_name       varchar(100)       DEFAULT NULL COMMENT '模块名称',
    class_name        varchar(200)       DEFAULT NULL COMMENT 'controller类名称',
    method_name       varchar(200)       DEFAULT NULL COMMENT 'controller方法名称',
    request_param     text COMMENT '请求参数',
    system_type       int(0)             DEFAULT NULL COMMENT '系统类型 1：Admin管理后台，2：APP移动端端',
    user_id           bigint(0)          DEFAULT NULL COMMENT '用户ID',
    request_ip        varchar(15)        DEFAULT NULL COMMENT '请求ip',
    ip_country        varchar(100)       DEFAULT NULL COMMENT 'IP国家',
    ip_province       varchar(100)       DEFAULT NULL COMMENT 'IP省份',
    ip_city           varchar(100)       DEFAULT NULL COMMENT 'IP城市',
    ip_area_desc      varchar(100)       DEFAULT NULL COMMENT 'IP区域描述',
    ip_isp            varchar(100)       DEFAULT NULL COMMENT 'IP运营商',
    log_type          int(0)    NOT NULL DEFAULT 0 COMMENT '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
    response_time     varchar(100)       DEFAULT NULL COMMENT '响应时间',
    response_success  tinyint(1)         DEFAULT NULL COMMENT '0:失败,1:成功',
    response_code     int(0)             DEFAULT NULL COMMENT '响应结果状态码',
    response_message  text COMMENT '响应结果消息',
    response_data     text COMMENT '响应数据',
    exception_name    varchar(200)       DEFAULT NULL COMMENT '异常类名称',
    exception_message text COMMENT '异常信息',
    diff_time         bigint(0)          DEFAULT NULL COMMENT '耗时，单位：毫秒',
    diff_time_desc    varchar(100)       DEFAULT NULL COMMENT '耗时描述',
    referer           varchar(1000)      DEFAULT NULL COMMENT '请求来源地址',
    origin            varchar(1000)      DEFAULT NULL COMMENT '请求来源服务名',
    source_type       varchar(100)       DEFAULT NULL COMMENT '请求来源类型',
    is_mobile         tinyint(1)         DEFAULT NULL COMMENT '是否手机 0：否，1：是',
    platform_name     varchar(100)       DEFAULT NULL COMMENT '平台名称',
    browser_name      varchar(100)       DEFAULT NULL COMMENT '浏览器名称',
    user_agent        varchar(1000)      DEFAULT NULL COMMENT '用户环境',
    remark            varchar(200)       DEFAULT NULL COMMENT '备注',
    create_id         bigint(0)          DEFAULT NULL COMMENT '创建人ID',
    create_time       datetime(0)        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id         bigint(0)          DEFAULT NULL COMMENT '修改人ID',
    update_time       datetime(0)        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX sys_log_log_name_index (log_name) USING BTREE,
    INDEX sys_log_log_type_index (log_type) USING BTREE,
    INDEX sys_log_module_name_index (module_name) USING BTREE,
    INDEX sys_log_permission_code_index (permission_code) USING BTREE,
    INDEX sys_log_request_ip_index (request_ip) USING BTREE,
    INDEX sys_log_response_success_index (response_success) USING BTREE,
    INDEX sys_log_trace_id_index (trace_id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统日志'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS sys_login_log;
CREATE TABLE sys_login_log
(
    id                bigint(0)  NOT NULL COMMENT '主键',
    request_id        varchar(32)         DEFAULT NULL COMMENT '请求ID',
    username          varchar(32)         DEFAULT NULL COMMENT '用户名称',
    ip                varchar(15)         DEFAULT NULL COMMENT 'IP',
    token             varchar(32)         DEFAULT NULL COMMENT 'token',
    type              int(0)              DEFAULT NULL COMMENT '1:登录，2：登出',
    success           tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否成功 true:成功/false:失败',
    code              int(0)              DEFAULT NULL COMMENT '响应码',
    exception_message varchar(300)        DEFAULT NULL COMMENT '失败消息记录',
    user_agent        varchar(300)        DEFAULT NULL COMMENT '用户环境',
    browser_name      varchar(100)        DEFAULT NULL COMMENT '浏览器名称',
    browser_version   varchar(100)        DEFAULT NULL COMMENT '浏览器版本',
    engine_name       varchar(100)        DEFAULT NULL COMMENT '浏览器引擎名称',
    engine_version    varchar(100)        DEFAULT NULL COMMENT '浏览器引擎版本',
    os_name           varchar(100)        DEFAULT NULL COMMENT '系统名称',
    platform_name     varchar(100)        DEFAULT NULL COMMENT '平台名称',
    mobile            tinyint(1)          DEFAULT NULL COMMENT '是否是手机,0:否,1:是',
    device_model      varchar(100)        DEFAULT NULL COMMENT '移动端设备型号',
    remark            varchar(200)        DEFAULT NULL COMMENT '备注',
    create_id         bigint(0)           DEFAULT NULL COMMENT '创建人ID',
    create_time       datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id         bigint(0)           DEFAULT NULL COMMENT '修改人ID',
    update_time       datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统登录日志'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_menu;
CREATE TABLE sys_menu
(
    id             bigint(0)  NOT NULL COMMENT '主键',
    name           varchar(32)         DEFAULT NULL COMMENT '菜单名称',
    parent_id      bigint(0)  NOT NULL COMMENT '父id',
    type           tinyint(0) NOT NULL COMMENT '菜单类型，1：目录，2：菜单，3：权限',
    code           varchar(100)        DEFAULT NULL COMMENT '菜单编码',
    route_url      varchar(200)        DEFAULT NULL COMMENT '前端路由地址',
    component_path varchar(100)        DEFAULT NULL COMMENT '组件路径',
    route_redirect varchar(100)        DEFAULT NULL COMMENT '重定向',
    icon           varchar(100)        DEFAULT NULL COMMENT '菜单图标',
    sort           int(0)     NOT NULL DEFAULT 0 COMMENT '排序',
    is_show        tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否显示,0：不显示，1：显示',
    is_cache       tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否缓存，0：否 1：是',
    status         tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态，0：禁用，1：启用',
    create_id      bigint(0)           DEFAULT NULL COMMENT '创建人ID',
    create_time    datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id      bigint(0)           DEFAULT NULL COMMENT '修改人ID',
    update_time    datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX sys_menu_name_index (name) USING BTREE,
    INDEX sys_menu_status_index (status) USING BTREE,
    INDEX sys_menu_type_index (type) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统菜单'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO sys_menu
VALUES (11, '系统管理', 0, 1, '', '/system', '', '', 'ele-setting', 99, 1, 0, 1, 1, '2023-05-27 12:02:42', 1,
        '2023-12-25 12:26:10');
INSERT INTO sys_menu
VALUES (1101, '用户管理', 11, 2, 'sys:user:list', '/system/user', 'system/user/index', NULL, 'ele-user', 1, 1, 0, 1, 1,
        '2023-05-27 12:04:05', NULL, '2023-06-16 09:08:20');
INSERT INTO sys_menu
VALUES (1102, '角色管理', 11, 2, 'sys:role:list', '/system/role', 'system/role/index', NULL, 'local-role', 2, 1, 1, 1, 1,
        '2023-06-18 08:02:48', NULL, NULL);
INSERT INTO sys_menu
VALUES (1103, '菜单管理', 11, 2, 'sys:menu:tree-list', '/system/menu', 'system/menu/index', NULL, 'local-menu', 3, 1, 1, 1,
        1, '2023-06-18 08:03:32', NULL, '2023-12-04 17:35:25');
INSERT INTO sys_menu
VALUES (110101, '添加用户', 1101, 3, 'sys:user:add', NULL, NULL, NULL, NULL, 1, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110102, '修改用户', 1101, 3, 'sys:user:update', NULL, NULL, NULL, NULL, 2, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110103, '删除用户', 1101, 3, 'sys:user:delete', NULL, NULL, NULL, NULL, 3, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110104, '用户详情', 1101, 3, 'sys:user:info', NULL, NULL, NULL, NULL, 4, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110106, '重置用户密码', 1101, 3, 'sys:user:reset-password', NULL, NULL, NULL, NULL, 6, 1, 0, 1, 1,
        '2023-06-18 20:22:12', NULL, NULL);
INSERT INTO sys_menu
VALUES (110201, '添加角色', 1102, 3, 'sys:role:add', NULL, NULL, NULL, NULL, 1, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110202, '修改角色', 1102, 3, 'sys:role:update', NULL, NULL, NULL, NULL, 2, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110203, '删除角色', 1102, 3, 'sys:role:delete', NULL, NULL, NULL, NULL, 3, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110204, '角色详情', 1102, 3, 'sys:role:info', NULL, NULL, NULL, NULL, 4, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110207, '设置角色权限', 1102, 3, 'sys:role:set-role-menus', NULL, NULL, NULL, NULL, 5, 1, 0, 1, 1,
        '2023-06-18 20:22:12', NULL, NULL);
INSERT INTO sys_menu
VALUES (110301, '添加菜单', 1103, 3, 'sys:menu:add', NULL, NULL, NULL, NULL, 1, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110302, '修改菜单', 1103, 3, 'sys:menu:update', NULL, NULL, NULL, NULL, 2, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110303, '删除菜单', 1103, 3, 'sys:menu:delete', NULL, NULL, NULL, NULL, 3, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (110304, '菜单详情', 1103, 3, 'sys:menu:info', NULL, NULL, NULL, NULL, 4, 1, 0, 1, 1, '2023-06-18 20:22:12', NULL,
        NULL);
INSERT INTO sys_menu
VALUES (11050101, '日志详情', 110501, 3, 'sys:log:info', NULL, NULL, NULL, NULL, 2, 0, 0, 1, 1, '2023-06-23 21:11:00', NULL,
        '2023-06-23 21:24:05');
INSERT INTO sys_menu
VALUES (11050102, '日志列表', 110501, 3, 'sys:log:list', NULL, NULL, NULL, NULL, 3, 1, 0, 1, 1, '2023-06-23 21:12:04', NULL,
        '2023-06-23 21:24:13');
INSERT INTO sys_menu
VALUES (488299583537157, '字典管理', 11, 2, 'dict', '/system/dict', 'system/dict/index', NULL, 'ele-notebook', 3, 1, 0, 1,
        1, '2023-11-30 21:16:07', NULL, NULL);
INSERT INTO sys_menu
VALUES (489978240356357, '系统配置', 11, 2, 'sys:config:manager', '/system/config', 'system/config/index', NULL, 'ele-cpu',
        4, 1, 0, 1, 1, '2023-12-05 15:06:35', NULL, '2023-12-05 16:18:59');
INSERT INTO sys_menu
VALUES (499911770910725, '系统工具', 0, 1, '', '/tool', '', '', 'ele-monitor', 98, 1, 0, 1, 1, '2024-01-02 16:46:14', 1,
        '2024-01-21 20:15:22');
INSERT INTO sys_menu
VALUES (499912002056197, '代码生成', 499911770910725, 2, 'code', '/tool/code', 'tool/code/index', '', 'ele-document-copy',
        1, 1, 0, 1, 1, '2024-01-02 16:47:11', 1, '2024-01-21 20:15:29');
INSERT INTO sys_menu
VALUES (504136134303749, '生成配置', 499911770910725, 2, 'generator:table:update', '/tool/code/config', 'tool/code/config',
        NULL, 'ele-suitcase-line', 1, 0, 0, 1, 1, '2024-01-14 15:15:13', 1, '2024-01-14 15:15:12');
INSERT INTO sys_menu
VALUES (506680317689861, '预览代码', 499912002056197, 3, 'generator:preview-code', NULL, NULL, NULL, NULL, 2, 0, 0, 1, 1,
        '2024-01-21 19:47:31', 1, '2024-01-21 19:47:31');
INSERT INTO sys_menu
VALUES (506686662549509, '编辑配置', 499912002056197, 3, 'generator:table:update', NULL, NULL, NULL, NULL, 3, 0, 0, 1, 1,
        '2024-01-21 20:13:20', 1, '2024-01-21 20:13:20');
INSERT INTO sys_menu
VALUES (506686906273797, '同步表信息', 499912002056197, 3, 'generator:sync-code', NULL, NULL, NULL, NULL, 4, 0, 0, 1, 1,
        '2024-01-21 20:14:20', 1, '2024-01-21 20:14:20');
INSERT INTO sys_menu
VALUES (506687030714373, '生成代码', 499912002056197, 3, 'generator:generator-code', NULL, NULL, NULL, NULL, 5, 0, 0, 1, 1,
        '2024-01-21 20:14:50', 1, '2024-01-21 20:14:50');
INSERT INTO sys_menu
VALUES (506687093297157, '下载代码', 499912002056197, 3, 'generator:download-code', NULL, NULL, NULL, NULL, 6, 0, 0, 1, 1,
        '2024-01-21 20:15:06', 1, '2024-01-21 20:15:05');


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role
(
    id          bigint(0)   NOT NULL COMMENT '主键',
    code        varchar(100)         DEFAULT NULL COMMENT '角色唯一编码',
    name        varchar(32) NOT NULL COMMENT '角色名称',
    is_system   tinyint(1)  NOT NULL DEFAULT 0 COMMENT '是否系统内置角色 1：是，0：否',
    remark      varchar(200)         DEFAULT NULL COMMENT '备注',
    create_id   bigint(0)            DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)            DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX sys_role_name_uindex (name) USING BTREE,
    UNIQUE INDEX sys_role_code_uindex (code) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO sys_role
VALUES (1, 'admin', '管理员', 1, NULL, 1, '2023-02-15 13:22:21', NULL, NULL);
INSERT INTO sys_role
VALUES (497014598377477, 'test', '测试', 1, '测试角色，普通权限1', 1, '2023-12-25 12:17:37', 1, '2023-12-25 12:24:52');
INSERT INTO sys_role
VALUES (507811226501125, 'custom_role', '自定义角色', 0, '用户自定义角色', 1, '2024-01-25 00:29:12', NULL, '2024-01-25 00:29:12');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS sys_role_menu;
CREATE TABLE sys_role_menu
(
    id          bigint(0) NOT NULL COMMENT '主键',
    role_id     bigint(0)   DEFAULT NULL COMMENT '角色id',
    menu_id     bigint(0)   DEFAULT NULL COMMENT '菜单id',
    is_choice   tinyint(1)  DEFAULT NULL COMMENT '是否用户选中 0：否，1：是',
    create_id   bigint(0)   DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)   DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    INDEX sys_role_menu_menu_id_index (menu_id) USING BTREE,
    INDEX sys_role_menu_role_id_index (role_id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (490996903432197, 490425003896837, 11, 1, 1, '2023-12-08 12:11:32', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (490996903436293, 490425003896837, 1101, 1, 1, '2023-12-08 12:11:32', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (491477241692165, 491472570187781, 110101, 1, 1, '2023-12-09 20:46:03', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (491477241700357, 491472570187781, 110102, 1, 1, '2023-12-09 20:46:03', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (491477241700358, 491472570187781, 11, 0, 1, '2023-12-09 20:46:03', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (491477241704453, 491472570187781, 1101, 0, 1, '2023-12-09 20:46:03', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (495707737645061, 495707261153285, 11, 1, 1, '2023-12-21 19:39:59', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (495707737657349, 495707261153285, 1101, 1, 1, '2023-12-21 19:39:59', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (495707737665541, 495707261153285, 110101, 1, 1, '2023-12-21 19:39:59', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (495707737669637, 495707261153285, 110102, 1, 1, '2023-12-21 19:39:59', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017157316613, 497014598377477, 1101, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185705989, 497014598377477, 110101, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185714181, 497014598377477, 110102, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185730565, 497014598377477, 110103, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185742853, 497014598377477, 110104, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185845253, 497014598377477, 110106, 1, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (497017185853445, 497014598377477, 11, 0, 1, '2023-12-25 12:28:08', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (500516359094277, 500516312236037, 110102, 1, 1, '2024-01-04 09:46:19', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (500516359110661, 500516312236037, 11, 0, 1, '2024-01-04 09:46:19', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (500516359114757, 500516312236037, 1101, 0, 1, '2024-01-04 09:46:19', null, null);
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (505545488814085, 497741963337733, 110201, 1, 1, '2024-01-18 14:49:54', null, '2024-01-18 14:49:53');
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (505545488818181, 497741963337733, 11, 0, 1, '2024-01-18 14:49:54', null, '2024-01-18 14:49:53');
INSERT INTO sys_role_menu (id, role_id, menu_id, is_choice, create_id, create_time, update_id, update_time)
VALUES (505545488818182, 497741963337733, 1102, 0, 1, '2024-01-18 14:49:54', null, '2024-01-18 14:49:53');


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user
(
    id          bigint(0)   NOT NULL COMMENT '主键',
    username    varchar(20) NOT NULL COMMENT '用户名',
    nickname    varchar(20) NOT NULL COMMENT '昵称',
    password    varchar(64) NOT NULL COMMENT '密码',
    salt        varchar(32) NOT NULL COMMENT '盐值',
    phone       varchar(20)          DEFAULT NULL COMMENT '手机号码',
    email       varchar(255)         DEFAULT NULL COMMENT '电子邮件',
    head        varchar(100)         DEFAULT NULL COMMENT '头像',
    status      tinyint(1)  NOT NULL DEFAULT 1 COMMENT '状态，0：禁用，1：启用',
    role_id     bigint(0)   NOT NULL COMMENT '角色ID',
    is_admin    tinyint(1)  NOT NULL DEFAULT 0 COMMENT '是否是超管 0：否，1：是',
    create_id   bigint(0)            DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)          DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)            DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)          DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX sys_us (username) USING BTREE,
    INDEX sys_user_status_index (status) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '系统用户'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO sys_user
VALUES (1, 'admin', '管理员', 'fa1a2b8314d2a19cb1881eeebc4747adcc31d0b3aa7fba8f45643f1ec8570883',
        '970eb1c2f043455e9f70c35578f7e87c', '15892309104', '2422089289@qq.com',
        'https://geekidea.oss-cn-chengdu.aliyuncs.com/boot/202312/20231212223125492564821676037.png', 1, 1, 1, 1,
        '2023-12-12 22:33:06', NULL, '2023-12-25 12:04:31');
INSERT INTO sys_user
VALUES (497024442028037, 'test', '测试账号', '36d561f763fa5d40a886fa4511edb318b69529bd5cf1886b8034148a3bd1553d',
        '9f6640f628a14d81ab94122c6d1ef41b', '15888888888', 'geekidea@qq.com', NULL, 1, 497014598377477, 0, 1,
        '2023-12-25 12:57:40', 1, '2023-12-27 11:12:42');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id                 bigint(0)  NOT NULL COMMENT 'ID',
    username           varchar(20)         DEFAULT NULL COMMENT '账号',
    nickname           varchar(20)         DEFAULT NULL COMMENT '昵称',
    password           varchar(64)         DEFAULT NULL COMMENT '密码',
    salt               varchar(32)         DEFAULT NULL COMMENT '盐值',
    openid             varchar(200)        DEFAULT NULL COMMENT '微信openid',
    phone              varchar(11)         DEFAULT NULL COMMENT '手机号码',
    head               varchar(200)        DEFAULT NULL COMMENT '头像',
    introduction       varchar(200)        DEFAULT NULL COMMENT '个人简介',
    user_role_id       bigint(0)           DEFAULT NULL COMMENT '用户角色ID',
    status             tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态 1：正常，0：禁用',
    register_time      datetime(0)         DEFAULT NULL COMMENT '注册时间',
    register_ip        varchar(20)         DEFAULT NULL COMMENT '注册IP',
    register_ip_area   varchar(100)        DEFAULT NULL COMMENT '注册IP区域',
    last_login_time    datetime(0)         DEFAULT NULL COMMENT '最后登录时间',
    last_login_ip      varchar(20)         DEFAULT NULL COMMENT '最后登录IP',
    last_login_ip_area varchar(100)        DEFAULT NULL COMMENT '最后一次登录IP区域',
    remark             varchar(200)        DEFAULT NULL COMMENT '备注',
    create_id          bigint(0)           DEFAULT NULL COMMENT '创建人ID',
    create_time        datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id          bigint(0)           DEFAULT NULL COMMENT '修改人ID',
    update_time        datetime(0)         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX user_openid_uindex (openid) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户信息'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user
VALUES (1, 'boot', 'boot用户11', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304',
        '94132dcd55eb478bb63170a2fa510ea1', NULL, NULL, NULL, NULL, 1, 1, '2023-11-25 00:00:00', NULL, NULL,
        '2024-02-12 16:29:45', '127.0.0.1', '内网IP', NULL, NULL, '2023-11-25 23:54:31', 1, '2024-01-12 23:07:01');
INSERT INTO user
VALUES (2, 'geekidea', 'geekidea用户', '35d9d2ad09f0a7b0c2b3143bd60affb719766972aa720827c97240ea04d0f304',
        '94132dcd55eb478bb63170a2fa510ea1', NULL, NULL, NULL, NULL, 2, 1, '2023-11-25 00:00:00', NULL, NULL,
        '2023-11-26 00:56:15', '127.0.0.1', '内网IP', NULL, NULL, '2023-11-25 23:54:31', NULL, NULL);

-- ----------------------------
-- Table structure for user_banner
-- ----------------------------
DROP TABLE IF EXISTS user_banner;
CREATE TABLE user_banner
(
    id               bigint(0)    NOT NULL COMMENT 'ID',
    type             tinyint(0)   NOT NULL COMMENT '轮播图类型 1：首页轮播图，2：广告轮播图',
    name             varchar(32)  NOT NULL COMMENT '轮播图名称',
    image_url        varchar(200) NOT NULL COMMENT '轮播图图片路径',
    jump_type        tinyint(0)   NOT NULL COMMENT '跳转类型 1：不跳转，2：跳转到商品详情，3：跳转到广告详情，4：跳转到客服页面',
    jump_business_id bigint(0)   DEFAULT NULL COMMENT '跳转到业务ID',
    create_id        bigint(0)   DEFAULT NULL COMMENT '创建人ID',
    create_time      datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id        bigint(0)   DEFAULT NULL COMMENT '修改人ID',
    update_time      datetime(0) DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户轮播图'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS user_role;
CREATE TABLE user_role
(
    id          bigint(0)   NOT NULL COMMENT '主键',
    code        varchar(100) DEFAULT NULL COMMENT '用户角色唯一编码',
    name        varchar(32) NOT NULL COMMENT '用户角色名称',
    remark      varchar(200) DEFAULT NULL COMMENT '备注',
    create_id   bigint(0)    DEFAULT NULL COMMENT '创建人ID',
    create_time datetime(0)  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_id   bigint(0)    DEFAULT NULL COMMENT '修改人ID',
    update_time datetime(0)  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id) USING BTREE,
    UNIQUE INDEX user_role_name_uindex (name) USING BTREE,
    UNIQUE INDEX user_role_code_uindex (code) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '用户角色'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO user_role
VALUES (1, 'normal', '普通用户', NULL, NULL, '2024-01-06 21:21:23', NULL, NULL);
INSERT INTO user_role
VALUES (2, 'vip', 'VIP用户', NULL, NULL, '2024-01-06 21:21:23', NULL, NULL);


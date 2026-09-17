create table clazz
(
    id          int unsigned auto_increment comment 'ID,主键'
        primary key,
    name        varchar(30)      not null comment '班级名称',
    room        varchar(20)      null comment '班级教室',
    begin_date  date             not null comment '开课时间',
    end_date    date             not null comment '结课时间',
    master_id   int unsigned     null comment '班主任ID, 关联员工表ID',
    subject     tinyint unsigned not null comment '学科, 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6: 嵌入式',
    create_time datetime         null comment '创建时间',
    update_time datetime         null comment '修改时间',
    constraint name
        unique (name)
)
    comment '班级表';

create table dept
(
    id          int unsigned auto_increment comment 'ID, 主键'
        primary key,
    name        varchar(10) not null comment '部门名称',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间',
    constraint name
        unique (name)
)
    comment '部门表';

create table emp
(
    id          int unsigned auto_increment comment 'ID,主键'
        primary key,
    username    varchar(20)                  not null comment '用户名',
    password    varchar(50) default '123456' null comment '密码',
    name        varchar(10)                  not null comment '姓名',
    gender      tinyint unsigned             not null comment '性别, 1:男, 2:女',
    phone       char(11)                     not null comment '手机号',
    job         tinyint unsigned             null comment '职位, 1 班主任, 2 讲师 , 3 学工主管, 4 教研主管, 5 咨询师',
    salary      int unsigned                 null comment '薪资',
    image       varchar(300)                 null comment '头像',
    entry_date  date                         null comment '入职日期',
    dept_id     int unsigned                 null comment '部门ID',
    create_time datetime                     null comment '创建时间',
    update_time datetime                     null comment '修改时间',
    constraint phone
        unique (phone),
    constraint username
        unique (username)
)
    comment '员工表';

create table emp_expr
(
    id      int unsigned auto_increment comment 'ID, 主键'
        primary key,
    emp_id  int unsigned null comment '员工ID',
    begin   date         null comment '开始时间',
    end     date         null comment '结束时间',
    company varchar(50)  null comment '公司名称',
    job     varchar(50)  null comment '职位'
)
    comment '工作经历';

create table operate_log
(
    id             int unsigned auto_increment comment 'ID'
        primary key,
    operate_emp_id int unsigned  null comment '操作人ID',
    operate_time   datetime      null comment '操作时间',
    class_name     varchar(100)  null comment '操作的类名',
    method_name    varchar(100)  null comment '操作的方法名',
    method_params  varchar(1000) null comment '方法参数',
    return_value   varchar(2000) null comment '返回值',
    cost_time      int           null comment '方法执行耗时, 单位:ms'
)
    comment '操作日志表';

create table student
(
    id              int unsigned auto_increment comment 'ID,主键'
        primary key,
    name            varchar(10)                  not null comment '姓名',
    no              char(10)                     not null comment '学号',
    gender          tinyint unsigned             not null comment '性别, 1: 男, 2: 女',
    phone           varchar(11)                  not null comment '手机号',
    id_card         char(18)                     not null comment '身份证号',
    is_college      tinyint unsigned             not null comment '是否来自于院校, 1:是, 0:否',
    address         varchar(100)                 null comment '联系地址',
    degree          tinyint unsigned             null comment '最高学历, 1:初中, 2:高中, 3:大专, 4:本科, 5:硕士, 6:博士',
    graduation_date date                         null comment '毕业时间',
    clazz_id        int unsigned                 not null comment '班级ID, 关联班级表ID',
    violation_count tinyint unsigned default '0' not null comment '违纪次数',
    violation_score tinyint unsigned default '0' not null comment '违纪扣分',
    create_time     datetime                     null comment '创建时间',
    update_time     datetime                     null comment '修改时间',
    constraint id_card
        unique (id_card),
    constraint no
        unique (no),
    constraint phone
        unique (phone)
)
    comment '学员表';

create
    definer = root@localhost procedure generate_emp_expr_data()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE v_emp_id INT;
    DECLARE v_begin DATE;
    DECLARE v_end DATE;
    DECLARE v_company VARCHAR(50);
    DECLARE v_job VARCHAR(50);

    -- 定义公司和职位的基础词库
    DECLARE company_prefix VARCHAR(200) DEFAULT '星辰,云海,智汇,创想,云端,天际,博睿,瑞信,华科,中软,东软,浪潮,神州,数码,金蝶,用友,新浪,搜狐,网易,百度';
    DECLARE company_suffix VARCHAR(100) DEFAULT '科技,网络,信息,数据,智能,云计算,大数据,互联网,软件,系统';

    DECLARE job_prefix VARCHAR(200) DEFAULT '高级,资深,初级,中级,首席,特聘,助理,实习,首席,核心';
    DECLARE job_suffix VARCHAR(200) DEFAULT 'Java工程师,前端开发,产品经理,UI设计师,测试工程师,运维工程师,数据分析师,算法工程师,架构师,项目经理,后端开发,全栈工程师,DBA,安全专家,技术总监';

    -- 清空旧数据（可选）
    -- TRUNCATE TABLE emp_expr;

    WHILE i <= 100 DO
            -- 1. emp_id: 1-130 随机
            SET v_emp_id = FLOOR(1 + RAND() * 130);

            -- 2. begin: 从 2015-01-01 开始，每次递增 15~45 天，保证绝对不重复且递增
            SET v_begin = DATE_ADD('2015-01-01', INTERVAL (i * 30 + FLOOR(RAND() * 15)) DAY);

            -- 3. end: begin 日期往后推 6个月 ~ 3年，保证 end > begin 且不重复
            SET v_end = DATE_ADD(v_begin, INTERVAL (180 + FLOOR(RAND() * 900)) DAY);

            -- 4. company: 词库随机组合 + i 后缀，保证 100% 不重复
            SET v_company = CONCAT(
                    SUBSTRING_INDEX(SUBSTRING_INDEX(company_prefix, ',', FLOOR(1 + RAND() * 20)), ',', -1),
                    SUBSTRING_INDEX(SUBSTRING_INDEX(company_suffix, ',', FLOOR(1 + RAND() * 10)), ',', -1),
                    '(', i, '部)'
                            );

            -- 5. job: 词库随机组合 + 随机编号，保证 100% 不重复
            SET v_job = CONCAT(
                    SUBSTRING_INDEX(SUBSTRING_INDEX(job_prefix, ',', FLOOR(1 + RAND() * 10)), ',', -1),
                    SUBSTRING_INDEX(SUBSTRING_INDEX(job_suffix, ',', FLOOR(1 + RAND() * 15)), ',', -1),
                    '-NO.', LPAD(i, 3, '0')
                        );

            -- 插入数据
            INSERT INTO emp_expr (emp_id, begin, end, company, job)
            VALUES (v_emp_id, v_begin, v_end, v_company, v_job);

            SET i = i + 1;
        END WHILE;
END;


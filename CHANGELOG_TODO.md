# CHANGELOG TODO

## [V1.4]
- :white_check_mark: 部门树形列表

## [V1.5]
- 代码生成器优化，是否生成自定义update方法，生成version,deleted注解
- :white_check_mark: 黑白名单，配置文件和数据库可配置，并缓存到Redis
- Redis Cache注解使用
- ok http工具类
- Excel解析,导入导出
- :white_check_mark: 项目代码遵循阿里代码规范优化
- 字典表
- 配置参数表
- 上传文件附件表
- Redis分布式锁
- 接口限流，ip限流，频率限流
- Shiro Redis缓存
- ApplicationRunner
- HikariCP

## [V1.6]
- Spring security集成


## [V2.0]
- 当主键类型为自动生成时，ID生成了NotNull注解，应去掉
- 是否生成list方法
- 是否生成id,name方法，提供公共的id,name获取方法
- fix SysRoleServiceImpl.java if(deleteSet.size() > 0){ // 删除权限关联 
- SysPermissionMapper.xml order by p.sort asc

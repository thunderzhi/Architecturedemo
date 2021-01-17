package com.cxz;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/1/17 17:38
 */
public class AutoGenerateUtil {
    //项目存储位置
    public static String PROJECT_GENERATE_DISK = "";
    //包名
    public static String PACKAGE_NAME= "";
    //数据库地址
    public static String DB_URL = "";
    //数据库实例名
    public static String DRIVER_CLASS_NAME = "";
    //数据库类型
    public static String DB_TYPE = "";
    //数据库用户
    public static String USER = "";
    //数据库密码
    public static String PASSWORD = "";
    //数据库schema
    public static String SCHEMA = "";
    //要查询的表名
    public static String TABLE_NAMES = "";
    //创建人
    public static String AUTHOR = "";
    //是否强制带上注解
    public static boolean ENABLE_TABLE_FIELD_ANNOTATION = false;
    //生成的注解带上IdType类型
    public static IdType TABLE_IDTYPE = null;
    //是否去掉生成实体的属性名前缀
    public static String[] FIELD_PREFIX = null;
    //生成的Service 接口类名是否以I开头 默认是以I开头  user表 -> IUserService, UserServiceImpl
    public static boolean SERVICE_CLASS_NAME_START_WITHI = false;
    //jsp生成地址
    public static String JSP_URL="";

    private static GlobalConfig GlobalGenerate(){
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)// 不需要ActiveRecord特性的请改为false
                .setIdType(TABLE_IDTYPE)
                .setEnableCache(false)// XML 二级缓存
                .setAuthor(AUTHOR)
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(false)// XML columList
                .setOutputDir(PROJECT_GENERATE_DISK+"\\java")
                .setFileOverride(true)
                .setControllerName("%sAction")//自定义文件命名，注意 %s 会自动填充表实体属性！
        ;
        if (!SERVICE_CLASS_NAME_START_WITHI) {
            config.setServiceName("%sService");
        }
        return config;
    }

    private static DataSourceConfig DaoSourceGenerate(){
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        DbType type=null;
        if("oracle".equals(DB_TYPE)){
            type=DbType.ORACLE;
        }else if("sql_server".equals(DB_TYPE)){
            type=DbType.SQL_SERVER;
        }else if("mysql".equals(DB_TYPE)){
            type=DbType.MYSQL;
        }else if("postgre_sql".equals(DB_TYPE)){
            type=DbType.POSTGRE_SQL;
        }
        dataSourceConfig.setDbType(type)//数据库类型
                .setUrl(DB_URL)//数据库地址
                .setUsername(AutoGenerateUtil.DB_TYPE)//数据库用户名
                .setPassword(AutoGenerateUtil.PASSWORD)//数据库密码
                .setDriverName(AutoGenerateUtil.DRIVER_CLASS_NAME);//实例名
                //.setSchemaname(AutoGenerateUtil.SCHEMA);
        return dataSourceConfig;
    }
    private static StrategyConfig StrategyGenerate(){
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setVersionFieldName("version")
                .setCapitalMode(true)// 全局大写命名 ORACLE 注意
                .setEntityLombokModel(false)
                //.setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setEntityTableFieldAnnotationEnable(ENABLE_TABLE_FIELD_ANNOTATION)
                .setFieldPrefix(FIELD_PREFIX)//test_id -> id, test_type -> type
                .setInclude(TABLE_NAMES)//修改替换成你需要的表名，多个表名传数组
            .setExclude(new String[]{"test"}) // 排除生成的表
            .setTablePrefix(new String[] { "tlog_", "tsys_" })// 此处可以修改为您的表前缀
             .setSuperEntityClass("com.lin.demo.TestEntity")// 自定义实体父类
            .setSuperEntityColumns(new String[] { "test_id","age" })// 自定义实体，公共字段
            .setSuperMapperClass("com.lin.demo.TestMapper")// 自定义 mapper 父类
             .setSuperServiceClass("com.lin.demo.TestService")// 自定义 service 父类
            .setSuperServiceImplClass("com.lin.demo.TestServiceImpl")// 自定义 service 实现类父类
             .setSuperControllerClass("com.lin.demo.TestController")// 自定义 controller 父类
            .setEntityColumnConstant(true)// 【实体】是否生成字段常量（默认 false）public static final String ID = "test_id";
            .setEntityBuilderModel(true);// 【实体】是否为构建者模型（默认 false）public User setName(String name) {this.name = name; return this;}
            return strategyConfig;
    }

    private static TemplateConfig TemplateGenerate(){
        TemplateConfig templateConfig = new TemplateConfig()
                .setXml("/templates/mapper2.xml")//注意：不要带上.vm
                .setController("/templates/action.java")
                .setMapper("/templates/mapper.java")
                .setXml("/templates/mapper.xml")
                .setService("/templates/service.java")
                .setServiceImpl("/templates/serviceImpl.java")
                ;
        return templateConfig;
    }
    private static InjectionConfig FileGenerate(){
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {//自定义参数
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };

        // 自定义 xxList.jsp 生成
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        //创建jsp目录
        Files files = new Files(JSP_URL);
        files.createlist();
        //生成列表页面
        focList.add(new FileOutConfig("/template/list.jsp.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return JSP_URL+"\\list.jsp";
            }
        });
        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }
    public static PackageConfig PackageGenerate(){
        PackageConfig pc = new PackageConfig()
                .setParent("com")
                .setModuleName(PACKAGE_NAME)
                .setController("action")
                .setEntity("entity")
                .setMapper("mapper")
                .setXml("mapper");
        return pc;
    }

    public void generateByTablesWithInjectConfig() {
        //全局配置
        GlobalConfig config =AutoGenerateUtil.GlobalGenerate();
        //配置数据源
        DataSourceConfig dataSourceConfig=AutoGenerateUtil.DaoSourceGenerate();
        //配置策略
        StrategyConfig strategyConfig = AutoGenerateUtil.StrategyGenerate();
        //配置模板
        TemplateConfig templateConfig = AutoGenerateUtil.TemplateGenerate();
        //生成jsp文件
        InjectionConfig injectionConfig = AutoGenerateUtil.FileGenerate();
        //配置包
        PackageConfig packageConfig=AutoGenerateUtil.PackageGenerate();
        //生成代码
        new AutoGenerator()
                .setGlobalConfig(config)
                .setTemplate(templateConfig)//自定义模板路径
                .setCfg(injectionConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .execute();
    }

    //AutoGenerateUtil generatorServiceEntity=new AutoGenerateUtil();
    //    generatorServiceEntity.generateByTablesWithInjectConfig();
}

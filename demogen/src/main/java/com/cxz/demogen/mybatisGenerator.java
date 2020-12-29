package com.cxz.demogen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/12/28 22:39
 */
public class mybatisGenerator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        System.out.println(System.getProperty("user.dir"));
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = ResourceUtils.getFile("classpath:mybatis-generator.xml");
        //File configFile = new File("mybatis-generator.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        if (warnings.isEmpty()) {
            System.out.println("MyBatis文件生成成功！！");
        } else {
            System.err.println(warnings);
        }
        myBatisGenerator.generate(null);
    }
}

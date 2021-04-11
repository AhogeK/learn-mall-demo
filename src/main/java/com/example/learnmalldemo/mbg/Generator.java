package com.example.learnmalldemo.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用于生产 MBG 的代码
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-03-25 21:23
 * @since 1.00
 */
public class Generator {

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException,
            SQLException, InterruptedException {
        // 存储 MBG 运行中的警告信息
        List<String> warnings = new ArrayList<>();
        // 读取 MBG 配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        // 实例化 MBG 配置解析器
        ConfigurationParser cp = new ConfigurationParser(warnings);
        // 解析 xml 配置文件
        Configuration config = cp.parseConfiguration(is);
        assert is != null;
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(true);
        // 创建 MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // 执行生成代码
        myBatisGenerator.generate(null);
        // 输出警告信息
        warnings.forEach(System.out::println);
    }
}

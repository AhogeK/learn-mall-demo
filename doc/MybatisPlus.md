# Mybatis Plus

> mp 版本 v3.5.0 mpg v3.5.1

## 代码生成

```java
/**
 * <p>
 * MyBatis Plus 代码生成器
 * </p>
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-04-15 09:30
 */
public class CodeGenerator {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig.Builder(
            "jdbc:mysql://192.168.50.26:3306/mall?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
            "root",
            "123456");

    public static void main(String[] args) {

        /* 代码生成器
         * 3.5.1及以上新代码生成
         */
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称？"))
                        .fileOverride().outputDir(System.getProperty("user.dir") + "/src/main/java"))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply(
                                "请输入表名，多个英文逗号分隔？所有输入" + " all"))).controllerBuilder().enableRestStyle()
                        .enableHyphenStyle().entityBuilder().enableLombok()
                        .addTableFills(new Column("create_time", FieldFill.INSERT))
                        .addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    /**
     * 处理 all 情况
     */
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
```

上述为 [MPG 新版](https://baomidou.com/pages/779a6e/#%E5%BF%AB%E9%80%9F%E5%85%A5%E9%97%A8) 的代码生成代码，相较与 3.4 的版本简单
了很多，更多详细的配置，可以进各 Config 类中查看有那些详细的配置，这边代码是按最基本默认的配置。

*模板走的 Mybatis-plus [Velocity](https://velocity.apache.org/) 默认模板，有自定义需求可自行配置*

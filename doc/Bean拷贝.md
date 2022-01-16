# Bean 拷贝

## Mapstruct

> 更多详细可参考 [https://www.tutorialspoint.com/mapstruct/index.htm](https://www.tutorialspoint.com/mapstruct/index.htm)

### 包引入

```xml
<!-- Map 映射工具 -->
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>${org.mapstruct.version}</version>
</dependency>
```

maven 插件配置

```xml
<plugin>
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-compiler-plugin</artifactId>
<configuration>
    <source>1.8</source>
    <target>1.8</target>
    <annotationProcessorPaths>
        <path>
            <groupId>org.mapstruct</groupId>
            <artifactId>mapstruct-processor</artifactId>
            <version>${org.mapstruct.version}</version>
        </path>
        <path>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
        </path>
    </annotationProcessorPaths>
    <compilerArgs>
        <compilerArg>
            -Amapstruct.defaultComponentModel=spring
        </compilerArg>
    </compilerArgs>
</configuration>
</plugin>
```

### 准备接口工具用于调用拷贝

```java
/**
 * brand map struct
 *
 * @author AhogeK ahogek@gmail.com
 * @date 2021-01-16 18:15
 */
@Mapper
public interface BrandStructMapper {

    BrandStructMapper INSTANCE = Mappers.getMapper(BrandStructMapper.class);

    /**
     * {@link PmsBrand} to {@link PmsBrandVo}
     *
     * @param pmsBrand source
     * @return {@link PmsBrandVo}
     */
    PmsBrandVo pmsBrandToPmsBrandVO(PmsBrand pmsBrand);

    /**
     * 品牌列表转换
     *
     * @param pmsBrands {@link PmsBrand}列表
     * @return {@link PmsBrandVo}列表
     */
    List<PmsBrandVo> pmsBrandListToPmsBrandVoList(List<PmsBrand> pmsBrands);
}
```

### 项目中使用

```java
@Override
public IPage<PmsBrandVo> getBrandPage(Page<PmsBrand> pmsBrandPage, String keywords) {
    Page<PmsBrand> brandPage = page(pmsBrandPage, Wrappers.<PmsBrand>lambdaQuery()
            .like(StringUtils.isNotEmpty(keywords), PmsBrand::getName, keywords));
    IPage<PmsBrandVo> resultPage = new Page<>();
    BeanUtils.copyProperties(brandPage, resultPage);
    if (CollectionUtils.isEmpty(brandPage.getRecords())) {
        log.debug("brand has no page");
        return resultPage;
    }
    resultPage.setRecords(BrandStructMapper.INSTANCE.pmsBrandListToPmsBrandVoList(brandPage.getRecords()));
    log.debug("brand page data:{}", resultPage);
    return resultPage;
}
```
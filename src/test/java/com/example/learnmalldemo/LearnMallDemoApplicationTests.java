package com.example.learnmalldemo;

import com.example.learnmalldemo.service.admin.PmsBrandTestService;
import com.example.learnmalldemo.service.pms.IPmsBrandService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class LearnMallDemoApplicationTests {

    @Autowired
    private IPmsBrandService brandService;

    @BeforeAll
    static void start() {
        log.info("\n\n\n ██████████   ████████    ████████   ██████████\n" +
                "░░░░░██░░░   ░██░░░░░    ██░░░░░░   ░░░░░██░░░ \n" +
                "    ░██      ░██        ░██             ░██    \n" +
                "    ░██      ░███████   ░█████████      ░██    \n" +
                "    ░██      ░██░░░░    ░░░░░░░░██      ░██    \n" +
                "    ░██      ░██               ░██      ░██    \n" +
                "    ░██      ░████████   ████████       ░██    \n" +
                "    ░░       ░░░░░░░░   ░░░░░░░░        ░░     \n\n\n");
    }

    @Test
    @DisplayName("品牌接口单元测试")
    void brandServiceTest() {
        PmsBrandTestService brandTest = new PmsBrandTestService(brandService);
        brandTest.listAllBrandTest();
        brandTest.createBrandTest();
        brandTest.updateBrandTest();
        brandTest.getBrandTest();
        brandTest.updateFactoryStatusTest();
        brandTest.updateShowStatusTest();
        brandTest.deleteBrandTest();
        brandTest.getBrandPageTest();
        brandTest.deleteBatchTest();
    }
}

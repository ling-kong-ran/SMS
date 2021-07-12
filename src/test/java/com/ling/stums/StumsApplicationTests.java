package com.ling.stums;

import com.ling.stums.dao.ClazzDao;
import com.ling.stums.entity.Clazz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StumsApplicationTests {
    @Autowired
    private ClazzDao clazzDao;
    @Test
    void contextLoads() {
        System.out.println("test生成表");
    }
    @Test
    void testJpa() {
        Clazz one = clazzDao.findById(1).orElse(null);
        assert one != null;
        //one.getStudents().forEach(r-> System.out.println(r.getSname()));
    }

}

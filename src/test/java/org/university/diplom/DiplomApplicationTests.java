package org.university.diplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiplomApplicationTests extends PostgresTest {

    @Test
    void contextLoads() {
    }

    @Test
    void test(){
        Assertions.assertTrue(true);
    }

}

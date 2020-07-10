package com.modules.io;

import com.modules.dao.UserDao;
import com.modules.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    void testArr() {

        User yan = userDao.getUser("zhu");
        System.out.println(yan);
    }


}

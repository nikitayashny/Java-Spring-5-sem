package bstu.yashny.nikitayashny_proj;


import bstu.yashny.nikitayashny_proj.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UserService userService;
    @Test
    public void existsUserByLogin() {
        try {
            Assert.assertTrue(userService.existsUserByLogin("nikita"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void existsUserByLoginAndPassword() {
        try {
            Assert.assertTrue(userService.existsUserByLoginAndPassword("nikita","1234"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

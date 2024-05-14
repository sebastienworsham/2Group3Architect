package main;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK)


public class UserServiceIntegrationTest {
    @Autowired
    private UserService userService;


    @Test
    public void testGetAllUsers(){
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setId(1L); // Set the user ID
        user.setUserName("TestUser 1");
        user.setGameLevel(0);
        userList.add(user);
        User usr2 = new User();
        usr2.setId(2L); // Set the user ID
        usr2.setUserName("TestUser 2");
        usr2.setGameLevel(2);
        userList.add(usr2);

        // first save 2 users in Database
        for (User usr : userList) {
            userService.saveUser(usr);
        }

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());

        User user1 = users.get(0);
        assertEquals(user.getGameLevel(), user1.getGameLevel());
        assertEquals(user.getUserName(), user1.getUserName());

        User user2 = users.get(1);
        assertEquals(usr2.getGameLevel(), user2.getGameLevel());
        assertEquals(usr2.getUserName(), user2.getUserName());
    }

    @Test
    public void testSaveUser(){
        User userToSave = getUser();
        User savedUser = userService.saveUser(userToSave);

        assertEquals(userToSave.getId(), savedUser.getId());
        assertEquals(userToSave.getUserName(), savedUser.getUserName());
        assertEquals(userToSave.getGameLevel(), savedUser.getGameLevel());
    }

    @Test
    public void testUpdateUser() {
        User userToSave = getUser();
        User savedUser = userService.saveUser(userToSave);

        assertEquals(userToSave.getId(), savedUser.getId());
        assertEquals(userToSave.getUserName(), savedUser.getUserName());
        assertEquals(userToSave.getGameLevel(), savedUser.getGameLevel());

        List<User> users = userService.getAllUsers();
        // update game level
        savedUser.setGameLevel(10);
        User updatedUser = userService.updateUser(users, savedUser.getUserName(), savedUser.getGameLevel());

        assertEquals(savedUser.getId(), updatedUser.getId());
        assertEquals(savedUser.getUserName(), updatedUser.getUserName());
        assertEquals(savedUser.getGameLevel(), updatedUser.getGameLevel());
    }

    private  User getUser(){
        User user = new User();
        user.setId(1L); // Set the user ID
        user.setUserName("TestUser");
        user.setGameLevel(2);
        return user;
    }
}
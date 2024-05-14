package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Locale;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(List<User> users, String userName, int gameLevel) {
        boolean userUpdated = false;
        System.out.println("Username to update: [" + userName +"]");
        for (User usr : users) {

            System.out.print("Id: " + usr.getId());
            System.out.print("\tUsername: [" + usr.getUserName()+ "]");
            System.out.println("\tScore: " + usr.getGameLevel());

            if (usr.getUserName().equals(userName)) {
                usr.setGameLevel(gameLevel);
                userRepository.save(usr);
                userUpdated = true;
                return usr;
            }
        }
        if (!userUpdated) {
            System.out.println("User not found");
        }

        return null;
    }
}
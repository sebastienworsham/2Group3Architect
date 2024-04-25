package main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserService {
    private  final UserRepository userRepository;

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

    public void updateUser(List<User> users, User user) {

        for(User usr: users)
        {
            if(usr.getUserName() == user.getUserName())
            {
                userRepository.deleteById(usr.getId());
                 userRepository.save(user);
                break;
            }
        }

    }

    @PostConstruct
    public void loadDataOnStartup() {
        System.out.println("loadDataOnStartup called");
        Main.users = userRepository.findAll();
        System.out.println("loadDataOnStartup called");
    }

    @EventListener
    public void eventListner(ApplicationStartedEvent event)
    {
        System.out.println("eventListner Called");
        Main.users = userRepository.findAll();
        System.out.println("eventListner Called");
    }

}


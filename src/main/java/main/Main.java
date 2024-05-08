package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;
import java.util.*;

@SpringBootApplication
//@ComponentScan
//@EnableAutoConfiguration
public class Main extends Application {
    public static final int GAMEWIDTH = 1320;
    public static final int GAMEHEIGHT = 720;
    public static final String SAVEPATH = "src/main/java/main/save.csv";
    protected Pane levelRoot = new Pane();
    protected Pane playerRoot = new Pane();
    protected Pane uiRoot = new Pane();
    // Users is a HashMap which stores the player name and their current level
    public static List<User> users = new ArrayList<>();

    ConfigurableApplicationContext springContext;

    @Autowired
    private UserService userService;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(Main.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample.fxml"));
        fxmlLoader.setControllerFactory(springContext::getBean);
        //root = fxmlLoader.load();
        springContext
                .getAutowireCapableBeanFactory()
                .autowireBeanProperties(
                        this,
                        AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE,
                        true
                );
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
//    public Server inMemoryDBServer() throws SQLException {
//        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene uiScene = new Scene(uiRoot, GAMEWIDTH, GAMEHEIGHT);
        // Load the CSS file
        uiScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        StartScreen startScreen;
        startScreen = new StartScreen(uiRoot, levelRoot, playerRoot, primaryStage, uiScene, userService);
        startScreen.renderStartScreen(startScreen);

        users = userService.getAllUsers();
        for (User usr : users) {
            System.out.print("Username: " + usr.getUserName());
            System.out.println(", Score: " + usr.getGameLevel());
        }
        primaryStage.setScene(uiScene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }


}



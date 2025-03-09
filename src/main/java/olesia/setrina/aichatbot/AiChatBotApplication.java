package olesia.setrina.aichatbot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import olesia.setrina.aichatbot.Repository.ProfileRepository;
import olesia.setrina.aichatbot.model.Gender;
import olesia.setrina.aichatbot.model.Profile;

@SpringBootApplication
public class AiChatBotApplication implements CommandLineRunner {

    @Autowired
    private ProfileRepository profileRepository;

    public static void main(String[] args) {
        SpringApplication.run(AiChatBotApplication.class, args);
    }

    public void run(String... args) {
        Profile profile = new Profile("1", "Olesia", "Setrina", 37, "Ukrainian", "Java developer", Gender.Female, "test.url", "chess");
        profileRepository.save(profile);
        List<Profile> allProfiles = profileRepository.findAll();
        System.out.println(allProfiles);
    }
}

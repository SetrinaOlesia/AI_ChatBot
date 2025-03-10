package olesia.setrina.aichatbot;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import olesia.setrina.aichatbot.Repository.ConversationRepository;
import olesia.setrina.aichatbot.Repository.ProfileRepository;
import olesia.setrina.aichatbot.conversations.ChatMessage;
import olesia.setrina.aichatbot.conversations.Conversation;
import olesia.setrina.aichatbot.model.Gender;
import olesia.setrina.aichatbot.model.Profile;

@SpringBootApplication
public class AiChatBotApplication implements CommandLineRunner {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    public static void main(String[] args) {
        SpringApplication.run(AiChatBotApplication.class, args);
    }

    public void run(String... args) {
        Profile profile = new Profile("18", "Olesia", "Setrina", 37, "Ukrainian", "Java developer", Gender.Female, "test.url", "chess");
        profileRepository.save(profile);
        List<Profile> allProfiles = profileRepository.findAll();
        System.out.println(allProfiles);

        Conversation conversation = new Conversation("1", profile.id(),
            List.of(new ChatMessage("Setrina Olesia , Hi my dear!", profile.id(), LocalDateTime.now())));
        conversationRepository.save(conversation);
        List<Conversation> conversationList = conversationRepository.findAll();
        System.out.println(conversationList);
    }

}

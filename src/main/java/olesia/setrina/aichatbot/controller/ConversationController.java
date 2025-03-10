package olesia.setrina.aichatbot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import olesia.setrina.aichatbot.conversations.ChatMessage;
import olesia.setrina.aichatbot.conversations.Conversation;
import olesia.setrina.aichatbot.repository.ConversationRepository;
import olesia.setrina.aichatbot.repository.ProfileRepository;

@RestController
public class ConversationController {

    private ConversationRepository conversationRepository;

    private ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }

    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
        profileRepository.findById(request.profileId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found" + request.profileId));
        Conversation conversation = new Conversation(UUID.randomUUID()
            .toString(), request.profileId(), new ArrayList<>());
        conversationRepository.save(conversation);
        return conversation;
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessage chatMessage) {
        Conversation conversation = conversationRepository.findById(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation message not found" + conversationId));

        profileRepository.findById(chatMessage.authorId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found" + chatMessage.authorId()));

        ChatMessage messageWithTime = new ChatMessage(chatMessage.messageText(), chatMessage.authorId(), LocalDateTime.now());

        conversation.messages()
            .add(messageWithTime);
        conversationRepository.save(conversation);
        return conversation;
    }

    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation message not found" + conversationId));
        return conversation;
    }

    public record CreateConversationRequest(String profileId) {

    }

}

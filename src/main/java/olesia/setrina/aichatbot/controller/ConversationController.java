package olesia.setrina.aichatbot.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import olesia.setrina.aichatbot.conversations.ChatMessage;
import olesia.setrina.aichatbot.conversations.Conversation;
import olesia.setrina.aichatbot.service.ConversationService;
import olesia.setrina.aichatbot.service.ProfileService;

@RestController
@RequiredArgsConstructor
public class ConversationController {

    private final ConversationService conversationService;

    private final ProfileService profileService;

    @PostMapping("/conversations")
    public Conversation createNewConversation(@RequestBody CreateConversationRequest request) {
        profileService.getProfile(request.profileId);
        Conversation conversation = new Conversation(UUID.randomUUID()
            .toString(), request.profileId(), new ArrayList<>());
        conversationService.saveConversation(conversation);
        return conversation;
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(@PathVariable String conversationId, @RequestBody ChatMessage chatMessage) {
        Conversation conversation = conversationService.getConversationById(conversationId);
        profileService.getProfile(chatMessage.authorId());
        ChatMessage messageWithTime = new ChatMessage(chatMessage.messageText(), chatMessage.authorId(), LocalDateTime.now());

        conversation.messages()
            .add(messageWithTime);
        conversationService.saveConversation(conversation);
        return conversation;
    }

    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversation(@PathVariable String conversationId) {
        return conversationService.getConversationById(conversationId);
    }

    public record CreateConversationRequest(String profileId) {

    }

}

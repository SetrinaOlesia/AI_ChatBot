package olesia.setrina.aichatbot.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import olesia.setrina.aichatbot.conversations.Conversation;
import olesia.setrina.aichatbot.repository.ConversationRepository;

@Service
@RequiredArgsConstructor
public class ConversationService {

    private final ConversationRepository conversationRepository;

    public void saveConversation(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public Conversation getConversationById(String conversationId) {
        return conversationRepository.findById(conversationId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conversation message not found" + conversationId));
    }
}

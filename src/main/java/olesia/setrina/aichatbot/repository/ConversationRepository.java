package olesia.setrina.aichatbot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import olesia.setrina.aichatbot.conversations.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

}

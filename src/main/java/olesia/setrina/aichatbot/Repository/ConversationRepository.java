package olesia.setrina.aichatbot.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import olesia.setrina.aichatbot.conversations.Conversation;
import olesia.setrina.aichatbot.model.Profile;

public interface ConversationRepository extends MongoRepository<Conversation, String> {

}

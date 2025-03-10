package olesia.setrina.aichatbot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import olesia.setrina.aichatbot.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

}

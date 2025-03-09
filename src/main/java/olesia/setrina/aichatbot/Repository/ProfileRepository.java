package olesia.setrina.aichatbot.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import olesia.setrina.aichatbot.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

}

package olesia.setrina.aichatbot.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import olesia.setrina.aichatbot.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

    @Aggregation(pipeline = { "{$sample: {size: 1}}" })
    Profile getRandomProfile();
}

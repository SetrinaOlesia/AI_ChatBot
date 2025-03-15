package olesia.setrina.aichatbot.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import olesia.setrina.aichatbot.model.Profile;
import olesia.setrina.aichatbot.repository.ProfileRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getRandomProfile() {
        return profileRepository.getRandomProfile();
    }

    public void getProfile(String profileId) {
        profileRepository.findById(profileId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found" + profileId));
    }
}

package olesia.setrina.aichatbot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import olesia.setrina.aichatbot.model.Profile;
import olesia.setrina.aichatbot.service.ProfileService;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profiles/random")
    public Profile getRandomProfile() {
        return profileService.getRandomProfile();
    }

}

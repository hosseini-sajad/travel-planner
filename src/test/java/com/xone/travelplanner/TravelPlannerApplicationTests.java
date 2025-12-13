package com.xone.travelplanner;

import com.xone.travelplanner.core.Gender;
import com.xone.travelplanner.core.TravelException;
import com.xone.travelplanner.core.UserRole;
import com.xone.travelplanner.model.User;
import com.xone.travelplanner.service.UserService;
import org.hibernate.event.internal.EntityState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class TravelPlannerApplicationTests {

    @Autowired
    private UserService userService;

	@Test
	void contextLoads() {
	}

    @Test
    void createBackdoorUser() {
        User backdoorUser = User.builder()
                .name("sajad")
                .email("saji@gmail.com")
                .password("123456")
                .isActive(true)
                .gender(Gender.MALE)
                .phone("123456789")
                .role(UserRole.Backdoor)
                .lastLogin(new Date())
                .isFirstLogin(true)
                .build();

        backdoorUser.setEntityState(EntityState.PERSISTENT);

        try {
            userService.register(backdoorUser);
        } catch (TravelException e) {
            e.printStackTrace();
        }

    }

}

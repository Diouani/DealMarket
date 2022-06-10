package com.parent.dealmarketadminsection.admin.user;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.bcrypt.*;

 class PasswordEncoderTest {

    @Test
    void testEncodePassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "test123123123";
        String encodedPassword = passwordEncoder.encode(password);

        boolean matches = passwordEncoder.matches(password, encodedPassword);


        assertThat(matches).isTrue();
    }
}

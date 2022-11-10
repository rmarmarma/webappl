package com.practice.webappl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("lebronjames@gmail.com");
        user.setPassword("lebronjamespassword");
        user.setFirstName("LeBron");
        user.setLastName("James");
        user.setDream("Become World's Greatest Basketball Player");
        User userRecords = repository.save(user);
        User existingUser = entityManager.find(User.class, userRecords.getId());
        assertThat(existingUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "lebronjames@gmail.com";
        User user = repository.findByEmail(email);
        assertThat(user).isNotNull();
    }
}

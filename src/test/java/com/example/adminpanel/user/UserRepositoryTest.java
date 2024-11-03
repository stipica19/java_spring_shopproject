package com.example.adminpanel.user;

import com.example.adminpanel.entity.Role;
import com.example.adminpanel.entity.User;
import com.example.adminpanel.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    @Transactional // ili odgovarajuća anotacija za transakcijski menadžment
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 2);

        User userStipica = new User("maca@gmail.net", "maca123", "Maca", "Maca");
        userStipica.addRole(roleAdmin);

        entityManager.persist(userStipica);
        entityManager.flush(); // obavezno da bi se osigurala perzistentnost u testu

        User foundUser = entityManager.find(User.class, userStipica.getId());
        assertNotNull(foundUser);
        assertEquals(1, foundUser.getRoles().size());
        assertTrue(foundUser.getRoles().contains(roleAdmin));
    }
}

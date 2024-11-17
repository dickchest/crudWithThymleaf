package crud.thymleaf.demo;

import crud.thymleaf.demo.user.User;
import crud.thymleaf.demo.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("test2@gmail.com");
        user.setPassword("password2");
        user.setFirstName("Name2");
        user.setLastName("Lastname2");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        List<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        Optional<User> optionalUser = repo.findById(id);
        User user = optionalUser.get();
        user.setPassword("new password");
        repo.save(user);

        User updatedUser = repo.findById(id).get();

        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("new password");
    }

    @Test
    public void testGet() {
        Integer id = 2;
        Optional<User> optionalUser = repo.findById(id);

        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer id = 2;
        repo.deleteById(id);

        Optional<User> optionalUser = repo.findById(id);

        Assertions.assertThat(optionalUser).isNotPresent();
    }
}

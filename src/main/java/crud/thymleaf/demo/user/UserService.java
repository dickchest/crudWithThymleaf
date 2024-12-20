package crud.thymleaf.demo.user;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any user with ID: " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        var count = repo.countById(id);
        if (count==null || count == 0) {
            throw new UserNotFoundException("Could not find any user with ID: " + id);
        }
        repo.deleteById(id);
    }
}

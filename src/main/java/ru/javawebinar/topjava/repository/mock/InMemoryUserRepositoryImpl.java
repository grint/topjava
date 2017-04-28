package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(null, "user", "user@domain.com", "pwd", Role.ROLE_USER));
        save(new User(null, "John", "john@domain.com", "pwd", Role.ROLE_USER));
        save(new User(null, "Chris", "chris@domain.com", "pwd", Role.ROLE_USER));
        save(new User(null, "admin", "admin@domain.com", "pwd", Role.ROLE_ADMIN, Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        LOG.info("Delete user with id " + id);

        if (!repository.containsKey(id))
            return false;

        repository.remove(id);

        return true;
    }

    @Override
    public User save(User user) {
        if (user.isNew())
            user.setId(counter.incrementAndGet());

        repository.put(user.getId(), user);

        LOG.info("Save user " + user);

        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("Get user with id " + id);

        if (!repository.containsKey(id))
            return null;

        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("Get all users");

        List<User> result = new ArrayList<>(repository.values());
        result.sort((o1, o2) -> o1.getEmail().compareTo(o2.getEmail()));
        result.forEach(u -> LOG.info(u.toString()));

        return Optional.of(result).orElseGet(Collections::emptyList);
    }

    @Override
    public User getByEmail(String email) {
        User user = repository.values().stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny()
                .orElse(null);

        LOG.info("Get user by e-mail " + email);
        LOG.info(user.toString());

        return user;
    }
}

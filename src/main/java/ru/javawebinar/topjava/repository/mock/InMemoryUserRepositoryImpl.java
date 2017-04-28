package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(null,"user","user@domain.com","pwd", Role.ROLE_USER));
        save(new User(null,"John","john@domain.com","pwd", Role.ROLE_USER));
        save(new User(null,"Chris","chris@domain.com","pwd", Role.ROLE_USER));
        save(new User(null,"admin","admin@domain.com","pwd", Role.ROLE_ADMIN, Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        User user = repository.remove(id);
        return user != null;
    }

    @Override
    public User save(User user) {
        if (user.isNew()){
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>(repository.values());
        Collections.sort(result, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return result;
    }

    @Override
    public User getByEmail(String email) {
        return repository.values().stream()
                .filter(x -> email.equals(x.getEmail()))
                .findAny()
                .orElse(null);
    }
}
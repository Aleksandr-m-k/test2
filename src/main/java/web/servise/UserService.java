package web.servise;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    User getUserById(int id);
    void save(User user);
    User update(User user);
    void deleteUser(int id);
}

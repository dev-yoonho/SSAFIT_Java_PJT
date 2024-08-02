package ssafitPjt;

import java.util.List;

public interface UserDao {
    void saveUsersToFile();
    void loadUsersFromFile();
    void addUser(User user);
    User getUserById(String id);
    List<User> getAllUsers();
}

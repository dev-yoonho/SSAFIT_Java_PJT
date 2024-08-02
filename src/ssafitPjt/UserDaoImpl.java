package ssafitPjt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private List<User> users;
    private static UserDaoImpl instance;
    private String fileName = "users.json";

    private UserDaoImpl() {
        users = new ArrayList<>();
    }

    public static UserDaoImpl getInstance() {
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    @Override
    public void saveUsersToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadUsersFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("users.json file created.");
                    saveUsersToFile(); // 빈 파일에 빈 리스트 저장
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            users = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public User getUserById(String id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }
}

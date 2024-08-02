package ssafitPjt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class VideoDaoImpl implements VideoDao {
    private List<Video> list;
    private static VideoDaoImpl instance;

    private VideoDaoImpl() {
        list = new ArrayList<>();
    }

    public static VideoDaoImpl getInstance() {
        if (instance == null) {
            instance = new VideoDaoImpl();
        }
        return instance;
    }

    @Override
    public List<Video> getAllVideos() {
        return list;
    }

    @Override
    public Video getVideoByNo(int no) {
        return list.stream()
                   .filter(video -> video.getNo() == no)
                   .findFirst()
                   .orElse(null);
    }

    public void loadData(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = new TypeToken<ArrayList<Video>>() {}.getType();
            list = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

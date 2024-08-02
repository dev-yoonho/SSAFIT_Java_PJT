package ssafitPjt;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VideoReviewDaoImpl implements VideoReviewDao {
    private int reviewNo;
    private Map<Integer, List<VideoReview>> videoReviewDb;
    private static VideoReviewDaoImpl instance;
    private String fileName = "reviews.json";

    private VideoReviewDaoImpl() {
        reviewNo = 0;
        videoReviewDb = new HashMap<>();
    }

    public static VideoReviewDaoImpl getInstance() {
        if (instance == null) {
            instance = new VideoReviewDaoImpl();
        }
        return instance;
    }

    @Override
    public int insertReview(VideoReview videoReview) {
        videoReview.setReviewNo(++reviewNo);
        videoReviewDb.computeIfAbsent(videoReview.getVideoNo(), k -> new ArrayList<>()).add(videoReview);
        return reviewNo;
    }

    @Override
    public List<VideoReview> getReviewsByVideoNo(int videoNo) {
        return videoReviewDb.getOrDefault(videoNo, new ArrayList<>());
    }

    @Override
    public Map<Integer, List<VideoReview>> getAllReviews() {
        return videoReviewDb;
    }

    public void saveReviewsToFile() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(videoReviewDb, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadReviewsFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("reviews.json file created.");
                    saveReviewsToFile(); // 빈 파일에 빈 리스트 저장
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        try (FileReader reader = new FileReader(fileName)) {
            Type mapType = new TypeToken<HashMap<Integer, List<VideoReview>>>() {}.getType();
            videoReviewDb = gson.fromJson(reader, mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

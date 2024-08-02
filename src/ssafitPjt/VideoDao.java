package ssafitPjt;

import java.util.List;

public interface VideoDao {
    List<Video> getAllVideos();
    Video getVideoByNo(int no);
}


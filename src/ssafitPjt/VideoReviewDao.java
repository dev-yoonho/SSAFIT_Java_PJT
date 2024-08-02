package ssafitPjt;

import java.util.List;
import java.util.Map;

public interface VideoReviewDao {
    int insertReview(VideoReview videoReview);
    List<VideoReview> getReviewsByVideoNo(int videoNo);
    Map<Integer, List<VideoReview>> getAllReviews(); // 모든 리뷰를 가져오는 메서드 추가
}

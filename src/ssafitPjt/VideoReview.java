package ssafitPjt;

public class VideoReview {
    private int videoNo;
    private int reviewNo;
    private String nickName;
    private String userId;
    private String content;

    // Getters and Setters
    public int getVideoNo() {
        return videoNo;
    }

    public void setVideoNo(int videoNo) {
        this.videoNo = videoNo;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "비디오 번호: " + videoNo + "\n" +
               "리뷰 번호: " + reviewNo + "\n" +
               "닉네임: " + nickName + "\n" +
               "내용: " + content + "\n";
    }
}

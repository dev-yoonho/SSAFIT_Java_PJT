package ssafitPjt;

import java.util.List;
import java.util.Scanner;

public class VideoReviewUi {
    private VideoReviewDao videoReviewDao;
    private int videoNo;
    private User currentUser;

    public VideoReviewUi(VideoReviewDao videoReviewDao, int videoNo, User currentUser) {
        this.videoReviewDao = videoReviewDao;
        this.videoNo = videoNo;
        this.currentUser = currentUser;
    }

    public void service() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("리뷰 관리 목록");
            System.out.println("1. 리뷰 등록");
            if (!videoReviewDao.getReviewsByVideoNo(videoNo).isEmpty()) {
                System.out.println("2. 리뷰 목록");
            }
            System.out.println("0. 종료");
            System.out.print("옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    registReview(scanner);
                    break;
                case 2:
                    if (!videoReviewDao.getReviewsByVideoNo(videoNo).isEmpty()) {
                        listReview();
                    } else {
                        System.out.println("잘못된 선택입니다.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void listReview() {
        List<VideoReview> reviews = videoReviewDao.getReviewsByVideoNo(videoNo);
        reviews.forEach(System.out::println);
    }

    private void registReview(Scanner scanner) {
        System.out.print("리뷰를 작성해주세요: ");
        String content = scanner.nextLine();

        VideoReview review = new VideoReview();
        review.setVideoNo(videoNo);
        review.setNickName(currentUser.getName());
        review.setUserId(currentUser.getId());
        review.setContent(content);

        videoReviewDao.insertReview(review);
        System.out.println("성공적으로 리뷰가 추가되었습니다.");
    }
}

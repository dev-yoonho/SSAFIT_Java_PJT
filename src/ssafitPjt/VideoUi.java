package ssafitPjt;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VideoUi {
    private VideoDao videoDao;
    private VideoReviewDao videoReviewDao;
    private User currentUser;

    public VideoUi(VideoDao videoDao, VideoReviewDao videoReviewDao, User currentUser) {
        this.videoDao = videoDao;
        this.videoReviewDao = videoReviewDao;
        this.currentUser = currentUser;
    }

    public void service() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printLine();
            System.out.println("비디오 관리 메뉴");
            printLine();
            System.out.println("1. 비디오 목록 보기");
            System.out.println("2. 비디오 세부 정보 보기");
            System.out.println("3. 모든 리뷰 보기");
            System.out.println("4. 리뷰 등록하기");
            System.out.println("0. 종료");
            printLine();
            System.out.print("옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    listVideos();
                    break;
                case 2:
                    videoDetails(scanner);
                    break;
                case 3:
                    listAllReviews();
                    break;
                case 4:
                    manageReviews(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void listVideos() {
        printLine();
        List<Video> videos = videoDao.getAllVideos();
        videos.forEach(video -> {
            System.out.println(video);
            printLine();
        });
    }

    private void videoDetails(Scanner scanner) {
        System.out.print("비디오 번호를 입력하세요: ");
        int no = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Video video = videoDao.getVideoByNo(no);
        printLine();
        if (video != null) {
            System.out.println(video);
        } else {
            System.out.println("비디오를 찾을 수 없습니다.");
        }
        printLine();
    }

    private void listAllReviews() {
        Map<Integer, List<VideoReview>> allReviews = videoReviewDao.getAllReviews();
        printLine();
        if (allReviews.isEmpty()) {
            System.out.println("등록된 리뷰가 없습니다.");
        } else {
            allReviews.forEach((videoNo, reviews) -> {
                reviews.forEach(review -> {
                    System.out.println(review);
                    printLine();
                });
            });
        }
    }

    private void manageReviews(Scanner scanner) {
        System.out.print("리뷰를 등록할 비디오 번호를 입력하세요: ");
        int videoNo = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Video video = videoDao.getVideoByNo(videoNo);
        if (video == null) {
            System.out.println("비디오를 찾을 수 없습니다.");
            return;
        }

        VideoReviewUi videoReviewUi = new VideoReviewUi(videoReviewDao, videoNo, currentUser);
        videoReviewUi.service();
    }

    private void printLine() {
        System.out.println("--------------------------------------------------");
    }
}

package ssafitPjt;

import java.util.Scanner;

public class MainUi {
    private User currentUser;
    private UserDao userDao;
    private VideoDaoImpl videoDao;
    private VideoReviewDaoImpl videoReviewDao;

    public MainUi() {
        this.userDao = UserDaoImpl.getInstance();
        this.videoDao = VideoDaoImpl.getInstance();
        this.videoReviewDao = VideoReviewDaoImpl.getInstance();
    }

    public void service() {
        userDao.loadUsersFromFile();
        videoDao.loadData("data/video.json"); // JSON 파일 경로 지정
        videoReviewDao.loadReviewsFromFile();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            printLine();
            System.out.println("메인 메뉴");
            printLine();
            System.out.println("1. 회원 관리");
            if (currentUser == null) {
                System.out.println("2. 비디오 관리 (로그인 필요)");
            } else {
                System.out.println("2. 비디오 관리");
            }
            System.out.println("0. 종료");
            printLine();
            System.out.print("옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    UserUi userUi = new UserUi(userDao);
                    userUi.service();
                    currentUser = userUi.getCurrentUser(); // 로그인 상태 업데이트
                    break;
                case 2:
                    if (currentUser == null) {
                        System.out.println("로그인 먼저 해주세요.");
                    } else {
                        VideoUi videoUi = new VideoUi(videoDao, videoReviewDao, currentUser);
                        videoUi.service();
                    }
                    break;
                case 0:
                    userDao.saveUsersToFile();
                    videoReviewDao.saveReviewsToFile();
                    exit();
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    private void exit() {
        printLine();
        System.out.println("프로그램을 종료합니다...");
        printLine();
    }

    private void printLine() {
        System.out.println("--------------------------------------------------");
    }
}

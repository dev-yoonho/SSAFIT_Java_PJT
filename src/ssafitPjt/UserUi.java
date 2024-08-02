package ssafitPjt;

import java.util.List;
import java.util.Scanner;

public class UserUi {
    private UserDao userDao;
    private User currentUser;

    public UserUi(UserDao userDao) {
        this.userDao = userDao;
    }

    public void service() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printLine();
            System.out.println("회원 관리 메뉴");
            printLine();
            System.out.println("1. 회원 가입");
            if (currentUser == null) {
                System.out.println("2. 로그인");
            } else {
                System.out.println("2. 로그아웃");
                System.out.println("3. 회원 프로필 보기");
            }
            System.out.println("0. 종료");
            printLine();
            System.out.print("옵션을 선택하세요: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    if (currentUser == null) {
                        login(scanner);
                    } else {
                        logout();
                    }
                    break;
                case 3:
                    if (currentUser != null) {
                        showUserList();
                    } else {
                        System.out.println("잘못된 선택입니다.");
                    }
                    break;
                case 0:
                    userDao.saveUsersToFile();
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }

    public void register(Scanner scanner) {
        User user = new User();
        System.out.print("아이디를 입력하세요: ");
        user.setId(scanner.nextLine());
        System.out.print("이름을 입력하세요: ");
        user.setName(scanner.nextLine());
        System.out.print("비밀번호를 입력하세요: ");
        user.setPassword(scanner.nextLine());
        System.out.print("이메일을 입력하세요: ");
        user.setEmail(scanner.nextLine());
        System.out.print("추가 정보 1을 입력하세요: ");
        user.setAdditionalInfo1(scanner.nextLine());
        System.out.print("추가 정보 2를 입력하세요: ");
        user.setAdditionalInfo2(scanner.nextLine());
        System.out.print("추가 정보 3을 입력하세요: ");
        user.setAdditionalInfo3(scanner.nextLine());

        userDao.addUser(user);
        printLine();
        System.out.println("회원 가입이 완료되었습니다.");
        printLine();
    }

    public void login(Scanner scanner) {
        System.out.print("아이디를 입력하세요: ");
        String id = scanner.nextLine();
        System.out.print("비밀번호를 입력하세요: ");
        String password = scanner.nextLine();

        User user = userDao.getUserById(id);
        printLine();
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("로그인 성공.");
        } else {
            System.out.println("아이디 또는 비밀번호가 잘못되었습니다.");
        }
        printLine();
    }

    public void logout() {
        currentUser = null;
        printLine();
        System.out.println("로그아웃 되었습니다.");
        printLine();
    }

    public void showUserList() {
        printLine();
        List<User> users = userDao.getAllUsers();
        users.forEach(user -> {
            System.out.println(user);
            printLine();
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void printLine() {
        System.out.println("--------------------------------------------------");
    }
}

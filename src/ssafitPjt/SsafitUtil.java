package ssafitPjt;

import java.util.Scanner;

public class SsafitUtil {
    private Scanner sc;

    public SsafitUtil() {
        sc = new Scanner(System.in);
    }

    // 문자열 입력 메서드
    public String input(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    // 정수 입력 메서드
    public int inputInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer.");
            sc.next(); // consume the invalid input
            System.out.print(msg);
        }
        int result = sc.nextInt();
        sc.nextLine(); // consume the newline
        return result;
    }

    // 화면 분할선 출력
    public void printLine() {
        printLine('-', 80);
    }

    // 특정 문자로 화면 분할선 출력
    public void printLine(char ch) {
        printLine(ch, 80);
    }

    // 특정 문자와 길이로 화면 분할선 출력
    public void printLine(char ch, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(ch);
        }
        System.out.println();
    }

    // 화면 클리어 메서드
    public void screenClear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


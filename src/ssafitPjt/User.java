package ssafitPjt;

public class User {
    private String id;
    private String name;
    private String password;
    private String email;
    private String additionalInfo1;
    private String additionalInfo2;
    private String additionalInfo3;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdditionalInfo1() {
        return additionalInfo1;
    }

    public void setAdditionalInfo1(String additionalInfo1) {
        this.additionalInfo1 = additionalInfo1;
    }

    public String getAdditionalInfo2() {
        return additionalInfo2;
    }

    public void setAdditionalInfo2(String additionalInfo2) {
        this.additionalInfo2 = additionalInfo2;
    }

    public String getAdditionalInfo3() {
        return additionalInfo3;
    }

    public void setAdditionalInfo3(String additionalInfo3) {
        this.additionalInfo3 = additionalInfo3;
    }

    @Override
    public String toString() {
        return "아이디: " + id + "\n" +
               "이름: " + name + "\n" +
               "이메일: " + email + "\n" +
               "추가 정보 1: " + additionalInfo1 + "\n" +
               "추가 정보 2: " + additionalInfo2 + "\n" +
               "추가 정보 3: " + additionalInfo3 + "\n";
    }
}

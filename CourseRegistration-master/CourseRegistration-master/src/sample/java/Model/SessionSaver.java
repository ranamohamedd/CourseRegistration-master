package sample.java.Model;

public class SessionSaver {

    private static String username = "";
    private  static String password="";
    private static String role="";

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionSaver.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        SessionSaver.password = password;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionSaver.role = role;
    }
}

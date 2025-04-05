package Utils; // hoặc package tương ứng nếu bạn để chỗ khác

public class Session {
    private static String currentUsername;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }
}

package Utils; // hoặc package tương ứng nếu bạn để chỗ khác

import DTO.TaiKhoanDTO;

public class Session {
    private static TaiKhoanDTO currentUser;

    public static void setUser(TaiKhoanDTO user) {
        currentUser = user;
    }

    public static TaiKhoanDTO getUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }
}
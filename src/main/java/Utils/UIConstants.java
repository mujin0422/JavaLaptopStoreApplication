package Utils;

import java.awt.Color;
import java.awt.Font;

public class UIConstants {
    // Kích thước Frame
    public static final int WIDTH = 1300;
    public static final int HEIGHT = 700;
    // Kích thước thành phần chính của Frame
    public static final int WIDTH_TITLE = WIDTH;
    public static final int HEIGHT_TITLE = 50;
    public static final int WIDTH_MENU = 200;
    public static final int HEIGHT_MENU = HEIGHT - HEIGHT_TITLE;
    public static final int WIDTH_CONTENT = WIDTH - WIDTH_MENU;
    public static final int HEIGHT_CONTENT = HEIGHT - HEIGHT_TITLE;
    
    
    // Màu chữ
    public static final Color WHITE_FONT = new Color(255, 255, 255);
    public static final Color BLACK_FONT = new Color(0,0,0);
    
    // Màu sắc chính
    public static final Color MAIN_BACKGROUND = new Color(204, 204, 204); // Xám nhạt
    public static final Color SUB_BACKGROUND = new Color(128,128,128); // Xám thường 
    public static final Color MAIN_BUTTON = new Color(15, 41, 47); //Lục đen
    public static final Color HOVER_BUTTON = new Color(203, 45, 111);//Hồng
    public static final Color BORDER = new Color(203, 45, 111);//lục sáng

    // Font chữ
    public static final Font TITLE_FONT = new Font("Roboto", Font.BOLD, 18);
    public static final Font SUBTITLE_FONT = new Font("Roboto", Font.PLAIN, 14);
    public static final Font BODY_FONT = new Font("Roboto", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Roboto", Font.BOLD, 14);
    
    // Màu button
    public static final Color BUTTON_BLUE = new Color(0, 122, 255); 
    public static final Color BUTTON_GREEN = new Color(0, 200, 83);  
    public static final Color BUTTON_RED = new Color(255, 0, 0);     
    public static final Color BUTTON_YELLOW = new Color(255, 193, 7);  
    public static final Color BUTTON_INFO = new Color(0, 188, 212);     
    public static final Color BUTTON_DEFAULT = new Color(108, 117, 125); 
}
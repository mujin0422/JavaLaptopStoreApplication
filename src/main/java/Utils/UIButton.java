package Utils;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public final class UIButton extends JButton implements MouseListener {
    private Color defaultColor; 
    private String buttonType;  
    
    public void initComponent(String typeBtn, String text, int width, int height) {
        Color bgColor = UIConstants.BUTTON_DEFAULT;
        this.buttonType = typeBtn; 

        if (typeBtn != null) {
            switch (typeBtn) {
                case "add" -> bgColor = UIConstants.BUTTON_GREEN;
                case "delete" -> bgColor = UIConstants.BUTTON_RED;
                case "edit" -> bgColor = UIConstants.BUTTON_BLUE;
                case "confirm" -> bgColor = UIConstants.BUTTON_GREEN;
                case "login" -> bgColor = UIConstants.BUTTON_BLUE;
                case "menuButton" -> bgColor = UIConstants.MAIN_BUTTON;
            }
        }

        this.defaultColor = bgColor; // Lưu màu gốc
        this.setText(text);
        this.setFont(UIConstants.FONT_BUTTON);
        this.setBackground(bgColor);
        if ("menuButton".equals(typeBtn)){
            this.setForeground(UIConstants.WHITE_FONT);
        } else {
            this.setForeground(UIConstants.BLACK_FONT);
        }
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(width, height));

        this.setHorizontalAlignment(SwingConstants.LEFT);  // Căn trái toàn bộ nội dung
        this.setHorizontalTextPosition(SwingConstants.RIGHT);  // Văn bảnt bên phải icon
        this.setMargin(new Insets(0, 5, 0, 0));
        
        this.addMouseListener(this);
    }
    
    public UIButton(String typeBtn, String text) {
        initComponent(typeBtn, text, 100, 40);
    }
    public UIButton(String typeBtn, String text, int width, int height) {
        initComponent(typeBtn, text, width, height);
    }
    public UIButton(String typeBtn, String text, int width, int height, String urlIcon) {
        initComponent(typeBtn, text, width, height);
        setButtonIcon(urlIcon);
    }

    public void setButtonIcon(String urlImage) {
        if (urlImage != null && !urlImage.isEmpty()) {
            ImageIcon icon = new ImageIcon(getClass().getResource(urlImage));
            Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
            this.setHorizontalTextPosition(SwingConstants.RIGHT);
            this.setIconTextGap(10); // Khoảng cách giữa icon và text
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if ("menuButton".equals(buttonType)) 
            this.setBackground(UIConstants.HOVER_BUTTON); // Màu hover khác cho menuButton
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if ("menuButton".equals(buttonType)) {
            this.setBackground(defaultColor); // Trả về màu gốc
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

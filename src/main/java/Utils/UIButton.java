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
    private boolean hasIcon = false;
    
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
    
    public void initComponent(String typeBtn, String text, int width, int height) {
        Color bgColor = UIConstants.BUTTON_DEFAULT;
        this.buttonType = typeBtn;

        if (typeBtn != null) {
            switch (typeBtn) {
                case "add", "confirm" -> bgColor = UIConstants.BUTTON_GREEN;
                case "delete", "exit" -> bgColor = UIConstants.BUTTON_RED;
                case "cancel", "edit" -> bgColor = UIConstants.BUTTON_BLUE;
                case "menuButton" -> bgColor = UIConstants.MAIN_BUTTON;
            }
        }

        this.defaultColor = bgColor;
        this.setText(text);
        this.setFont(UIConstants.FONT_BUTTON);
        this.setBackground(bgColor);
        this.setForeground("menuButton".equals(typeBtn) ? UIConstants.WHITE_FONT : UIConstants.BLACK_FONT);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setPreferredSize(new Dimension(width, height));
        this.setMargin(new Insets(0, 5, 0, 0));

        updateTextAlignment(); 
        this.addMouseListener(this);
    }
    
    public void setButtonIcon(String urlImage) {
        if (urlImage != null && !urlImage.isEmpty()) {
            ImageIcon icon = new ImageIcon(getClass().getResource(urlImage));
            Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
            this.setIconTextGap(3);
            hasIcon = true; 
        } else {
            hasIcon = false;
        }
        updateTextAlignment(); 
    }

    private void updateTextAlignment() {
        if (hasIcon) {
            this.setHorizontalAlignment(SwingConstants.LEFT);  // Căn trái toàn bộ nội dung
            this.setHorizontalTextPosition(SwingConstants.RIGHT);  // Văn bản bên phải icon
        } else {
            this.setHorizontalAlignment(SwingConstants.CENTER); 
        }
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        if ("menuButton".equals(buttonType)) 
            this.setBackground(UIConstants.HOVER_BUTTON);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if ("menuButton".equals(buttonType)) {
            this.setBackground(defaultColor);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}

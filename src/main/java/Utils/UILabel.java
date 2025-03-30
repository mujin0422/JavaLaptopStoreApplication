package Utils;

import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;

public class UILabel extends JLabel {
    
    public UILabel(String text, int width, int height) {
        initComponent(text, width, height);
    }
    
    public UILabel(String text) {
        initComponent(text, 100, 20);
    }
    
    private void initComponent(String text, int width, int height) {
        this.setText(text); 
        this.setPreferredSize(new Dimension(width, height)); 
        this.setFont(UIConstants.FONT_BUTTON); 
        this.setHorizontalAlignment(JLabel.LEFT); 
    }
}

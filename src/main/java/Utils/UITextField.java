package Utils;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class UITextField extends JTextField{
    public UITextField(int width, int height){
        initComponent(width, height);
    }
    
    private void initComponent(int width, int height){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(UIConstants.WHITE_FONT);
        this.setBorder(new EmptyBorder(0, 5, 0, 0));
    }
    
   
    public void setEditable(boolean editable) {
        super.setEditable(editable);
        if (!editable) {
            this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));  
        } else {
            this.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));  
        }
    }
}

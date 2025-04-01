package Utils;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class UIAboutPanel extends JPanel {
    private JPanel pnlIcon_Text, pnlButton ,pnlContent;
    
    public UIAboutPanel(String iconUrl, String text, int width, int height) {
        initComponent(iconUrl, text, width, height);
    }
    
    public void initComponent(String iconUrl, String text, int width, int height){
        this.setBackground(UIConstants.MAIN_BUTTON);
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        //==========================( PANEL ICON AND TEXT )=============================//
        pnlIcon_Text = new JPanel(new BorderLayout());
        pnlIcon_Text.setBackground(UIConstants.MAIN_BUTTON);
        pnlIcon_Text.setPreferredSize(new Dimension(width *20/100, height));
            // Icon
        int iconWidth = pnlIcon_Text.getPreferredSize().width;
        int iconHeight = iconWidth;
        JLabel lblIcon = new JLabel();
        lblIcon.setBackground(UIConstants.MAIN_BUTTON);
        ImageIcon icon = new ImageIcon(getClass().getResource(iconUrl));
        Image scaleIcon = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        lblIcon.setIcon(new ImageIcon(scaleIcon));
            // Text
        JLabel lblText = new JLabel(text, JLabel.CENTER);
        lblText.setBackground(UIConstants.MAIN_BUTTON);
        lblText.setFont(UIConstants.TITLE_FONT);
        lblText.setForeground(UIConstants.WHITE_FONT);
        
        pnlIcon_Text.add(lblIcon,BorderLayout.CENTER);
        pnlIcon_Text.add(lblText,BorderLayout.SOUTH);
        //========================( End Panel Icon And Text )===========================//
        
        
        
        //==============================( PANEL BUTTON )================================//
        pnlButton = new JPanel();
        pnlButton.setPreferredSize(new Dimension(width *20/100, height));
        //============================( End Panel Button )==============================//
        
        //==============================( PANEL CONTENT )===============================//
        pnlContent = new JPanel();
        pnlContent.setPreferredSize(new Dimension(width *60/100, height));
        pnlContent.setLayout(new BorderLayout());
        //============================( End Panel Content )=============================//

        this.add(pnlIcon_Text);
        this.add(pnlButton);
        this.add(pnlContent);
    }
    
    
    public void addButton(UIButton button) {
        pnlButton.add(button);
    }

    public JPanel getPnlContent() {
        return pnlContent;
    }
}

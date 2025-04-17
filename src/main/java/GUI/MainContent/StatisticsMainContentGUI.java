package GUI.MainContent;

import GUI.ThongKeComponent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class StatisticsMainContentGUI extends JPanel{
    private UIButton btnDoanhThu, btnSach;
    private JPanel pnlHeader, pnlContent;
    private ThongKeDoanhThu panelTkDoanhThu;
    private ThongKeSanPham panelTkSanPham;
    
    public StatisticsMainContentGUI() {
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));
        
        panelTkDoanhThu = new ThongKeDoanhThu();
        panelTkSanPham = new ThongKeSanPham();
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 40));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnDoanhThu = new UIButton("menuButton", "DOANH THU", 120, 30);
        btnSach = new UIButton("menuButton", "SẢN PHẨM", 120, 30);
        btnDoanhThu.addActionListener(e -> switchPanel(panelTkDoanhThu));
        btnSach.addActionListener(e -> switchPanel(panelTkSanPham));
        
        pnlButton.add(btnDoanhThu);
        pnlButton.add(btnSach);
        pnlHeader.add(pnlButton, BorderLayout.WEST);
        
        
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
 
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        
    }
     private void switchPanel(JPanel newPanel) {
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }
}


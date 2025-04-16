package GUI.MainContent;

import GUI.ThongKeComponent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class StatisticsMainContentGUI extends JPanel{
    private UIButton btnDoanhthu, btnSach, btnKhachHang;
    private JPanel pnlHeader, pnlContent;
    private ThongKeDoanhThu panelTkDoanhThu;
    private ThongKeSanPham panelTkSanPham;
    private ThongKeKhachHang panelTkKhachHang;
    
    public StatisticsMainContentGUI() {
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));
        
        panelTkDoanhThu = new ThongKeDoanhThu();
        panelTkKhachHang = new ThongKeKhachHang();
        panelTkSanPham = new ThongKeSanPham();
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 40));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnDoanhthu = new UIButton("menuButton", "DOANH THU", 120, 30);
        btnKhachHang = new UIButton("menuButton", "KHÁCH HÀNG", 120, 30);
        btnSach = new UIButton("menuButton", "SẢN PHẨM", 120, 30);
        btnDoanhthu.addActionListener(e -> switchPanel(panelTkDoanhThu));
        btnKhachHang.addActionListener(e -> switchPanel(panelTkKhachHang));
        btnSach.addActionListener(e -> switchPanel(panelTkSanPham));
        
        pnlButton.add(btnDoanhthu);
        pnlButton.add(btnKhachHang);
        pnlButton.add(btnSach);
        pnlHeader.add(pnlButton, BorderLayout.WEST);
        
        
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.SUB_BACKGROUND);
 
        
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


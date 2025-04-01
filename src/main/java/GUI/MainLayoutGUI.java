package GUI;

import GUI.MainContent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public final class MainLayoutGUI extends JFrame {
    private JPanel pnlTitle, pnlMenu, pnlContent; 
    private ArrayList<UIButton> buttons; 

    public MainLayoutGUI() {
        initComponent();
    }

    public void initComponent() {
        this.setSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        this.getContentPane().setBackground(UIConstants.SUB_BACKGROUND);
        this.getContentPane().setLayout(new BorderLayout(5, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        //==============================( PANEL TITLE )=================================//
        pnlTitle = new JPanel(null);
        pnlTitle.setBackground(UIConstants.MAIN_BUTTON);
        pnlTitle.setPreferredSize(new Dimension(UIConstants.WIDTH_TITLE, UIConstants.HEIGHT_TITLE));

        JLabel lblTitle = new JLabel("QUẢN LÝ CỬA HÀNG SÁCH");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setForeground(UIConstants.WHITE_FONT);
        lblTitle.setBounds(10, 5, 300, 40);

        ImageIcon minimizeIcon = new ImageIcon(getClass().getResource("/Icon/minimize_icon.png"));
        ImageIcon closeIcon = new ImageIcon(getClass().getResource("/Icon/close_icon.png"));
        Image imgMinimize = minimizeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image imgClose = closeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);

        JButton btnMinimize = new JButton(new ImageIcon(imgMinimize));
        btnMinimize.setBackground(UIConstants.MAIN_BACKGROUND);
        btnMinimize.setBorder(null);
        btnMinimize.setBounds(UIConstants.WIDTH - 80, 10, 30, 30);
        btnMinimize.addActionListener(e -> setState(JFrame.ICONIFIED));

        JButton btnClose = new JButton(new ImageIcon(imgClose));
        btnClose.setBackground(UIConstants.MAIN_BACKGROUND);
        btnClose.setBorder(null);
        btnClose.setBounds(UIConstants.WIDTH - 40, 10, 30, 30);
        btnClose.addActionListener(e -> System.exit(0));

        pnlTitle.add(lblTitle);
        pnlTitle.add(btnMinimize);
        pnlTitle.add(btnClose);
        //==============================( End Panel Title )=============================//

        
        //================================( PANEL MENU )================================//
        pnlMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlMenu.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlMenu.setPreferredSize(new Dimension(UIConstants.WIDTH_MENU, UIConstants.HEIGHT_MENU));

        String[] buttonLabels = {"THỐNG KÊ", "SẢN PHẨM", "CẤU HÌNH", "KHÁCH HÀNG", "XUẤT HÀNG",
                                 "NHÂN VIÊN", "NHÀ CUNG CẤP", "PHÂN QUYỀN", "NHẬP HÀNG"};
        buttons = new ArrayList<>(); 
        //==============================( End Panel Menu )==============================//
        
        
        //===============================( PANEL CONTENT )==============================//
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.SUB_BACKGROUND);
        
        StatisticsMainContentGUI statisticsPanel = new StatisticsMainContentGUI();
        ProductMainContentGUI bookPanel = new ProductMainContentGUI();
        CustomerMainContentGUI customerPanel = new CustomerMainContentGUI();
        StaffMainContentGUI staffPanel = new StaffMainContentGUI();
        AboutProductMainContentGUI aboutBookPanel = new AboutProductMainContentGUI();
        SupplierMainContentGUI supplierPanel = new SupplierMainContentGUI();
        DecentralizationMainContentGUI decentralizationPanel = new DecentralizationMainContentGUI();
        ImportProductMainContentGUI importBookPanel = new ImportProductMainContentGUI();
        ExportProductMainContentGUI exportBookPanel = new ExportProductMainContentGUI();

        for (int i = 0; i < buttonLabels.length; i++) {
            UIButton button = new UIButton("menuButton", buttonLabels[i], 180, 40);
            pnlMenu.add(button);
            buttons.add(button);

            JPanel targetPanel;
            switch (i) {
                case 0:
                    button.setButtonIcon("/Icon/ThongKe_icon.png");
                    targetPanel = statisticsPanel;
                    break;
                case 1:
                    button.setButtonIcon("/Icon/Laptop_icon.png");
                    targetPanel = bookPanel;
                    break;
                case 2:
                    button.setButtonIcon("/Icon/ThongTinSach_icon.png");
                    targetPanel = aboutBookPanel;
                    break;
                case 3:
                    button.setButtonIcon("/Icon/KhachHang_icon.png");
                    targetPanel = customerPanel;
                    break;
                case 4:
                    button.setButtonIcon("/Icon/XuatHang_icon.png");
                    targetPanel = exportBookPanel;
                    break;
                case 5:
                    button.setButtonIcon("/Icon/NhanVien_icon.png");
                    targetPanel = staffPanel;
                    break;
                case 6:
                    button.setButtonIcon("/Icon/NhaCungCap_icon.png");
                    targetPanel = supplierPanel;
                    break;
                case 7:
                    button.setButtonIcon("/Icon/PhanQuyen_icon.png");
                    targetPanel = decentralizationPanel;
                    break;
                case 8:
                    button.setButtonIcon("/Icon/NhapHang_icon.png");
                    targetPanel = importBookPanel;
                    break;
                default:
                    targetPanel = new JPanel();
            }
            button.addActionListener(e -> switchPanel(targetPanel));
        }
        //==============================( End Panel Content )===========================//

        
        
        this.getContentPane().add(pnlMenu, BorderLayout.WEST);
        this.getContentPane().add(pnlContent, BorderLayout.CENTER);
        this.getContentPane().add(pnlTitle, BorderLayout.NORTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void switchPanel(JPanel newPanel) {
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

}
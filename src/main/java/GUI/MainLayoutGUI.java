package GUI;

import GUI.MainContent.*;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.*;
import javax.swing.*;

public final class MainLayoutGUI extends JFrame {
    private JPanel pnlTitle, pnlMenu, pnlContent; 
    private UIButton[] buttons; 

    public MainLayoutGUI() {
        initComponent();
    }

    public void initComponent() {
        this.setSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        this.getContentPane().setBackground(UIConstants.SUB_BACKGROUND);
        this.getContentPane().setLayout(new BorderLayout(5, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        //==========================Panel Title=================================
        pnlTitle = new JPanel(null);
        pnlTitle.setBackground(UIConstants.MAIN_BUTTON);
        pnlTitle.setPreferredSize(new Dimension(0, 50));

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

        //==========================Panel Menu==================================
        pnlMenu = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pnlMenu.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlMenu.setPreferredSize(new Dimension(200, 0));

        // Danh sách button
        String[] buttonLabels = {"TRANG CHỦ", "SẢN PHẨM","THÔNG TIN SP", "KHÁCH HÀNG","XUẤT HÀNG", "NHÂN VIÊN","NHÀ CUNG CẤP","PHÂN QUYỀN","NHẬP HÀNG","THỐNG KÊ"};
        buttons = new UIButton[buttonLabels.length];
        
        //=========================Panel Content================================
        pnlContent = new JPanel(new BorderLayout()); 
        pnlContent.setBackground(UIConstants.SUB_BACKGROUND);
        // Tạo các màn hình
        JPanel emptyPanel = new JPanel(); // Trang chủ
        ProductMainContentGUI bookPanel = new ProductMainContentGUI();
        CustomerMainContentGUI customerPanel = new CustomerMainContentGUI();
        StaffMainContentGUI staffPanel = new StaffMainContentGUI();
        AboutProductMainContentGUI aboutBookPanel = new AboutProductMainContentGUI();
        SupplierMainContentGUI supplierPanel = new SupplierMainContentGUI();
        DecentralizationMainContentGUI decentralizationPanel = new DecentralizationMainContentGUI();
        ImportProductMainContentGUI importBookPanel = new ImportProductMainContentGUI();
        ExportProductMainContentGUI exportBookPanel = new ExportProductMainContentGUI();
        // Gán sự kiện cho các button
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new UIButton("menuButton", buttonLabels[i], 180, 40);
            pnlMenu.add(buttons[i]);

            JPanel targetPanel;
            switch (i) {
                case 1:
                    buttons[i].setButtonIcon("/Icon/Sach_icon.png");
                    targetPanel = bookPanel;
                    break;
                case 2:
                    buttons[i].setButtonIcon("/Icon/ThongTinSach_icon.png");
                    targetPanel = aboutBookPanel;
                    break;
                case 3:
                    buttons[i].setButtonIcon("/Icon/KhachHang_icon.png");
                    targetPanel = customerPanel;
                    break;
                case 4:
                    buttons[i].setButtonIcon("/Icon/XuatHang_icon.png");
                    targetPanel = exportBookPanel;
                    break;
                case 5:
                    buttons[i].setButtonIcon("/Icon/NhanVien_icon.png");
                    targetPanel = staffPanel;
                    break;
                case 6:
                    buttons[i].setButtonIcon("/Icon/NhaCungCap_icon.png");
                    targetPanel = supplierPanel;
                    break;
                case 7:
                    buttons[i].setButtonIcon("/Icon/PhanQuyen_icon.png");
                    targetPanel = decentralizationPanel;
                    break;
                case 8:
                    buttons[i].setButtonIcon("/Icon/XuatHang_icon.png");
                    targetPanel = importBookPanel;
                    break;
                default:
                    targetPanel = emptyPanel;
            }
            buttons[i].addActionListener(e -> switchPanel(targetPanel));
        }

        // Thêm panel vào JFrame
        this.getContentPane().add(pnlMenu, BorderLayout.WEST);
        this.getContentPane().add(pnlContent, BorderLayout.CENTER);
        this.getContentPane().add(pnlTitle, BorderLayout.NORTH);

        // Hiển thị cửa sổ giữa màn hình
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    // Phương thức thay đổi panel
    private void switchPanel(JPanel newPanel) {
        pnlContent.removeAll();
        pnlContent.add(newPanel, BorderLayout.CENTER);
        pnlContent.revalidate();
        pnlContent.repaint();
    }

    public static void main(String args[]) {
        new MainLayoutGUI();
    }
}

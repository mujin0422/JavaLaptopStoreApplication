package GUI;

import java.awt.Dimension;
import javax.swing.*;
import Utils.UIConstants;
import Utils.UIButton;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public final class LoginGUI extends JFrame {
    private JTextField txtAccount, txtPassword; 
    private JLabel lblAccount, lblPassword, lblTitle;
    private JPanel pnlLeft, pnlCenter;
    private UIButton btnLogin;
    
    public LoginGUI(){
        initComponent();
    }
    
    public void initComponent(){
        this.setTitle("ĐĂNG NHẬP VÀO HỆ THỐNG");
        this.setSize(new Dimension(700, 400));
        this.getContentPane().setBackground(UIConstants.SUB_BACKGROUND);
        this.getContentPane().setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //======================== Panel Left ==================================
        pnlLeft = new JPanel();
        pnlLeft.setBackground(UIConstants.SUB_BACKGROUND);
        pnlLeft.setPreferredSize(new Dimension(200, 0));
        this.getContentPane().add(pnlLeft, BorderLayout.WEST);
        
        //======================== Panel Center ================================
        pnlCenter = new JPanel();
        pnlCenter.setLayout(null); // Không dùng Layout Manager
        pnlCenter.setBackground(UIConstants.MAIN_BACKGROUND);
        this.getContentPane().add(pnlCenter, BorderLayout.CENTER);
        
        // Tiêu đề
        lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lblTitle.setFont(UIConstants.TITLE_FONT);
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(100, 30, 300, 40); // Vị trí (x, y, width, height)
        
        // Label Tài khoản
        lblAccount = new JLabel("Tài khoản: ");
        lblAccount.setFont(UIConstants.BODY_FONT);
        lblAccount.setBounds(80, 100, 100, 30);
        
        // TextField nhập tài khoản
        txtAccount = new JTextField();
        txtAccount.setBounds(200, 100, 200, 30);
        
        // Label Mật khẩu
        lblPassword = new JLabel("Mật khẩu: ");
        lblPassword.setFont(UIConstants.BODY_FONT);
        lblPassword.setBounds(80, 150, 100, 30);
        
        // TextField nhập mật khẩu
        txtPassword = new JPasswordField();
        txtPassword.setBounds(200, 150, 200, 30);
        
        // Nút đăng nhập (Sử dụng UIButton)
        btnLogin = new UIButton("login", "Đăng nhập");
        btnLogin.setBounds(200, 200, 120, 35);
        
        
        
        
       
        pnlCenter.add(lblTitle);
        pnlCenter.add(lblAccount);
        pnlCenter.add(txtAccount);
        pnlCenter.add(lblPassword);
        pnlCenter.add(txtPassword);
        pnlCenter.add(btnLogin);
        
        // Hiển thị cửa sổ giữa màn hình
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new LoginGUI();
    }
}

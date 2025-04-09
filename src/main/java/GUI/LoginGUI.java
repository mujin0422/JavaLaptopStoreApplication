package GUI;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.awt.*;
import javax.swing.*;
import Utils.UIButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LoginGUI extends JFrame {
    private JTextField txtAccount;
    private JPasswordField txtPassword;
    private JLabel lblAccount, lblPassword, lblTitle;
    private JPanel pnlLeft, pnlCenter;
    private UIButton btnLogin;
    private TaiKhoanDAO taiKhoanDAO;

    public LoginGUI() {
        initComponent();
        taiKhoanDAO = new TaiKhoanDAO();
        txtAccount.setText("admin1");
        txtPassword.setText("123456");
    }

    public void initComponent() {
        this.setTitle("ĐĂNG NHẬP VÀO HỆ THỐNG");
        this.setSize(new Dimension(800, 400));
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.getContentPane().setLayout(new BorderLayout(5, 5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //==============================( PANEL LEFT )==================================//
        pnlLeft = new JPanel(new BorderLayout());
        pnlLeft.setBackground(Color.GRAY);
        pnlLeft.setPreferredSize(new Dimension(300, 0));
        this.getContentPane().add(pnlLeft, BorderLayout.WEST);

        JLabel lblStoreName = new JLabel("LAPTOPSTORE");
        lblStoreName.setFont(new Font("Roboto", Font.BOLD, 24));
        lblStoreName.setHorizontalAlignment(SwingConstants.CENTER);
        lblStoreName.setPreferredSize(new Dimension(300, 100));

        JLabel lblImage = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/Image/login_image.png"));
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(img));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);

        pnlLeft.add(lblStoreName, BorderLayout.NORTH);
        pnlLeft.add(lblImage, BorderLayout.CENTER);
        //============================( End Panel Left )================================//

        //=============================( PANEL CENTER )=================================//
        pnlCenter = new JPanel();
        pnlCenter.setLayout(null);
        pnlCenter.setBackground(Color.LIGHT_GRAY);
        this.getContentPane().add(pnlCenter, BorderLayout.CENTER);

        lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
        lblTitle.setFont(new Font("Roboto", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(100, 50, 300, 40);

        lblAccount = new JLabel("Tài khoản: ");
        lblAccount.setFont(new Font("Roboto", Font.BOLD, 18));
        lblAccount.setBounds(40, 120, 100, 35);

        ImageIcon userIcon = new ImageIcon(getClass().getResource("/Icon/login_user_icon.png"));
        Image userImg = userIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(userImg);
        JLabel lblUserIcon = new JLabel(userIcon);
        lblUserIcon.setPreferredSize(new Dimension(35, 35));

        JPanel accountPanel = new JPanel(new BorderLayout());
        accountPanel.setBounds(180, 120, 250, 35);
        accountPanel.setBackground(Color.WHITE);
        accountPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        txtAccount = new JTextField();
        txtAccount.setBorder(null); 
        accountPanel.add(lblUserIcon, BorderLayout.WEST);
        accountPanel.add(txtAccount, BorderLayout.CENTER);

        lblPassword = new JLabel("Mật khẩu: ");
        lblPassword.setFont(new Font("Roboto", Font.BOLD, 18));
        lblPassword.setBounds(40, 180, 100, 35);

        ImageIcon lockIcon = new ImageIcon(getClass().getResource("/Icon/login_lock_icon.png"));
        Image lockImg = lockIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lockIcon = new ImageIcon(lockImg);
        JLabel lblLockIcon = new JLabel(lockIcon);
        lblLockIcon.setPreferredSize(new Dimension(35, 35));

        JPanel passwordPanel = new JPanel(new BorderLayout());
        passwordPanel.setBounds(180, 180, 250, 35);
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        txtPassword = new JPasswordField();
        txtPassword.setBorder(null); 
        passwordPanel.add(lblLockIcon, BorderLayout.WEST);
        passwordPanel.add(txtPassword, BorderLayout.CENTER);

        btnLogin = new UIButton("confirm", "ĐĂNG NHẬP", 160, 35, "/Icon/login_key_icon.png");
        btnLogin.setBounds(160, 240, 140, 40 );
        btnLogin.addActionListener(e -> login());

        //=============================( End panel Center )=============================//
        pnlCenter.add(lblTitle);
        pnlCenter.add(lblAccount);
        pnlCenter.add(accountPanel);
        pnlCenter.add(lblPassword);
        pnlCenter.add(passwordPanel);
        pnlCenter.add(btnLogin);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginGUI();
    }
    public void login(){
        String tenDangNhap = txtAccount.getText();
        String matKhau = new String(txtPassword.getPassword());

        if (tenDangNhap.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        TaiKhoanDTO taiKhoan = taiKhoanDAO.getByUsername(tenDangNhap);

        if (taiKhoan == null) {
            JOptionPane.showMessageDialog(this, "Tài khoản không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else if (!taiKhoan.getMatKhau().equals(matKhau)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            new MainLayoutGUI(taiKhoan);
            this.dispose();
        }
        
    }
}

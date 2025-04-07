package GUI.MainContentDiaLog;

import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Dell Vostro
 */
public class AddAndEditAccountGUI extends JDialog{
    private JTextField txtTenDangNhap, txtMatKhau;
    private JComboBox cbMaNV, cbMaQuyen;
    private UIButton btnAdd, btnSave, btnCancel;
    private TaiKhoanBUS tkBus;
    private TaiKhoanDTO tk;

    private HashMap<String,Integer> nvMap, quyenMap;
    
    public AddAndEditAccountGUI(JFrame parent, TaiKhoanBUS tkBus, String title, String type, TaiKhoanDTO tk) {
        super(parent, title, true);
        this.tkBus = tkBus;
        this.tk = tk;
        initComponent(type);
        if (tk != null) {
            txtTenDangNhap.setText(tk.getTenDangNhap());
            txtMatKhau.setText(tk.getMatKhau());
            for (String tenNV : nvMap.keySet()) {
                if (nvMap.get(tenNV) == tk.getMaNV()) {
                    cbMaNV.setSelectedItem(tenNV);
                    break;
                }
            }
            for (String tenQuyen : quyenMap.keySet()) {
                if (quyenMap.get(tenQuyen) == tk.getMaQuyen()) {
                    cbMaQuyen.setSelectedItem(tenQuyen);
                    break;
                }
            }

        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditAccountGUI(JFrame parent, TaiKhoanBUS tkBus, String title, String type) {
        super(parent, title, true);
        this.tkBus = tkBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        this.setSize(450, 300);
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BUTTON);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Tên đăng nhập:"));
        inputPanel.add(txtTenDangNhap = new JTextField());
        
        inputPanel.add(new UILabel("Mật khẩu:"));
        inputPanel.add(txtMatKhau = new JTextField());
        
        inputPanel.add(new UILabel("Nhân viên:"));
        cbMaNV = new JComboBox<>();
        cbMaNV.setBackground(UIConstants.WHITE_FONT);
        nvMap = new HashMap<>();  
        NhanVienBUS nhanVienBus = new NhanVienBUS();
        for(NhanVienDTO nv : nhanVienBus.getAllNhanVien()){
            cbMaNV.addItem(nv.getTenNV());
            nvMap.put(nv.getTenNV(),nv.getMaNV());
        }
        inputPanel.add(cbMaNV);
        
        inputPanel.add(new UILabel("Nhóm Quyền:"));
        cbMaQuyen = new JComboBox<>();
        cbMaQuyen.setBackground(UIConstants.WHITE_FONT);
        quyenMap = new HashMap<>(); 
        QuyenBUS quyenBus = new QuyenBUS();
        for(QuyenDTO quyen : quyenBus.getAllQuyen()){
            cbMaQuyen.addItem(quyen.getTenQuyen());
            quyenMap.put(quyen.getTenQuyen(),quyen.getMaQuyen());
        }
        inputPanel.add(cbMaQuyen);
        
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setBackground(UIConstants.MAIN_BUTTON);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);

        switch (type) {
            case "add" -> btnPanel.add(btnAdd);
            case "save" -> btnPanel.add(btnSave);
        }
        btnPanel.add(btnCancel);
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addAccount());
        btnSave.addActionListener(e -> saveAccount());
    }

    private void addAccount() {
        if (!CheckFormInput()) return;
        try {
            String tenDangNhap = txtTenDangNhap.getText().trim();
            String matKhau = txtMatKhau.getText().trim();
            int maNV = nvMap.get(cbMaNV.getSelectedItem().toString());
            int maQuyen = quyenMap.get(cbMaQuyen.getSelectedItem().toString());
            TaiKhoanDTO tk = new TaiKhoanDTO(tenDangNhap, matKhau, maNV, maQuyen);
            if (tkBus.addTaiKhoan(tk)) {
                JOptionPane.showMessageDialog(this, "Thêm tài khoản thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveAccount() {
        if (!CheckFormInput()) return;
        try {
            String tenDangNhap = txtTenDangNhap.getText().trim();
            String matKhau = txtMatKhau.getText().trim();
            int maNV = nvMap.get(cbMaNV.getSelectedItem().toString());
            int maQuyen = quyenMap.get(cbMaQuyen.getSelectedItem().toString());
            TaiKhoanDTO tk = new TaiKhoanDTO(tenDangNhap, matKhau, maNV, maQuyen);
            if (tkBus.updateTaiKhoan(tk)) {
                JOptionPane.showMessageDialog(this, "Cập thật tài khoản thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckFormInput() {
        try {
            String tenDangNhap = txtTenDangNhap.getText().trim();
            if (tenDangNhap.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên dăng nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String matKhau = txtMatKhau.getText().trim();
            if (matKhau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
}

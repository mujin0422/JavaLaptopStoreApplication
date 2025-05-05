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
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddAndEditAccountGUI extends JDialog{
    private UITextField txtTenDangNhap, txtMatKhau;
    private JComboBox cbMaNV, cbMaQuyen;
    private UIButton btnAdd, btnSave, btnCancel;
    private TaiKhoanBUS tkBus;
    private NhanVienBUS nvBus;
    private QuyenBUS quyenBus;
    private TaiKhoanDTO tk;

    public AddAndEditAccountGUI(JFrame parent, TaiKhoanBUS tkBus, String title, String type, TaiKhoanDTO tk) {
        super(parent, title, true);
        this.tkBus = tkBus;
        this.tk = tk;
        initComponent(type);
        if (tk != null) {
            txtTenDangNhap.setText(tk.getTenDangNhap());
            txtMatKhau.setText(tk.getMatKhau());
            
            String tenNv = nvBus.getTenNvByMaNv(tk.getMaNV());
            if (tenNv != null) {
                cbMaNV.setSelectedItem(tenNv);
            }
            String tenQuyen = quyenBus.getTenQuyenByMaQuyen(tk.getMaQuyen());
            if (tenQuyen != null) {
                cbMaQuyen.setSelectedItem(tenQuyen);
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
        this.nvBus = new NhanVienBUS();
        this.quyenBus = new QuyenBUS();
        this.setSize(450, 280);
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Tên đăng nhập:"));
        inputPanel.add(txtTenDangNhap = new UITextField(0,0));
        inputPanel.add(new UILabel("Mật khẩu:"));
        inputPanel.add(txtMatKhau = new UITextField(0,0));
        inputPanel.add(new UILabel("Nhân viên:"));
        cbMaNV = new JComboBox<>();
        cbMaNV.setBackground(UIConstants.WHITE_FONT);
        if(type.equals("add")) {
            for(NhanVienDTO nv : nvBus.getAllNvNotExistsTk()) {
                cbMaNV.addItem(nv.getTenNV());
            }
        } else {
            for(NhanVienDTO nv : nvBus.getAllNhanVien()) {
                cbMaNV.addItem(nv.getTenNV());
            }
        }
        inputPanel.add(cbMaNV);
        inputPanel.add(new UILabel("Nhóm Quyền:"));
        cbMaQuyen = new JComboBox<>();
        cbMaQuyen.setBackground(UIConstants.WHITE_FONT);
        for(QuyenDTO quyen : quyenBus.getAllQuyen()){
            cbMaQuyen.addItem(quyen.getTenQuyen());
        }
        inputPanel.add(cbMaQuyen);
        
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
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
            int maNV = nvBus.getMaNvByTenNv(cbMaNV.getSelectedItem().toString());
            int maQuyen = quyenBus.getMaQuyenByTenQuyen(cbMaQuyen.getSelectedItem().toString());
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
            int maNV = nvBus.getMaNvByTenNv(cbMaNV.getSelectedItem().toString());
            int maQuyen = quyenBus.getMaQuyenByTenQuyen(cbMaQuyen.getSelectedItem().toString());
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

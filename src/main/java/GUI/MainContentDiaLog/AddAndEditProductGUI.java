package GUI.MainContentDiaLog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
public class AddAndEditProductGUI extends JDialog {
    private JTextField txtMaSP, txtTenSP, txtGiaSP, txtSoLuongTon, txtMaRam, txtMaCPU, txtMaRom, txtMaDPG, txtMaLoai, txtMaTH, txtThoiGianBH;
    private UIButton btnAdd, btnSave, btnCancel;
    private SanPhamBUS sanPhamBus;
    private SanPhamDTO sanPham;
    public AddAndEditProductGUI(JFrame parent, SanPhamBUS sanPhamBus, String title, String type, SanPhamDTO sanPham) {
        super(parent, title, true);
        this.sanPhamBus = sanPhamBus;
        this.sanPham = sanPham;
        initComponent(type);
        
        if (sanPham != null) {
            txtMaSP.setText(String.valueOf(sanPham.getMaSP()));
            txtTenSP.setText(sanPham.getTenSP());
            txtGiaSP.setText(String.valueOf(sanPham.getGiaSP()));
            txtSoLuongTon.setText(String.valueOf(sanPham.getSoLuongTon()));
            txtMaRam.setText(String.valueOf(sanPham.getMaRAM()));
            txtMaCPU.setText(String.valueOf(sanPham.getMaCPU()));
            txtMaRom.setText(String.valueOf(sanPham.getMaROM()));
            txtMaDPG.setText(String.valueOf(sanPham.getMaDPG()));
            txtMaLoai.setText(String.valueOf(sanPham.getMaLoai()));
            txtMaTH.setText(String.valueOf(sanPham.getMaTH()));
            txtThoiGianBH.setText(String.valueOf(sanPham.getThoiGianBH()));
            txtMaSP.setEnabled(false); // Không sửa mã sản phẩm
        }
        
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditProductGUI(JFrame parent, SanPhamBUS sanPhamBus, String title, String type) {
        super(parent, title, true);
        this.sanPhamBus = sanPhamBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        this.setSize(550, 400);
        this.setLayout(new BorderLayout());

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new UILabel("Mã Sản Phẩm:"));
        inputPanel.add(txtMaSP = new JTextField());
        inputPanel.add(new UILabel("Tên Sản Phẩm:"));
        inputPanel.add(txtTenSP = new JTextField());
        inputPanel.add(new UILabel("Giá Sản Phẩm:"));
        inputPanel.add(txtGiaSP = new JTextField());
        inputPanel.add(new UILabel("Số Lượng Tồn:"));
        inputPanel.add(txtSoLuongTon = new JTextField());
        inputPanel.add(new UILabel("Mã RAM:"));
        inputPanel.add(txtMaRam = new JTextField());
        inputPanel.add(new UILabel("Mã CPU:"));
        inputPanel.add(txtMaCPU = new JTextField());
        inputPanel.add(new UILabel("Mã ROM:"));
        inputPanel.add(txtMaRom = new JTextField());
        inputPanel.add(new UILabel("Mã Độ Phân Giải:"));
        inputPanel.add(txtMaDPG = new JTextField());
        inputPanel.add(new UILabel("Mã Loại:"));
        inputPanel.add(txtMaLoai = new JTextField());
        inputPanel.add(new UILabel("Mã Thương Hiệu:"));
        inputPanel.add(txtMaTH = new JTextField());
        inputPanel.add(new UILabel("Thời Gian Bảo Hành:"));
        inputPanel.add(txtThoiGianBH = new JTextField());

        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);

        if (type.equals("add")) {
            btnPanel.add(btnAdd);
        } else if (type.equals("save")) {
            btnPanel.add(btnSave);
        }
        btnPanel.add(btnCancel);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        if (btnAdd != null) btnAdd.addActionListener(e -> addProduct());
        if (btnSave != null) btnSave.addActionListener(e -> saveProduct());
    }

    private void saveProduct() {
        if (!CheckFormInput()) return;
        try {
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String tenSP = txtTenSP.getText().trim();
            double gia = Double.parseDouble(txtGiaSP.getText().trim());
            // int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            // int soLuongTon = Integer.parseInt(txtSoLuong.getText().trim());
            // int maCPU = Integer.parseInt(txtSoLuong.getText().trim());
            // int maRAM = Integer.parseInt(txtSoLuong.getText().trim());
            // int maROM = Integer.parseInt(txtSoLuong.getText().trim());
            // int maDPG = Integer.parseInt(txtSoLuong.getText().trim());
            // int maLoai = Integer.parseInt(txtSoLuong.getText().trim());
            // int maTH = Integer.parseInt(txtSoLuong.getText().trim());
            // int thoiGianBH = Integer.parseInt(txtSoLuong.getText().trim());
            
            // SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, gia);

            if (sanPhamBus.updateSanPham(sanPham)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProduct() {
        if (!checkFormInput()) return;
        try {
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String tenSP = txtTenSP.getText().trim();
            double giaSP = Double.parseDouble(txtGiaSP.getText().trim());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            String maRam = txtMaRam.getText().trim();
            String maCPU = txtMaCPU.getText().trim();
            String maRom = txtMaRom.getText().trim();
            String maDPG = txtMaDPG.getText().trim();
            String maLoai = txtMaLoai.getText().trim();
            String maTH = txtMaTH.getText().trim();
            String thoiGianBH = txtThoiGianBH.getText().trim();

            // SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, int(giaSP), int(soLuongTon),int( aRam), int(maCPU), int(maRom), int(maDPG), int(maLoai),int( maTH), int(thoiGianBH));
            if (sanPhamBus.addSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkFormInput() {
        try {
            String maSPStr = txtMaSP.getText().trim();
            if (maSPStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maSP = Integer.parseInt(maSPStr);
            if (maSP <= 0) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenSP = txtTenSP.getText().trim();
            if (tenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            // Kiểm tra giá sản phẩm và số lượng tồn
            String giaSPStr = txtGiaSP.getText().trim();
            if (giaSPStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            double giaSP = Double.parseDouble(giaSPStr);
            if (giaSP <= 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String soLuongTonStr = txtSoLuongTon.getText().trim();
            if (soLuongTonStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int soLuongTon = Integer.parseInt(soLuongTonStr);
            if (soLuongTon < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn phải là số nguyên không âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    

    private boolean CheckFormInput() {
        try {
            String maSPStr = txtMaSP.getText().trim();
            if (maSPStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maSP = Integer.parseInt(maSPStr);
            if (maSP <= 0) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenSP = txtTenSP.getText().trim();
            if (tenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String giaStr = txtGiaSP.getText().trim();
            if (giaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            double gia = Double.parseDouble(giaStr);
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, "Giá phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String soLuongStr = txtSoLuongTon.getText().trim();
            if (soLuongStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int soLuong = Integer.parseInt(soLuongStr);
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "Số lượng không thể âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    
}

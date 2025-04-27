package GUI.MainContentDiaLog;

import BUS.ChiTietSanPhamBUS;
import BUS.NhanVienBUS;
import BUS.PhieuBaoHanhBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.NhanVienDTO;
import DTO.PhieuBaoHanhDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AddAndEditGuaranteeGUI extends JDialog{
    private UITextField txtMaPBH, txtMaPX;
    private JComboBox cbNhanVien, cbSerialSP, cbTrangThaiBH;
    private JTextArea txtMoTaLoi;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhanVienBUS nvBus;
    private ChiTietSanPhamBUS chiTietSanPhamBUS;
    private PhieuBaoHanhBUS phieuBaoHanhBus;
    private PhieuBaoHanhDTO phieuBaoHanh;
    
    public AddAndEditGuaranteeGUI(JFrame parent, PhieuBaoHanhBUS phieuBaoHanhBus, String title, String type, PhieuBaoHanhDTO phieuBaoHanh){
        super(parent, title, true);
        this.phieuBaoHanhBus = phieuBaoHanhBus;
        this.phieuBaoHanh = phieuBaoHanh;
        initComponent(type);
        if (phieuBaoHanh != null) {
            txtMaPBH.setText(String.valueOf(phieuBaoHanh.getMaPBH()));
            txtMaPBH.setEnabled(false);
            txtMaPX.setText(String.valueOf(phieuBaoHanhBus.getMaPxByMaPbh(phieuBaoHanh.getMaPBH())));
            txtMaPX.setEnabled(false);
            cbSerialSP.setSelectedItem(phieuBaoHanh.getSerialSP());
            cbSerialSP.setEnabled(false);
            txtMoTaLoi.setText(phieuBaoHanh.getMoTaLoi());
            txtMoTaLoi.setEditable(false);
            cbTrangThaiBH.setSelectedIndex(phieuBaoHanh.getTrangThaiBH());
            cbTrangThaiBH.setEnabled(true); 
            int maNVBH = phieuBaoHanh.getMaNVBH();
            String tenNv = nvBus.getTenNvByMaNv(maNVBH);
            if (tenNv != null) {
                cbNhanVien.setSelectedItem(tenNv);
                cbNhanVien.setEnabled(false);
            }
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditGuaranteeGUI(JFrame parent, PhieuBaoHanhBUS phieuBaoHanhBus, String title, String type) {
        super(parent, title, true);
        this.phieuBaoHanhBus = phieuBaoHanhBus;
        initComponent(type);
        
        // Thêm mới thì tự động lấy mã mới
        txtMaPBH.setText(phieuBaoHanhBus.getNextGuaranteeID());
        txtMaPBH.setEnabled(false); // Không cho sửa
        
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void initComponent(String type) {
        this.chiTietSanPhamBUS = new ChiTietSanPhamBUS();
        this.nvBus = new NhanVienBUS();
        this.setSize(450, 350);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new UILabel("Mã Phiếu Bảo Hành:"));
        inputPanel.add(txtMaPBH = new UITextField(250,30));
        
        inputPanel.add(new UILabel("Nhân Viên Bảo Hành:"));
        cbNhanVien = new JComboBox<>();
        cbNhanVien.setBackground(UIConstants.WHITE_FONT);
        for(NhanVienDTO nv : nvBus.getAllNhanVien()){
            if(nv.getVaiTro().equals("Nhân viên kỹ thuật")) cbNhanVien.addItem(nv.getTenNV());
        }
        inputPanel.add(cbNhanVien);
        
        // ===== UI Components =====
        inputPanel.add(new UILabel("Mã Phiếu Xuất:"));
        inputPanel.add(txtMaPX = new UITextField(250,30));

        inputPanel.add(new UILabel("Serial Sản Phẩm:"));
        cbSerialSP = new JComboBox<>();
        cbSerialSP.setBackground(UIConstants.WHITE_FONT);
        inputPanel.add(cbSerialSP);

        // ===== DocumentListener cho txtMaPX =====
        txtMaPX.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateSerialComboBox();
            }
            public void removeUpdate(DocumentEvent e) {
                updateSerialComboBox();
            }
            public void changedUpdate(DocumentEvent e) {
                updateSerialComboBox();
            }
            private void updateSerialComboBox() {
                cbSerialSP.removeAllItems();
                try {
                    int maPx = Integer.parseInt(txtMaPX.getText().trim());
                    ArrayList<ChiTietSanPhamDTO> dsCTSP = chiTietSanPhamBUS.getAllByMaPX(maPx);

                    if (dsCTSP.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy Serial nào cho mã phiếu xuất " + maPx, "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (ChiTietSanPhamDTO ctsp : dsCTSP) {
                            cbSerialSP.addItem(ctsp.getSerialSP());
                        }
                    }
                } catch (NumberFormatException ex) {
                }
            }
        });

        inputPanel.add(new UILabel("Mô Tả Lỗi:"));
        txtMoTaLoi = new JTextArea();
        inputPanel.add(txtMoTaLoi);
        
        
        inputPanel.add(new UILabel("Trạng Thái Bảo Hành:"));
        cbTrangThaiBH = new JComboBox<>();
        cbNhanVien.setBackground(UIConstants.WHITE_FONT);
        cbTrangThaiBH.addItem("Đang bảo hành");
        cbTrangThaiBH.addItem("Đẫ bảo hành");
        inputPanel.add(cbTrangThaiBH);

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
        if (btnAdd != null) btnAdd.addActionListener(e -> addGuarantee());
        if (btnSave != null) btnSave.addActionListener(e -> saveGuarantee());
        
    }

    public Date getCurrentDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDateStr = sdf.format(new Date());  
            return sdf.parse(currentDateStr); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;  
        }
    }
    
    private void addGuarantee() {
        if (!checkFormInput()) return;
        try {
            int maPBH = Integer.parseInt(txtMaPBH.getText().trim());
            String serialSP = (String) cbSerialSP.getSelectedItem();
            String moTaLoi = txtMoTaLoi.getText().trim();
            Date ngayTiepNhan = getCurrentDate();
            int trangThaiBH = cbTrangThaiBH.getSelectedIndex(); 
            String tenNhanVien = (String) cbNhanVien.getSelectedItem();
            int maNVBH = nvBus.getMaNvByTenNv(tenNhanVien);
            PhieuBaoHanhDTO pbh = new PhieuBaoHanhDTO(maPBH,  serialSP, maNVBH, ngayTiepNhan, moTaLoi, trangThaiBH);

            if (phieuBaoHanhBus.addPhieuBaoHanh(pbh)) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu bảo hành thành công!");
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Không thể thêm phiếu bảo hành. Vui lòng kiểm tra dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng dữ liệu số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void saveGuarantee(){
        if(!checkFormInput()) return;
        try {
            int maPBH = Integer.parseInt(txtMaPBH.getText().trim());
            String serialSP = (String) cbSerialSP.getSelectedItem();
            String moTaLoi = txtMoTaLoi.getText().trim();
            Date ngayTiepNhan = getCurrentDate();
            int trangThaiBH = cbTrangThaiBH.getSelectedIndex(); 
            String tenNhanVien = (String) cbNhanVien.getSelectedItem();
            int maNVBH = nvBus.getMaNvByTenNv(tenNhanVien);
            PhieuBaoHanhDTO pbh = new PhieuBaoHanhDTO(maPBH,  serialSP, maNVBH, ngayTiepNhan, moTaLoi, trangThaiBH);

            if (phieuBaoHanhBus.updatePhieuBaoHanh(pbh)) {
                JOptionPane.showMessageDialog(this, "Cập nhật phiếu bảo hành thành công!");
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "lỗi cập nhật", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi định dạng dữ liệu số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean checkFormInput() {
        if (txtMaPBH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu bảo hành.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int maPBH = Integer.parseInt(txtMaPBH.getText().trim());
        if (maPBH <= 0) {
            JOptionPane.showMessageDialog(this, "Mã phiếu bảo hành phải là số nguyên dương.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtMaPX.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã phiếu xuất.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtMoTaLoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mô tả lỗi.", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

}

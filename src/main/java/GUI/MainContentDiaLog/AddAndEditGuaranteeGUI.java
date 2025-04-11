package GUI.MainContentDiaLog;

import BUS.ChiTietPhieuXuatBUS;
import BUS.NhanVienBUS;
import BUS.PhieuBaoHanhBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.NhanVienDTO;
import DTO.PhieuBaoHanhDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private UITextField txtMaPBH, txtMaPX, txtMaSP;
    private JComboBox cbNhanVien, cbSerialSP, cbTrangThaiBH;
    private JTextArea txtMoTaLoi;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhanVienBUS nvBus;
    private ChiTietPhieuXuatBUS chiTietPhieuXuatBus;
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
            txtMaSP.setText(String.valueOf(phieuBaoHanh.getMaSP()));
            txtMaSP.setEnabled(false);
            txtMaPX.setText(String.valueOf(phieuBaoHanh.getMaPX()));
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
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    private void initComponent(String type) {
        this.chiTietPhieuXuatBus = new ChiTietPhieuXuatBUS();
        this.nvBus = new NhanVienBUS();
        this.setSize(550, 400);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
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

        inputPanel.add(new UILabel("Mã Sản Phẩm:"));
        inputPanel.add(txtMaSP = new UITextField(250,30));

        // ===== ActionListener cho cbSerialSP (chỉ gắn 1 lần) =====
        cbSerialSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSerial = (String) cbSerialSP.getSelectedItem();
                if (selectedSerial != null) {
                    try {
                        int maPx = Integer.parseInt(txtMaPX.getText().trim());
                        for (ChiTietPhieuXuatDTO ctpx : chiTietPhieuXuatBus.getAllChiTietPhieuXuatByMaPx(maPx)) {
                            if (selectedSerial.equals(ctpx.getSerialSP())) {
                                txtMaSP.setText(String.valueOf(ctpx.getMaSP()));
                                return;
                            }
                        }
                    } catch (NumberFormatException ex) {
                    }
                }
            }
        });

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
                    ArrayList<ChiTietPhieuXuatDTO> dsCTPX = chiTietPhieuXuatBus.getAllChiTietPhieuXuatByMaPx(maPx);

                    if (dsCTPX.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy Serial nào cho mã phiếu xuất " + maPx, "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (ChiTietPhieuXuatDTO ctpx : dsCTPX) {
                            cbSerialSP.addItem(ctpx.getSerialSP());
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
            int maPX = Integer.parseInt(txtMaPX.getText().trim());
            String serialSP = (String) cbSerialSP.getSelectedItem();
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String moTaLoi = txtMoTaLoi.getText().trim();
            Date ngayTiepNhan = getCurrentDate();
            int trangThaiBH = cbTrangThaiBH.getSelectedIndex(); 
            String tenNhanVien = (String) cbNhanVien.getSelectedItem();
            int maNVBH = nvBus.getMaNvByTenNv(tenNhanVien);
            PhieuBaoHanhDTO pbh = new PhieuBaoHanhDTO(maPBH, maSP, maPX, serialSP, ngayTiepNhan, moTaLoi, trangThaiBH, maNVBH);

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
            int maPX = Integer.parseInt(txtMaPX.getText().trim());
            String serialSP = (String) cbSerialSP.getSelectedItem();
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String moTaLoi = txtMoTaLoi.getText().trim();
            Date ngayTiepNhan = getCurrentDate();
            int trangThaiBH = cbTrangThaiBH.getSelectedIndex(); 
            String tenNhanVien = (String) cbNhanVien.getSelectedItem();
            int maNVBH = nvBus.getMaNvByTenNv(tenNhanVien);
            PhieuBaoHanhDTO pbh = new PhieuBaoHanhDTO(maPBH, maSP, maPX, serialSP, ngayTiepNhan, moTaLoi, trangThaiBH, maNVBH);

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

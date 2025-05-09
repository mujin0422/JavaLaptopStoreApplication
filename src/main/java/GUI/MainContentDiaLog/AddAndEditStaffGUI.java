package GUI.MainContentDiaLog;

import javax.swing.*;
import java.awt.*;
import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;

public class AddAndEditStaffGUI extends JDialog{
    private UITextField txtMaNV, txtTenNV, txtEmail, txtSDT, txtVaiTro;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhanVienBUS nhanVienBus;
    private NhanVienDTO nhanVien;

    public AddAndEditStaffGUI(JFrame parent, NhanVienBUS nhanVienBus, String title, String type, NhanVienDTO nhanVien){
        super(parent, title, true);
        this.nhanVienBus = nhanVienBus;
        this.nhanVien = nhanVien;
        initComponent(type);
        if (nhanVien != null) {
            txtMaNV.setText(String.valueOf(nhanVien.getMaNV()));
            txtTenNV.setText(nhanVien.getTenNV());
            txtSDT.setText(nhanVien.getSdt());
            txtEmail.setText(nhanVien.getEmail());
            txtVaiTro.setText(nhanVien.getVaiTro());
            txtMaNV.setEditable(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditStaffGUI(JFrame parent, NhanVienBUS nhanVienBus, String title, String type){
        super(parent, title, true);
        this.nhanVienBus = nhanVienBus;
        initComponent(type);
        txtMaNV.setText(nhanVienBus.getNextEmployeeID());
        txtMaNV.setEditable(false); 
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public void initComponent(String type){
        this.setSize(450, 280);
        this.setLayout(new BorderLayout());
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã Nhân Viên:"));
        inputPanel.add(txtMaNV = new UITextField(0,0));
        inputPanel.add(new UILabel("Tên Nhân Viên:"));
        inputPanel.add(txtTenNV = new UITextField(0,0));
        inputPanel.add(new UILabel("Số Điện Thoại:"));
        inputPanel.add(txtSDT = new UITextField(0,0));
        inputPanel.add(new UILabel("Email:"));
        inputPanel.add(txtEmail = new UITextField(0,0));
        inputPanel.add(new UILabel("Vai Trò:"));
        inputPanel.add(txtVaiTro = new UITextField(0,0));
        //=============================( End Panel Input )==============================//
        
        
        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        
        switch(type) {
            case("add") -> btnPanel.add(btnAdd);
            case("save") -> btnPanel.add(btnSave);          
        }
        btnPanel.add(btnCancel);
        //============================( End Panel Button )==============================//
        
        
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addStaff());
        btnSave.addActionListener(e -> saveStaff());
    }
    

    private void addStaff(){
        if(!CheckFormInput()) return;
        try {
            int maNV = Integer.parseInt(txtMaNV.getText().trim());
            String tenNV = txtTenNV.getText().trim();
            String soDT = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String vaiTro = txtVaiTro.getText().trim();

            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, soDT, email, vaiTro);
            if(nhanVienBus.addNhanVien(nv)){
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveStaff(){
        if(!CheckFormInput()) return;
        try {
            int maNV = Integer.parseInt(txtMaNV.getText().trim());
            String tenNV = txtTenNV.getText().trim();
            String soDT = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            String vaiTro = txtVaiTro.getText().trim();

            NhanVienDTO nv = new NhanVienDTO(maNV, tenNV, soDT, email, vaiTro);
            if(nhanVienBus.updateNhanVien(nv)){
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean CheckFormInput() {
        try {
            String tenNV = txtTenNV.getText().trim();
            if (tenNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sdt = txtSDT.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!sdt.matches("0\\d{9}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có 10 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String email = txtEmail.getText().trim();
            if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String vaiTro = txtVaiTro.getText().trim();
            if(vaiTro.isEmpty()){
                JOptionPane.showMessageDialog(this, "Vai trò không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

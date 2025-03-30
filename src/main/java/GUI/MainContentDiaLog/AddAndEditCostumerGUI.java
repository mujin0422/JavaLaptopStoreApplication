/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.MainContentDiaLog;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author Dell Vostro
 */
public class AddAndEditCostumerGUI extends JDialog{
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail;
    private UIButton btnAdd, btnSave, btnCancel;
    private KhachHangBUS khachHangBus;
    private KhachHangDTO khachHang;
    
    public AddAndEditCostumerGUI(JFrame parent, KhachHangBUS khachHangBus, String title, String type, KhachHangDTO khachHang){
        super(parent, title, true);
        this.khachHangBus = khachHangBus;
        this.khachHang = khachHang;
        initComponent(type);
        if (khachHang != null) {
            txtMaKH.setText(String.valueOf(khachHang.getMaKH()));
            txtTenKH.setText(khachHang.getTenKH());
            txtSDT.setText(khachHang.getSdt());
            txtEmail.setText(khachHang.getEmail());
            txtMaKH.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditCostumerGUI(JFrame parent, KhachHangBUS khachHangBus, String title, String type){
        super(parent, title, true);
        this.khachHangBus = khachHangBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type){
        this.setSize(550, 300);
        this.setLayout(new BorderLayout());
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã Khách Hàng:"));
        inputPanel.add(txtMaKH = new JTextField());
        inputPanel.add(new UILabel("Tên Khách Hàng:"));
        inputPanel.add(txtTenKH = new JTextField());
        inputPanel.add(new UILabel("Số điện thoại:"));
        inputPanel.add(txtSDT = new JTextField());
        inputPanel.add(new UILabel("Email:"));
        inputPanel.add(txtEmail = new JTextField());
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
        btnAdd.addActionListener(e -> addCostumer());
        btnSave.addActionListener(e -> saveCostumer());
    }
    
    private void saveCostumer(){
        if(!CheckFormInput()) return;
        try {
            int maKH = Integer.parseInt(txtMaKH.getText().trim());
            String tenKH = txtTenKH.getText().trim();
            String soDT = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            KhachHangDTO kh = new KhachHangDTO(maKH, tenKH, soDT, email);
            if(khachHangBus.addKhachHang(kh)){
                JOptionPane.showMessageDialog(this, "Cập nhật khach hang thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật khach hang that bai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addCostumer(){
        if(!CheckFormInput()) return;
        try {
            int maKH = Integer.parseInt(txtMaKH.getText().trim());
            String tenKH = txtTenKH.getText().trim();
            String soDT = txtSDT.getText().trim();
            String email = txtEmail.getText().trim();
            KhachHangDTO kh = new KhachHangDTO(maKH, tenKH, soDT, email);
            if(khachHangBus.addKhachHang(kh)){
                JOptionPane.showMessageDialog(this, "Thêm khach hang thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã khach hang đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean CheckFormInput() {
        try {
            String maKHStr = txtMaKH.getText().trim();
            if (maKHStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maKH = Integer.parseInt(maKHStr);
            if (maKH <= 0) {
                JOptionPane.showMessageDialog(this, "Mã khách hàng phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenKH = txtTenKH.getText().trim();
            if (tenKH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên khách hàng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String sdt = txtSDT.getText().trim();
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (!sdt.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải có từ 10 đến 11 chữ số!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String email = txtEmail.getText().trim();
            if (!email.isEmpty() && !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    
}

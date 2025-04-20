package GUI.MainContentDiaLog;

import BUS.ThuongHieuBUS;
import DTO.ThuongHieuDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddAndEditBrandGUI extends JDialog {
    private JTextField txtMaBrand, txtTenBrand;
    private UIButton btnAdd, btnSave, btnCancel;
    private ThuongHieuBUS brandBus;
    private ThuongHieuDTO brand;
    
    public AddAndEditBrandGUI(JFrame parent, ThuongHieuBUS brandBus, String title, String type, ThuongHieuDTO brand) {
        super(parent, title, true);
        this.brandBus = brandBus;
        this.brand = brand;
        initComponent(type);
        if (brand != null) {
            txtMaBrand.setText(String.valueOf(brand.getMaTH()));
            txtTenBrand.setText(brand.getTenTH());
            txtMaBrand.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditBrandGUI(JFrame parent, ThuongHieuBUS brandBus, String title, String type) {
        super(parent, title, true);
        this.brandBus = brandBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type) {
        this.setSize(400, 180);
        this.setLayout(new BorderLayout());
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã thương hiệu:"));
        inputPanel.add(txtMaBrand = new JTextField());
        inputPanel.add(new UILabel("Tên thương hiệu:"));
        inputPanel.add(txtTenBrand = new JTextField());
        
        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        
        switch(type) {
            case ("add") -> btnPanel.add(btnAdd);
            case ("save") -> btnPanel.add(btnSave);          
        }
        btnPanel.add(btnCancel);
        
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        
        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addBrand());
        btnSave.addActionListener(e -> saveBrand());
    }
    
    private void addBrand() {
        if (!CheckFormInput()) return;
        try {
            int maBrand = Integer.parseInt(txtMaBrand.getText().trim());
            String tenBrand = txtTenBrand.getText().trim();
            ThuongHieuDTO brand = new ThuongHieuDTO(maBrand, tenBrand);
            if (brandBus.addThuongHieu(brand)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã thương hiệu đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveBrand() {
        if (!CheckFormInput()) return;
        try {
            int maBrand = Integer.parseInt(txtMaBrand.getText().trim());
            String tenBrand = txtTenBrand.getText().trim();
            ThuongHieuDTO brand = new ThuongHieuDTO(maBrand, tenBrand);
            if (brandBus.updateThuongHieu(brand)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean CheckFormInput() {
        try {
            String maBrandStr = txtMaBrand.getText().trim();
            if (maBrandStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã thương hiệu không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maBrand = Integer.parseInt(maBrandStr);
            if (maBrand < 0) {
                JOptionPane.showMessageDialog(this, "Mã thương hiệu phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenBrand = txtTenBrand.getText().trim();
            if (tenBrand.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên thương hiệu không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
package GUI.MainContentDiaLog;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class AddAndEditSupplierGUI extends JDialog{
    private UITextField txtMaNCC, txtTenNCC, txtDiaChi, txtSDT;
    private UIButton btnAdd, btnSave, btnCancel;
    private NhaCungCapBUS nccBus;
    private NhaCungCapDTO ncc;
    
    public AddAndEditSupplierGUI(JFrame parent, NhaCungCapBUS nccBus, String title, String type, NhaCungCapDTO ncc){
        super(parent, title, true);
        this.nccBus = nccBus;
        this.ncc = ncc;
        initComponent(type);
        if (ncc != null) {
            txtMaNCC.setText(String.valueOf(ncc.getMaNCC()));
            txtTenNCC.setText(ncc.getTenNCC());
            txtDiaChi.setText(ncc.getDiaChi());
            txtSDT.setText(ncc.getSdt());
            txtMaNCC.setEditable(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditSupplierGUI(JFrame parent, NhaCungCapBUS nccBus, String title, String type){
        super(parent, title, true);
        this.nccBus = nccBus;
        initComponent(type);
        txtMaNCC.setText(nccBus.getNextSupplierID());
        txtMaNCC.setEditable(false); 
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public void initComponent(String type){
        this.setSize(450, 250);
        this.setLayout(new BorderLayout());
        
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new UILabel("Mã Nhà Cung Cấp:"));
        inputPanel.add(txtMaNCC = new UITextField(0,0));
        inputPanel.add(new UILabel("Tên Nhà Cung Cấp:"));
        inputPanel.add(txtTenNCC = new UITextField(0,0));
        inputPanel.add(new UILabel("Địa chỉ:"));
        inputPanel.add(txtDiaChi = new UITextField(0,0));
        inputPanel.add(new UILabel("Số điện thoại:"));
        inputPanel.add(txtSDT = new UITextField(0,0));
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
        btnAdd.addActionListener(e -> addSupplier());
        btnSave.addActionListener(e -> saveSupplier());
    }
    
    private void addSupplier(){
        if(!CheckFormInput()) return;
        try {
            int maNCC = Integer.parseInt(txtMaNCC.getText().trim());
            String tenNCC = txtTenNCC.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt);
            if(nccBus.addNhaCungCap(ncc)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveSupplier(){
        if(!CheckFormInput()) return;
        try {
            int maNCC = Integer.parseInt(txtMaNCC.getText().trim());
            String tenNCC = txtTenNCC.getText().trim();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaChi, sdt);
            if(nccBus.updateNhaCungCap(ncc)){
                JOptionPane.showMessageDialog(this, "Cập nhật nhà cung cấp thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật nhaf cung cấp that bai!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    private boolean CheckFormInput(){
        try {
            String tenNCCStr = txtTenNCC.getText().trim();
            if(tenNCCStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tên nhà cung cấp không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String diaChiStr = txtDiaChi.getText().trim();
            if(diaChiStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Địa chỉ nhà cung cấp không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

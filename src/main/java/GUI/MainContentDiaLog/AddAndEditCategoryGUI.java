package GUI.MainContentDiaLog;

import BUS.PhanLoaiBUS;
import DTO.PhanLoaiDTO;
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

public class AddAndEditCategoryGUI extends JDialog{
    private JTextField txtMaPhanLoai, txtTenPhanLoai;
    private UIButton btnAdd, btnSave, btnCancel;
    private PhanLoaiBUS phanLoaiBus;
    private PhanLoaiDTO phanLoai;
    
    public AddAndEditCategoryGUI(JFrame parent, PhanLoaiBUS phanLoaiBus, String title, String type, PhanLoaiDTO phanLoai){
        super(parent, title, true);
        this.phanLoaiBus = phanLoaiBus;
        this.phanLoai = phanLoai;
        initComponent(type);
        if (phanLoai != null){
            txtMaPhanLoai.setText(String.valueOf(phanLoai.getMaLoai()));
            txtTenPhanLoai.setText(phanLoai.getTenLoai());
            txtMaPhanLoai.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditCategoryGUI(JFrame parent, PhanLoaiBUS phanLoaiBus, String title, String type) {
        super(parent, title, true);
        this.phanLoaiBus = phanLoaiBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type){
        this.setSize(400, 180);
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã Phân Loại:"));
        inputPanel.add(txtMaPhanLoai = new JTextField());
        inputPanel.add(new UILabel("Tên Phân Loại:"));
        inputPanel.add(txtTenPhanLoai = new JTextField());
        
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
        
        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addCategory());
        btnSave.addActionListener(e -> saveCategory());
    }
    
    private void addCategory(){
        if(!CheckFormInput()) return;
        try {
            int maPhanLoai = Integer.parseInt(txtMaPhanLoai.getText().trim());
            String tenPhanLoai = txtTenPhanLoai.getText().trim();
            PhanLoaiDTO phanLoai = new PhanLoaiDTO(maPhanLoai, tenPhanLoai);
            if(phanLoaiBus.addPhanLoai(phanLoai)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã Phân Loại đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveCategory(){
        if(!CheckFormInput()) return;
        try {
            int maPhanLoai = Integer.parseInt(txtMaPhanLoai.getText().trim());
            String tenPhanLoai = txtTenPhanLoai.getText().trim();
            PhanLoaiDTO phanLoai = new PhanLoaiDTO(maPhanLoai, tenPhanLoai);
            if (phanLoaiBus.updatePhanLoai(phanLoai)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean CheckFormInput(){
        try {
            String maPhanLoaiStr = txtMaPhanLoai.getText().trim();
            if (maPhanLoaiStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã Phân Loại không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maPhanLoai = Integer.parseInt(maPhanLoaiStr);
            if (maPhanLoai < 0){
                JOptionPane.showMessageDialog(this, "Mã Phân Loại phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenPhanLoai = txtTenPhanLoai.getText().trim();
            if (tenPhanLoai.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tên Phân Loại không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

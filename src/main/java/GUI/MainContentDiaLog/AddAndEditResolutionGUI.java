package GUI.MainContentDiaLog;

import BUS.DoPhanGiaiBUS;
import DTO.DoPhanGiaiDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddAndEditResolutionGUI extends JDialog {
    private JTextField txtMaDoPhanGiai, txtTenDoPhanGiai;
    private UIButton btnAdd, btnSave, btnCancel;
    private DoPhanGiaiBUS doPhanGiaiBus;
    private DoPhanGiaiDTO doPhanGiai;
    
    public AddAndEditResolutionGUI(JFrame parent, DoPhanGiaiBUS doPhanGiaiBus, String title, String type, DoPhanGiaiDTO doPhanGiai){
        super(parent, title, true);
        this.doPhanGiaiBus = doPhanGiaiBus;
        this.doPhanGiai = doPhanGiai;
        initComponent(type);
        if (doPhanGiai != null){
            txtMaDoPhanGiai.setText(String.valueOf(doPhanGiai.getMaDPG()));
            txtTenDoPhanGiai.setText(doPhanGiai.getTenDPG());
            txtMaDoPhanGiai.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditResolutionGUI(JFrame parent, DoPhanGiaiBUS doPhanGiaiBus, String title, String type) {
        super(parent, title, true);
        this.doPhanGiaiBus = doPhanGiaiBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type){
        this.setSize(550, 200);
        this.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã Độ Phân Giải:"));
        inputPanel.add(txtMaDoPhanGiai = new JTextField());
        inputPanel.add(new UILabel("Tên Độ Phân Giải:"));
        inputPanel.add(txtTenDoPhanGiai = new JTextField());
        
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
        btnAdd.addActionListener(e -> addResolution());
        btnSave.addActionListener(e -> saveResolution());
    }
    
    private void addResolution(){
        if(!CheckFormInput()) return;
        try {
            int maDoPhanGiai = Integer.parseInt(txtMaDoPhanGiai.getText().trim());
            String tenDoPhanGiai = txtTenDoPhanGiai.getText().trim();
            DoPhanGiaiDTO doPhanGiai = new DoPhanGiaiDTO(maDoPhanGiai, tenDoPhanGiai);
            if(doPhanGiaiBus.addDoPhanGiai(doPhanGiai)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã Độ Phân Giải đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveResolution(){
        if(!CheckFormInput()) return;
        try {
            int maDoPhanGiai = Integer.parseInt(txtMaDoPhanGiai.getText().trim());
            String tenDoPhanGiai = txtTenDoPhanGiai.getText().trim();
            DoPhanGiaiDTO doPhanGiai = new DoPhanGiaiDTO(maDoPhanGiai, tenDoPhanGiai);
            if (doPhanGiaiBus.updateDoPhanGiai(doPhanGiai)) {
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
            String maDoPhanGiaiStr = txtMaDoPhanGiai.getText().trim();
            if (maDoPhanGiaiStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã Độ Phân Giải không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maDoPhanGiai = Integer.parseInt(maDoPhanGiaiStr);
            if (maDoPhanGiai < 0){
                JOptionPane.showMessageDialog(this, "Mã Độ Phân Giải phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenDoPhanGiai = txtTenDoPhanGiai.getText().trim();
            if (tenDoPhanGiai.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tên Độ Phân Giải không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
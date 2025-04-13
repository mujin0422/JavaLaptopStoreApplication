package GUI.MainContentDiaLog;

import BUS.RamBUS;
import DTO.RamDTO;
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

public class AddAndEditRamGUI extends JDialog {
    private JTextField txtMaRam, txtDungLuongRam;
    private UIButton btnAdd, btnSave, btnCancel;
    private RamBUS ramBus;
    private RamDTO ram;
    
    public AddAndEditRamGUI(JFrame parent, RamBUS ramBus, String title, String type, RamDTO ram){
        super(parent, title, true);
        this.ramBus = ramBus;
        this.ram = ram;
        initComponent(type);
        if (ram != null){
            txtMaRam.setText(String.valueOf(ram.getMaRAM()));
            txtDungLuongRam.setText(ram.getDungLuongRAM());
            txtMaRam.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditRamGUI(JFrame parent, RamBUS ramBus, String title, String type) {
        super(parent, title, true);
        this.ramBus = ramBus;
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
        inputPanel.add(new UILabel("Mã RAM:"));
        inputPanel.add(txtMaRam = new JTextField());
        inputPanel.add(new UILabel("Dung lượng RAM:"));
        inputPanel.add(txtDungLuongRam = new JTextField());
        
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
        btnAdd.addActionListener(e -> addRam());
        btnSave.addActionListener(e -> saveRam());
    }
    
    private void addRam(){
        if(!CheckFormInput()) return;
        try {
            int maRam = Integer.parseInt(txtMaRam.getText().trim());
            String dungLuongRam = txtDungLuongRam.getText().trim();
            RamDTO ram = new RamDTO(maRam, dungLuongRam);
            if(ramBus.addRAM(ram)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã RAM đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveRam(){
        if(!CheckFormInput()) return;
        try {
            int maRam = Integer.parseInt(txtMaRam.getText().trim());
            String dungLuongRam = txtDungLuongRam.getText().trim();
            RamDTO ram = new RamDTO(maRam, dungLuongRam);
            if (ramBus.updateRAM(ram)) {
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
            String maRamStr = txtMaRam.getText().trim();
            if (maRamStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã RAM không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maRam = Integer.parseInt(maRamStr);
            if (maRam < 0){
                JOptionPane.showMessageDialog(this, "Mã RAM phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String dungLuongRam = txtDungLuongRam.getText().trim();
            if (dungLuongRam.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dung lượng RAM không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
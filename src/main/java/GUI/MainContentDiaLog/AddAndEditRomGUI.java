package GUI.MainContentDiaLog;

import BUS.RomBUS;
import DTO.RomDTO;
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

public class AddAndEditRomGUI extends JDialog{
    private JTextField txtMaRom, txtDungLuongRom;
    private UIButton btnAdd, btnSave, btnCancel;
    private RomBUS romBus;
    private RomDTO rom;
    
    public AddAndEditRomGUI(JFrame parent, RomBUS romBus, String title, String type, RomDTO rom){
        super(parent, title, true);
        this.romBus = romBus;
        this.rom = rom;
        initComponent(type);
        if (rom != null){
            txtMaRom.setText(String.valueOf(rom.getMaROM()));
            txtDungLuongRom.setText(rom.getDungLuongROM());
            txtMaRom.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditRomGUI(JFrame parent, RomBUS romBus, String title, String type) {
        super(parent, title, true);
        this.romBus = romBus;
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
        inputPanel.add(new UILabel("Mã ROM:"));
        inputPanel.add(txtMaRom = new JTextField());
        inputPanel.add(new UILabel("Dung lượng ROM:"));
        inputPanel.add(txtDungLuongRom = new JTextField());
        
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
        btnAdd.addActionListener(e -> addRom());
        btnSave.addActionListener(e -> saveRom());
    }
    
    private void addRom(){
        if(!CheckFormInput()) return;
        try {
            int maRom = Integer.parseInt(txtMaRom.getText().trim());
            String dungLuongRom = txtDungLuongRom.getText().trim();
            RomDTO rom = new RomDTO(maRom, dungLuongRom);
            if(romBus.addROM(rom)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã ROM đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveRom(){
        if(!CheckFormInput()) return;
        try {
            int maRom = Integer.parseInt(txtMaRom.getText().trim());
            String dungLuongRom = txtDungLuongRom.getText().trim();
            RomDTO rom = new RomDTO(maRom, dungLuongRom);
            if (romBus.updateROM(rom)) {
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
            String maRomStr = txtMaRom.getText().trim();
            if (maRomStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã ROM không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maRom = Integer.parseInt(maRomStr);
            if (maRom < 0){
                JOptionPane.showMessageDialog(this, "Mã ROM phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String dungLuongRom = txtDungLuongRom.getText().trim();
            if (dungLuongRom.isEmpty()){
                JOptionPane.showMessageDialog(this, "Dung lượng ROM không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
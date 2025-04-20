package GUI.MainContentDiaLog;

import BUS.CpuBUS;
import DTO.CpuDTO;
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

public class AddAndEditCpuGUI extends JDialog{
    private JTextField txtMaCpu, txtTenCpu;
    private UIButton btnAdd, btnSave, btnCancel;
    private CpuBUS cpuBus;
    private CpuDTO cpu;
    
    public AddAndEditCpuGUI(JFrame parent, CpuBUS cpuBus, String title, String type, CpuDTO cpu){
        super(parent, title, true);
        this.cpuBus = cpuBus;
        this.cpu = cpu;
        initComponent(type);
        if (cpu != null){
            txtMaCpu.setText(String.valueOf(cpu.getMaCPU()));
            txtTenCpu.setText(cpu.getTenCPU());
            txtMaCpu.setEnabled(false);
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public AddAndEditCpuGUI(JFrame parent, CpuBUS cpuBus, String title, String type) {
        super(parent, title, true);
        this.cpuBus = cpuBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }
    
    public void initComponent(String type){
        this.setSize(400, 180);
        this.setLayout(new BorderLayout());
        //===============================( PANEL INPUT )================================//
        JPanel inputPanel = new JPanel(new GridLayout(2,2,10,10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new UILabel("Mã cpu:"));
        inputPanel.add(txtMaCpu = new JTextField());
        inputPanel.add(new UILabel("Tên cpu:"));
        inputPanel.add(txtTenCpu = new JTextField());
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
        btnAdd.addActionListener(e -> addCpu());
        btnSave.addActionListener(e -> saveCpu());
    }
    
    private void addCpu(){
        if(!CheckFormInput()) return;
        try {
            int maCpu = Integer.parseInt(txtMaCpu.getText().trim());
            String tenCpu = txtTenCpu.getText().trim();
            CpuDTO cpu = new CpuDTO(maCpu, tenCpu);
            if(cpuBus.addCPU(cpu)){
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã CPU đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveCpu(){
        if(!CheckFormInput()) return;
        try {
            int maCpu = Integer.parseInt(txtMaCpu.getText().trim());
            String tenCpu = txtTenCpu.getText().trim();
            CpuDTO cpu = new CpuDTO(maCpu, tenCpu);
            if (cpuBus.updateCPU(cpu)) {
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
            String maCpuStr = txtMaCpu.getText().trim();
            if (maCpuStr.isEmpty()){
                JOptionPane.showMessageDialog(this, "Mã CPU không được trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maCpu = Integer.parseInt(maCpuStr);
            if (maCpu < 0){
                JOptionPane.showMessageDialog(this, "Mã CPU phải là số nguyên dương !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenCpu = txtTenCpu.getText().trim();
            if (tenCpu.isEmpty()){
                JOptionPane.showMessageDialog(this, "Tên CPU không được để trống !!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}

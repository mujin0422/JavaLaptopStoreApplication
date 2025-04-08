package GUI.MainContentDiaLog;

import BUS.ChiTietChucNangBUS;
import BUS.QuyenBUS;
import BUS.ChucNangBUS;
import BUS.HanhDongBUS;
import DTO.ChiTietChucNangDTO;
import DTO.QuyenDTO;
import DTO.ChucNangDTO;
import DTO.HanhDongDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class AddAndEditDecentralizationGUI extends JDialog {
    private UITextField txtMaQuyen, txtTenQuyen;
    private ArrayList<ChucNangDTO> danhMucChucNang;
    private ArrayList<HanhDongDTO> danhMucHanhDong;
    private JCheckBox[][] ckbChucNang;
    private UIButton btnAdd, btnSave, btnCancel;
    private QuyenBUS quyenBUS;
    private ChucNangBUS chucNangBUS;
    private int rowCkc, colCkc;
    private HanhDongBUS hanhDongBUS;
    private ChiTietChucNangBUS chiTietChucNangBUS;
    private QuyenDTO quyen;

    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, String title, String type) {
        super(parent, title, true);
        this.quyenBUS = quyenBUS;
        initComponent(type);
        
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditDecentralizationGUI(JFrame parent, QuyenBUS quyenBUS, String title, String type, QuyenDTO quyen) {
        super(parent, title, true);
        this.quyenBUS = quyenBUS;
        this.quyen = quyen;
        initComponent(type);
        if (quyen != null) {
            txtMaQuyen.setText(String.valueOf(quyen.getMaQuyen()));
            txtTenQuyen.setText(quyen.getTenQuyen());
            txtMaQuyen.setEnabled(false);
            
            ArrayList<ChiTietChucNangDTO> dsCTCN = chiTietChucNangBUS.getChiTietChucNangByMaQuyen(quyen.getMaQuyen());
            for (int i = 0; i < rowCkc; i++) {
                for (int j = 0; j < colCkc; j++) {
                    int maCN = danhMucChucNang.get(i).getMaCN();
                    String maHD = danhMucHanhDong.get(j).getMaHD();
                    for (ChiTietChucNangDTO ct : dsCTCN) {
                        if (ct.getMaCN() == maCN && ct.getMaHD().equals(maHD)) {
                            ckbChucNang[i][j].setSelected(true);
                            break;
                        }
                    }
                }
            
            }
        }
       this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        quyenBUS = new QuyenBUS();
        chucNangBUS = new ChucNangBUS();
        hanhDongBUS = new HanhDongBUS();
        chiTietChucNangBUS = new ChiTietChucNangBUS();
        danhMucChucNang = chucNangBUS.getAllChucNang();
        danhMucHanhDong = hanhDongBUS.getAllHanhDong();
        rowCkc = danhMucChucNang.size();
        colCkc = danhMucHanhDong.size();
        ckbChucNang = new JCheckBox[rowCkc][colCkc];
        
        this.setSize(800, 500);
        this.setLayout(new BorderLayout());
        
        JPanel pnlTextField = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        pnlTextField.setBackground(UIConstants.WHITE_FONT);
        JPanel pnlGroupMaQuyen = new JPanel(new FlowLayout(0));
        pnlGroupMaQuyen.setBackground(UIConstants.WHITE_FONT);
        pnlGroupMaQuyen.add(new UILabel("Mã quyền:", 80, 30));
        pnlGroupMaQuyen.add(txtMaQuyen = new UITextField(100 ,30));
        JPanel pnlGroupTenQuyen = new JPanel(new FlowLayout(0));
        pnlGroupTenQuyen.setBackground(UIConstants.WHITE_FONT);
        pnlGroupTenQuyen.add(new UILabel("Tên nhóm quyền:", 130, 30));
        pnlGroupTenQuyen.add(txtTenQuyen = new UITextField(350, 30));
        pnlTextField.add(pnlGroupMaQuyen);
        pnlTextField.add(pnlGroupTenQuyen);
        
               
        JPanel pnlLabel = new JPanel(new GridLayout(1 + rowCkc, 1));
        pnlLabel.setPreferredSize(new Dimension(200,0));
        pnlLabel.setBackground(UIConstants.WHITE_FONT);
        pnlLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        UILabel labelTenChucNang = new UILabel("CÁC CHỨC NĂNG");
        pnlLabel.add(labelTenChucNang);
        for(ChucNangDTO dmcn : danhMucChucNang){
            pnlLabel.add(new UILabel(dmcn.getTenCN()));
        }
        
        
        JPanel pnlCheckBox = new JPanel(new GridLayout(1 + rowCkc, colCkc));
        pnlCheckBox.setBackground(UIConstants.WHITE_FONT);
        for (HanhDongDTO dmhd : danhMucHanhDong) {
            UILabel labelTenHanhDong = new UILabel(dmhd.getTenHD());
            labelTenHanhDong.setHorizontalAlignment(SwingConstants.CENTER);
            pnlCheckBox.add(labelTenHanhDong);
        }
        for (int i = 0; i < rowCkc; i++) {
            for (int j = 0; j < colCkc; j++) {
                ckbChucNang[i][j] = new JCheckBox();
                ckbChucNang[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                ckbChucNang[i][j].setBackground(UIConstants.WHITE_FONT);
                pnlCheckBox.add(ckbChucNang[i][j]);
            }
        }
        
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlButton.setBackground(UIConstants.WHITE_FONT);
        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);
        if ("add".equals(type)) pnlButton.add(btnAdd);
        if ("save".equals(type)) pnlButton.add(btnSave);
        pnlButton.add(btnCancel);
        
        this.add(pnlTextField, BorderLayout.NORTH);
        this.add(pnlLabel, BorderLayout.WEST);
        this.add(pnlCheckBox, BorderLayout.CENTER);
        this.add(pnlButton, BorderLayout.SOUTH);

        btnCancel.addActionListener(e -> dispose());
        btnAdd.addActionListener(e -> addDecentralization());
        btnSave.addActionListener(e -> saveDecentralization());
    }
    
    private void addDecentralization(){
        if(!checkFormInput()) return;
        try {
            int maQuyen = Integer.parseInt(txtMaQuyen.getText().trim());
            String tenQuyen = txtTenQuyen.getText().trim();
            QuyenDTO quyen = new QuyenDTO(maQuyen, tenQuyen);
            if(quyenBUS.addQuyen(quyen)){
                for (int i = 0; i < rowCkc; i++) {
                    for (int j = 0; j < colCkc; j++) {
                        if(ckbChucNang[i][j].isSelected()){
                            if(chiTietChucNangBUS.addChiTietChucNang(new ChiTietChucNangDTO(danhMucChucNang.get(i).getMaCN(), maQuyen, danhMucHanhDong.get(j).getMaHD()))){
                                JOptionPane.showMessageDialog(this, "Phân quyền thành công!");
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(this, "Lỗi phân quyền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Mã quyền đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void saveDecentralization(){
        if(!checkFormInput()) return;
        try {
            int maQuyen = Integer.parseInt(txtMaQuyen.getText().trim());
            String tenQuyen = txtTenQuyen.getText().trim();
            QuyenDTO quyen = new QuyenDTO(maQuyen, tenQuyen);
            if(quyenBUS.updateQuyen(quyen)){
                for (int i = 0; i < rowCkc; i++) {
                    for (int j = 0; j < colCkc; j++) {
                        int maCN = danhMucChucNang.get(i).getMaCN();
                        String maHD = danhMucHanhDong.get(j).getMaHD();
                        boolean isChecked = ckbChucNang[i][j].isSelected();
                        boolean exists = chiTietChucNangBUS.existsChiTietChucNang(maCN, maQuyen, maHD);

                        if (isChecked && exists) {
                            if (!chiTietChucNangBUS.activeChiTietChucNang(maCN, maQuyen, maHD)) {
                                return;
                            }
                        } else if(isChecked && !exists) {
                            ChiTietChucNangDTO ctn = new ChiTietChucNangDTO(maCN, maQuyen, maHD);
                            if (!chiTietChucNangBUS.addChiTietChucNang(ctn)) {
                                return;
                            }
                        } else if (!isChecked && exists) {
                            if (!chiTietChucNangBUS.deleteChiTietChucNang(maCN, maQuyen, maHD)) {
                                return;
                            }
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "Lưu phân quyền thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật quyền thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }


    private boolean checkFormInput(){
        try {
            String maQuyenStr = txtMaQuyen.getText().trim();
            if (maQuyenStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã quyền không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maQuyen = Integer.parseInt(maQuyenStr);
            if (maQuyen <= 0) {
                JOptionPane.showMessageDialog(this, "Mã quyền phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenSach = txtTenQuyen.getText().trim();
            if (tenSach.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên quyên không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}

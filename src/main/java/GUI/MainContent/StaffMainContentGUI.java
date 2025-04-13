package GUI.MainContent;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditStaffGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StaffMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private NhanVienBUS nhanVienBUS;

    public StaffMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.nhanVienBUS= new NhanVienBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        
        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addStaff());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteStaff());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editStaff());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);

        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(190 ,30);
        pnlSearchFilter.add(txtSearch);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        String[] columnNames = {"MÃ NHÂN VIÊN", "TÊN NHÂN VIÊN", "SỐ ĐIỆN THOẠI", "EMAIL", "VAI TRÒ"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);   
        ArrayList<NhanVienDTO> listNhanVien = nhanVienBUS.getAllNhanVien();
        for (NhanVienDTO nhanvien : listNhanVien) {
            tableModel.addRow(new Object[]{
                nhanvien.getMaNV(),
                nhanvien.getTenNV(),
                nhanvien.getEmail(),
                nhanvien.getSdt(),
                nhanvien.getVaiTro()
            });
        }
    }

    private void addStaff(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditStaffGUI((JFrame) window, nhanVienBUS, "Thêm nhân viên", "add");
        loadTableData();
    }

    private void editStaff(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        int maNV = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        NhanVienDTO nv = nhanVienBUS.getById(maNV);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditStaffGUI((JFrame) window, nhanVienBUS, "Chỉnh sửa nhân viên", "save", nv);
        loadTableData();
    }
    
    
    private void deleteStaff(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một nhân viên để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn không", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String maNV = tableModel.getValueAt(selectedRow, 0).toString();
            int maNVInt = Integer.parseInt(maNV);
            if (nhanVienBUS.deleteNhanVien(maNVInt)) { 
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}

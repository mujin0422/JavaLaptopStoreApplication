package GUI.MainContent;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
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
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StaffMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit, btnExcel;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private NhanVienBUS nhanVienBUS;
    private TaiKhoanBUS taiKhoanBUS;

    public StaffMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.nhanVienBUS = new NhanVienBUS();
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        
        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 100, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addStaff());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteStaff());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editStaff());
        btnExcel = new UIButton("menuButton", "EXCEL", 100, 40, "/Icon/excel_icon.png");
        btnExcel.addActionListener(e -> exportExcel());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        pnlButton.add(btnExcel);
        applyPermissions(taiKhoan.getTenDangNhap(), 4);

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
        addSearchFunctionality();
    }
    
    private void applyPermissions(String username, int maCN) {
        btnAdd.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        btnEdit.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        btnDelete.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
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
    
    private void exportExcel() {
        try {
            File exportDir = new File("bang");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            String filePath = "bang/DanhSachNhanVien.xlsx";

            // Tạo workbook và sheet
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nhân Viên");

            XSSFRow headerRow = sheet.createRow(0);
            for (int col = 0; col < tableModel.getColumnCount(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(tableModel.getColumnName(col));
            }
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                XSSFRow excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    Object value = tableModel.getValueAt(row, col);
                    Cell cell = excelRow.createCell(col);
                    if (value instanceof Number) {
                        cell.setCellValue(Double.parseDouble(value.toString()));
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
            }
            workbook.close();

            JOptionPane.showMessageDialog(this, "Xuất danh sách nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
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
    
    private void addSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchCustomer(); }
            public void removeUpdate(DocumentEvent e) { searchCustomer(); }
            public void changedUpdate(DocumentEvent e) { searchCustomer(); }
        });
    }
    
    private void searchCustomer() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0); 
        ArrayList<NhanVienDTO> listNV = nhanVienBUS.searchNhanVien(keyword);
        for (NhanVienDTO nhanvien : listNV) {
            tableModel.addRow(new Object[]{
                nhanvien.getMaNV(),
                nhanvien.getTenNV(),
                nhanvien.getEmail(),
                nhanvien.getSdt(),
                nhanvien.getVaiTro()
            });
        }
    }
    


}

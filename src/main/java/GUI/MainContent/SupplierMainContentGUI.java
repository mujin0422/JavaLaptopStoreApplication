package GUI.MainContent;

import BUS.NhaCungCapBUS;
import BUS.TaiKhoanBUS;
import DTO.NhaCungCapDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditSupplierGUI;
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

public class SupplierMainContentGUI extends JPanel implements ReloadablePanel{
    private UIButton btnAdd, btnDelete, btnEdit, btnExcel;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    
    private DefaultTableModel tableModel;
    private NhaCungCapBUS nhaCungCapBus;
    private TaiKhoanBUS taiKhoanBUS;
    
    public SupplierMainContentGUI(TaiKhoanDTO taiKhoan){
        this.nhaCungCapBus = new NhaCungCapBUS();
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));
        
        //===============================( Panel Header )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 100, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addSupplier());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteSupplier());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editSupplier());
        btnExcel = new UIButton("menuButton", "EXCEL", 100, 40, "/Icon/excel_icon.png");
        btnExcel.addActionListener(e -> exportExcel());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        pnlButton.add(btnExcel);
        applyPermissions(taiKhoan.getTenDangNhap(), 6);
        
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
        String[] columnNames = {"MÃ NHÀ CUNG CÁP", "TÊN NHÀ CUNG CẤP", "ĐỊA CHỈ", "SỐ ĐIỆN THOẠI"};
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
    
    public void loadTableData(){
        tableModel.setRowCount(0);
        ArrayList<NhaCungCapDTO> listNCC = nhaCungCapBus.getAllNhaCungCap();
        for (NhaCungCapDTO ncc: listNCC){
            tableModel.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChi(),
                ncc.getSdt()
            });
        }
    }
    private void exportExcel() {
        try {
            File exportDir = new File("bang");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            String filePath = "bang/DanhSachNhaCungCap.xlsx";

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Nhà Cung Cấp");

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

            JOptionPane.showMessageDialog(this, "Xuất danh sách nhà cung cấp thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void addSupplier(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditSupplierGUI((JFrame) window, nhaCungCapBus, "Thêm Nhà Cung Cấp", "add");
        loadTableData();
    }
    
    private void editSupplier(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một NCC để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maNCC = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        NhaCungCapDTO ncc = nhaCungCapBus.getById(maNCC);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditSupplierGUI((JFrame) window, nhaCungCapBus, "Sửa Nhà Cung Cấp", "save", ncc);
        loadTableData();
    }
    
    private void deleteSupplier(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một NCC để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac chan khong", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maNCC = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (nhaCungCapBus.deleteNhaCungCap(maNCC)) { 
                JOptionPane.showMessageDialog(this, "Xóa NCC thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa NCC thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        ArrayList<NhaCungCapDTO> listNCC = nhaCungCapBus.searchNhaCungCap(keyword);
        for (NhaCungCapDTO ncc : listNCC) {
            tableModel.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChi(),
                ncc.getSdt()
            });
        }
    }
}

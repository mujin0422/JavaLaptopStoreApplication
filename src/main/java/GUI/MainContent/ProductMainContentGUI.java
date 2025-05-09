package GUI.MainContent;

import BUS.CpuBUS;
import BUS.DoPhanGiaiBUS;
import BUS.RamBUS;
import BUS.RomBUS;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import BUS.ThuongHieuBUS;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditProductGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ProductMainContentGUI extends JPanel implements ReloadablePanel{
    private SanPhamBUS sanPhamBUS;
    private ThuongHieuBUS thuongHieuBUS;
    private DoPhanGiaiBUS doPhanGiaiBUS;
    private RamBUS ramBUS;
    private RomBUS romBUS;
    private CpuBUS cpuBUS;
    private UIButton btnAdd, btnDelete, btnEdit, btnExcel;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private TaiKhoanBUS taiKhoanBUS;

    public ProductMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.sanPhamBUS = new SanPhamBUS();
        this.ramBUS = new RamBUS();
        this.romBUS = new RomBUS();
        this.cpuBUS = new CpuBUS();
        this.thuongHieuBUS = new ThuongHieuBUS();
        this.doPhanGiaiBUS = new DoPhanGiaiBUS();
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH - 200 - 10, UIConstants.HEIGHT - 200 - 10));
        this.setLayout(new BorderLayout(5, 5));

        //=========================== Panel Header =============================
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 100, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addProduct()); 
        btnDelete = new UIButton("menuButton", "XÓA", 100, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteProduct());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editProduct());
        btnExcel = new UIButton("menuButton", "EXCEL", 100, 40, "/Icon/excel_icon.png");
        btnExcel.addActionListener(e -> exportExcel());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        pnlButton.add(btnExcel);
        
        applyPermissions(taiKhoan.getTenDangNhap(), 1);
        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(190,30);
        pnlSearchFilter.add(txtSearch);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //============================ Panel Content ===========================
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        String[] columnNames = {"MÃ", "TÊN SẢN PHÂM", "GIÁ", "TỒN KHO", "CPU", "RAM", "ROM", "ĐỘ PHÂN GIẢI", "THƯƠNG HIỆU", "THỜI GIAN BH"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);

        pnlContent.add(scrollPane, BorderLayout.CENTER);
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
    
    public void loadTableData() { 
        tableModel.setRowCount(0); 
        for (SanPhamDTO sp : sanPhamBUS.getAllSanPham()) {
            tableModel.addRow(new Object[] {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP() + " VNĐ",
                sp.getSoLuongTon(),
                cpuBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaCPU()).getTenCPU(),
                ramBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaRAM()).getDungLuongRAM(),
                romBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaROM()).getDungLuongROM(),
                doPhanGiaiBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaDPG()).getTenDPG(),
                thuongHieuBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaTH()).getTenTH(),
                sp.getThoiGianBH() + " tháng"
            });
        }
    }
    
    private void exportExcel() {
        try {
            File exportDir = new File("bang");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            String filePath = "bang/DanhSachSanPham.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Danh sách sản phẩm");

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
            JOptionPane.showMessageDialog(this, "Đã xuất file Excel!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi xuất file Excel: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProduct() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditProductGUI((JFrame) window, sanPhamBUS, "Thêm Sản Phẩm", "add");
        loadTableData();
    }
    private void editProduct(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maSP = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        SanPhamDTO sp = sanPhamBUS.getById(maSP);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditProductGUI((JFrame) window, sanPhamBUS, "Chỉnh sửa sản phẩm", "save", sp);
        loadTableData();
    }
    private void deleteProduct(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maSP = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (sanPhamBUS.deleteSanPham(maSP)) { 
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa san phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        ArrayList<SanPhamDTO> listSP = sanPhamBUS.searchSanPham(keyword);
        for (SanPhamDTO sp : listSP) {
            tableModel.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon(),
                cpuBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaCPU()).getTenCPU(),
                ramBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaRAM()).getDungLuongRAM(),
                romBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaROM()).getDungLuongROM(),
                doPhanGiaiBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaDPG()).getTenDPG(),
                thuongHieuBUS.getById(sanPhamBUS.getById(sp.getMaSP()).getMaTH()).getTenTH(),
                sp.getThoiGianBH()
            });
        }
    }
}

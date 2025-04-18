package GUI.MainContent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditProductGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ProductMainContentGUI extends JPanel implements ReloadablePanel{
    private SanPhamBUS sanPhamBUS;
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;

    public ProductMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.sanPhamBUS = new SanPhamBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH - 200 - 10, UIConstants.HEIGHT - 200 - 10));
        this.setLayout(new BorderLayout(5, 5));

        //=========================== Panel Header =============================
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addProduct()); 
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteProduct());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editProduct());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);

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
    
    public void loadTableData() { 
        tableModel.setRowCount(0); 
        for (SanPhamDTO sp : sanPhamBUS.getAllSanPham()) {
            tableModel.addRow(new Object[] {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon(),
                sanPhamBUS.getTenCpuByMaSp(sp.getMaSP()),
                sanPhamBUS.getDungLuongRamByMaSp(sp.getMaSP()) ,
                sanPhamBUS.getDungLuongRomByMaSp(sp.getMaSP()),
                sanPhamBUS.getTenDpgByMaSp(sp.getMaSP()),
                sanPhamBUS.getTenThByMaSp(sp.getMaSP()),
                sp.getThoiGianBH()
            });
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
        SanPhamDTO sp = sanPhamBUS.getSanPhamById(maSP);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditProductGUI((JFrame) window, sanPhamBUS, "Chỉnh sửa sản phẩm", "save", sp);
        loadTableData();
    }
    private void deleteProduct(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một san pham để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac chan khong", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maSP = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (sanPhamBUS.deleteSanPham(maSP)) { 
                JOptionPane.showMessageDialog(this, "Xóa san pham thanh công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
        ArrayList<SanPhamDTO> listSP = sanPhamBUS.searchSanPham1(keyword);
        for (SanPhamDTO sp : listSP) {
            tableModel.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon(),
                sanPhamBUS.getTenCpuByMaSp(sp.getMaSP()),
                sanPhamBUS.getDungLuongRamByMaSp(sp.getMaSP()) ,
                sanPhamBUS.getDungLuongRomByMaSp(sp.getMaSP()),
                sanPhamBUS.getTenDpgByMaSp(sp.getMaSP()),
                sanPhamBUS.getTenThByMaSp(sp.getMaSP()),
                sp.getThoiGianBH()
            });
        }
    }

}

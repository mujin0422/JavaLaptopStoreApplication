package GUI.MainContent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import GUI.MainContentDiaLog.AddAndEditProductGUI;
import Utils.UIButton;
import Utils.UIConstants;

public class ProductMainContentGUI extends JPanel {
    private SanPhamBUS sanPhamBUS;
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;

    public ProductMainContentGUI() {
        this.sanPhamBUS = new SanPhamBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH - 200 - 10, UIConstants.HEIGHT - 200 - 10));
        this.setLayout(new BorderLayout(5, 5));

        //=========================== Panel Header =============================
        pnlHeader = new JPanel();
        
        pnlHeader.setLayout(null); // Sử dụng null layout
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        // Tạo các button (Nằm bên trái)
        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addProduct()); // Thêm sự kiện cho nút thêm
        btnDelete = new UIButton("menuButton", "XÓA", 100, 30, "/Icon/xoa_icon.png");
        btnEdit = new UIButton("menuButton", "SỬA", 100, 30, "/Icon/sua_icon.png");

        btnAdd.setBounds(10, 10, 100, 30);
        btnDelete.setBounds(120, 10, 100, 30);
        btnEdit.setBounds(230, 10, 100, 30);

        // Tạo combobox và ô tìm kiếm (Nằm bên phải)
        int panelWidth = this.getPreferredSize().width; // Lấy chiều rộng thực tế
        cbFilter = new JComboBox<>(new String[]{"Lọc"});
        cbFilter.setBounds(panelWidth - 310, 10, 100, 30);

        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 200, 10, 190, 30);

        // Thêm tất cả vào pnlHeader
        pnlHeader.add(btnAdd);
        pnlHeader.add(btnDelete);
        pnlHeader.add(btnEdit);
        pnlHeader.add(cbFilter);
        pnlHeader.add(txtSearch);
        
        //============================ Panel Content ===========================
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);

        // Tạo bảng dữ liệu
        String[] columnNames = {"MÃ SẢN PHẨM", "TÊN SẢN PHÂM", "GIÁ SẢN PHẨM", "NHÀ XUẤT BẢN", "TỒN KHO", "....."};
        Object[][] data = {}; // Chưa có dữ liệu
        tableModel = new DefaultTableModel(data,columnNames);
        tblContent = new JTable(tableModel);
        
        // Thiết lập header của bảng
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.setRowHeight(30);

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tblContent);
        scrollPane.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);

        // Thêm JScrollPane vào pnlContent
        pnlContent.add(scrollPane, BorderLayout.CENTER);

        // Thêm panel tiêu đề và bảng vào giao diện chính
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    


    private void loadTableData() {
        
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        ArrayList<SanPhamDTO> dsSanPham = sanPhamBUS.getAllSanPham();

        for (SanPhamDTO sp : dsSanPham) {
            tableModel.addRow(new Object[] {
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon(),
                sp.getMaCPU(),
                sp.getMaRAM(),
                sp.getMaROM(),
                sp.getMaDPG(),
                sp.getMaLoai(),
                sp.getMaTH(),
                sp.getThoiGianBH()
            });
        }
    }

    private void addProduct() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditProductGUI((JFrame) window, sanPhamBUS, "Thêm Sản Phẩm", "add");
        // loadTableData(); // Cập nhật lại bảng sau khi thêm
    }

}

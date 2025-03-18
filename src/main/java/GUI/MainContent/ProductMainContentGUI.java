package GUI.MainContent;

import Utils.UIConstants;
import Utils.UIButton;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductMainContentGUI extends JPanel {
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;

    public ProductMainContentGUI() {
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
        String[] columnNames = {"MÃ", "TÊN SÁCH", "TÁC GIẢ", "NHÀ XUẤT BẢN", "TỒN KHO", "....."};
        Object[][] data = {}; // Chưa có dữ liệu
        tblContent = new JTable(new DefaultTableModel(data, columnNames));

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
    }
}

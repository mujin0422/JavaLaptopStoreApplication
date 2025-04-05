package GUI.MainContent;

import DAO.PhieuXuatDAO;
import DAO.SanPhamDAO;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import Utils.Session;
import Utils.UIButton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ExportProductMainContentGUI extends JPanel {
    private JTable table, tableProduct;  // Đổi tên từ tableKhachHang thành tableProduct
    private DefaultTableModel tableModel, modelProduct;  // Đổi từ modelKhachHang thành modelProduct
    private JTextField txtInfo;
    private PhieuXuatDAO phieuXuatDAO = new PhieuXuatDAO();
    private SanPhamDAO productDAO = new SanPhamDAO();  // Giả sử bạn có một DAO cho sản phẩm
    private NhanVienDAO nhanVienDAO = new NhanVienDAO();
    private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

    public ExportProductMainContentGUI() {
        setLayout(new GridBagLayout());
        setBackground(new Color(204, 204, 204));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Panel chứa các button thêm, sửa, xóa
        JPanel buttonPanel = createButtonPanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);

        // Panel thông tin nhân viên đăng nhập
        JPanel infoPanel = createInfoPanel();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(infoPanel, gbc);

        // Panel danh sách sản phẩm thay vì khách hàng
        JPanel listPanel = createProductPanel();  // Sửa thành createProductPanel
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridheight = 2;
        gbc.weighty = 0.5;
        gbc.fill = GridBagConstraints.BOTH;
        add(listPanel, gbc);

        // Panel danh sách phiếu xuất
        JPanel tablePanel = createTablePanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(tablePanel, gbc);

        loadProduct();  // Gọi phương thức loadProduct thay vì loadKhachHang
        loadPhieuXuat();
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Thao tác"));
        panel.setBackground(Color.WHITE);

        UIButton btnThem = new UIButton("add", "THÊM", 120, 40, "/Icon/them_icon.png");
        UIButton btnSua = new UIButton("edit", "SỬA", 120, 40, "/Icon/sua_icon.png");
        UIButton btnXoa = new UIButton("delete", "XÓA", 120, 40, "/Icon/xoa_icon.png");

        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);

        return panel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        txtInfo = new JTextField(getNhanVienInfo(), 20);
        txtInfo.setEditable(false);
        txtInfo.setHorizontalAlignment(JTextField.CENTER);
        txtInfo.setBackground(Color.LIGHT_GRAY);
        infoPanel.add(txtInfo, BorderLayout.CENTER);
        return infoPanel;
    }

    private JPanel createProductPanel() {  // Đổi tên phương thức thành createProductPanel
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        listPanel.setBackground(Color.WHITE);

        modelProduct = new DefaultTableModel(new Object[]{"Mã SP", "Tên SP"}, 0);  // Chỉnh lại cột để hiển thị sản phẩm
        tableProduct = new JTable(modelProduct);  // Đổi từ tableKhachHang thành tableProduct
        tableProduct.setRowHeight(25);
        tableProduct.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(tableProduct);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        return listPanel;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu xuất"));
        tablePanel.setBackground(Color.WHITE);

        tableModel = new DefaultTableModel(new Object[]{"Mã phiếu", "Mã NV", "Mã KH", "Tổng tiền", "Ngày"}, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        return tablePanel;
    }

    private void loadPhieuXuat() {
        tableModel.setRowCount(0);
        List<PhieuXuatDTO> list = phieuXuatDAO.getAll();
        for (PhieuXuatDTO px : list) {
            tableModel.addRow(new Object[]{px.getMaPX(), px.getMaNV(), px.getMaKH(), px.getTongTien(), px.getNgayXuat()});
        }
    }

    private void loadProduct() {  // Phương thức mới để tải danh sách sản phẩm
        modelProduct.setRowCount(0);
        List<SanPhamDTO> list = productDAO.getAll();  // Giả sử bạn có một ProductDAO để lấy dữ liệu sản phẩm
        for (SanPhamDTO product : list) {
            modelProduct.addRow(new Object[]{product.getMaSP(), product.getTenSP()});  // Hiển thị mã và tên sản phẩm
        }
    }

    private String getNhanVienInfo() {
        String tenDangNhap = Session.getCurrentUsername(); // Lấy tên đăng nhập của người dùng hiện tại
        TaiKhoanDTO tk = taiKhoanDAO.getByTenDangNhap(tenDangNhap); // Lấy thông tin tài khoản từ tên đăng nhập
        if (tk != null) {
            NhanVienDTO nv = nhanVienDAO.getById(tk.getMaNV()); // Lấy thông tin nhân viên từ mã nhân viên
            if (nv != null) {
                return "NV: " + nv.getMaNV() + " - " + nv.getTenNV(); // Hiển thị mã và tên nhân viên
            }
        }
        return "Không tìm thấy nhân viên"; // Trường hợp không tìm thấy nhân viên
    }
}


package GUI.MainContent;

import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import DTO.SanPhamDTO;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
import BUS.PhieuXuatBUS;
import Utils.UIConstants;
import Utils.Session;
import Utils.UIButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ExportProductMainContentGUI extends JPanel {
    private JTable table, tableProduct, tableCart;
    private DefaultTableModel tableModel, modelProduct, modelCart;
    private JTextField txtMaPhieu, txtTenNV, txtSearch, txtSoLuong;
    private JComboBox<String> cbMaKhach;
    private UIButton btnThem;
    private JButton btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnLoc;
    private PhieuXuatBUS pxBUS = new PhieuXuatBUS();

    public ExportProductMainContentGUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setBackground(UIConstants.SUB_BACKGROUND);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnThem = new UIButton("menuButton", " THÊM", 120, 35, "/Icon/them_icon.png");
        topPanel.add(btnThem);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBackground(UIConstants.SUB_BACKGROUND);
        add(centerPanel, BorderLayout.CENTER);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout(15, 15));
        leftPanel.setBackground(UIConstants.SUB_BACKGROUND);
        centerPanel.add(leftPanel);

        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin phiếu xuất"));

        txtMaPhieu = new JTextField(pxBUS.getMaPhieuXuatTiepTheo());
        txtMaPhieu.setEditable(false);  // Không cho phép chỉnh sửa mã phiếu xuất
        txtTenNV = new JTextField();
        txtTenNV.setEditable(false);
        cbMaKhach = new JComboBox<>();

        infoPanel.add(new JLabel("Mã phiếu xuất:"));
        infoPanel.add(txtMaPhieu);
        infoPanel.add(new JLabel("Mã nhân viên:"));
        infoPanel.add(txtTenNV);
        infoPanel.add(new JLabel("Mã khách hàng:"));
        infoPanel.add(cbMaKhach);
        leftPanel.add(infoPanel, BorderLayout.NORTH);

        String[] cartCols = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"};
        modelCart = new DefaultTableModel(cartCols, 0);
        tableCart = new JTable(modelCart);
        JScrollPane cartScrollPane = new JScrollPane(tableCart);
        cartScrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm tạm"));
        cartScrollPane.setPreferredSize(new Dimension(400, 200));
        leftPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(UIConstants.SUB_BACKGROUND);

        btnXoaKhoiPhieu = new JButton("Xóa khỏi phiếu");
        btnXoaKhoiPhieu.setBackground(new Color(220, 53, 69));
        btnXoaKhoiPhieu.setForeground(Color.WHITE);

        btnSuaSoLuong = new JButton("Sửa số lượng");
        btnSuaSoLuong.setBackground(new Color(0, 123, 255));
        btnSuaSoLuong.setForeground(Color.WHITE);

        actionPanel.add(btnXoaKhoiPhieu);
        actionPanel.add(btnSuaSoLuong);
        leftPanel.add(actionPanel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setBackground(UIConstants.SUB_BACKGROUND);
        centerPanel.add(rightPanel);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        searchPanel.setBackground(UIConstants.SUB_BACKGROUND);
        txtSearch = new JTextField(20);
        btnLoc = new JButton("Tìm Kiếm");
        btnLoc.setBackground(UIConstants.MAIN_BUTTON);
        btnLoc.setForeground(Color.WHITE);
        searchPanel.add(txtSearch);
        searchPanel.add(btnLoc);
        rightPanel.add(searchPanel, BorderLayout.NORTH);

        String[] productCols = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "ĐƠN GIÁ", "SỐ LƯỢNG"};
        modelProduct = new DefaultTableModel(productCols, 0);
        tableProduct = new JTable(modelProduct);
        
        tableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProduct.setRowSelectionAllowed(true);

        tableProduct.setSelectionBackground(Color.CYAN);
        tableProduct.setSelectionForeground(Color.BLACK);

        JScrollPane productScroll = new JScrollPane(tableProduct);
        productScroll.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        productScroll.setPreferredSize(new Dimension(400, 250));
        rightPanel.add(productScroll, BorderLayout.CENTER);


        JPanel quantityAndAddPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        quantityAndAddPanel.setBackground(UIConstants.SUB_BACKGROUND);

        JLabel lblSoLuong = new JLabel("SỐ LƯỢNG:");
        txtSoLuong = new JTextField(10);
        quantityAndAddPanel.add(lblSoLuong);
        quantityAndAddPanel.add(txtSoLuong);

        btnThemVaoPhieu = new JButton("THÊM VÀO PHIẾU");
        btnThemVaoPhieu.setBackground(new Color(40, 167, 69));
        btnThemVaoPhieu.setForeground(Color.WHITE);
        quantityAndAddPanel.add(btnThemVaoPhieu);

        rightPanel.add(quantityAndAddPanel, BorderLayout.SOUTH);

        String[] cols = {"MÃ PHIẾU XUẤT", "MÃ NHÂN VIÊN", "MÃ KHÁCH HÀNG", "TỔNG TIỀN", "NGÀY XUẤT"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Danh sách phiếu xuất"));
        scrollPane.setPreferredSize(new Dimension(800, 200));
        add(scrollPane, BorderLayout.SOUTH);

        initializeData();
        addEventHandlers();
    }

    private void loadProductData() {
        SanPhamDAO sanPhamDAO = new SanPhamDAO();
        List<SanPhamDTO> danhSachSanPham = sanPhamDAO.getAllSanPham();

        for (SanPhamDTO sanPham : danhSachSanPham) {
            modelProduct.addRow(new Object[]{
                    sanPham.getMaSP(),
                    sanPham.getTenSP(),
                    sanPham.getGiaSP(),
                    sanPham.getSoLuongTon()
            });
        }
    }
    
    private void loadPhieuXuatData() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        List<PhieuXuatDTO> danhSachPhieuXuat = pxBUS.getAllPhieuXuat();
        for (PhieuXuatDTO px : danhSachPhieuXuat) {
            tableModel.addRow(new Object[]{
                px.getMaPX(),
                px.getMaNV(),
                px.getMaKH(),
                px.getTongTien(),
                px.getNgayXuat()
            });
        }
    }

    private void loadCustomerData() {
        KhachHangDAO khachHangDAO = new KhachHangDAO();
        List<KhachHangDTO> danhSachKhachHang = khachHangDAO.getAllKhachHang();

        for (KhachHangDTO khachHang : danhSachKhachHang) {
            cbMaKhach.addItem(khachHang.getMaKH());
        }
    }

    public void initializeData() {
        String maNhanVienDangNhap = Session.getUser().getMaNV();
        txtTenNV.setText(maNhanVienDangNhap);
        txtTenNV.setEditable(false);
        loadProductData();
        loadCustomerData();
        loadPhieuXuatData();
    }

    private void addEventHandlers() {
        // Bật tính năng chọn dòng trong bảng sản phẩm
        tableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProduct.setRowSelectionAllowed(true);
        
        tableProduct.setSelectionBackground(Color.CYAN);
        tableProduct.setSelectionForeground(Color.BLACK);

        // Lắng nghe sự kiện thay đổi lựa chọn trong bảng sản phẩm
        tableProduct.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableProduct.getSelectedRow();
                if (selectedRow != -1) {

                    String maSP = modelProduct.getValueAt(selectedRow, 0).toString();
                    String tenSP = modelProduct.getValueAt(selectedRow, 1).toString();
                    double donGia = Double.parseDouble(modelProduct.getValueAt(selectedRow, 2).toString());

                    // Cập nhật thông tin sản phẩm đã chọn vào giao diện người dùng (có thể in ra hoặc hiển thị)
                    System.out.println("Đã chọn sản phẩm: " + tenSP + " với mã: " + maSP + " và đơn giá: " + donGia);
                }
            }
        });

        // Lắng nghe sự kiện thêm sản phẩm vào phiếu tạm
            btnThemVaoPhieu.addActionListener(e -> {
            int selectedRow = tableProduct.getSelectedRow();  // Lấy chỉ số dòng được chọn
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
                return;
            }

            // Lấy dữ liệu từ dòng đã chọn
            String maSP = modelProduct.getValueAt(selectedRow, 0).toString();
            String tenSP = modelProduct.getValueAt(selectedRow, 1).toString();
            double donGia = Double.parseDouble(modelProduct.getValueAt(selectedRow, 2).toString());
            int soLuongTon = Integer.parseInt(modelProduct.getValueAt(selectedRow, 3).toString());

            // Kiểm tra số lượng nhập vào có hợp lệ không
            int soLuong;
            try {
                soLuong = Integer.parseInt(txtSoLuong.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return;
            }

            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                return;
            }

            // Kiểm tra số lượng nhập vào có vượt quá số lượng tồn kho không
            if (soLuong > soLuongTon) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập vào không thể vượt quá số lượng tồn kho! (Tồn kho: " + soLuongTon + ")");
                return;
            }

            // Kiểm tra xem sản phẩm đã có trong bảng sản phẩm tạm chưa
            for (int i = 0; i < modelCart.getRowCount(); i++) {
                if (modelCart.getValueAt(i, 0).equals(maSP)) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại trong phiếu!");
                    return;
                }
            }

            // Thêm sản phẩm vào bảng sản phẩm tạm
            modelCart.addRow(new Object[]{maSP, tenSP, soLuong, donGia * soLuong});
        });

        // Xóa sản phẩm khỏi phiếu tạm
        btnXoaKhoiPhieu.addActionListener(e -> {
            int selected = tableCart.getSelectedRow();
            if (selected != -1) modelCart.removeRow(selected);
        });
        
        // Lắng nghe sự kiện nhấn "Sửa số lượng"
        btnSuaSoLuong.addActionListener(e -> {
            int selectedRow = tableCart.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa số lượng!");
                return;
            }

            String input = JOptionPane.showInputDialog("Nhập số lượng mới:");

            if (input != null) {
                try {
                    int newQty = Integer.parseInt(input);

                    if (newQty <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                        return;
                    }

                    double donGia = (double) modelCart.getValueAt(selectedRow, 3) / (int) modelCart.getValueAt(selectedRow, 2);

                    // Cập nhật số lượng mới và thành tiền
                    modelCart.setValueAt(newQty, selectedRow, 2);
                    modelCart.setValueAt(donGia * newQty, selectedRow, 3);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                }
            }
        });


        // Lọc phiếu xuất theo tìm kiếm
        btnLoc.addActionListener(e -> {
            String keyword = txtSearch.getText().trim().toLowerCase();
            tableModel.setRowCount(0);
            for (PhieuXuatDTO px : pxBUS.searchPhieuXuat(keyword)) {
                tableModel.addRow(new Object[]{
                    px.getMaPX(),
                    px.getMaNV(),
                    px.getMaKH(),
                    px.getTongTien(),
                    px.getNgayXuat()
                });
            }
        });

        // Thêm phiếu xuất
        btnThem.addActionListener(e -> {
            if (modelCart.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
                return;
            }

            int tongTien = 0;
            for (int i = 0; i < modelCart.getRowCount(); i++) {
                try {
                    tongTien += Double.parseDouble(modelCart.getValueAt(i, 3).toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Thành tiền không hợp lệ ở dòng " + (i + 1));
                    return;
                }
            }

            try {
                int maPX = Integer.parseInt(txtMaPhieu.getText().replace("PX", "").trim());
                int maNV = Integer.parseInt(txtTenNV.getText().trim());
                int maKH = Integer.parseInt(cbMaKhach.getSelectedItem().toString().trim());

                PhieuXuatDTO px = new PhieuXuatDTO();
                px.setMaPX(maPX);
                px.setMaNV(maNV);
                px.setMaKH(maKH);
                px.setTongTien(tongTien);
                px.setNgayXuat(LocalDate.now().toString());  // Tự động set ngày xuất

                if (pxBUS.addPhieuXuat(px)) {
                    JOptionPane.showMessageDialog(this, "Lưu phiếu xuất thành công!");
                    modelCart.setRowCount(0);
                    tableModel.addRow(new Object[]{
                        px.getMaPX(),
                        px.getMaNV(),
                        px.getMaKH(),
                        px.getTongTien(),
                        px.getNgayXuat()
                    });
                    txtMaPhieu.setText(pxBUS.getMaPhieuXuatTiepTheo());
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu phiếu xuất thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dữ liệu số không hợp lệ: " + ex.getMessage());
            }
        });
    }
}

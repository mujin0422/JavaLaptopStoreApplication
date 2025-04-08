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
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ExportProductMainContentGUI extends JPanel {
    private UITable table;
    private JTable tableProduct, tableCart;
    private DefaultTableModel tableModel, modelProduct, modelCart;
    private JTextField txtMaPhieu, txtTenNV;
    private UITextField txtSearch, txtSoLuong, txtTongTien;
    private JComboBox<String> cbMaKhach;
    private UIButton btnThem, btnXem , btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnLoc;
    private PhieuXuatBUS pxBUS;

    public ExportProductMainContentGUI() {
        pxBUS = new PhieuXuatBUS();
        
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnThem = new UIButton("menuButton", " THÊM", 100, 35, "/Icon/them_icon.png");
        btnXem = new UIButton("menuButton", "XEM", 100, 33, "/Icon/chitiet_icon.png");
        topPanel.add(btnThem);
        topPanel.add(btnXem);
        

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlFormNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlFormNorth.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlFormNorth.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlFormNorth.setPreferredSize(new Dimension(0, 100));
        txtMaPhieu = new JTextField(pxBUS.getMaPhieuXuatTiepTheo());
        txtMaPhieu.setEditable(false);  
        txtTenNV = new JTextField();
        txtTenNV.setEditable(false);
        cbMaKhach = new JComboBox<>();
        txtMaPhieu.setPreferredSize(new Dimension(330,25));
        txtTenNV.setPreferredSize(new Dimension(330,25));
        cbMaKhach.setPreferredSize(new Dimension(330,25));
        pnlFormNorth.add(new UILabel("Mã phiếu xuất:",160, 25));
        pnlFormNorth.add(txtMaPhieu);
        pnlFormNorth.add(new UILabel("Mã nhân viên:" ,160, 25));
        pnlFormNorth.add(txtTenNV);
        pnlFormNorth.add(new UILabel("Mã khách hàng:", 160, 25));
        pnlFormNorth.add(cbMaKhach);
        leftPanel.add(pnlFormNorth, BorderLayout.NORTH);
        
        String[] cartCols = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"};
        modelCart = new DefaultTableModel(cartCols, 0);
        tableCart = new JTable(modelCart);
        tableCart.setRowHeight(30);
        UIScrollPane cartScrollPane = new UIScrollPane(tableCart);
        leftPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel pnlFormSouth = new JPanel(new GridLayout(2, 1, 5, 5)); 
        pnlFormSouth.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER,25,5)); 
        btnXoaKhoiPhieu = new UIButton("delete", "XÓA KHỎI PHIẾU", 130, 30);
        btnSuaSoLuong = new UIButton("edit", "SỬA SỐ LƯỢNG", 130, 30);
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        pnl1.add(btnSuaSoLuong);
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
        pnl2.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlGroupTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlGroupTongTien.add(new UILabel("Tổng tiền:",160,25));
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        txtTongTien = new UITextField(330, 25);
        txtTongTien.setEditable(false); 
        pnlGroupTongTien.add(txtTongTien);
        pnl2.add(pnlGroupTongTien, BorderLayout.WEST);
        pnlFormSouth.add(pnl1);
        pnlFormSouth.add(pnl2);
        leftPanel.add(pnlFormSouth, BorderLayout.SOUTH);
        
        JPanel rightPanel = new JPanel(new BorderLayout(5,5));
        rightPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        rightPanel.setPreferredSize(new Dimension(550, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        searchPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(420, 30);
        btnLoc = new UIButton("add","Tìm Kiếm",100, 30);
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
        UIScrollPane productScroll = new UIScrollPane(tableProduct);
        rightPanel.add(productScroll, BorderLayout.CENTER);

        JPanel quantityAndAddPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        quantityAndAddPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        UILabel lblSoLuong = new UILabel("Số lượng:", 80, 30);
        txtSoLuong = new UITextField(50,30);
        quantityAndAddPanel.add(lblSoLuong);
        quantityAndAddPanel.add(txtSoLuong);
        btnThemVaoPhieu = new UIButton("add" ,"THÊM VÀO PHIẾU", 200, 30);
        quantityAndAddPanel.add(btnThemVaoPhieu);
        rightPanel.add(quantityAndAddPanel, BorderLayout.SOUTH);

        String[] cols = {"MÃ PHIẾU XUẤT", "MÃ NHÂN VIÊN", "MÃ KHÁCH HÀNG", "TỔNG TIỀN", "NGÀY XUẤT"};
        tableModel = new DefaultTableModel(cols, 0);
        table = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(0, 200));
        
        
        this.add(topPanel, BorderLayout.NORTH);
        this.add(leftPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(scrollPane, BorderLayout.SOUTH);
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
        tableModel.setRowCount(0); 
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
        int maNhanVienDangNhap = Session.getUser().getMaNV();
        txtTenNV.setText(String.valueOf(maNhanVienDangNhap));
        txtTenNV.setEditable(false);
        loadProductData();
        loadCustomerData();
        loadPhieuXuatData();
    }

    private void addEventHandlers() {
        tableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableProduct.setRowSelectionAllowed(true);
        
        tableProduct.setSelectionBackground(Color.CYAN);
        tableProduct.setSelectionForeground(Color.BLACK);

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

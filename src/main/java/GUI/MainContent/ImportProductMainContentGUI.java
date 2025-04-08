package GUI.MainContent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import BUS.PhieuNhapBUS;
import DAO.NhaCungCapDAO;
import DAO.SanPhamDAO;
import DTO.NhaCungCapDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import Utils.Session;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;

public class ImportProductMainContentGUI extends JPanel {
    private UITable table;
    private JTable tableProduct, tableCart;
    private DefaultTableModel tableModel, modelProduct, modelCart;
    private JTextField txtMaPhieu, txtMaNV;
    private UITextField txtSearch, txtSoLuong, txtTongTien;
    private JComboBox<String> cbTenNCC;
    private UIButton btnThem, btnXem, btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnLoc;
    private PhieuNhapBUS imBUS;
    private List<NhaCungCapDTO> nhaCungCapList;

    public ImportProductMainContentGUI() {
        imBUS = new PhieuNhapBUS();

        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        btnThem = new UIButton("menuButton", " THÊM", 100, 35, "/Icon/them_icon.png");
        btnXem = new UIButton("menuButton", "XEM", 100, 33, "/Icon/chitiet_icon.png");
        topPanel.add(btnThem);
        topPanel.add(btnXem);

        // Left Panel
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlFormNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlFormNorth.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlFormNorth.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlFormNorth.setPreferredSize(new Dimension(0, 100));
        txtMaPhieu = new JTextField(imBUS.getMaPhieuNhapTiepTheo());
        txtMaPhieu.setEditable(false);
        txtMaNV = new JTextField();
        txtMaNV.setEditable(false);
        cbTenNCC = new JComboBox<>();
        txtMaPhieu.setPreferredSize(new Dimension(330, 25));
        txtMaNV.setPreferredSize(new Dimension(330, 25));
        cbTenNCC.setPreferredSize(new Dimension(330, 25));
        pnlFormNorth.add(new UILabel("Mã phiếu nhập:", 160, 25));
        pnlFormNorth.add(txtMaPhieu);
        pnlFormNorth.add(new UILabel("Mã nhân viên:", 160, 25));
        pnlFormNorth.add(txtMaNV);
        pnlFormNorth.add(new UILabel("Nhà cung cấp:", 160, 25));
        pnlFormNorth.add(cbTenNCC);
        leftPanel.add(pnlFormNorth, BorderLayout.NORTH);

        String[] cartCols = {"MÃ SẢN PHẨM", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"};
        modelCart = new DefaultTableModel(cartCols, 0);
        tableCart = new JTable(modelCart);
        tableCart.setRowHeight(30);
        UIScrollPane cartScrollPane = new UIScrollPane(tableCart);
        leftPanel.add(cartScrollPane, BorderLayout.CENTER);

        JPanel pnlFormSouth = new JPanel(new GridLayout(2, 1, 5, 5));
        pnlFormSouth.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5));
        btnXoaKhoiPhieu = new UIButton("delete", "XÓA KHỎI PHIẾU", 130, 30);
        btnSuaSoLuong = new UIButton("edit", "SỬA SỐ LƯỢNG", 130, 30);
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        pnl1.add(btnSuaSoLuong);
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));
        pnl2.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlGroupTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlGroupTongTien.add(new UILabel("Tổng tiền:", 160, 25));
        txtTongTien = new UITextField(330, 25);
        txtTongTien.setEditable(false);
        pnlGroupTongTien.add(txtTongTien);
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl2.add(pnlGroupTongTien, BorderLayout.WEST);
        pnlFormSouth.add(pnl1);
        pnlFormSouth.add(pnl2);
        leftPanel.add(pnlFormSouth, BorderLayout.SOUTH);

        // Right Panel
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        rightPanel.setPreferredSize(new Dimension(550, 0));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        searchPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(420, 30);
        btnLoc = new UIButton("add", "TÌM KIẾM", 100, 30);
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
        txtSoLuong = new UITextField(50, 30);
        btnThemVaoPhieu = new UIButton("add", "THÊM VÀO PHIẾU", 200, 30);
        quantityAndAddPanel.add(lblSoLuong);
        quantityAndAddPanel.add(txtSoLuong);
        quantityAndAddPanel.add(btnThemVaoPhieu);
        rightPanel.add(quantityAndAddPanel, BorderLayout.SOUTH);

        String[] cols = {"MÃ PHIẾU NHẬP", "MÃ NHÂN VIÊN", "TÊN NCC", "TỔNG TIỀN", "NGÀY NHẬP"};
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
        modelProduct.setRowCount(0);
        for (SanPhamDTO sanPham : danhSachSanPham) {
            modelProduct.addRow(new Object[]{
                sanPham.getMaSP(),
                sanPham.getTenSP(),
                sanPham.getGiaSP(),
                sanPham.getSoLuongTon()
            });
        }
    }

    private void loadPhieuNhapData() {
        tableModel.setRowCount(0);
        List<PhieuNhapDTO> danhSachPhieuNhap = imBUS.getAllPhieuNhap();
        for (PhieuNhapDTO pn : danhSachPhieuNhap) {
            String tenNCC = getTenNCCFromMaNCC(pn.getMaNCC());
            tableModel.addRow(new Object[]{
                pn.getMaPN(),
                pn.getMaNV(),
                tenNCC,
                pn.getTongTien(),
                pn.getNgayNhap()
            });
        }
    }

    private void loadNCCData() {
        NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO();
        nhaCungCapList = nhaCungCapDAO.getAllNhaCungCap();
        cbTenNCC.removeAllItems();
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            cbTenNCC.addItem(ncc.getTenNCC());
        }
    }

    private String getTenNCCFromMaNCC(int maNCC) {
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            if (ncc.getMaNCC() == maNCC) {
                return ncc.getTenNCC();
            }
        }
        return "Unknown";
    }

    private int getMaNCCFromTenNCC(String tenNCC) {
        for (NhaCungCapDTO ncc : nhaCungCapList) {
            if (ncc.getTenNCC().equals(tenNCC)) {
                return ncc.getMaNCC();
            }
        }
        return -1;
    }

    public void initializeData() {
        int maNhanVienDangNhap = Session.getUser().getMaNV();
        txtMaNV.setText(String.valueOf(maNhanVienDangNhap));
        loadProductData();
        loadNCCData();
        loadPhieuNhapData();
    }

    private void updateTongTien() {
        double tongTien = 0;
        for (int i = 0; i < modelCart.getRowCount(); i++) {
            tongTien += Double.parseDouble(modelCart.getValueAt(i, 3).toString());
        }
        txtTongTien.setText(String.format("%.2f", tongTien));
    }

    private void addEventHandlers() {
        tableProduct.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableProduct.getSelectedRow();
                if (selectedRow != -1) {
                    String maSP = modelProduct.getValueAt(selectedRow, 0).toString();
                    String tenSP = modelProduct.getValueAt(selectedRow, 1).toString();
                    double donGia = Double.parseDouble(modelProduct.getValueAt(selectedRow, 2).toString());
                    System.out.println("Đã chọn sản phẩm: " + tenSP + " với mã: " + maSP + " và đơn giá: " + donGia);
                }
            }
        });

        btnThemVaoPhieu.addActionListener(e -> {
            int selectedRow = tableProduct.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
                return;
            }

            String maSP = modelProduct.getValueAt(selectedRow, 0).toString();
            String tenSP = modelProduct.getValueAt(selectedRow, 1).toString();
            double donGia = Double.parseDouble(modelProduct.getValueAt(selectedRow, 2).toString());
            int soLuongTon = Integer.parseInt(modelProduct.getValueAt(selectedRow, 3).toString());

            int soLuong;
            try {
                soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                if (soLuong <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                return;
            }


            for (int i = 0; i < modelCart.getRowCount(); i++) {
                if (modelCart.getValueAt(i, 0).equals(maSP)) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại trong phiếu!");
                    return;
                }
            }

            double thanhTien = donGia * soLuong;
            modelCart.addRow(new Object[]{maSP, tenSP, soLuong, thanhTien});
            updateTongTien();
            txtSoLuong.setText("");
        });

        btnXoaKhoiPhieu.addActionListener(e -> {
            int selected = tableCart.getSelectedRow();
            if (selected != -1) {
                modelCart.removeRow(selected);
                updateTongTien();
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa!");
            }
        });

        btnSuaSoLuong.addActionListener(e -> {
            int selectedRow = tableCart.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để sửa số lượng!");
                return;
            }

            String input = JOptionPane.showInputDialog(this, "Nhập số lượng mới:");
            if (input != null) {
                try {
                    int newQty = Integer.parseInt(input);
                    if (newQty <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!");
                        return;
                    }

                    double donGia = Double.parseDouble(modelCart.getValueAt(selectedRow, 3).toString()) / 
                                   Integer.parseInt(modelCart.getValueAt(selectedRow, 2).toString());
                    modelCart.setValueAt(newQty, selectedRow, 2);
                    modelCart.setValueAt(donGia * newQty, selectedRow, 3);
                    updateTongTien();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ!");
                }
            }
        });

        btnLoc.addActionListener(e -> {
            String keyword = txtSearch.getText().trim().toLowerCase();
            tableModel.setRowCount(0);
            for (PhieuNhapDTO pn : imBUS.searchPhieuNhap(keyword)) {
                String tenNCC = getTenNCCFromMaNCC(pn.getMaNCC());
                tableModel.addRow(new Object[]{
                    pn.getMaPN(),
                    pn.getMaNV(),
                    tenNCC,
                    pn.getTongTien(),
                    pn.getNgayNhap()
                });
            }
        });

        btnThem.addActionListener(e -> {
            if (modelCart.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm!");
                return;
            }

            int tongTien = 0;
            for (int i = 0; i < modelCart.getRowCount(); i++) {
                try {
                    tongTien += Integer.parseInt(modelCart.getValueAt(i, 3).toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Thành tiền không hợp lệ ở dòng " + (i + 1));
                    return;
                }
            }

            try {
                int maPN = Integer.parseInt(txtMaPhieu.getText().replace("PN", "").trim());
                int maNV = Integer.parseInt(txtMaNV.getText().trim());
                String selectedNCC = cbTenNCC.getSelectedItem().toString();
                int maNCC = getMaNCCFromTenNCC(selectedNCC);

                if (maNCC == -1) {
                    throw new IllegalArgumentException("Nhà cung cấp không hợp lệ!");
                }

                PhieuNhapDTO pn = new PhieuNhapDTO();
                pn.setMaPN(maPN);
                pn.setMaNV(maNV);
                pn.setMaNCC(maNCC);
                pn.setTongTien(tongTien);
                pn.setNgayNhap(LocalDate.now().toString());

                if (imBUS.addPhieuNhap(pn)) {
                    JOptionPane.showMessageDialog(this, "Lưu phiếu nhập thành công!");
                    modelCart.setRowCount(0);
                    tableModel.addRow(new Object[]{
                        pn.getMaPN(),
                        pn.getMaNV(),
                        selectedNCC,
                        pn.getTongTien(),
                        pn.getNgayNhap()
                    });
                    txtMaPhieu.setText(imBUS.getMaPhieuNhapTiepTheo());
                    updateTongTien();
                } else {
                    JOptionPane.showMessageDialog(this, "Lưu phiếu nhập thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dữ liệu số không hợp lệ: " + ex.getMessage());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
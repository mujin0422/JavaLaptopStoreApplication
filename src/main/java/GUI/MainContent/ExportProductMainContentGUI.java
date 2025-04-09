package GUI.MainContent;

import BUS.ChiTietPhieuXuatBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.PhieuXuatDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class ExportProductMainContentGUI extends JPanel{
    private UIButton btnAdd, btnView, btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnAddToPX, btnSavePX;
    private UITextField txtSearch, txtSoLuong, txtMaPX, txtMaNV, txtTongTien ,txtSearchSach;
    private JComboBox<String> cbMaKH;
    private UITable tblContent, tblForProduct , tblForForm;
    private JPanel pnlHeader, pnlContent, pnlForm, pnlProduct;
    private DefaultTableModel tableModel, tableModelForProduct, tableModelForForm;
    private PhieuXuatBUS phieuXuatBUS;
    private KhachHangBUS khachHangBUS;
    private SanPhamBUS sanPhamBUS;
    private NhanVienBUS nhanVienBUS;
    private ChiTietPhieuXuatBUS chiTietPhieuXuatBUS;

    public ExportProductMainContentGUI(TaiKhoanDTO taiKhoan) {
        phieuXuatBUS = new PhieuXuatBUS();
        nhanVienBUS = new NhanVienBUS();
        khachHangBUS = new KhachHangBUS();
        chiTietPhieuXuatBUS = new ChiTietPhieuXuatBUS();
        sanPhamBUS = new SanPhamBUS();
        
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

       //===============================( Panel Header )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));
        
        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        btnView = new UIButton("menuButton", "XEM", 90, 40, "/Icon/chitiet_icon.png");
        pnlButton.add(btnAdd);
        pnlButton.add(btnView);
        
        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(190,30);
        pnlSearchFilter.add(txtSearch);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //==============================( End Panel Header )============================//
        
        
        //==================================( PANEL FORM )==============================//
        pnlForm = new JPanel(new BorderLayout());
        pnlForm.setBackground(UIConstants.MAIN_BACKGROUND);
            //NORTH
        JPanel pnlFormNorth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        pnlFormNorth.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlFormNorth.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlFormNorth.setPreferredSize(new Dimension(0, 100));

        pnlFormNorth.add(new UILabel("Mã phiếu xuất:", 150, 25));
        txtMaPX = new UITextField(340,25);
        pnlFormNorth.add(txtMaPX);
        pnlFormNorth.add(new UILabel("Nhân viên :", 150, 25));
        txtMaNV = new UITextField(340, 25);
        
        NhanVienDTO nhanVien = nhanVienBUS.getCurrentStaffByUserName(taiKhoan.getTenDangNhap());
        if (nhanVien != null) {
            txtMaNV.setText(nhanVien.getTenNV()); 
            txtMaNV.setEditable(false); 
        }
        pnlFormNorth.add(txtMaNV);
        
        pnlFormNorth.add(new UILabel("Khách hàng :", 150, 25));
        cbMaKH = new JComboBox<>();
        cbMaKH.setPreferredSize(new Dimension(340, 25));
        cbMaKH.setBackground(UIConstants.WHITE_FONT);
        for(KhachHangDTO kh : khachHangBUS.getAllKhachHang()){
            cbMaKH.addItem(kh.getTenKH());
        }
        pnlFormNorth.add(cbMaKH);
            //CENTER
        String[] columns = {"MÃ", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"};
        tableModelForForm = new DefaultTableModel(columns, 0);
        tblForForm = new UITable(tableModelForForm);
        tblForForm.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tblForForm.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        tblForForm.getTableHeader().setPreferredSize(new Dimension(0,25));
        UIScrollPane scrollPaneForForm = new UIScrollPane(tblForForm);
        scrollPaneForForm.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            //SOUTH
        JPanel pnlFormSouth = new JPanel(new GridLayout(2, 1, 5, 5)); 
        pnlFormSouth.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnl1 = new JPanel(new FlowLayout(FlowLayout.CENTER,25,5)); 
        btnXoaKhoiPhieu = new UIButton("delete", "XÓA KHỎI PHIẾU", 130, 30);
        btnXoaKhoiPhieu.addActionListener(e -> removeFromTableForForm());
        btnSuaSoLuong = new UIButton("edit", "SỬA SỐ LƯỢNG", 130, 30);
        btnSuaSoLuong.addActionListener(e -> editSoLuongInFromTableForForm());
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        pnl1.add(btnSuaSoLuong);
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
        pnl2.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlGroupTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlGroupTongTien.add(new UILabel("Tổng tiền:",80,30));
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        txtTongTien = new UITextField(100, 30);
        txtTongTien.setEditable(false); 
        pnlGroupTongTien.add(txtTongTien);
        btnAddToPX = new UIButton("add", "THÊM", 100, 25);
        //btnAddToPX.addActionListener(e -> addPhieuXuat());
        pnl2.add(pnlGroupTongTien, BorderLayout.WEST);
        pnl2.add(btnAddToPX, BorderLayout.EAST);
        pnlFormSouth.add(pnl1);
        pnlFormSouth.add(pnl2);
        
        pnlForm.add(pnlFormNorth, BorderLayout.NORTH);
        pnlForm.add(scrollPaneForForm, BorderLayout.CENTER);
        pnlForm.add(pnlFormSouth, BorderLayout.SOUTH);
        //================================( End Panel Form )============================//
        
        //=================================( PANEL PRODUCT )============================//
        pnlProduct = new JPanel(new BorderLayout(5,5));
        pnlProduct.setPreferredSize(new Dimension(550, 0));
        pnlProduct.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlProduct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtSearchSach = new UITextField(400 ,30);
        
        String[] columnForProduct = {"MÃ", "TÊN SẢN PHẨM", "GIÁ", "TỒN KHO"};
        tableModelForProduct = new DefaultTableModel(columnForProduct, 0);
        tblForProduct = new UITable(tableModelForProduct);
        tblForProduct.getTableHeader().setBackground(UIConstants.MAIN_BACKGROUND);
        tblForProduct.getTableHeader().setForeground(UIConstants.BLACK_FONT);
        UIScrollPane scrollPaneForProduct = new UIScrollPane(tblForProduct);
        
        JPanel pnlSouthOfproduct = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlSouthOfproduct.setBackground(UIConstants.MAIN_BACKGROUND);
        UILabel lblSoLuong = new UILabel("Số lượng:", 70, 30);
        lblSoLuong.setForeground(Color.BLACK);
        pnlSouthOfproduct.add(lblSoLuong);
        txtSoLuong = new UITextField(40, 30);
        txtSoLuong.setHorizontalAlignment(JTextField.CENTER);
        pnlSouthOfproduct.add(txtSoLuong);
        btnThemVaoPhieu = new UIButton("add","THÊM VÀO PHIẾU", 140, 30);
        btnThemVaoPhieu.addActionListener(e -> addToTableForForm());
        pnlSouthOfproduct.add(btnThemVaoPhieu);
        
        pnlProduct.add(txtSearchSach, BorderLayout.NORTH);
        pnlProduct.add(scrollPaneForProduct, BorderLayout.CENTER);
        pnlProduct.add(pnlSouthOfproduct, BorderLayout.SOUTH);
        //===============================( End Panel Product )==========================//
        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel(new BorderLayout(5,5));
        pnlContent.setPreferredSize(new Dimension(0, 200));
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] columnNames = {"MÃ PHIẾU XUẤT", "NHÂN VIÊN", "KHÁCH HÀNG", "TỔNG TIỀN", "NGÀY GHI PHIẾU"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlForm, BorderLayout.CENTER);
        this.add(pnlProduct, BorderLayout.EAST);
        this.add(pnlContent, BorderLayout.SOUTH);
        loadTableData();
        addSearchFunctionality();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        for(PhieuXuatDTO px : phieuXuatBUS.getAllPhieuXuat()){
            tableModel.addRow(new Object[]{
                px.getMaPX(),
                nhanVienBUS.getTenNvByMaNv(px.getMaNV()),
                khachHangBUS.getTenKhByMaKh(px.getMaKH()),
                px.getTongTien(),
                px.getNgayXuat()
            });
        }     
        tableModelForProduct.setRowCount(0);
        for(SanPhamDTO sp : sanPhamBUS.getAllSanPham()){
            tableModelForProduct.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon()
            });
        }
    }
    
    private void addToTableForForm() {
        String soLuongText = txtSoLuong.getText().trim();
        int selectedRow = tblForProduct.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để thêm vào phiếu", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (soLuongText.isEmpty() || !soLuongText.matches("\\d+") || Integer.parseInt(soLuongText) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (Integer.parseInt(soLuongText) > sanPhamBUS.getSoLuongTonSanPham(Integer.parseInt(tblForProduct.getValueAt(selectedRow, 0).toString()))){
            JOptionPane.showMessageDialog(this, "Số lượng sách bán ra không đuọc lớn hơn số lượng sách trong kho", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int soLuong = Integer.parseInt(soLuongText);
        String maSach = tblForProduct.getValueAt(selectedRow, 0).toString();
        String tenSach = tblForProduct.getValueAt(selectedRow, 1).toString();
        int giaBan = Integer.parseInt(tblForProduct.getValueAt(selectedRow, 2).toString());
        int thanhTien = giaBan * soLuong;
        DefaultTableModel model = (DefaultTableModel) tblForForm.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String maSachTrongBang = model.getValueAt(i, 0).toString();
            if (maSach.equals(maSachTrongBang)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong phiếu", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        model.addRow(new Object[]{maSach, tenSach, soLuong, thanhTien});
        
        calcTongTien();
        txtSoLuong.setText("");
    }
    
    private void removeFromTableForForm(){
        int selectedRow = tblForForm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong phiếu để hủy bỏ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModelForForm.removeRow(selectedRow);
    }
    
    private void editSoLuongInFromTableForForm(){
        int selectedRow = tblForForm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong phiếu để sửa số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Window window = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog((Frame) window, "Sửa Số Lượng", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        UITextField txtSoLuong = new UITextField(50, 30);
        dialog.add(new UILabel("Số lượng mới: ", 150, 30));
        dialog.add(txtSoLuong);
        UIButton btnSave = new UIButton("add","Lưu", 100, 30);
        dialog.add(btnSave);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String soLuongText = txtSoLuong.getText().trim();
                if (soLuongText.isEmpty() || !soLuongText.matches("\\d+") || Integer.parseInt(soLuongText) <= 0) {
                    JOptionPane.showMessageDialog(dialog, "Số lượng không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // kiểm tra số lượng lớn hơn kho
                int soLuong = Integer.parseInt(soLuongText);
                tblForForm.setValueAt(soLuong, selectedRow, 2); 
                dialog.dispose(); 
            }
        });
        dialog.setLocationRelativeTo(this); 
        dialog.setVisible(true);
    }
    
    private void calcTongTien() {
        int tongTien = 0;
        for (int i = 0; i < tblForForm.getRowCount(); i++) {
            int thanhTien = (int) tblForForm.getValueAt(i, 3); 
            tongTien += thanhTien;
        }
        txtTongTien.setText(String.valueOf(tongTien));
    }
    
    public Date getCurrentDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String currentDateStr = sdf.format(new Date());  
            return sdf.parse(currentDateStr); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;  
        }
    }
    
    private void resetFormInput(){
        txtMaPX.setText("");
        tableModelForForm.setRowCount(0);
    }
    
    private boolean checkFormInput(){
        try {
            String maPxStr = txtMaPX.getText().trim();
            if (maPxStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã phiếu xuất không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maPx = Integer.parseInt(maPxStr);
            if (maPx <= 0) {
                JOptionPane.showMessageDialog(this, "Mã phiếu xuất phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (tblForForm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu xuất!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
//    private void addPhieuXuat(){
//        if(!checkFormInput()) return;
//        int maPX = Integer.parseInt(txtMaPX.getText().trim());
//        int maNV = nhanVienBUS.getMaNvByTenNv(txtMaNV.getText().trim());
//        int maNCC = khachHangBUS.getMaKhByTenKh(cbMaKH.getSelectedItem().toString());
//        int tongTien = Integer.parseInt(txtTongTien.getText().trim());
//        Date ngayXuat = getCurrentDate();
//        if (phieuXuatBUS.existsPhieuXuat(maPX)) {
//            JOptionPane.showMessageDialog(this, "Mã phiếu xuất đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        PhieuXuatDTO phieuXuat = new PhieuXuatDTO(maPX, maNV, maNCC, tongTien, ngayXuat);
//        if (phieuXuatBUS.addPhieuXuat(phieuXuat)) {
//            tableModelForForm = (DefaultTableModel) tblForForm.getModel();
//            for (int i = 0; i < tableModelForForm.getRowCount(); i++) {
//                int maSp = Integer.parseInt(tableModelForForm.getValueAt(i, 0).toString());
//                int soluong = Integer.parseInt(tableModelForForm.getValueAt(i, 2).toString());
//                int giaBan = Integer.parseInt(tableModelForForm.getValueAt(i, 3).toString());
//                ChiTietPhieuXuatDTO chiTietPhieuXuat = new ChiTietPhieuXuatDTO(maPX, maSp, soluong, giaBan);
//                boolean isChiTietAdded = chiTietPhieuXuatBUS.addChiTietPhieuXuat(chiTietPhieuXuat);
//                if (!isChiTietAdded) {
//                    JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu xuất thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                    //cap nhat so lai so luong ton
//                int soLuongHienTai = sanPhamBUS.getSoLuongTonSanPham(maSp); 
//                int soLuongMoi = soLuongHienTai - soluong;
//                sanPhamBUS.updateSoLuongTonSanPham(maSp, soLuongMoi);
//            }
//        }
//        JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//        loadTableData();
//        resetFormInput();
//    }
    
    private void addSearchFunctionality() {
        txtSearchSach.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchProduct(); }
            public void removeUpdate(DocumentEvent e) { searchProduct(); }
            public void changedUpdate(DocumentEvent e) { searchProduct(); }
        });
    }
    
    private void searchProduct() {
        String keyword = txtSearchSach.getText().trim().toLowerCase();
        tableModelForProduct.setRowCount(0); 
        ArrayList<SanPhamDTO> list= sanPhamBUS.searchSanPham(keyword);
        for (SanPhamDTO sp : list) {
            tableModelForProduct.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                sp.getGiaSP(),
                sp.getSoLuongTon()
            });
        }
    }
}

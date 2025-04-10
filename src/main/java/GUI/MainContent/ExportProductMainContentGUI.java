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
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;


public class ExportProductMainContentGUI extends JPanel implements ReloadablePanel{
    private UIButton btnAdd, btnView, btnThemVaoPhieu, btnXoaKhoiPhieu, btnNhapSerial, btnAddToPX, btnSavePX;
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
        btnAdd.addActionListener(e -> resetFormInput());
        btnView = new UIButton("menuButton", "XEM", 90, 40, "/Icon/chitiet_icon.png");
        btnView.addActionListener(e -> viewChiTietPhieuXuat());
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
        String[] columns = {"MÃ", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN", "SERIAL"};
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
        btnNhapSerial = new UIButton("add", "NHẬP SERIAL", 130, 30);
        btnNhapSerial.addActionListener(e -> addSerial());
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        pnl1.add(btnNhapSerial);
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
        btnAddToPX.addActionListener(e -> addPhieuXuat());
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
        txtSoLuong.setText("1");
        txtSoLuong.setEditable(false);
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
    
    public void loadTableData(){
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
    
    private void viewChiTietPhieuXuat() {
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu xuất để xem chi tiết.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maPX = Integer.parseInt(tblContent.getValueAt(selectedRow, 0).toString());
        PhieuXuatDTO px = phieuXuatBUS.getById(maPX);

        Window window = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog((Frame) window, "Chi tiết phiếu xuất", true);
        dialog.setLayout(new BorderLayout());

        JPanel panelThongTin = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelThongTin.setPreferredSize(new Dimension(600, 125));
        panelThongTin.add(new UILabel("PHIẾU XUẤT " + px.getMaPX(), 550, 25));
        panelThongTin.add(new UILabel("NHÂN VIÊN XUẤT HÀNG: " + nhanVienBUS.getTenNvByMaNv(px.getMaNV()), 550, 25));
        panelThongTin.add(new UILabel("KHÁCH HÀNG: " + khachHangBUS.getTenKhByMaKh(px.getMaKH()), 550, 25));
        panelThongTin.add(new UILabel("NGÀY GHI PHIẾU: " + px.getNgayXuat().toString(), 550, 25));
        panelThongTin.add(new UILabel("TỔNG TIỀN: " + px.getTongTien(), 550, 25));

        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setLayout(new BoxLayout(panelChiTiet, BoxLayout.Y_AXIS));
        panelChiTiet.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        Font monoFont = new Font("Monospaced", Font.PLAIN, 14);
        UILabel lblTitle = new UILabel("CHI TIẾT PHIẾU XUẤT:", 550, 25);
        panelChiTiet.add(lblTitle);

        UILabel lblHeader = new UILabel(String.format("%-35s %-20s %-10s %-15s", "SẢN PHẨM", "SERIAL" ,"SỐ LƯỢNG", "THÀNH TIỀN"), 600, 25);
        lblHeader.setFont(monoFont);
        panelChiTiet.add(lblHeader);

        for (ChiTietPhieuXuatDTO ct : chiTietPhieuXuatBUS.getAllChiTietPhieuXuatByMaPx(maPX)) {
            UILabel lblRow = new UILabel(String.format("%-35s %-20s %-10s %-15s", sanPhamBUS.getTenSanPhamByMaSanPham(ct.getMaSP()),ct.getSerialSP(),ct.getSoLuongSP(),ct.getGiaBan()), 600, 25);
            lblRow.setFont(monoFont);
            panelChiTiet.add(lblRow);
        }

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        UIButton btnClose = new UIButton("add", "Đóng", 100, 30);
        btnClose.addActionListener(e -> dialog.dispose());
        panelButton.add(btnClose);

        dialog.add(panelThongTin, BorderLayout.NORTH);
        dialog.add(panelChiTiet, BorderLayout.CENTER);
        dialog.add(panelButton, BorderLayout.SOUTH);

        dialog.pack();
        int preferredHeight = dialog.getHeight(); 
        dialog.setSize(800, preferredHeight);   
        dialog.setLocationRelativeTo(null);   
        dialog.setVisible(true);
    }
    
    private void addToTableForForm() {
        String soLuongText = txtSoLuong.getText().trim();
        int selectedRow = tblForProduct.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để thêm vào phiếu", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (Integer.parseInt(soLuongText) > sanPhamBUS.getSoLuongTonSanPham(Integer.parseInt(tblForProduct.getValueAt(selectedRow, 0).toString()))){
            JOptionPane.showMessageDialog(this, "San phẩm đã hết", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int soLuong = Integer.parseInt(soLuongText);
        String maSach = tblForProduct.getValueAt(selectedRow, 0).toString();
        String tenSach = tblForProduct.getValueAt(selectedRow, 1).toString();
        int giaBan = Integer.parseInt(tblForProduct.getValueAt(selectedRow, 2).toString());
        
        DefaultTableModel model = (DefaultTableModel) tblForForm.getModel();
        model.addRow(new Object[]{maSach, tenSach, soLuong, giaBan});
        
        calcTongTien();
    }
    
    private void removeFromTableForForm(){
        int selectedRow = tblForForm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong phiếu để hủy bỏ", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        tableModelForForm.removeRow(selectedRow);
    }
    
    private void addSerial() {
        int selectedRow = tblForForm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để nhập Serial!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String serial = JOptionPane.showInputDialog(this, "Nhập Serial:");
        if (serial != null && !serial.trim().isEmpty()) {
            serial = serial.trim().toUpperCase();
            if (!serial.matches("^[A-Z0-9]{7,20}$")) {
                JOptionPane.showMessageDialog(this, "Serial phải từ 7 đến 20 ký tự, chỉ bao gồm chữ in hoa và số, không chứa khoảng trắng hay ký tự đặc biệt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for (int i = 0; i < tblForForm.getRowCount(); i++) {
                if (i != selectedRow) {
                    Object existingSerial = tblForForm.getValueAt(i, 4);
                    if (serial.equals(existingSerial)) {
                        JOptionPane.showMessageDialog(this, "Serial này đã được sử dụng cho sản phẩm khác trong phiếu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            tableModelForForm.setValueAt(serial, selectedRow, 4);
        }
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
            int serialColumnIndex = 4; 
            for (int i = 0; i < tblForForm.getRowCount(); i++) {
                Object serialObj = tblForForm.getValueAt(i, serialColumnIndex);
                if (serialObj == null || serialObj.toString().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Dòng " + (i + 1) + " chưa nhập serial!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void addPhieuXuat(){
        if(!checkFormInput()) return;
        int maPX = Integer.parseInt(txtMaPX.getText().trim());
        int maNV = nhanVienBUS.getMaNvByTenNv(txtMaNV.getText().trim());
        int maNCC = khachHangBUS.getMaKhByTenKh(cbMaKH.getSelectedItem().toString());
        int tongTien = Integer.parseInt(txtTongTien.getText().trim());
        Date ngayXuat = getCurrentDate();
        if (phieuXuatBUS.existsPhieuXuat(maPX)) {
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PhieuXuatDTO phieuXuat = new PhieuXuatDTO(maPX, maNV, maNCC, tongTien, ngayXuat);
        if (phieuXuatBUS.addPhieuXuat(phieuXuat)) {
            tableModelForForm = (DefaultTableModel) tblForForm.getModel();
            for (int i = 0; i < tableModelForForm.getRowCount(); i++) {
                int maSp = Integer.parseInt(tableModelForForm.getValueAt(i, 0).toString());
                int soluong = Integer.parseInt(tableModelForForm.getValueAt(i, 2).toString());
                int giaBan = Integer.parseInt(tableModelForForm.getValueAt(i, 3).toString());
                String serialSP = tableModelForForm.getValueAt(i, 4).toString();
                ChiTietPhieuXuatDTO chiTietPhieuXuat = new ChiTietPhieuXuatDTO(maPX, maSp, giaBan, soluong, serialSP);
                boolean isChiTietAdded = chiTietPhieuXuatBUS.addChiTietPhieuXuat(chiTietPhieuXuat);
                if (!isChiTietAdded) {
                    JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu xuất thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    //cap nhat so lai so luong ton
                int soLuongHienTai = sanPhamBUS.getSoLuongTonSanPham(maSp); 
                int soLuongMoi = soLuongHienTai - soluong;
                sanPhamBUS.updateSoLuongTonSanPham(maSp, soLuongMoi);
            }
        }
        JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
        resetFormInput();
    }
    
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

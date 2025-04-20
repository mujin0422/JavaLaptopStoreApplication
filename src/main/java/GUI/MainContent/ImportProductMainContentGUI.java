package GUI.MainContent;

import BUS.ChiTietPhieuNhapBUS;
import BUS.ChiTietSanPhamBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.NhaCungCapDTO;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import DTO.SanPhamDTO;
import DTO.TaiKhoanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
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

public class ImportProductMainContentGUI extends JPanel implements ReloadablePanel{
    private UIButton btnAdd ,btnView, btnPdf, btnThemVaoPhieu, btnXoaKhoiPhieu, btnSuaSoLuong, btnAddToPN, btnExportPDF;
    private UITextField txtSearch, txtSoLuong, txtMaPN, txtMaNV, txtTongTien, txtSearchSach;
    private JComboBox<String> cbMaNCC;
    private UITable tblContent, tblForProduct , tblForForm;
    private JPanel pnlHeader, pnlContent, pnlForm, pnlProduct;
    private DefaultTableModel tableModel, tableModelForProduct, tableModelForForm;
    private PhieuNhapBUS phieuNhapBUS;
    private NhaCungCapBUS nhaCungCapBUS;
    private SanPhamBUS sanPhamBUS;
    private NhanVienBUS nhanVienBUS;
    private ChiTietPhieuNhapBUS chiTietPhieuNhapBUS;
    private ChiTietSanPhamBUS chiTietSanPhamBUS;
    private TaiKhoanBUS taiKhoanBUS;

    public ImportProductMainContentGUI(TaiKhoanDTO taiKhoan) {
        phieuNhapBUS = new PhieuNhapBUS();
        nhanVienBUS = new NhanVienBUS();
        nhaCungCapBUS = new NhaCungCapBUS();
        chiTietPhieuNhapBUS = new ChiTietPhieuNhapBUS();
        chiTietSanPhamBUS = new ChiTietSanPhamBUS();
        sanPhamBUS = new SanPhamBUS();
        taiKhoanBUS = new TaiKhoanBUS();
        
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
        btnView.addActionListener(e -> viewChiTietPhieuNhap());
        btnPdf = new UIButton("menuButton", "PDF", 90, 40, "/Icon/pdf_icon.png");
        btnPdf.addActionListener(e -> exportPdf());
        pnlButton.add(btnAdd);
        pnlButton.add(btnView);
        pnlButton.add(btnPdf);
        
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

        pnlFormNorth.add(new UILabel("Mã phiếu nhập:", 120, 25));
        txtMaPN = new UITextField(370,25);
        pnlFormNorth.add(txtMaPN);
        
        pnlFormNorth.add(new UILabel("Nhân viên :", 120, 25));
        txtMaNV = new UITextField(370, 25);
        NhanVienDTO nhanVien = nhanVienBUS.getCurrentStaffByUserName(taiKhoan.getTenDangNhap());
        if (nhanVien != null) {
            txtMaNV.setText(nhanVien.getTenNV()); 
            txtMaNV.setEditable(false); 
        }
        pnlFormNorth.add(txtMaNV);
        
        pnlFormNorth.add(new UILabel("Nhà cung cấp :", 120, 25));
        cbMaNCC = new JComboBox<>();
        cbMaNCC.setPreferredSize(new Dimension(370, 25));
        cbMaNCC.setBackground(UIConstants.WHITE_FONT);
        for(NhaCungCapDTO ncc : nhaCungCapBUS.getAllNhaCungCap()){
            cbMaNCC.addItem(ncc.getTenNCC());
        }
        pnlFormNorth.add(cbMaNCC);
            //CENTER
        String[] columnsForm = {"MÃ", "TÊN SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"};
        tableModelForForm = new DefaultTableModel(columnsForm, 0);
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
        pnlGroupTongTien.add(new UILabel("Tổng thành tiền:",120,30));
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        txtTongTien = new UITextField(200, 30);
        txtTongTien.setEditable(false); 
        pnlGroupTongTien.add(txtTongTien);
        btnAddToPN = new UIButton("add", "THÊM", 100, 25);
        btnAddToPN.addActionListener(e -> addPhieuNhap());
        pnl2.add(pnlGroupTongTien, BorderLayout.WEST);
        pnl2.add(btnAddToPN, BorderLayout.EAST);
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

        String[] columnNames = {"MÃ PHIẾU NHẬP", "NHÂN VIÊN", "NHÀ CUNG CẤP", "TỔNG TIỀN", "NGÀY GHI PHIẾU"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        applyPermissions(taiKhoan.getTenDangNhap(), 7);
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlForm, BorderLayout.CENTER);
        this.add(pnlProduct, BorderLayout.EAST);
        this.add(pnlContent, BorderLayout.SOUTH);
        loadTableData();
        addSearchFunctionality();
    }
    
    private void applyPermissions(String username, int maCN) {
        btnAdd.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        btnAddToPN.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        //btnEdit.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        //btnDelete.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
    }
    
    public void loadTableData(){
        tableModel.setRowCount(0);
        for(PhieuNhapDTO pn : phieuNhapBUS.getAllPhieuNhap()){
            tableModel.addRow(new Object[]{
                pn.getMaPN(),
                nhanVienBUS.getTenNvByMaNv(pn.getMaNV()),
                nhaCungCapBUS.getTenNccByMaNcc(pn.getMaNCC()),
                pn.getTongTien(),
                pn.getNgayNhap()
            });
        }   
        tableModelForProduct.setRowCount(0);
        for(SanPhamDTO sp : sanPhamBUS.getAllSanPham()){
            long giaNhap = (long) sp.getGiaSP() * 85 / 100; 
            tableModelForProduct.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                (int)giaNhap,
                sp.getSoLuongTon()
            });
        }
    }
    
    private void viewChiTietPhieuNhap() {
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu nhập để xem chi tiết.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int maPN = Integer.parseInt(tblContent.getValueAt(selectedRow, 0).toString());
        PhieuNhapDTO pn = phieuNhapBUS.getById(maPN);

        Window window = SwingUtilities.getWindowAncestor(this);
        JDialog dialog = new JDialog((Frame) window, "Chi tiết phiếu nhập", true);
        dialog.setLayout(new BorderLayout());

        JPanel panelThongTin = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelThongTin.setPreferredSize(new Dimension(600, 125));
        panelThongTin.add(new UILabel("PHIẾU NHẬP " + pn.getMaPN(), 550, 25));
        panelThongTin.add(new UILabel("NHÂN VIÊN NHẬP HÀNG: " + nhanVienBUS.getTenNvByMaNv(pn.getMaNV()), 550, 25));
        panelThongTin.add(new UILabel("NHÀ CUNG CẤP: " + nhaCungCapBUS.getTenNccByMaNcc(pn.getMaNCC()), 550, 25));
        panelThongTin.add(new UILabel("NGÀY GHI PHIẾU: " + pn.getNgayNhap().toString(), 550, 25));
        panelThongTin.add(new UILabel("TỔNG TIỀN: " + pn.getTongTien(), 550, 25));

        JPanel panelChiTiet = new JPanel();
        panelChiTiet.setLayout(new BoxLayout(panelChiTiet, BoxLayout.Y_AXIS));
        panelChiTiet.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        UILabel lblTitle = new UILabel("CHI TIẾT PHIẾU NHẬP:", 550, 25);
        panelChiTiet.add(lblTitle);

        UILabel lblHeader = new UILabel(String.format("%-40s %-10s %-15s", "SẢN PHẨM", "SỐ LƯỢNG", "THÀNH TIỀN"), 600, 25);
        lblHeader.setFont(UIConstants.monoFont);
        panelChiTiet.add(lblHeader);

        for (ChiTietPhieuNhapDTO ct : chiTietPhieuNhapBUS.getAllChiTietPhieuNhapByMaPn(maPN)) {
            UILabel lblRow = new UILabel(String.format("%-40s %-10s %-15s", sanPhamBUS.getTenSanPhamByMaSanPham(ct.getMaSP()), ct.getSoLuongSP(),ct.getGiaNhap()), 600, 25);
            lblRow.setFont(UIConstants.monoFont);
            panelChiTiet.add(lblRow);
        }

        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        UIButton btnClose = new UIButton("add", "ĐÓNG", 100, 30);
        btnClose.addActionListener(e -> dialog.dispose());
        panelButton.add(btnClose);

        btnExportPDF = new UIButton("add", "XUẤT PDF", 100, 30);
        btnExportPDF.addActionListener(e -> exportToPDF(maPN));
        panelButton.add(btnExportPDF);
        
        dialog.add(panelThongTin, BorderLayout.NORTH);
        dialog.add(panelChiTiet, BorderLayout.CENTER);
        dialog.add(panelButton, BorderLayout.SOUTH);

        dialog.pack();
        int preferredHeight = dialog.getHeight(); 
        dialog.setSize(650, preferredHeight);   
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
        if (soLuongText.isEmpty() || !soLuongText.matches("\\d+") || Integer.parseInt(soLuongText) <= 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ, phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        calcTongTien();
    }
    
    private void editSoLuongInFromTableForForm(){
        int selectedRow = tblForForm.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm trong phiếu để sửa số lượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        SanPhamDTO sp = sanPhamBUS.getSanPhamById(Integer.parseInt(tblForForm.getValueAt(selectedRow, 0).toString()));
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
                int soLuong = Integer.parseInt(soLuongText);
                int thanhTien = soLuong * sp.getGiaSP();
                tblForForm.setValueAt(soLuong, selectedRow, 2); 
                tblForForm.setValueAt(thanhTien, selectedRow, 3); 
                dialog.dispose();
                
            }
        });
        dialog.setLocationRelativeTo(this); 
        dialog.setVisible(true);
        calcTongTien();
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
        txtMaPN.setText("");
        tableModelForForm.setRowCount(0);
    }
    private boolean checkFormInput(){
        try {
            String maPnStr = txtMaPN.getText().trim();
            if (maPnStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maPn= Integer.parseInt(maPnStr);
            if (maPn <= 0) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            if (tblForForm.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Chưa có sản phẩm nào trong phiếu nhập!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private void addPhieuNhap() {
        if (!checkFormInput()) return;
        try {
            int maPN = Integer.parseInt(txtMaPN.getText().trim());
            int maNV = nhanVienBUS.getMaNvByTenNv(txtMaNV.getText().trim());
            int maNCC = nhaCungCapBUS.getMaNccByTenNcc(cbMaNCC.getSelectedItem().toString());
            int tongTien = Integer.parseInt(txtTongTien.getText().trim());
            Date ngayNhap = getCurrentDate();
            if (phieuNhapBUS.existsPhieuNhap(maPN)) {
                JOptionPane.showMessageDialog(this, "Mã phiếu nhập đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PhieuNhapDTO phieuNhap = new PhieuNhapDTO(maPN, maNV, maNCC, tongTien, ngayNhap);
            if (!phieuNhapBUS.addPhieuNhap(phieuNhap)) {
                JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            tableModelForForm = (DefaultTableModel) tblForForm.getModel();
            for (int i = 0; i < tableModelForForm.getRowCount(); i++) {
                int maSP = Integer.parseInt(tableModelForForm.getValueAt(i, 0).toString());
                int soLuong = Integer.parseInt(tableModelForForm.getValueAt(i, 2).toString());
                int giaNhap = Integer.parseInt(tableModelForForm.getValueAt(i, 3).toString());

                // Thêm chi tiết phiếu nhập
                ChiTietPhieuNhapDTO chiTietPhieuNhap = new ChiTietPhieuNhapDTO(maPN, maSP, soLuong, giaNhap);
                if (!chiTietPhieuNhapBUS.addChiTietPhieuNhap(chiTietPhieuNhap)) {
                    JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu nhập thất bại ở dòng " + (i + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Thêm chi tiết sản phẩm theo số lượng
                for (int j = 0; j < soLuong; j++) {
                    String serialSP = chiTietSanPhamBUS.generateUniqueSerial(); 
                    ChiTietSanPhamDTO chiTietSanPham = new ChiTietSanPhamDTO(serialSP, maSP, maPN);
                    if (!chiTietSanPhamBUS.addChiTietSanPham(chiTietSanPham)) {
                        JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm thất bại ở dòng " + (i + 1), "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                int soLuongHienTai = sanPhamBUS.getSoLuongTonSanPham(maSP);
                sanPhamBUS.updateSoLuongTonSanPham(maSP, soLuongHienTai + soLuong);
            }
            JOptionPane.showMessageDialog(this, "Thêm phiếu nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            resetFormInput();
            loadTableData();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
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
    
    private void exportToPDF(int maPN) {
        try {
            PhieuNhapDTO pn = phieuNhapBUS.getById(maPN);
            Document document = new Document();
            
            File dir = new File("./phieu/phieunhap");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Lưu file PDF
            String filePath = "./phieu/phieunhap/PhieuNhap" + maPN + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            // Tiêu đề
            BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 16);
            Paragraph title = new Paragraph("CHI TIẾT PHIẾU NHẬP", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Font infoFont = new Font(baseFont, 12);
            document.add(new Paragraph("Mã phiếu nhập: " + pn.getMaPN(), infoFont));
            document.add(new Paragraph("Nhân viên nhập hàng: " + nhanVienBUS.getTenNvByMaNv(pn.getMaNV()), infoFont));
            document.add(new Paragraph("Nhà cung cấp: " + nhaCungCapBUS.getTenNccByMaNcc(pn.getMaNCC()), infoFont));
            document.add(new Paragraph("Ngày ghi phiếu: " + pn.getNgayNhap().toString(), infoFont));
            document.add(new Paragraph("Tổng tiền: " + pn.getTongTien(), infoFont));
            // Thêm một khoảng cách
            document.add(new Paragraph("\n"));

            Font tableHeaderFont = new Font(baseFont, 12);
            Font tableDataFont = new Font(baseFont, 12);
            // Tạo bảng chi tiết phiếu nhập
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            // Đặt tiêu đề bảng
            table.addCell(new Phrase("SẢN PHẨM", tableHeaderFont));
            table.addCell(new Phrase("SỐ LƯỢNG", tableHeaderFont));
            table.addCell(new Phrase("THÀNH TIỀN", tableHeaderFont));
            // Dữ liệu chi tiết phiếu nhập
            for (ChiTietPhieuNhapDTO ct : chiTietPhieuNhapBUS.getAllChiTietPhieuNhapByMaPn(maPN)) {
                table.addCell(new Phrase(sanPhamBUS.getTenSanPhamByMaSanPham(ct.getMaSP()), tableDataFont));
                table.addCell(new Phrase(String.valueOf(ct.getSoLuongSP()), tableDataFont));
                table.addCell(new Phrase(String.valueOf(ct.getGiaNhap()), tableDataFont));
            }
            // Thêm bảng vào tài liệu
            document.add(table);
            // Đóng tài liệu
            document.close();
            JOptionPane.showMessageDialog(this, "Xuất PDF thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xuất PDF: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void exportPdf(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu nhập!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maPN = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        exportToPDF(maPN);
    }
}

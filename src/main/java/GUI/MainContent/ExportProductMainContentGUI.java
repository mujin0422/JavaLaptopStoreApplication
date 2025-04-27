package GUI.MainContent;

import BUS.ChiTietPhieuXuatBUS;
import BUS.ChiTietSanPhamBUS;
import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import DTO.ChiTietPhieuXuatDTO;
import DTO.ChiTietSanPhamDTO;
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
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public final class ExportProductMainContentGUI extends JPanel implements ReloadablePanel{
    private UIButton btnAdd, btnView, btnPdf, btnThemVaoPhieu, btnXoaKhoiPhieu, btnAddToPX, btnExportPDF;
    private UITextField txtSearch, txtSoLuong, txtMaPX, txtMaNV, txtTongTien;
    private JComboBox<String> cbMaKH, cbFilterChiTietSanPham;
    private UITable tblContent, tblForProduct , tblForForm;
    private JPanel pnlHeader, pnlContent, pnlForm, pnlProduct;
    private DefaultTableModel tableModel, tableModelForProduct, tableModelForForm;
    private PhieuXuatBUS phieuXuatBUS;
    private KhachHangBUS khachHangBUS;
    private SanPhamBUS sanPhamBUS;
    private NhanVienBUS nhanVienBUS;
    private ChiTietPhieuXuatBUS chiTietPhieuXuatBUS;
    private ChiTietSanPhamBUS chiTietSanPhamBUS;
    private TaiKhoanBUS taiKhoanBUS;

    public ExportProductMainContentGUI(TaiKhoanDTO taiKhoan) {
        phieuXuatBUS = new PhieuXuatBUS();
        nhanVienBUS = new NhanVienBUS();
        khachHangBUS = new KhachHangBUS();
        chiTietPhieuXuatBUS = new ChiTietPhieuXuatBUS();
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
        btnView.addActionListener(e -> viewChiTietPhieuXuat());
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

        pnlFormNorth.add(new UILabel("Mã phiếu xuất:", 120, 25));
        txtMaPX = new UITextField(520,25);
        pnlFormNorth.add(txtMaPX);
        pnlFormNorth.add(new UILabel("Nhân viên :", 120, 25));
        txtMaNV = new UITextField(520, 25);
        
        NhanVienDTO nhanVien = nhanVienBUS.getCurrentStaffByUserName(taiKhoan.getTenDangNhap());
        if (nhanVien != null) {
            txtMaNV.setText(nhanVien.getTenNV()); 
            txtMaNV.setEditable(false); 
        }
        pnlFormNorth.add(txtMaNV);
        
        pnlFormNorth.add(new UILabel("Khách hàng :", 120, 25));
        cbMaKH = new JComboBox<>();
        cbMaKH.setPreferredSize(new Dimension(520, 25));
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
        pnl1.setBackground(UIConstants.MAIN_BACKGROUND);
        pnl1.add(btnXoaKhoiPhieu);
        JPanel pnl2 = new JPanel(new BorderLayout());
        pnl2.setBorder(BorderFactory.createEmptyBorder(0,10,5,10));
        pnl2.setBackground(UIConstants.MAIN_BACKGROUND);
        JPanel pnlGroupTongTien = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlGroupTongTien.add(new UILabel("Tổng thành tiền:",120,30));
        pnlGroupTongTien.setBackground(UIConstants.MAIN_BACKGROUND);
        txtTongTien = new UITextField(150, 30);
        txtTongTien.setEditable(false); 
        pnlGroupTongTien.add(txtTongTien);
        btnAddToPX = new UIButton("add", "XÁC NHẬN", 100, 25);
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
        pnlProduct.setPreferredSize(new Dimension(400, 0));
        pnlProduct.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlProduct.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        cbFilterChiTietSanPham = new JComboBox<>();
        cbFilterChiTietSanPham.setBackground(UIConstants.WHITE_FONT);
        cbFilterChiTietSanPham.setPreferredSize(new Dimension(350,30));
        for (SanPhamDTO sp : sanPhamBUS.getAllSanPham()) {
            cbFilterChiTietSanPham.addItem(sp.getTenSP());   
        }
        cbFilterChiTietSanPham.addActionListener(e -> loadChiTietSanPhamToTable());


        String[] columnForProduct = {"SERIAL"};
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
        
        pnlProduct.add(cbFilterChiTietSanPham, BorderLayout.NORTH);
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
        applyPermissions(taiKhoan.getTenDangNhap(), 8);
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlForm, BorderLayout.CENTER);
        this.add(pnlProduct, BorderLayout.EAST);
        this.add(pnlContent, BorderLayout.SOUTH);
        loadTableData();
    }
    
    private void applyPermissions(String username, int maCN) {
        btnAdd.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        btnAddToPX.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        //btnEdit.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        //btnDelete.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
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
    }
    
    private void loadChiTietSanPhamToTable() {
        tableModelForProduct.setRowCount(0);
        String tenSP = cbFilterChiTietSanPham.getSelectedItem().toString();
        int maSP = sanPhamBUS.getMaSpByTenSp(tenSP); 
        ArrayList<ChiTietSanPhamDTO> list = chiTietSanPhamBUS.getAllWithoutMaPXByMaSP(maSP);
        for (ChiTietSanPhamDTO ctsp : list) {
            tableModelForProduct.addRow(new Object[]{ ctsp.getSerialSP() });
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

        UILabel lblTitle = new UILabel("CHI TIẾT PHIẾU XUẤT:", 550, 25);
        panelChiTiet.add(lblTitle);

        UILabel lblHeader = new UILabel(String.format("%-35s %-20s %-10s %-15s", "SẢN PHẨM", "SERIAL" ,"SỐ LƯỢNG", "THÀNH TIỀN"), 600, 25);
        lblHeader.setFont(UIConstants.monoFont);
        panelChiTiet.add(lblHeader);

        ArrayList<ChiTietSanPhamDTO> dsSerial = chiTietSanPhamBUS.getAllByMaPX(maPX);

        for (ChiTietPhieuXuatDTO ct : chiTietPhieuXuatBUS.getAllChiTietPhieuXuatByMaPx(maPX)) {
            // Lọc ra tất cả serial tương ứng với maSP
            for (ChiTietSanPhamDTO sp : dsSerial) {
                if (sp.getMaSP() == ct.getMaSP()) {
                    UILabel lblRow = new UILabel(String.format("%-35s %-20s %-10s %-15s", sanPhamBUS.getTenSanPhamByMaSanPham(ct.getMaSP()), sp.getSerialSP(),"1",  ct.getGiaBan()), 600, 25);
                    lblRow.setFont(UIConstants.monoFont);
                    panelChiTiet.add(lblRow);
                }
            }
        }
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        UIButton btnClose = new UIButton("add", "ĐÓNG", 100, 30);
        btnClose.addActionListener(e -> dialog.dispose());
        panelButton.add(btnClose);
        btnExportPDF = new UIButton("add", "XUẤT PDF", 100, 30);
        btnExportPDF.addActionListener(e -> exportToPDF(maPX));
        panelButton.add(btnExportPDF);

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
        int soLuong = Integer.parseInt(soLuongText);
        String serialSp = tblForProduct.getValueAt(selectedRow, 0).toString();

        DefaultTableModel model = (DefaultTableModel) tblForForm.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingSerial = model.getValueAt(i, 4).toString(); 
            if (serialSp.equals(existingSerial)) {
                JOptionPane.showMessageDialog(this, "Sản phẩm với serial này đã được thêm vào phiếu", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        int maSp = chiTietSanPhamBUS.getMaSpBySerialSp(serialSp);
        String tenSp = sanPhamBUS.getTenSanPhamByMaSanPham(maSp);
        int giaBan = sanPhamBUS.getGiaSpByMaSp(maSp);
        model.addRow(new Object[]{maSp, tenSp, soLuong, giaBan, serialSp});
        calcTongTien();
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
        } catch (ParseException e) {
            return null;  
        }
    }
    
    private void resetFormInput(){
        String nextMaPX = phieuXuatBUS.getNextExportID();  //Tự động tạo mã
        txtMaPX.setText(nextMaPX);
        txtMaPX.setEditable(false); //Khóa không cho sửa
        tableModelForForm.setRowCount(0);
    }
    
    private boolean checkFormInput(){
        try {
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
    
    private void addPhieuXuat() {
        if (!checkFormInput()) return;

        int maPX = Integer.parseInt(txtMaPX.getText().trim());
        int maNV = nhanVienBUS.getMaNvByTenNv(txtMaNV.getText().trim());
        int maKH = khachHangBUS.getMaKhByTenKh(cbMaKH.getSelectedItem().toString());
        int tongTien = Integer.parseInt(txtTongTien.getText().trim());
        Date ngayXuat = getCurrentDate();

        if (phieuXuatBUS.existsPhieuXuat(maPX)) {
            JOptionPane.showMessageDialog(this, "Mã phiếu xuất đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        PhieuXuatDTO phieuXuat = new PhieuXuatDTO(maPX, maNV, maKH, tongTien, ngayXuat);
        if (!phieuXuatBUS.addPhieuXuat(phieuXuat)) {
            JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tableModelForForm = (DefaultTableModel) tblForForm.getModel();

        Map<Integer, ChiTietPhieuXuatDTO> mapChiTiet = new HashMap<>();
        Map<Integer, List<String>> mapSerial = new HashMap<>();

        for (int i = 0; i < tableModelForForm.getRowCount(); i++) {
            int maSp = Integer.parseInt(tableModelForForm.getValueAt(i, 0).toString());
            int soLuong = Integer.parseInt(tableModelForForm.getValueAt(i, 2).toString());
            int giaBan = Integer.parseInt(tableModelForForm.getValueAt(i, 3).toString());
            String serialSP = tableModelForForm.getValueAt(i, 4).toString();

            mapSerial.computeIfAbsent(maSp, k -> new ArrayList<>()).add(serialSP);
            if (mapChiTiet.containsKey(maSp)) {
                ChiTietPhieuXuatDTO existing = mapChiTiet.get(maSp);
                existing.setSoLuongSP(existing.getSoLuongSP()+ soLuong);
            } else {
                mapChiTiet.put(maSp, new ChiTietPhieuXuatDTO(maPX, maSp, giaBan, soLuong));
            }
        }

        for (Map.Entry<Integer, ChiTietPhieuXuatDTO> entry : mapChiTiet.entrySet()) {
            int maSp = entry.getKey();
            ChiTietPhieuXuatDTO ct = entry.getValue();

            if (!chiTietPhieuXuatBUS.addChiTietPhieuXuat(ct)) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết phiếu xuất thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Cập nhật lại các serialSP tương ứng
            for (String serial : mapSerial.get(maSp)) {
                chiTietSanPhamBUS.updateMaPX(serial, maPX);
            }

            int soLuongHienTai = sanPhamBUS.getSoLuongTonSanPham(maSp);
            int soLuongMoi = soLuongHienTai - ct.getSoLuongSP();
            sanPhamBUS.updateSoLuongTonSanPham(maSp, soLuongMoi);
        }

        JOptionPane.showMessageDialog(this, "Thêm phiếu xuất thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        loadTableData();
        resetFormInput();
    }
    
    private void exportToPDF(int maPX) {
        try {
            PhieuXuatDTO px = phieuXuatBUS.getById(maPX);
            ArrayList<ChiTietPhieuXuatDTO> dsChiTiet = chiTietPhieuXuatBUS.getAllChiTietPhieuXuatByMaPx(maPX);
            ArrayList<ChiTietSanPhamDTO> dsSerial = chiTietSanPhamBUS.getAllByMaPX(maPX);

            File dir = new File("./phieu/phieuxuat");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // Đường dẫn file
            String filePath = "./phieu/phieuxuat/PhieuXuat" + maPX + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            // Font tiếng Việt
            BaseFont baseFont = BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(baseFont, 16);
            Font infoFont = new Font(baseFont, 12);
            Font tableHeaderFont = new Font(baseFont, 12, Font.BOLD);
            Font tableDataFont = new Font(baseFont, 12);
            // Tiêu đề
            Paragraph title = new Paragraph("CHI TIẾT PHIẾU XUẤT", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            // Thông tin phiếu xuất
            document.add(new Paragraph("Mã phiếu xuất: " + px.getMaPX(), infoFont));
            document.add(new Paragraph("Nhân viên xuất hàng: " + nhanVienBUS.getTenNvByMaNv(px.getMaNV()), infoFont));
            document.add(new Paragraph("Khách hàng: " + khachHangBUS.getTenKhByMaKh(px.getMaKH()), infoFont));
            document.add(new Paragraph("Ngày ghi phiếu: " + px.getNgayXuat().toString(), infoFont));
            document.add(new Paragraph("Tổng tiền: " + px.getTongTien(), infoFont));
            document.add(new Paragraph("\n"));
            // Bảng chi tiết sản phẩm
            PdfPTable table = new PdfPTable(4); // 4 cột: Sản phẩm - Serial - Số lượng - Thành tiền
            table.setWidthPercentage(100);
            table.setWidths(new float[] {3, 2, 1, 2});

            table.addCell(new Phrase("SẢN PHẨM", tableHeaderFont));
            table.addCell(new Phrase("SERIAL", tableHeaderFont));
            table.addCell(new Phrase("SỐ LƯỢNG", tableHeaderFont));
            table.addCell(new Phrase("THÀNH TIỀN", tableHeaderFont));

            for (ChiTietPhieuXuatDTO ct : dsChiTiet) {
                for (ChiTietSanPhamDTO sp : dsSerial) {
                    if (sp.getMaSP() == ct.getMaSP()) {
                        table.addCell(new Phrase(sanPhamBUS.getTenSanPhamByMaSanPham(ct.getMaSP()), tableDataFont));
                        table.addCell(new Phrase(sp.getSerialSP(), tableDataFont));
                        table.addCell(new Phrase("1", tableDataFont));
                        table.addCell(new Phrase(String.valueOf(ct.getGiaBan()), tableDataFont));
                    }
                }
            }
            document.add(table);
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
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu xuất!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maPX = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        exportToPDF(maPX);
    }

}

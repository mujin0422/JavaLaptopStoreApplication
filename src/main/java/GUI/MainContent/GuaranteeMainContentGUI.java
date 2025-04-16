package GUI.MainContent;

import BUS.ChiTietSanPhamBUS;
import BUS.KhachHangBUS;
import BUS.PhieuBaoHanhBUS;
import BUS.PhieuXuatBUS;
import BUS.SanPhamBUS;
import DTO.PhieuBaoHanhDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditGuaranteeGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class GuaranteeMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit, btnView;
    private JComboBox<String> cbFilter;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private PhieuBaoHanhBUS phieuBaoHanhBUS;
    private ChiTietSanPhamBUS chiTietSanPhamBUS;
    private SanPhamBUS sanPhamBUS;
    private PhieuXuatBUS phieuXuatBUS;
    private KhachHangBUS khachHangBUS;
    
    public GuaranteeMainContentGUI(TaiKhoanDTO taiKhoan){
        this.sanPhamBUS = new SanPhamBUS();
        this.phieuXuatBUS = new PhieuXuatBUS();
        this.khachHangBUS = new KhachHangBUS();
        this.phieuBaoHanhBUS = new PhieuBaoHanhBUS();
        this.chiTietSanPhamBUS  = new ChiTietSanPhamBUS();
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
        btnAdd.addActionListener(e -> addGuarantee());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteGuarantee());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editGuarantee());
        btnView = new UIButton("menuButton","XEM" ,90, 40, "/Icon/chitiet_icon.png");
        btnView.addActionListener(e -> viewGuarantee());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        pnlButton.add(btnView);
            
        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        cbFilter = new JComboBox<>(new String[] {"Tất cả", "Đã bảo hành", "Đang bảo hành"});
        cbFilter.setPreferredSize(new Dimension(100,30));
        cbFilter.addActionListener(e -> filterGuarantee()); 
        pnlSearchFilter.add(cbFilter);
        
        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] columnNames = {"MÃ PHIẾU", "TÊN KHÁCH HÀNG", "TÊN SẢN PHẨM", "SERIAL", "TRẠNG THÁI"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new UITable(tableModel);
            
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//

        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    public void loadTableData() {
        tableModel.setRowCount(0);
        for (PhieuBaoHanhDTO pbh : phieuBaoHanhBUS.getAllPhieuBaoHanh()) {
            String trangThai = (pbh.getTrangThaiBH() == 0) ? "Đang bảo hành" : "Đã bảo hành";
            tableModel.addRow(new Object[]{
                pbh.getMaPBH(),
                phieuBaoHanhBUS.getTenKhByMaPbh(pbh.getMaPBH()),
                phieuBaoHanhBUS.getTenSpByMaPbh(pbh.getMaPBH()),
                pbh.getSerialSP(),
                trangThai
            });
        }
    }

    private void addGuarantee() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditGuaranteeGUI((JFrame) window, phieuBaoHanhBUS, "Thêm Phiếu Bảo Hành", "add");
        loadTableData();
    }

    private void editGuarantee() {
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phiếu bảo hành chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maPBH = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        PhieuBaoHanhDTO pbh = phieuBaoHanhBUS.getPhieuBaoHanhById(maPBH);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditGuaranteeGUI((JFrame) window, phieuBaoHanhBUS, "Thêm Phiếu Bảo Hành", "save", pbh);
        loadTableData();
    }

    private void deleteGuarantee() {
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maPBH = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if(phieuBaoHanhBUS.deletePhieuBaoHanh(maPBH)){
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewGuarantee() {
       
    }

    private void filterGuarantee() {
        String selected = cbFilter.getSelectedItem().toString();
        tableModel.setRowCount(0);

        for (PhieuBaoHanhDTO pbh : phieuBaoHanhBUS.getAllPhieuBaoHanh()) {
            boolean match = false;

            if ("Tất cả".equals(selected)) {
                match = true;
            } else if ("Đã bảo hành".equals(selected) && pbh.getTrangThaiBH() == 1) {
                match = true;
            } else if ("Đang bảo hành".equals(selected) && pbh.getTrangThaiBH() == 0) {
                match = true;
            }

            if (match) {
                String trangThai = (pbh.getTrangThaiBH() == 0) ? "Đang bảo hành" : "Đã bảo hành";
                tableModel.addRow(new Object[]{
                    pbh.getMaPBH(),
                    phieuBaoHanhBUS.getTenKhByMaPbh(pbh.getMaPBH()),
                    phieuBaoHanhBUS.getTenSpByMaPbh(pbh.getMaPBH()),
                    pbh.getSerialSP(),
                    trangThai
                });
            }
        }
    }


}

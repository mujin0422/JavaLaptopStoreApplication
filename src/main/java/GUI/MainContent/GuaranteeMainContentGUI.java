package GUI.MainContent;

import BUS.ChiTietPhieuXuatBUS;
import BUS.PhieuBaoHanhBUS;
import DTO.TaiKhoanDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class GuaranteeMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit, btnView;
    private UITextField txtSearch;
    private JComboBox<String> cbFilter;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private PhieuBaoHanhBUS phieuBaoHanhBUS;
    private ChiTietPhieuXuatBUS chiTietPhieuXuatBUS;
    
    public GuaranteeMainContentGUI(TaiKhoanDTO taiKhoan){
        this.phieuBaoHanhBUS = new PhieuBaoHanhBUS();
        this.chiTietPhieuXuatBUS = new ChiTietPhieuXuatBUS();
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
        txtSearch = new UITextField(190, 30);
        pnlSearchFilter.add(txtSearch);
        
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
    
    public void loadTableData(){
        
    }

    private void addGuarantee() {
    }

    private void deleteGuarantee() {
        
    }

    private void editGuarantee() {
        
    }

    private void viewGuarantee() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

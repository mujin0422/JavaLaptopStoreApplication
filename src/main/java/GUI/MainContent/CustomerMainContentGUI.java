package GUI.MainContent;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import GUI.MainContentDiaLog.AddAndEditCostumerGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class CustomerMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private KhachHangBUS khachHangBUS;
    
    public CustomerMainContentGUI() {
        this.khachHangBUS = new KhachHangBUS();
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
        btnAdd.addActionListener(e -> addCustomer());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteCustomer());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editCustomer());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
            
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
            
        String[] columnNames = {"MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "SỐ ĐIỆN THOẠI", "EMAIL"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new UITable(tableModel);
            
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//

        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
        addSearchFunctionality();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        for(KhachHangDTO kh : khachHangBUS.getAllKhachHang()){
            tableModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSdt(),
                kh.getEmail()
            });
        }
    }
    
    private void addCustomer(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCostumerGUI((JFrame) window, khachHangBUS, "Thêm khách hàng", "add");
        loadTableData();
    }
    
    private void editCustomer(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khach hang để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maKH = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenKh = tableModel.getValueAt(selectedRow, 1).toString();
        String sdt = tableModel.getValueAt(selectedRow, 2).toString();
        String email = tableModel.getValueAt(selectedRow, 3).toString();
        KhachHangDTO kh = new KhachHangDTO(maKH, tenKh, sdt, email);
        
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCostumerGUI((JFrame) window, khachHangBUS, "Chinh sua khách hàng", "save", kh);
        loadTableData();
    }
    
    private void deleteCustomer(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khach hang để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac chan khong", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maKH = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (khachHangBUS.deleteKhachHang(maKH)) { 
                JOptionPane.showMessageDialog(this, "Xóa khach hang thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa khach hang thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void addSearchFunctionality() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { searchCustomer(); }
            public void removeUpdate(DocumentEvent e) { searchCustomer(); }
            public void changedUpdate(DocumentEvent e) { searchCustomer(); }
        });
    }
    
    private void searchCustomer() {
        String keyword = txtSearch.getText().trim().toLowerCase();
        tableModel.setRowCount(0); 
        ArrayList<KhachHangDTO> listKH = khachHangBUS.searchKhachHang(keyword);
        for (KhachHangDTO kh : listKH) {
            tableModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSdt(),
                kh.getEmail()
            });
        }
    }
}

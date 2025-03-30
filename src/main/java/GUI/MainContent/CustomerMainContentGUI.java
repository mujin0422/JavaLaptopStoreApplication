package GUI.MainContent;

import BUS.KhachHangBUS;
import DTO.KhachHangDTO;
import GUI.MainContentDiaLog.AddAndEditCostumerGUI;
import Utils.UIButton;
import Utils.UIConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class CustomerMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;
    // $$$$
    private DefaultTableModel tableModel;
    private KhachHangBUS khachHangBUS;
    
    public CustomerMainContentGUI() {
        this.khachHangBUS = new KhachHangBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

       //===============================( Panel Header )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addCustomer());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 30, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteCustomer());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 30, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editCustomer());
        btnAdd.setBounds(5, 5, 90, 40);
        btnDelete.setBounds(105, 5, 90, 40);
        btnEdit.setBounds(210, 5, 90, 40);
            // Tạo combobox và ô tìm kiếm
        int panelWidth = this.getPreferredSize().width; 
        cbFilter = new JComboBox<>(new String[]{"Lọc"});
        cbFilter.setBounds(panelWidth - 320, 10, 100, 30);
        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);
            // Thêm tất cả vào pnlHeader
        pnlHeader.add(btnAdd);
        pnlHeader.add(btnDelete);
        pnlHeader.add(btnEdit);
        pnlHeader.add(cbFilter);
        pnlHeader.add(txtSearch);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
            // Tạo bảng dữ liệu
        String[] columnNames = {"MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "SỐ ĐIỆN THOẠI", "EMAIL"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new JTable(tableModel);
        tblContent.setDefaultEditor(Object.class, null);
            // Thiết lập header của bảng
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.setRowHeight(25);
            // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(tblContent);
        scrollPane.getViewport().setBackground(UIConstants.MAIN_BACKGROUND);
            // Thêm JScrollPane vào pnlContent
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//

        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        ArrayList<KhachHangDTO> listKH = khachHangBUS.getAllKhachHang();
        for(KhachHangDTO kh : listKH){
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
}

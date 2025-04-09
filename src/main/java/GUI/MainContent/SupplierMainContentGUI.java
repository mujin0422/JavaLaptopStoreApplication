package GUI.MainContent;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.MainContentDiaLog.AddAndEditSupplierGUI;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SupplierMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITextField txtSearch;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    
    private DefaultTableModel tableModel;
    private NhaCungCapBUS nhaCungCapBus;
    
    public SupplierMainContentGUI(){
        this.nhaCungCapBus = new NhaCungCapBUS();
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
        btnAdd.addActionListener(e -> addSupplier());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteSupplier());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editSupplier());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        
        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        txtSearch = new UITextField(190 ,30);
        pnlSearchFilter.add(txtSearch);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //==============================( End Panel Header )============================//
        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        String[] columnNames = {"MÃ NHÀ CUNG CÁP", "TÊN NHÀ CUNG CẤP", "ĐỊA CHỈ", "SỐ ĐIỆN THOẠI"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        ArrayList<NhaCungCapDTO> listNCC = nhaCungCapBus.getAllNhaCungCap();
        for (NhaCungCapDTO ncc: listNCC){
            tableModel.addRow(new Object[]{
                ncc.getMaNCC(),
                ncc.getTenNCC(),
                ncc.getDiaChi(),
                ncc.getSdt()
            });
        }
    }
    
    private void addSupplier(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditSupplierGUI((JFrame) window, nhaCungCapBus, "Thêm Nhà Cung Cấp", "add");
        loadTableData();
    }
    
    private void editSupplier(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một NCC để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maNCC = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenNCC = tableModel.getValueAt(selectedRow, 1).toString();
        String diaCHi = tableModel.getValueAt(selectedRow, 2).toString();
        String sdt = tableModel.getValueAt(selectedRow, 3).toString();
        NhaCungCapDTO ncc = new NhaCungCapDTO(maNCC, tenNCC, diaCHi, sdt);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditSupplierGUI((JFrame) window, nhaCungCapBus, "Sửa Nhà Cung Cấp", "save", ncc);
        loadTableData();
    }
    
    private void deleteSupplier(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một NCC để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac chan khong", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maNCC = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (nhaCungCapBus.deleteNhaCungCap(maNCC)) { 
                JOptionPane.showMessageDialog(this, "Xóa NCC thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa NCC thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

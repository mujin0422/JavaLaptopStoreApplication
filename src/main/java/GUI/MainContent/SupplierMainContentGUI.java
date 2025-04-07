package GUI.MainContent;

import BUS.NhaCungCapBUS;
import DTO.NhaCungCapDTO;
import GUI.MainContentDiaLog.AddAndEditSupplierGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SupplierMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;
    
    private DefaultTableModel tableModel;
    private NhaCungCapBUS nhaCungCapBus;
    
    public SupplierMainContentGUI(){
        this.nhaCungCapBus = new NhaCungCapBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));
        
        //===============================( Panel Header )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 35, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addNhaCungCap());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 35, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteNhaCungCap());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 35, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editNhaCungCap());
        btnAdd.setBounds(5, 5, 90, 40);
        btnDelete.setBounds(105, 5, 90, 40);
        btnEdit.setBounds(210, 5, 90, 40);
        int panelWidth = this.getPreferredSize().width; 
        cbFilter = new JComboBox<>(new String[]{"Lọc"});
        cbFilter.setBounds(panelWidth - 320, 10, 100, 30);
        txtSearch = new JTextField();
        txtSearch.setBounds(panelWidth - 210, 10, 190, 30);
            
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
        String[] columnNames = {"MÃ NHÀ CUNG CÁP", "TÊN NHÀ CUNG CẤP", "ĐỊA CHỈ", "SỐ ĐIỆN THOẠI"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tblContent = new JTable(tableModel);
        tblContent.setDefaultEditor(Object.class, null);
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.setRowHeight(30);
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
    
    private void addNhaCungCap(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditSupplierGUI((JFrame) window, nhaCungCapBus, "Thêm Nhà Cung Cấp", "add");
        loadTableData();
    }
    
    private void editNhaCungCap(){
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
    
    private void deleteNhaCungCap(){
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

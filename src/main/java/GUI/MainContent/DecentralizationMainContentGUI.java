package GUI.MainContent;

import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditDecentralizationGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class DecentralizationMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    
    private DefaultTableModel tableModel;
    private QuyenBUS quyenBUS;
    private TaiKhoanBUS taiKhoanBUS;

    public DecentralizationMainContentGUI(TaiKhoanDTO taiKhoan) {
        this.quyenBUS = new QuyenBUS();
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel(new BorderLayout());
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        pnlButton.setBackground(UIConstants.MAIN_BACKGROUND);
        btnAdd = new UIButton("menuButton", "THÊM", 90, 40, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addDecentralization());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteDecentralization());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editDecentralization());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);
        applyPermissions(taiKhoan.getTenDangNhap(), 9);
        pnlHeader.add(pnlButton, BorderLayout.WEST);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        String[] columnNames = {"MÃ QUYỀN", "TÊN QUYỀN"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void applyPermissions(String username, int maCN) {
        btnAdd.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        btnEdit.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        btnDelete.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        for(QuyenDTO qn : quyenBUS.getAllQuyen()){
            tableModel.addRow(new Object[]{
                qn.getMaQuyen(),
                qn.getTenQuyen()
            });
        }
    }
    
    private void addDecentralization(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditDecentralizationGUI((JFrame) window, quyenBUS, "Thêm Quyen", "add");
        loadTableData(); 
    }
    
    private void editDecentralization(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một quyền để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maQuyen = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String tenQuyen = tableModel.getValueAt(selectedRow, 1).toString();
        QuyenDTO quyen = new QuyenDTO(maQuyen, tenQuyen);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditDecentralizationGUI((JFrame) window, quyenBUS, "Phân quyền", "save", quyen);
        loadTableData();
    }
    
    private void deleteDecentralization(){
        int selectedRow = tblContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một quyền để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maQuyen = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
            if (quyenBUS.deleteQuyen(maQuyen)) { 
                JOptionPane.showMessageDialog(this, "Xóa quyền thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa quyền thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

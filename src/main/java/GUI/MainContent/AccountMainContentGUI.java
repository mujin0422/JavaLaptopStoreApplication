package GUI.MainContent;

import BUS.NhanVienBUS;
import BUS.QuyenBUS;
import BUS.TaiKhoanBUS;
import DTO.NhanVienDTO;
import DTO.QuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.MainContentDiaLog.AddAndEditAccountGUI;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
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

public class AccountMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private UITextField txtSearch;
    private JComboBox<String> cbFilter;
    private UITable tblContent;
    private JPanel pnlHeader, pnlContent;
    private DefaultTableModel tableModel;
    private TaiKhoanBUS taiKhoanBUS;

    public AccountMainContentGUI() {
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
        btnAdd.addActionListener(e -> addAccount());
        btnDelete = new UIButton("menuButton", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteAccount());
        btnEdit = new UIButton("menuButton", "SỬA", 90, 40, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editAccount());
        pnlButton.add(btnAdd);
        pnlButton.add(btnDelete);
        pnlButton.add(btnEdit);

        JPanel pnlSearchFilter = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,10));
        pnlSearchFilter.setBackground(UIConstants.MAIN_BACKGROUND);
        cbFilter = new JComboBox<>(new String[]{"Lọc"});
        cbFilter.setPreferredSize(new Dimension(150,30));
        txtSearch = new UITextField(190, 30);
        pnlSearchFilter.add(cbFilter);
        pnlSearchFilter.add(txtSearch);

        pnlHeader.add(pnlButton, BorderLayout.WEST);
        pnlHeader.add(pnlSearchFilter, BorderLayout.CENTER);
        //==============================( End Panel Header )============================//

        
        
        //================================( PANEL CONTENT )=============================//
        pnlContent = new JPanel();
        pnlContent.setLayout(new BorderLayout());
        pnlContent.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Tạo bảng dữ liệu
        String[] columnNames = {"NHÂN VIÊN", "TÊN ĐĂNG NHẬP", "MẬT KHẨU", "QUYỀN"};
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
        for(TaiKhoanDTO tk : taiKhoanBUS.getAllTaiKhoan()){
            tableModel.addRow(new Object[]{
                taiKhoanBUS.getTenNvByUsername(tk.getTenDangNhap()),
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                taiKhoanBUS.getTenQuyenByUsername(tk.getTenDangNhap())
            });
        }
    }
    
    private void addAccount(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditAccountGUI((JFrame) window, taiKhoanBUS, "Thêm Tài Khoản", "add");
        loadTableData();
    }
    
    private void editAccount(){
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String tenNV = tableModel.getValueAt(selectedRow, 0).toString();
        String tenDangNhap = tableModel.getValueAt(selectedRow, 1).toString();
        String matKhau = tableModel.getValueAt(selectedRow, 2).toString();
        String tenQuyen = tableModel.getValueAt(selectedRow, 3).toString();
        int maNV = 0;
        NhanVienBUS nvBus = new NhanVienBUS();
        for(NhanVienDTO nv : nvBus.getAllNhanVien()){
            if(nv.getTenNV().equals(tenNV)){
                maNV = nv.getMaNV();
                break;
            }
        }
        int maQuyen = 0;
        QuyenBUS quyenBus = new QuyenBUS();
        for(QuyenDTO quyen : quyenBus.getAllQuyen()){
            if(quyen.getTenQuyen().equals(tenQuyen))
                maQuyen = quyen.getMaQuyen();
            break;
        }
        
        TaiKhoanDTO tk = new TaiKhoanDTO(tenDangNhap, matKhau, maNV, maQuyen);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditAccountGUI((JFrame) window, taiKhoanBUS, "Chỉnh sửa tài khoản", "save", tk);
        loadTableData();
    }
    
    private void deleteAccount(){
        int selectedRow = tblContent.getSelectedRow();
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một tài khoản để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String tenDangNhap = tableModel.getValueAt(selectedRow, 1).toString();
            if(taiKhoanBUS.deleteTaiKhoan(tenDangNhap)){
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableData();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

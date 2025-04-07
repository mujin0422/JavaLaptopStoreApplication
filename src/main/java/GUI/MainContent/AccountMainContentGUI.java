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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell Vostro
 */
public class AccountMainContentGUI extends JPanel{
    private UIButton btnAdd, btnDelete, btnEdit;
    private JTextField txtSearch;
    private JComboBox<String> cbFilter;
    private JTable tblContent;
    private JPanel pnlHeader, pnlContent;
    
    private DefaultTableModel tableModel;
    private TaiKhoanBUS taiKhoanBUS;

    public AccountMainContentGUI() {
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.SUB_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new BorderLayout(5, 5));

        //==============================( PANEL HEADER )================================//
        pnlHeader = new JPanel();
        pnlHeader.setLayout(null); 
        pnlHeader.setBackground(UIConstants.MAIN_BACKGROUND);
        pnlHeader.setPreferredSize(new Dimension(this.getWidth(), 50));

        btnAdd = new UIButton("menuButton", "THÊM", 100, 30, "/Icon/them_icon.png");
        btnAdd.addActionListener(e -> addAccount());
        btnDelete = new UIButton("menuButton", "XÓA", 100, 30, "/Icon/xoa_icon.png");
        btnDelete.addActionListener(e -> deleteAccount());
        btnEdit = new UIButton("menuButton", "SỬA", 100, 30, "/Icon/sua_icon.png");
        btnEdit.addActionListener(e -> editAccount());
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
        pnlContent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Tạo bảng dữ liệu
        String[] columnNames = {"NHÂN VIÊN", "TÊN ĐĂNG NHẬP", "MẬT KHẨU", "QUYỀN"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new JTable(tableModel);
        tblContent.setDefaultEditor(Object.class, null);
        // Thiết lập header của bảng
        tblContent.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        tblContent.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        tblContent.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        tblContent.getTableHeader().setPreferredSize(new Dimension(0,30));
        tblContent.setRowHeight(30);
        // Đặt bảng vào JScrollPane
        UIScrollPane scrollPane = new UIScrollPane(tblContent);
        // Thêm JScrollPane vào pnlContent
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //===============================( End Panel Content )===========================//
        
        
        
        this.add(pnlHeader, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
    }
    
    private void loadTableData(){
        tableModel.setRowCount(0);
        NhanVienBUS nvBus = new NhanVienBUS();
        HashMap<Integer,String> nvMap = new HashMap<>();
        for(NhanVienDTO nv : nvBus.getAllNhanVien()){
            nvMap.put(nv.getMaNV(), nv.getTenNV());
        }
        QuyenBUS quyenBus = new QuyenBUS();
        HashMap<Integer,String> quyenMap = new HashMap<>();
        for(QuyenDTO quyen : quyenBus.getAllQuyen()){
            quyenMap.put(quyen.getMaQuyen(), quyen.getTenQuyen());
        }
        ArrayList<TaiKhoanDTO> listTK = taiKhoanBUS.getAllTaiKhoan();
        for(TaiKhoanDTO tk : listTK){
            String tenNV = nvMap.get(tk.getMaNV());
            String tenQuyen = quyenMap.get(tk.getMaQuyen());
            tableModel.addRow(new Object[]{
                tenNV,
                tk.getTenDangNhap(),
                tk.getMatKhau(),
                tenQuyen
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

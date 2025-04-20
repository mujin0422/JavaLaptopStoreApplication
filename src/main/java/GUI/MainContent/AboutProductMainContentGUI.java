package GUI.MainContent;

import BUS.CpuBUS;
import BUS.DoPhanGiaiBUS;
import BUS.PhanLoaiBUS;
import BUS.RamBUS;
import BUS.RomBUS;
import BUS.TaiKhoanBUS;
import BUS.ThuongHieuBUS;
import DTO.CpuDTO;
import DTO.DoPhanGiaiDTO;
import DTO.PhanLoaiDTO;
import DTO.RamDTO;
import DTO.RomDTO;
import DTO.TaiKhoanDTO;
import DTO.ThuongHieuDTO;
import GUI.MainContentDiaLog.AddAndEditBrandGUI;
import GUI.MainContentDiaLog.AddAndEditCategoryGUI;
import GUI.MainContentDiaLog.AddAndEditCpuGUI;
import GUI.MainContentDiaLog.AddAndEditRamGUI;
import GUI.MainContentDiaLog.AddAndEditResolutionGUI;
import GUI.MainContentDiaLog.AddAndEditRomGUI;
import Utils.UIAboutPanel;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UIScrollPane;
import Utils.UITable;
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

public class AboutProductMainContentGUI extends JPanel{
    private UIButton addRAM, addROM, addCPU, addBrand, addResolution, addCategory;
    private UIButton editRAM, editROM, editCPU, editBrand, editResolution, editCategory;
    private UIButton deleteRAM, deleteROM, deleteCPU, deleteBrand, deleteResolution, deleteCategory;
    private UIAboutPanel pnlRAM, pnlROM, pnlCPU, pnlThuongHieu, pnlDoPhanGiai, pnlPhanLoai;
    private UITable tblRAM, tblROM, tblCPU, tblThuongHieu, tblDoPhanGiai, tblPhanLoai;
    private DefaultTableModel tblModelRAM, tblModelROM, tblModelCPU, tblModelThuongHieu, tblModelDoPhanGiai, tblModelPhanLoai;
    private RamBUS ramBus;
    private RomBUS romBus;
    private CpuBUS cpuBus;
    private ThuongHieuBUS thuongHieuBus;
    private DoPhanGiaiBUS doPhanGiaiBus;
    private PhanLoaiBUS phanLoaiBus;
    private TaiKhoanBUS taiKhoanBUS;
    
    public AboutProductMainContentGUI(TaiKhoanDTO taiKhoan){
        this.taiKhoanBUS = new TaiKhoanBUS();
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setPreferredSize(new Dimension(UIConstants.WIDTH_CONTENT, UIConstants.HEIGHT_CONTENT));
        this.setLayout(new FlowLayout(10));
        
        int pnlWidth = this.getPreferredSize().width/2 - 48;
        int pnlHeight = this.getPreferredSize().height/3 - 26;
        
        //=================================( PANEL RAM )================================//
        ramBus = new RamBUS();
        pnlRAM = new UIAboutPanel("/Icon/RAM_icon.png","RAM", pnlWidth , pnlHeight);
        addRAM = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addRAM.addActionListener(e -> addRam());
        deleteRAM = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteRAM.addActionListener(e -> deleteRam());
        editRAM = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editRAM.addActionListener(e -> editRam());
        pnlRAM.addButton(addRAM);
        pnlRAM.addButton(deleteRAM);
        pnlRAM.addButton(editRAM);
        
        String[] columnRAMNames = {"MÃ RAM", "DUNG LƯỢNG RAM"};
        tblModelRAM = new DefaultTableModel(columnRAMNames,0);
        tblRAM = new UITable(tblModelRAM);
        UIScrollPane scrollRAM = new UIScrollPane(tblRAM);
        pnlRAM.getPnlContent().add(scrollRAM, BorderLayout.CENTER);
        //===============================( End Panel RAM )==============================//
        
        
        
        //=================================( PANEL ROM )================================//
        romBus = new RomBUS();
        pnlROM = new UIAboutPanel("/Icon/ROM_icon.png","ROM", pnlWidth , pnlHeight);
        addROM = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addROM.addActionListener(e -> addRom());
        deleteROM = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteROM.addActionListener(e -> deleteRom());
        editROM = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editROM.addActionListener(e -> editRom());
        pnlROM.addButton(addROM);
        pnlROM.addButton(deleteROM);
        pnlROM.addButton(editROM);
        
        String[] columnROMNames = {"MÃ ROM", "DUNG LƯỢNG ROM"};
        tblModelROM = new DefaultTableModel(columnROMNames,0);
        tblROM = new UITable(tblModelROM);
        UIScrollPane scrollROM = new UIScrollPane(tblROM);
        pnlROM.getPnlContent().add(scrollROM, BorderLayout.CENTER);
        //===============================( End Panel ROM )==============================//
        
        
        
        //=================================( PANEL CPU )================================//
        cpuBus = new CpuBUS();
        pnlCPU = new UIAboutPanel("/Icon/CPU_icon.png","CPU", pnlWidth , pnlHeight);
        addCPU = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addCPU.addActionListener(e -> addCpu());
        deleteCPU = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteCPU.addActionListener(e -> deleteCpu());
        editCPU = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editCPU.addActionListener(e -> editCpu());
        pnlCPU.addButton(addCPU);
        pnlCPU.addButton(deleteCPU);
        pnlCPU.addButton(editCPU);
        
        String[] columnCPUNames = {"MÃ CPU", "TÊN CPU"};
        tblModelCPU = new DefaultTableModel(columnCPUNames,0);
        tblCPU = new UITable(tblModelCPU);
        UIScrollPane scrollCPU = new UIScrollPane(tblCPU);
        pnlCPU.getPnlContent().add(scrollCPU, BorderLayout.CENTER);
        //===============================( End Panel CPU )==============================//
        
        
        
        //=============================( PANEL THUONG HIEU )============================//
        thuongHieuBus = new ThuongHieuBUS();
        pnlThuongHieu = new UIAboutPanel("/Icon/ThuongHieu_icon.png","THƯƠNG HIỆU", pnlWidth , pnlHeight);
        addBrand = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addBrand.addActionListener(e -> addThuongHieu());
        deleteBrand = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteBrand.addActionListener(e -> deleteThuongHieu());
        editBrand = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editBrand.addActionListener(e -> editThuongHieu());
        pnlThuongHieu.addButton(addBrand);
        pnlThuongHieu.addButton(deleteBrand);
        pnlThuongHieu.addButton(editBrand);
        
        String[] columnBrandNames = {"MÃ THƯƠNG HIỆU", "TÊN THƯƠNG HIỆU"};
        tblModelThuongHieu = new DefaultTableModel(columnBrandNames,0);
        tblThuongHieu = new UITable(tblModelThuongHieu);
        UIScrollPane scrollBrand = new UIScrollPane(tblThuongHieu);
        pnlThuongHieu.getPnlContent().add(scrollBrand, BorderLayout.CENTER);
        //===========================( End Panel Thuong Hieu )==========================//
        
        
        
        //=============================( PANEL DO PHAN GIAI )===========================//
        doPhanGiaiBus = new DoPhanGiaiBUS();
        pnlDoPhanGiai = new UIAboutPanel("/Icon/DoPhanGiai_icon.png","ĐỘ PHÂN GIẢI", pnlWidth , pnlHeight);
        addResolution = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addResolution.addActionListener(e -> addDoPhanGiai());
        deleteResolution = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteResolution.addActionListener(e -> deleteDoPhanGiai());
        editResolution = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editResolution.addActionListener(e -> editDoPhanGiai());
        pnlDoPhanGiai.addButton(addResolution);
        pnlDoPhanGiai.addButton(deleteResolution);
        pnlDoPhanGiai.addButton(editResolution);
        
        String[] columnResolutionNames = {"MÃ ĐỘ PHÂN GIẢI", "ĐỘ PHÂN GIẢI"};
        tblModelDoPhanGiai = new DefaultTableModel(columnResolutionNames,0);
        tblDoPhanGiai = new UITable(tblModelDoPhanGiai); 
        UIScrollPane scrollResolution = new UIScrollPane(tblDoPhanGiai);
        pnlDoPhanGiai.getPnlContent().add(scrollResolution, BorderLayout.CENTER);
        //===========================( End Panel Do Phan Giai )=========================//
        
        
        
        //==============================( PANEL PHAN LOAI )=============================//
        phanLoaiBus = new PhanLoaiBUS();
        pnlPhanLoai = new UIAboutPanel("/Icon/PhanLoai_icon.png","PHÂN LOẠI", pnlWidth , pnlHeight);
        addCategory = new UIButton("add", "THÊM", 90, 40, "/Icon/them_icon.png");
        addCategory.addActionListener(e -> addPhanLoai());
        deleteCategory = new UIButton("delete", "XÓA", 90, 40, "/Icon/xoa_icon.png");
        deleteCategory.addActionListener(e -> deletePhanLoai());
        editCategory = new UIButton("edit", "SỬA", 90, 40, "/Icon/sua_icon.png");
        editCategory.addActionListener(e -> editPhanLoai());
        pnlPhanLoai.addButton(addCategory);
        pnlPhanLoai.addButton(deleteCategory);
        pnlPhanLoai.addButton(editCategory);
        
        String[] columnCategoryNames = {"MÃ LOẠI", "TÊN LOẠI"};
        tblModelPhanLoai = new DefaultTableModel(columnCategoryNames,0);
        tblPhanLoai = new UITable(tblModelPhanLoai);
        UIScrollPane scrollCategory = new UIScrollPane(tblPhanLoai);
        pnlPhanLoai.getPnlContent().add(scrollCategory, BorderLayout.CENTER);
        //============================( End Panel Phan Loai )===========================//
        
        
        applyPermissions(taiKhoan.getTenDangNhap(), 2);
        this.add(pnlRAM);
        this.add(pnlROM);
        this.add(pnlCPU);
        this.add(pnlThuongHieu);
        this.add(pnlDoPhanGiai);
        this.add(pnlPhanLoai);
        loadTableDataRAM();
        loadTableDataROM();
        loadTableDataCPU();
        loadTableDataThuongHieu();
        loadTableDataDoPhanGiai();
        loadTableDataPhanLoai();
    }
    
    private void applyPermissions(String username, int maCN) {
        addRAM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addROM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addCPU.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addBrand.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addResolution.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        addCategory.setVisible(taiKhoanBUS.hasPermission(username, maCN, "add"));
        editRAM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editROM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editCPU.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editBrand.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editResolution.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        editCategory.setVisible(taiKhoanBUS.hasPermission(username, maCN, "edit"));
        deleteRAM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteROM.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteCPU.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteBrand.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteResolution.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
        deleteCategory.setVisible(taiKhoanBUS.hasPermission(username, maCN, "delete"));
    }
    
    private void loadTableDataRAM(){
        tblModelRAM.setRowCount(0);
        ArrayList<RamDTO> listRAM = ramBus.getAllRAM();
        for (RamDTO ram : listRAM) {
            tblModelRAM.addRow(new Object[]{ ram.getMaRAM(), ram.getDungLuongRAM()});
        }
    }
    private void addRam() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRamGUI((JFrame) window, ramBus, "Thêm RAM", "add");
        loadTableDataRAM();
    }
    private void editRam() {
        int selectedRow = tblRAM.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một RAM để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maRam = Integer.parseInt(tblModelRAM.getValueAt(selectedRow, 0).toString());
        String dungLuongRam = tblModelRAM.getValueAt(selectedRow, 1).toString();
        RamDTO ram = new RamDTO(maRam, dungLuongRam);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRamGUI((JFrame) window, ramBus, "Chỉnh sửa RAM", "save", ram);
        loadTableDataRAM();
    }
    private void deleteRam() {
        int selectedRow = tblRAM.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một RAM để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maRAM = Integer.parseInt(tblModelRAM.getValueAt(selectedRow, 0).toString());
            if (ramBus.deleteRAM(maRAM)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataRAM();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    

    private void loadTableDataROM(){
        tblModelROM.setRowCount(0);
        ArrayList<RomDTO> listROM = romBus.getAllROM();
        for (RomDTO rom : listROM) {
            tblModelROM.addRow(new Object[]{ rom.getMaROM(), rom.getDungLuongROM()});
        }
    }
    private void addRom() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRomGUI((JFrame) window, romBus, "Thêm ROM", "add");
        loadTableDataROM();
    }
    private void editRom() {
        int selectedRow = tblROM.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một ROM để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maRom = Integer.parseInt(tblModelROM.getValueAt(selectedRow, 0).toString());
        String dungLuongRom = tblModelROM.getValueAt(selectedRow, 1).toString();
        RomDTO rom = new RomDTO(maRom, dungLuongRom);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditRomGUI((JFrame) window, romBus, "Chỉnh sửa ROM", "save", rom);
        loadTableDataROM();
    }
    private void deleteRom() {
        int selectedRow = tblROM.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một ROM để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maROM = Integer.parseInt(tblModelROM.getValueAt(selectedRow, 0).toString());
            if (romBus.deleteROM(maROM)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataROM();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadTableDataCPU(){
        tblModelCPU.setRowCount(0);
        ArrayList<CpuDTO> listCPU = cpuBus.getAllCPU();
        for (CpuDTO cpu : listCPU) {
            tblModelCPU.addRow(new Object[]{ cpu.getMaCPU(), cpu.getTenCPU()});
        }
    }
    private void addCpu(){
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCpuGUI((JFrame) window, cpuBus, "Thêm CPU", "add");
        loadTableDataCPU();
    }
    private void editCpu(){
        int selectedRow = tblCPU.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một CPU để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maCpu = Integer.parseInt(tblModelCPU.getValueAt(selectedRow, 0).toString());
        String tenCpu = tblModelCPU.getValueAt(selectedRow, 1).toString();
        CpuDTO cpu = new CpuDTO(maCpu, tenCpu);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCpuGUI((JFrame) window, cpuBus, "Chỉnh sửa CPU", "save", cpu);
        loadTableDataCPU();
    }
    private void deleteCpu(){
        int selectedRow = tblCPU.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một CPU để xoa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maCPU = Integer.parseInt(tblModelCPU.getValueAt(selectedRow, 0).toString());
            if (cpuBus.deleteCPU(maCPU)) { 
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataCPU();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadTableDataThuongHieu(){
        tblModelThuongHieu.setRowCount(0);
        ArrayList<ThuongHieuDTO> listThuongHieu = thuongHieuBus.getAllThuongHieu();
        for (ThuongHieuDTO th : listThuongHieu) {
            tblModelThuongHieu.addRow(new Object[]{ th.getMaTH(), th.getTenTH()});
        }
    }
    private void addThuongHieu() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditBrandGUI((JFrame) window, thuongHieuBus, "Thêm Thương Hiệu", "add");
        loadTableDataThuongHieu();
    }
    private void editThuongHieu() {
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thương hiệu để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maThuongHieu = Integer.parseInt(tblModelThuongHieu.getValueAt(selectedRow, 0).toString());
        String tenThuongHieu = tblModelThuongHieu.getValueAt(selectedRow, 1).toString();
        ThuongHieuDTO thuongHieu = new ThuongHieuDTO(maThuongHieu, tenThuongHieu);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditBrandGUI((JFrame) window, thuongHieuBus, "Chỉnh sửa Thương Hiệu", "save", thuongHieu);
        loadTableDataThuongHieu();
    }
    private void deleteThuongHieu() {
        int selectedRow = tblThuongHieu.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một thương hiệu để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maThuongHieu = Integer.parseInt(tblModelThuongHieu.getValueAt(selectedRow, 0).toString());
            if (thuongHieuBus.deleteThuongHieu(maThuongHieu)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataThuongHieu();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadTableDataDoPhanGiai(){
        tblModelDoPhanGiai.setRowCount(0);
        ArrayList<DoPhanGiaiDTO> listDoPhanGiai = doPhanGiaiBus.getAllDoPhanGiai();
        for (DoPhanGiaiDTO dp : listDoPhanGiai) {
            tblModelDoPhanGiai.addRow(new Object[]{ dp.getMaDPG(), dp.getTenDPG()});
        }
    }
    private void addDoPhanGiai() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditResolutionGUI((JFrame) window, doPhanGiaiBus, "Thêm Độ Phân Giải", "add");
        loadTableDataDoPhanGiai();
    }
    private void editDoPhanGiai() {
        int selectedRow = tblDoPhanGiai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một độ phân giải để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maDoPhanGiai = Integer.parseInt(tblModelDoPhanGiai.getValueAt(selectedRow, 0).toString());
        String doPhanGiai = tblModelDoPhanGiai.getValueAt(selectedRow, 1).toString();
        DoPhanGiaiDTO doPhanGiaiDTO = new DoPhanGiaiDTO(maDoPhanGiai, doPhanGiai);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditResolutionGUI((JFrame) window, doPhanGiaiBus, "Chỉnh sửa Độ Phân Giải", "save", doPhanGiaiDTO);
        loadTableDataDoPhanGiai();
    }
    private void deleteDoPhanGiai() {
        int selectedRow = tblDoPhanGiai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một độ phân giải để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maDoPhanGiai = Integer.parseInt(tblModelDoPhanGiai.getValueAt(selectedRow, 0).toString());
            if (doPhanGiaiBus.deleteDoPhanGiai(maDoPhanGiai)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataDoPhanGiai();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadTableDataPhanLoai(){
        tblModelPhanLoai.setRowCount(0);
        ArrayList<PhanLoaiDTO> listPhanLoai = phanLoaiBus.getAllPhanLoai();
        for (PhanLoaiDTO pl : listPhanLoai) {
            tblModelPhanLoai.addRow(new Object[]{ pl.getMaLoai(), pl.getTenLoai()});
        }
    }
    private void addPhanLoai() {
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCategoryGUI((JFrame) window, phanLoaiBus, "Thêm Phân Loại", "add");
        loadTableDataPhanLoai();
    }
    private void editPhanLoai() {
        int selectedRow = tblPhanLoai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phân loại để chỉnh sửa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int maPhanLoai = Integer.parseInt(tblModelPhanLoai.getValueAt(selectedRow, 0).toString());
        String tenPhanLoai = tblModelPhanLoai.getValueAt(selectedRow, 1).toString();
        PhanLoaiDTO phanLoai = new PhanLoaiDTO(maPhanLoai, tenPhanLoai);
        Window window = SwingUtilities.getWindowAncestor(this);
        new AddAndEditCategoryGUI((JFrame) window, phanLoaiBus, "Chỉnh sửa Phân Loại", "save", phanLoai);
        loadTableDataPhanLoai();
    }
    private void deletePhanLoai() {
        int selectedRow = tblPhanLoai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một phân loại để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int maPhanLoai = Integer.parseInt(tblModelPhanLoai.getValueAt(selectedRow, 0).toString());
            if (phanLoaiBus.deletePhanLoai(maPhanLoai)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadTableDataPhanLoai();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}

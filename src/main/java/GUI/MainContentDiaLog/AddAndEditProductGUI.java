package GUI.MainContentDiaLog;

import BUS.CpuBUS;
import BUS.DoPhanGiaiBUS;
import BUS.PhanLoaiBUS;
import BUS.RamBUS;
import BUS.RomBUS;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BUS.SanPhamBUS;
import BUS.ThuongHieuBUS;
import DTO.CpuDTO;
import DTO.DoPhanGiaiDTO;
import DTO.PhanLoaiDTO;
import DTO.RamDTO;
import DTO.RomDTO;
import DTO.SanPhamDTO;
import DTO.ThuongHieuDTO;
import Utils.UIButton;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UITextField;
import java.awt.Dimension;
import javax.swing.JComboBox;
public class AddAndEditProductGUI extends JDialog {
    private UITextField txtMaSP, txtTenSP, txtGiaSP, txtSoLuongTon, txtThoiGianBH;
    private JComboBox<String> cbRam, cbRom, cbCpu, cbDpg, cbLoai, cbTh;
    private UIButton btnAdd, btnSave, btnCancel;
    private SanPhamBUS sanPhamBus;
    private RamBUS ramBus;
    private RomBUS romBus;
    private CpuBUS cpuBus;
    private DoPhanGiaiBUS doPhanGiaiBus;
    private PhanLoaiBUS phanLoaiBus;
    private ThuongHieuBUS thuongHieuBus;  
    private SanPhamDTO sanPham;
    
    public AddAndEditProductGUI(JFrame parent, SanPhamBUS sanPhamBus, String title, String type, SanPhamDTO sanPham) {
        super(parent, title, true);
        this.sanPhamBus = sanPhamBus;
        this.sanPham = sanPham;
        initComponent(type);
        
        if (sanPham != null) {
            txtMaSP.setText(String.valueOf(sanPham.getMaSP()));
            txtTenSP.setText(sanPham.getTenSP());
            txtGiaSP.setText(String.valueOf(sanPham.getGiaSP()));
            txtSoLuongTon.setText(String.valueOf(sanPham.getSoLuongTon()));
            String tenCpu = cpuBus.getTenCpuByMaCpu(sanPham.getMaCPU());
            if (tenCpu != null) {
                cbCpu.setSelectedItem(tenCpu);
            }
            String tenRam = ramBus.getDungLuongRamByMaRam(sanPham.getMaRAM());
            if (tenRam != null) {
                cbRam.setSelectedItem(tenRam);
            }

            String tenRom = romBus.getDungLuongRomByMaRom(sanPham.getMaROM());
            if (tenRom != null) {
                cbRom.setSelectedItem(tenRom);
            }

            String tenDpg = doPhanGiaiBus.getTenDpgByMaDpg(sanPham.getMaDPG());
            if (tenDpg != null) {
                cbDpg.setSelectedItem(tenDpg);
            }

            String tenLoai = phanLoaiBus.getTenLoaiByMaLoai(sanPham.getMaLoai());
            if (tenLoai != null) {
                cbLoai.setSelectedItem(tenLoai);
            }

            String tenTH = thuongHieuBus.getTenThByMaTh(sanPham.getMaTH());
            if (tenTH != null) {
                cbTh.setSelectedItem(tenTH);
            }
            txtThoiGianBH.setText(String.valueOf(sanPham.getThoiGianBH()));
            txtMaSP.setEnabled(false);
            txtSoLuongTon.setEnabled(false);
            
        }
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    public AddAndEditProductGUI(JFrame parent, SanPhamBUS sanPhamBus, String title, String type) {
        super(parent, title, true);
        this.sanPhamBus = sanPhamBus;
        initComponent(type);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void initComponent(String type) {
        this.ramBus = new RamBUS();
        this.romBus = new RomBUS();
        this.cpuBus = new CpuBUS();
        this.doPhanGiaiBus = new DoPhanGiaiBUS();
        this.thuongHieuBus = new ThuongHieuBUS();
        this.phanLoaiBus = new PhanLoaiBUS();
        this.setSize(550, 600);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(12, 2, 10, 10));
        inputPanel.setBackground(UIConstants.MAIN_BACKGROUND);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new UILabel("Mã Sản Phẩm:"));
        inputPanel.add(txtMaSP = new UITextField(250,30));
        inputPanel.add(new UILabel("Tên Sản Phẩm:"));
        inputPanel.add(txtTenSP = new UITextField(250,30));
        inputPanel.add(new UILabel("Giá Sản Phẩm:"));
        inputPanel.add(txtGiaSP = new UITextField(250,30));
        inputPanel.add(new UILabel("Số Lượng Tồn:"));
        inputPanel.add(txtSoLuongTon = new UITextField(250,30));
        txtSoLuongTon.setText("0");
        txtSoLuongTon.setEditable(false);
        
        inputPanel.add(new UILabel("RAM:"));
        cbRam = new JComboBox<>();
        cbRam.setBackground(UIConstants.WHITE_FONT);
        cbRam.setPreferredSize(new Dimension(250,30));
        for (RamDTO ram : ramBus.getAllRAM()) {
            cbRam.addItem(ram.getDungLuongRAM());   
        }
        inputPanel.add(cbRam);
        
        
        inputPanel.add(new UILabel("ROM:"));
        cbRom = new JComboBox<>();
        cbRom.setBackground(UIConstants.WHITE_FONT);
        cbRom.setPreferredSize(new Dimension(250, 30));
        for (RomDTO rom : romBus.getAllROM()) {
            cbRom.addItem(rom.getDungLuongROM());
        }
        inputPanel.add(cbRom);

        inputPanel.add(new UILabel("CPU:"));
        cbCpu = new JComboBox<>();
        cbCpu.setBackground(UIConstants.WHITE_FONT);
        cbCpu.setPreferredSize(new Dimension(250, 30));
        for (CpuDTO cpu : cpuBus.getAllCPU()) {
            cbCpu.addItem(cpu.getTenCPU());
        }
        inputPanel.add(cbCpu);

        inputPanel.add(new UILabel("Độ Phân Giải:"));
        cbDpg = new JComboBox<>();
        cbDpg.setBackground(UIConstants.WHITE_FONT);
        cbDpg.setPreferredSize(new Dimension(250, 30));
        for (DoPhanGiaiDTO dpg : doPhanGiaiBus.getAllDoPhanGiai()) {
            cbDpg.addItem(dpg.getTenDPG());
        }
        inputPanel.add(cbDpg);

        inputPanel.add(new UILabel("Phân Loại:"));
        cbLoai = new JComboBox<>();
        cbLoai.setBackground(UIConstants.WHITE_FONT);
        cbLoai.setPreferredSize(new Dimension(250, 30));
        for (PhanLoaiDTO loai : phanLoaiBus.getAllPhanLoai()) {
            cbLoai.addItem(loai.getTenLoai());
        }
        inputPanel.add(cbLoai);

        inputPanel.add(new UILabel("Thương Hiệu:"));
        cbTh = new JComboBox<>();
        cbTh.setBackground(UIConstants.WHITE_FONT);
        cbTh.setPreferredSize(new Dimension(250, 30));
        for (ThuongHieuDTO th : thuongHieuBus.getAllThuongHieu()) {
            cbTh.addItem(th.getTenTH());
        }
        inputPanel.add(cbTh);

        
        inputPanel.add(new UILabel("Thời Gian Bảo Hành:"));
        inputPanel.add(txtThoiGianBH = new UITextField(250, 30));

        //==============================( PANEL BUTTON )================================//
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnPanel.setBackground(UIConstants.MAIN_BACKGROUND);

        btnAdd = new UIButton("add", "THÊM", 90, 35);
        btnSave = new UIButton("confirm", "LƯU", 90, 35);
        btnCancel = new UIButton("cancel", "HỦY", 90, 35);

        if (type.equals("add")) {
            btnPanel.add(btnAdd);
        } else if (type.equals("save")) {
            btnPanel.add(btnSave);
        }
        btnPanel.add(btnCancel);

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
        

        btnCancel.addActionListener(e -> dispose());
        if (btnAdd != null) btnAdd.addActionListener(e -> addProduct());
        if (btnSave != null) btnSave.addActionListener(e -> saveProduct());
        
    }

    private void saveProduct() {
        if (!checkFormInput()) return;
        try {
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String tenSP = txtTenSP.getText().trim();
            int giaSP = Integer.parseInt(txtGiaSP.getText().trim());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            int maRam = ramBus.getMaRamByDungLuongRam(cbRam.getSelectedItem().toString());
            int maCPU = cpuBus.getMaCpuByTenCpu(cbCpu.getSelectedItem().toString());
            int maRom = romBus.getMaRomByDungLuongRom(cbRom.getSelectedItem().toString());
            int maDPG = doPhanGiaiBus.getMaDpgByTenDpg(cbDpg.getSelectedItem().toString());
            int maLoai = phanLoaiBus.getMaLoaiByTenLoai(cbLoai.getSelectedItem().toString());
            int maTH = thuongHieuBus.getMaThByTenTh(cbTh.getSelectedItem().toString());
            int thoiGianBH = Integer.parseInt(txtThoiGianBH.getText().trim());
            
            SanPhamDTO sp = new SanPhamDTO(maSP, tenSP, giaSP, soLuongTon, maCPU, maRam, maRom, maDPG, maLoai, maTH, thoiGianBH);
            if (sanPhamBus.updateSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addProduct() {
        if (!checkFormInput()) return;
        try {   
            int maSP = Integer.parseInt(txtMaSP.getText().trim());
            String tenSP = txtTenSP.getText().trim();
            int giaSP = Integer.parseInt(txtGiaSP.getText().trim());
            int soLuongTon = Integer.parseInt(txtSoLuongTon.getText().trim());
            int maRam = ramBus.getMaRamByDungLuongRam(cbRam.getSelectedItem().toString());
            int maCPU = cpuBus.getMaCpuByTenCpu(cbCpu.getSelectedItem().toString());
            int maRom = romBus.getMaRomByDungLuongRom(cbRom.getSelectedItem().toString());
            int maDPG = doPhanGiaiBus.getMaDpgByTenDpg(cbDpg.getSelectedItem().toString());
            int maLoai = phanLoaiBus.getMaLoaiByTenLoai(cbLoai.getSelectedItem().toString());
            int maTH = thuongHieuBus.getMaThByTenTh(cbTh.getSelectedItem().toString());
            int thoiGianBH = Integer.parseInt(txtThoiGianBH.getText().trim());

            SanPhamDTO sp = new SanPhamDTO( maSP, tenSP, giaSP, soLuongTon, maCPU, maRam, maRom, maDPG, maLoai, maTH, thoiGianBH);
            if (sanPhamBus.addSanPham(sp)) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại hoặc dữ liệu không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi nhập dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);  
        }
    }

    private boolean checkFormInput() {
        try {
            String maSPStr = txtMaSP.getText().trim();
            if (maSPStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int maSP = Integer.parseInt(maSPStr);
            if (maSP <= 0) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tenSP = txtTenSP.getText().trim();
            if (tenSP.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String giaSPStr = txtGiaSP.getText().trim();
            if (giaSPStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            double giaSP = Double.parseDouble(giaSPStr);
            if (giaSP <= 0) {
                JOptionPane.showMessageDialog(this, "Giá sản phẩm phải là số dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            String tgBhStr = txtThoiGianBH.getText().trim();
            if (tgBhStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Thời gian bảo hành không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            int tgBh = Integer.parseInt(tgBhStr);
            if (tgBh <= 0) {
                JOptionPane.showMessageDialog(this, "Thời gian bảo hành phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dữ liệu nhập vào không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    
}

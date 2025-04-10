package GUI.ThongKeComponent;

import BUS.KhachHangBUS;
import BUS.PhieuNhapBUS;
import BUS.PhieuXuatBUS;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
import Utils.UIConstants;
import Utils.UILabel;
import Utils.UIScrollPane;
import Utils.UITable;
import Utils.UITextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ThongKeKhachHang extends JPanel{
    private KhachHangBUS khachHangBus;
    private PhieuXuatBUS phieuXuatBus;
    private JPanel pnlFilter, pnlContent, pnlKhTiemNang, pnlSoLuongKh;
    private UITextField txtDateFrom, txtDateTo;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    private UITable tblContent;
    private DefaultTableModel tableModel;
    
    public ThongKeKhachHang(){
        this.setLayout(new BorderLayout(5,5));
        khachHangBus = new KhachHangBUS();
        phieuXuatBus = new PhieuXuatBUS();
        
        //================================(PANEL FILTER)================================//
        pnlFilter = new JPanel(new FlowLayout(FlowLayout.LEFT, 10,5));
        pnlFilter.setBackground(UIConstants.WHITE_FONT);
        pnlFilter.setPreferredSize(new Dimension(0, 35));

        JPanel groupDayFilter = new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        txtDateFrom = new UITextField(100,25);
        txtDateTo = new UITextField(100,25);
        JPanel pnlFrom = new JPanel(new BorderLayout());
        pnlFrom.add(new UILabel("Từ: ",40, 25), BorderLayout.WEST);
        pnlFrom.add(txtDateFrom, BorderLayout.CENTER);
        JPanel pnlTo = new JPanel(new BorderLayout());
        pnlTo.add(new UILabel("Đến: ",40, 25), BorderLayout.WEST);
        pnlTo.add(txtDateTo, BorderLayout.CENTER);
        groupDayFilter.add(pnlFrom);
        groupDayFilter.add(pnlTo);
        
        pnlFilter.add(groupDayFilter);
        //==============================(End Panel Filter)==============================//
        
        
        //================================(PANEL CONTENT)===============================//
        pnlContent = new JPanel(new BorderLayout());
            // center
        String[] columnNames = {"MÃ KHÁCH HÀNG", "TÊN KHÁCH HÀNG", "SỐ LẦN MUA HÀNG", "TỔNG TIỀN ĐÃ MUA"};
        tableModel = new DefaultTableModel(columnNames, 0); 
        tblContent = new UITable(tableModel);
        UIScrollPane scrollPane = new UIScrollPane(tblContent);  
            //left
        JPanel pnlContentLeft = new JPanel(new FlowLayout(FlowLayout.CENTER, 5 ,5));
        pnlContentLeft.setPreferredSize(new Dimension(300, 0));
        pnlKhTiemNang = new JPanel();
        pnlKhTiemNang.setPreferredSize(new Dimension(290,150));
        pnlKhTiemNang.setBackground(Color.red);
        pnlSoLuongKh = new JPanel();
        pnlSoLuongKh.setPreferredSize(new Dimension(290,150));
        pnlSoLuongKh.setBackground(Color.green);
        
        pnlContentLeft.add(pnlKhTiemNang);
        pnlContentLeft.add(pnlSoLuongKh);
        
        pnlContent.add(pnlContentLeft, BorderLayout.WEST);
        pnlContent.add(scrollPane, BorderLayout.CENTER);
        //==============================(End Panel Content)=============================//

        this.add(pnlFilter, BorderLayout.NORTH);
        this.add(pnlContent, BorderLayout.CENTER);
        loadTableData();
        showKhachHangThongKe();
        
    }
    
    public Date getDateFromField(JTextField field) {
        try {
            return dateFormat.parse(field.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Ngày không hợp lệ. Định dạng đúng: dd/MM/yyyy");
            return null;
        }
    }
    
    public void loadTableData(){
        tableModel.setRowCount(0);
        ArrayList<Object[]> rows = new ArrayList<>();
        for(KhachHangDTO kh : khachHangBus.getAllKhachHang()){
            int maKh = kh.getMaKH();
            int tongTien = 0;
            for(PhieuXuatDTO px: phieuXuatBus.getAllPhieuXuat()){
                if(px.getMaKH() == maKh) tongTien += px.getTongTien();
            }
            int soLanMua = phieuXuatBus.countPhieuXuatByMaKh(maKh);

            rows.add(new Object[]{
                maKh,
                kh.getTenKH(),
                soLanMua,
                tongTien
            });
        }
        rows.sort((a, b) -> Integer.compare((int) b[3], (int) a[3]));

        for (Object[] row : rows) {
            tableModel.addRow(row);
        }
    }
    
    public void showKhachHangThongKe() {
        // Hiển thị tên khách hàng tiềm năng
        pnlKhTiemNang.removeAll();
        pnlKhTiemNang.setLayout(new BorderLayout());
        if (tableModel.getRowCount() > 0) {
            String tenKhachHangTiemNang = tableModel.getValueAt(0, 1).toString();
            UILabel lblKhTiemNang = new UILabel("<html><center>KHÁCH HÀNG TIỀM NĂNG<br/><b>" + tenKhachHangTiemNang + "</b></center></html>", 14, 20);
            lblKhTiemNang.setHorizontalAlignment(JLabel.CENTER);
            pnlKhTiemNang.add(lblKhTiemNang, BorderLayout.CENTER);
        } else {
            pnlKhTiemNang.add(new UILabel("Không có dữ liệu"), BorderLayout.CENTER);
        }

        // Hiển thị số lượng khách hàng
        pnlSoLuongKh.removeAll();
        pnlSoLuongKh.setLayout(new BorderLayout());
        int soLuongKhachHang = tableModel.getRowCount();
        UILabel lblSoLuong = new UILabel("<html><center>TỔNG SỐ KHÁCH HÀNG<br/><b>" + soLuongKhachHang + "</b></center></html>", 14, 20);
        lblSoLuong.setHorizontalAlignment(JLabel.CENTER);
        pnlSoLuongKh.add(lblSoLuong, BorderLayout.CENTER);

        // Cập nhật lại giao diện
        pnlKhTiemNang.revalidate();
        pnlKhTiemNang.repaint();
        pnlSoLuongKh.revalidate();
        pnlSoLuongKh.repaint();
    }

}


package GUI.ThongKeComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ThongKeSach extends JPanel {
    private JTable tbl;
    private JTable table;
    private DefaultTableModel model;

    public ThongKeSach() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Tiêu đề cột
        String[] columnNames = {"Mã sách", "Tên sách", "Tác giả", "Nhà xuất bản", "Mã thể loại"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Tiêu đề
        JLabel lblTitle = new JLabel("Thống kê sách", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(lblTitle, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }
}

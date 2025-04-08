package Utils;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class UITable extends JTable {

    public UITable(DefaultTableModel tableModel) {
        super(tableModel);
        initComponent();
        applyLeftPadding();
    }

    private void initComponent() {
        this.setDefaultEditor(Object.class, null);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setFont(UIConstants.SUBTITLE_FONT);
        this.getTableHeader().setBackground(UIConstants.MAIN_BUTTON);
        this.getTableHeader().setForeground(UIConstants.WHITE_FONT);
        this.getTableHeader().setPreferredSize(new Dimension(0, 30));
        this.setRowHeight(30);
    }

    private void applyLeftPadding() {
    DefaultTableCellRenderer paddedRenderer = new DefaultTableCellRenderer() {
        @Override
        public java.awt.Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (c instanceof javax.swing.JLabel label) {
                label.setHorizontalAlignment(LEFT);
                label.setBorder(new EmptyBorder(0, 5, 0, 0)); 
            }
            return c;
        }
    };
    for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
        this.getColumnModel().getColumn(i).setCellRenderer(paddedRenderer);
    }
}

}

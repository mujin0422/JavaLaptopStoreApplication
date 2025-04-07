package Utils;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class UIScrollPane extends JScrollPane{

    public UIScrollPane(JComponent component) {
        super(component);  
        initComponent();
    }

    private void initComponent() {
        this.setBackground(UIConstants.MAIN_BACKGROUND);
        this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.getViewport().setBackground(UIConstants.WHITE_FONT);
    }
}

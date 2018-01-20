package my.fmlUi;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*; 
import javax.swing.table.*;
/**
 *
 * @author termaat
 */
//public class TableChartPopup extends JFrame {
public class TableChartPopup extends JPanel {    

    JPanel myPanel;
    
    public TableChartPopup(TableModel tm) { 
        myPanel = new JPanel();
        //super("Table Chart");
        //setSize(300,200);
        TableChart tc = new TableChart(tm);
        myPanel.setLayout(new BorderLayout());
        myPanel.add(tc,BorderLayout.CENTER);
        myPanel.setSize(200, 150);
        
        //getContentPane( ).add(tc, BorderLayout.CENTER);
        // Use the following line to turn on tooltips: 
        ToolTipManager.sharedInstance( ).registerComponent(tc); 
    }
    
    public JPanel GetPieChart(){
        return myPanel;
    }
}

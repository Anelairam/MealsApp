import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

public class ViewDatabase extends JFrame{
    private JTable table1;
    private JButton viewDbBtn;
    private JButton expPDFBtn;
    private JButton goBackBtn;
    private JPanel ViewDatabase;
    private int idmeal;
    private String strmeal;
    private String strcate;
    private String strarea;
    private int views;


    public ViewDatabase() {


        setContentPane(ViewDatabase);
        setTitle("Database Information");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //DefaultTableModel model = new DefaultTableModel();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        table1.setBackground(Color.darkGray);
        table1.setForeground(Color.white);

        model.setColumnIdentifiers(new String[]{"idMeal", "strMeal", "strCategory", "strArea", "Views"});
        //model.addRow(new Object[],DBfunctions.getStrArea());
        //Create Table header
        Object[] datahd = {"idMeal", "strMeal", "strCategory", "strArea", "Views"};
        model.addRow(datahd);
        //Read all data from database ordered by descending VIEWSTATS
        DBfunctions.selectAll();
        //Object[] datadt = {"1","stra","strb","strc","2"};
        Object[] datadt = {"1","stra","strb","strc","2"};
        model.addRow(datadt);

        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeScreen s1 = new HomeScreen();
                s1.setVisible(true);
            }
        });
        expPDFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}





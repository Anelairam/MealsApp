import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewDatabase extends JFrame{
    private JTable table1;
    private JButton viewDbBtn;
    private JButton expPDFBtn;
    private JButton goBackBtn;
    private JPanel ViewDatabase;
    private int idmeal, viewstats;
    private String strmeal, strcate, strarea ;



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
        //Create Table header
        Object[] datahd = {"idMeal", "strMeal", "strCategory", "strArea", "Views"};
        model.addRow(datahd);
        //Read all data from database ordered by descending VIEWSTATS
        DBfunctions.selectAll();
        //Εμφάνιση στον πίνακα
        Object[] datadt = new Object[5];
        for (int y = 0; y <= DBfunctions.result.size()-1; y++) {
            for (int x = 0; x <= 4; x++){
                datadt[x] = DBfunctions.result.get(y).get(x);
            }
            model.addRow(datadt);
        }
        goBackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == goBackBtn){
                    dispose();
                    HomeScreen s1 = new HomeScreen();
                    s1.setVisible(true);
                }
            }
        });
    }

}





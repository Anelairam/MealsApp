import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;


public class ViewDatabase extends JFrame{
    private JTable table1;
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

        //Create Table model
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        table1.setBackground(Color.darkGray);
        table1.setForeground(Color.white);

        model.setColumnIdentifiers(new String[]{"idMeal", "strMeal", "strCategory", "strArea", "Views"});

        TableColumnModel tcm = table1.getColumnModel();
        tcm.getColumn(1).setPreferredWidth(300);
        //Create Table header
        Object[] datahd = {"idMeal", "strMeal", "strCategory", "strArea", "Views"};
        model.addRow(datahd);
        //Read all data from database ordered by descending VIEWSTATS
        DBfunctions.selectAll();
        //Display query results on JTable
        Object[] datadt = new Object[5];
        for (int y = 0; y <= DBfunctions.result.size()-1; y++) {
            for (int x = 0; x <= 4; x++){
                datadt[x] = DBfunctions.result.get(y).get(x);
            }
            model.addRow(datadt);
        }

        //Exit and GoBack to Homescreen
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

        //Export JTable to PDF - filename: meals.pdf
        expPDFBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (actionEvent.getSource() == expPDFBtn){
                    try {
                       Document document = new Document(PageSize.A4.rotate(), 20.0f, 20.0f, 10.0f, 20.0f);
                        PdfWriter.getInstance(document, new FileOutputStream("meals.pdf"));
                        document.open();
                        PdfPTable tab = new PdfPTable(5);
                        for (int y = 0; y <= DBfunctions.result.size() - 1; y++) {
                            for (int x = 0; x <= 4; x++) {
                                Object obj = table1.getModel().getValueAt(y, x);
                                String value = obj.toString();
                                tab.addCell(value);
                            }
                        }
                        document.add(tab);
                        document.close();
                        System.out.println("pdf saved");
                        JOptionPane.showMessageDialog(null,"Export to PDF (meals.pdf) -  Done! ");
                    }
                    catch(Exception e){}
                }
            }
        });
    }
}





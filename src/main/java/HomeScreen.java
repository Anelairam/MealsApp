import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HomeScreen extends JFrame{
    private JPanel Main;
    private JButton mealsDataButton;
    private JButton listOfMealsPerButton;
    private JButton statisticsOfMealsDataButton;
    private JButton exitButton;
    private JLabel imageLogo;
    private JLabel infoLogo;

    public HomeScreen() {
//      Initializating the JFrame/GUI of the class
        setContentPane(Main);
        setTitle("Meals App");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  Navigation button to meal search functionality
        mealsDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == mealsDataButton) {
                    dispose();
                    searchByName searchName = new searchByName();
                    searchName.setVisible(true);
                }
            }
        });

//  Navigation button to meals selected by category functionality
        listOfMealsPerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             if(e.getSource()==listOfMealsPerButton){
                 dispose();
                 AvailableCategories s2=new AvailableCategories();
                 s2.setVisible(true);
             }
            }
        });

//  Navigation button to statistics and exporting functionality
        statisticsOfMealsDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==statisticsOfMealsDataButton){
                    dispose();

                    ViewDatabase vd = new ViewDatabase();
                    vd.setVisible(true);
                }
            }
        });

//  Exit button, application termination
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Exit");

                if (JOptionPane.showConfirmDialog( frame,"Confirm if you want to Exit","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    System.exit(0);
                    dispose();
                }
            }
        });
        infoLogo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog( null, "Μέλη ομάδας: Κεραμίδας Βασίλειος, Λιβαθηνοπούλου Μαριαλένα, Πέππας Παναγιώτης" );
            }
        });
    }

//  Method for inserting the logo to the main page
    private void createUIComponents() {
        imageLogo = new JLabel(new ImageIcon("image.png"));
        infoLogo = new JLabel(new ImageIcon("icon-info.png"));
    }
}

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

    public HomeScreen() {
        setContentPane(Main);
        setTitle("Meals App");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    statisticsOfMealsDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
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

    }

    public static void main(String[] args) {
        HomeScreen s1 = new HomeScreen();
    }

    //Μέθοδος για εισαγωγή εικόνας
    private void createUIComponents() {
        //Εισαγωγή εικόνας
        imageLogo = new JLabel(new ImageIcon("image.png"));
    }
}

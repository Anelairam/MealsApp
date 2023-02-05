import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setSize(600,500);
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
            dispose();
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

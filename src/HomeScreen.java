import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame{
    private JPanel Main;
    private JButton mealsDataButton;
    private JButton listOfMealsPerButton;
    private JButton statisticsOfMealsDataButton;
    private JButton exitButton;

public HomeScreen() {
    mealsDataButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

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

        }
    });
}

    public static void main(String[] args) {
        HomeScreen s1 = new HomeScreen();
        s1.setContentPane(s1.Main);
        s1.setTitle("Meals App");
        s1.setSize(300,400);
        s1.setVisible(true);
        s1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

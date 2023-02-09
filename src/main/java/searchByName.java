import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchByName extends JFrame{
    private JPanel nameSearchPanel;
    private JLabel miniLogo;
    private JButton goBack;
    private JLabel nameSearch;
    private JTextField nameSearchValue;
    private JButton searchButton;
    private JList list1;
    private JButton saveData;

    public searchByName(){
        setContentPane(nameSearchPanel);
        setTitle("Search meals by name");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == goBack){
                    dispose();
                    HomeScreen s1 = new HomeScreen();
                    s1.setVisible(true);
                }
            }
        });
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        //Εισαγωγή εικόνας
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }
}


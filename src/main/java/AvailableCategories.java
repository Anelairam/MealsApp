import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AvailableCategories extends JFrame {
    private JPanel KategoryList;
    private JLabel Logo;
    private JComboBox selectBox;
    private JLabel categoryLabel;
    private JList mealList;
    private JButton goBack;

    public AvailableCategories(){
        setContentPane(KategoryList);
        setTitle("Category List");
        setSize(650,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        selectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                CategoryListCall category = new CategoryListCall();
//                ViewList.setListData(CategoryListCall.getCategoryResults().toArray());
                mealList.setVisible(true);
            }
        });
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeScreen s1 = new HomeScreen();
                s1.setVisible(true);
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        Logo= new JLabel(new ImageIcon("mini_image.png"));
    }
}

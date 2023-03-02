import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AvailableCategories extends JFrame {
    private JPanel CategoryList;
    private JLabel Logo;
    private JComboBox selectBox;
    private JLabel categoryLabel;
    private JList mealNameList;
    private JButton goBack;
    private JButton searchByNameBtn;

    private String userSelection;

    private int screenId;
    public AvailableCategories(){
        screenId=3;
        setContentPane(CategoryList);
        setTitle("Category List");
        setSize(650,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CategoryListCall category = new CategoryListCall();
        for (int i=0; i<category.getCategoryResults().size(); i++){
                selectBox.addItem(CategoryListCall.getCategoryResults().get(i));
        }

        selectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MealByCategoryCall categoryName = new MealByCategoryCall(selectBox.getSelectedItem().toString());
//             selectBox.getSelectedItem();
                mealNameList.setVisible(true);
                mealNameList.setListData(MealByCategoryCall.getMealNameResults().toArray());
            }
        });

        mealNameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userSelection = mealNameList.getSelectedValue().toString();
                searchByNameBtn.setVisible(true);
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
        searchByNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                searchByName search = new searchByName();
                search.setComingScreenId(screenId);
                search.setVisible(true);
                search.setApiCall(userSelection);
                search.setSearchValue(userSelection);
            }
        });
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        Logo= new JLabel(new ImageIcon("mini_image.png"));
    }
}

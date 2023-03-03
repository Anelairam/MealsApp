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
//  Assisting variables for path navigation and
//  connections through screens
    private String userSelection;
    private int selectedMealIndex;
    private int cScreenId = 3;

    public AvailableCategories() {
//      Initializating the JFrame/GUI of the class
        setContentPane(CategoryList);
        setTitle("Category List");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      Calling the CategoryListCall
        CategoryListCall category = new CategoryListCall();
//      Populating the selectBox with the choices from the API call
//      created to get the available categories
        for (int i = 0; i < category.getCategoryResults().size(); i++) {
            selectBox.addItem(CategoryListCall.getCategoryResults().get(i));
        }

//      Creating listener for the comboBox
        selectBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              Passing the user's category choice to the MealByCategoryCall
                MealByCategoryCall categoryName = new MealByCategoryCall(selectBox.getSelectedItem().toString());
                mealNameList.setVisible(true);
//              Generate the list of meals based on the category that the user chose before
                mealNameList.setListData(MealByCategoryCall.getMealNameResults().toArray());
            }
        });
//      Saving the user's choices into variables and enables extra button
//      when the user has selected the meal of interest
        mealNameList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userSelection = mealNameList.getSelectedValue().toString();
                searchByNameBtn.setVisible(true);
                selectedMealIndex=mealNameList.getSelectedIndex();
            }
        });
//      Navigation button, redirecting the user to the main page
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                HomeScreen s1 = new HomeScreen();
                s1.setVisible(true);
            }
        });
//      Navigation button connecting the category list view with the
//      search meal by name view based on the user's choices. Passing the
//      user's choices to the search by name view for navigation purpose.
        searchByNameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                searchByName search = new searchByName();
                search.setComingScreen(cScreenId);
                search.setVisible(true);
                search.setApiCall(userSelection);
                search.setSearchValue(userSelection);
                search.setCategoryAndIndex(selectBox.getSelectedItem().toString(), selectedMealIndex);
            }
        });
    }
//  Adding mini logo to the GUI
    private void createUIComponents() {
        // TODO: place custom component creation code here
        Logo = new JLabel(new ImageIcon("mini_image.png"));
    }

//  Navigation assisting method receiving the user's choice
//  which were made in first place
    public void setComingCategory(String value, int index) {
        CategoryListCall category = new CategoryListCall();
        selectBox.setSelectedItem(value);
        mealNameList.setSelectedIndex(index);
    }
}

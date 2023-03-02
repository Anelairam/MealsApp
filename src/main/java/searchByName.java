import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class searchByName extends JFrame{
    private JPanel nameSearchPanel;
    private JLabel miniLogo;
    private JButton goBack;
    private JLabel nameSearch;
    private JTextField nameSearchValue;
    private JButton searchButton;
    private JList displayView;
    private JButton viewDBButton;
    private JScrollPane scroll;
    private JButton showDetails;
    private String searchValue;

    public searchByName(){
        setContentPane(nameSearchPanel);
        setTitle("Search meals by name");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayView.clearSelection();
                searchValue = nameSearchValue.getText();
                NameSearchCall search = new NameSearchCall(searchValue);
                displayView.setListData(NameSearchCall.get_meal_results().toArray());
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


        displayView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //System.out.println("mouse click displayview" + displayView.getSelectedIndex());
                MealItems.mealList.get(displayView.getSelectedIndex());
                int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                showDetails.setVisible(true);

                //System.out.println("-->" + idm);
            }
        });

        //view database contents on the terminal
        viewDBButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DBfunctions.selectAll();
            }
        });

        //show detail button
        showDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                int idM = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                boolean found = false;
                DBfunctions.selectMeal(idM, found);
                if (DBfunctions.getfound() == true) {
                    System.out.println("Found in db from search btn");
                    String strM = DBfunctions.getStrMeal();
                    String strA = DBfunctions.getStrCategory();
                    String strC = DBfunctions.getStrArea();
                    String strI = DBfunctions.getStrInstructions();
                    MealsInfo meal = new MealsInfo();
                    boolean sedit = false;
                    meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
                    meal.setSearchValue(searchValue);
                    meal.setVisible(true);
                }
                else{
                    System.out.println("Not found in db from search btn");
                    String strM = MealItems.mealList.get(displayView.getSelectedIndex()).getStrmeal();
                    String strA = MealItems.mealList.get(displayView.getSelectedIndex()).getStrarea();
                    String strC = MealItems.mealList.get(displayView.getSelectedIndex()).getStrcat();
                    String strI = MealItems.mealList.get(displayView.getSelectedIndex()).getStrinstr();
                    boolean sedit = false;
                    System.out.println();
                    MealsInfo meal = new MealsInfo();
                    meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
                    meal.setSearchValue(searchValue);
                    meal.setVisible(true);
                }

            }
        });

    }

    private void createUIComponents() {
        //Εισαγωγή εικόνας
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }

    public void setSearchValue(String value){
        nameSearchValue.setText(value);
    }

    public void setApiCall(String value){
        NameSearchCall secSearch = new NameSearchCall(value);
        displayView.setListData(secSearch.get_meal_results().toArray());
    }
}


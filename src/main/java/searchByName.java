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
    private JScrollPane scroll;
    private JButton showDetails;
    private String searchValue;
    private int comingScreen;
    private String comingCat;
    private int comingIndex;
    private int screenId = 1;

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
                    if (comingScreen == 3){
                        dispose();
                        AvailableCategories c1 = new AvailableCategories();
                        c1.setVisible(true);
                        c1.setComingCategory(comingCat, comingIndex);
                    }
                    else {
                        dispose();
                        HomeScreen s1 = new HomeScreen();
                        s1.setVisible(true);
                    }
                }
            }
        });


        displayView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                MealItems.mealList.get(displayView.getSelectedIndex());
                int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                showDetails.setVisible(true);
            }
        });

        //show detail button
        showDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                int idM = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                boolean found = false;
                DBfunctions.selectMeal(idM, found, screenId);
                if (DBfunctions.getfound() == true) {
                    String strM = DBfunctions.getStrMeal();
                    String strA = DBfunctions.getStrCategory();
                    String strC = DBfunctions.getStrArea();
                    String strI = DBfunctions.getStrInstructions();
                    DBfunctions.UpdateViewStats(idM);
                    MealsInfo meal = new MealsInfo();
                    boolean sedit = false;
                    meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
                    meal.setComingScreen(comingScreen);
//                    meal.setSearchValue(searchValue);
                    meal.setVisible(true);
                    meal.setIndex(comingIndex);
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
                    meal.setComingScreen(comingScreen);
//                    meal.setSearchValue(searchValue);
                    meal.setVisible(true);
                    meal.setIndex(comingIndex);
                }

            }
        });

//      On enter key pressed into the Jtextfiled for name search
//      a new API call based on the user's input value is made.
        nameSearchValue.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    displayView.clearSelection();
                    searchValue = nameSearchValue.getText();
                    NameSearchCall search = new NameSearchCall(searchValue);
                    displayView.setListData(NameSearchCall.get_meal_results().toArray());
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
        displayView.setSelectedIndex(0);
        showDetails.setVisible(true);
    }

    public int getComingScreen(){ return comingScreen;}
    public void setComingScreen(int value){ comingScreen = value;}
    public void setCategoryAndIndex(String value, int index){
        comingCat = value;
        comingIndex = index;
    }
}


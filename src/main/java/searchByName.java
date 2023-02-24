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
    private JButton saveData;
    private JButton deleteData;
    private JButton editMealButton;
    private JButton viewDBButton;
    private JScrollPane scroll;
    private JButton showDetails;

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
                String searchField = nameSearchValue.getText();
                NameSearchCall search = new NameSearchCall(searchField);
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

        //save data to DB
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (displayView.getSelectedIndex() >= 0 ){
                    int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                    String strm = MealItems.mealList.get(displayView.getSelectedIndex()).getStrmeal();
                    String stra = MealItems.mealList.get(displayView.getSelectedIndex()).getStrarea();
                    String strc = MealItems.mealList.get(displayView.getSelectedIndex()).getStrcat();
                    String stri = MealItems.mealList.get(displayView.getSelectedIndex()).getStrinstr();
                    DBfunctions.insertNewMeal(idm, strm, stra, strc, stri );
                    System.out.println("save db");
                }
            }
        });

        //
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

        //button delete meal from db
        deleteData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame frame = new JFrame("Delete");

                if (JOptionPane.showConfirmDialog( frame,"Delete Meal from the database","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                    DBfunctions.DeleteNewMeal (idm);

                }
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

        editMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == editMealButton) {
 //                       dispose();
                        JFrame frame = new JFrame("Edit");
                        if (JOptionPane.showConfirmDialog( frame,"Edit Meal ?","MealsAPP",
                                JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                            int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                            boolean found = false;
                            DBfunctions.selectMeal(idm, found);
                            int idM = DBfunctions.getIdMeal();
                            String strM = DBfunctions.getStrMeal();
                            String strA = DBfunctions.getStrCategory();
                            String strC = DBfunctions.getStrArea();
                            String strI = DBfunctions.getStrInstructions();
                            boolean sedit = true;
                            System.out.println();

                            if (DBfunctions.getfound() == true) {
                                MealsInfo meal = new MealsInfo();
                                meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
                                meal.setVisible(true);
                            }
                        }
                    }

            }
        });

        //show detail button
        showDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idM = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                String strM = MealItems.mealList.get(displayView.getSelectedIndex()).getStrmeal();
                String strA = MealItems.mealList.get(displayView.getSelectedIndex()).getStrarea();
                String strC = MealItems.mealList.get(displayView.getSelectedIndex()).getStrcat();
                String strI = MealItems.mealList.get(displayView.getSelectedIndex()).getStrinstr();
                boolean sedit = false;
                System.out.println();
                MealsInfo meal = new MealsInfo();
                meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
                meal.setVisible(true);
            }
        });

    }

    private void createUIComponents() {
        //Εισαγωγή εικόνας
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }
}


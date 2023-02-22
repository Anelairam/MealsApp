import javax.swing.*;
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
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchField = nameSearchValue.getText();
                nameSearchCall search = new nameSearchCall(searchField);
                displayView.setListData(nameSearchCall.get_meal_results().toArray());
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
                JFrame frame = new JFrame("Exit");

                if (JOptionPane.showConfirmDialog( frame,"Delete Meal from the database","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                    int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                    DBfunctions.DeleteNewMeal (idm);

                }
            }
        });
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
                        int idm = MealItems.mealList.get(displayView.getSelectedIndex()).getIdmeal();
                        DBfunctions.selectMeal(idm);
                        MealViewData mvd = new MealViewData();
                        mvd.setVisible(true);
                    }

            }
        });
        showDetails.addActionListener(new ActionListener() {
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


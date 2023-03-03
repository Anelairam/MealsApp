import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealsInfo extends JFrame{
    private JPanel mealsInfo;
    private JLabel idMea;
    private JLabel miniLogo;
    private JLabel strName;
    private JLabel strCat;
    private JLabel strArea;
    private JLabel strInst;
    private JTextField idMeal;
    private JTextField strMeal;
    private JTextField strCate;
    private JTextField strMealArea;
    private JTextArea strInstructions;
    private JButton btnBack;
    private JButton saveData;
    private JButton editBtn;
    private JButton deleteBtn;
//  Assisting variables for navigation
    private int index;
    private int comingScreen;
    private int mScreenId=2;

    public MealsInfo() {
//      Initializating the JFrame/GUI of the class
        setContentPane(mealsInfo);
        setTitle("Meal Information");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        strInstructions.setLineWrap(true);

//      Navigation button to search by name screen
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                searchByName s1 = new searchByName();
                s1.setVisible(true);
                s1.setSearchValue(strMeal.getText());
                s1.setApiCall(strMeal.getText());
                s1.setComingScreen(comingScreen);
                s1.setCategoryAndIndex(strCate.getText(), index);
            }
        });

//      Database button, saving the recipe's data into the database.
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//              Requesting user confirmation for saving the recipe's data
//              for a new recipe or updating an existing one.
                JFrame frame = new JFrame("Update");
                if (JOptionPane.showConfirmDialog( frame,"Save changes to Database?","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
//                  For possitive user reposnse, the system checks if the data exist in the DB.
                    int idm = Integer.valueOf(idMeal.getText());
                    boolean found = false;
                    DBfunctions.selectMeal(idm, found);
                    if (DBfunctions.getfound() == true) {
//                      if the data exists, they system updates the db's data.
                        String strm = strMeal.getText();
                        String stra = strMealArea.getText();
                        String strc = strCate.getText();
                        String stri = strInstructions.getText();
                        DBfunctions.UpdateMeal(idm, strm, stra, strc, stri);
//                      Disables editable fields.
                        strMeal.setEditable(false);
                        strCate.setEditable(false);
                        strMealArea.setEditable(false);
                        strInstructions.setEditable(false);
//                      Updating visually the fields that changed.
                        strMeal.setText(strMeal.getText());
                        strCate.setText(strCate.getText());
                        strMealArea.setText(strMealArea.getText());
                        strInstructions.setText(strInstructions.getText());
                        System.out.println("update db");
                    }else {
//                      if the data doesn't exist, the system saves the information in the db.
                        String strm = strMeal.getText();
                        String stra = strMealArea.getText();
                        String strc = strCate.getText();
                        String stri = strInstructions.getText();
                        DBfunctions.insertNewMeal(idm, strm, stra, strc, stri );
//                      System.out.println("save db"); ------------------------------->to delete
                    }
                }
            }
        });

//      Database button, editing the recipe's data into the database if they exist.
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editBtn) {
//                  Requesting user confirmation for editing the recipe's data
//                  if the recipe already exists in the database.
                    JFrame frame = new JFrame("Edit");
//                  For possitive user reposnse, the system checks if the data exist in the DB.
                    if (JOptionPane.showConfirmDialog( frame,"Edit Meal ?","MealsAPP",
                            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                        int idm = Integer.valueOf(idMeal.getText());
                        boolean found = false;
                        DBfunctions.selectMeal(idm, found);
//                      If the data exists in the db, the system enables the name, category,
//                      origin and instructions for editing.
                        if (DBfunctions.getfound() == true) {
                            strMeal.setEditable(true);
                            strCate.setEditable(true);
                            strMealArea.setEditable(true);
                            strInstructions.setEditable(true);
                        }
                    }
                }
            }
        });

//      Database button, removing the recipe and its detail from the system.
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//              Requesting user confirmation for deleting the recipe's data
//              if the recipe already exists in the database.
                JFrame frame = new JFrame("Delete");
                if (JOptionPane.showConfirmDialog( frame,"Delete Meal from the database","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    System.out.println("You pressed delete");
                    int idm = Integer.valueOf(idMeal.getText());
                    boolean found = false;
//                  The system function locating the recipe if it exists in the database.
                    DBfunctions.selectMeal(idm, found);
                    if (DBfunctions.getfound() == true) {
                        DBfunctions.DeleteNewMeal(idm);
                    }else{
                        System.out.println("Meal not found in DB");
                    }
                }
            }
        });
    }

//  Method that enables the editing fields
    public void setMealInfo(int idm, String name, String area, String category, String inst, boolean sedit){
        if (sedit == true){
            strMeal.setEditable(true);
            strCate.setEditable(true);
            strMealArea.setEditable(true);
            strInstructions.setEditable(true);
        }
        idMeal.setText(String.valueOf(idm));
        strMeal.setText(name);
        strCate.setText(category);
        strMealArea.setText(area);
        strInstructions.setText(inst);
    };

    private void createUIComponents() {
        // TODO: place custom component creation code here
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }

//  Navigation assisting methods.
//  Method for receiving the user's path.
    public void setComingScreen(int value){
        comingScreen = value;
    }
//  Method for receiving the user's choice from the meals received
//  from the category choice.
    public void setIndex(int comingIndex){
        index = comingIndex;
    }
}

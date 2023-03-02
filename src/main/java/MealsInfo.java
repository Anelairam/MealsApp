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

    private String userSearchValue;

    private int screenId;

    public MealsInfo() {
        setContentPane(mealsInfo);
        setTitle("Meal Information");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        strInstructions.setLineWrap(true);
        screenId = 2;
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                searchByName s1 = new searchByName();
                s1.setVisible(true);
                s1.setSearchValue(getSearchValue());
                s1.setApiCall(getSearchValue());
            }
        });
        saveData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Update");

                if (JOptionPane.showConfirmDialog( frame,"Save changes to Database?","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
//                    Firstly checks if the data exist in the DB
                    int idm = Integer.valueOf(idMeal.getText());
                    boolean found = false;
                    DBfunctions.selectMeal(idm, found);
                    if (DBfunctions.getfound() == true) {
                        String strm = strMeal.getText();
                        String stra = strMealArea.getText();
                        String strc = strCate.getText();
                        String stri = strInstructions.getText();
                        DBfunctions.UpdateMeal(idm, strm, stra, strc, stri);
//                    Disable editable fields
                        strMeal.setEditable(false);
                        strCate.setEditable(false);
                        strMealArea.setEditable(false);
                        strInstructions.setEditable(false);
//                    Updating visually the fields that changed
                        strMeal.setText(strMeal.getText());
                        strCate.setText(strCate.getText());
                        strMealArea.setText(strMealArea.getText());
                        strInstructions.setText(strInstructions.getText());
                        System.out.println("update db");
                    }else {
                        String strm = strMeal.getText();
                        String stra = strMealArea.getText();
                        String strc = strCate.getText();
                        String stri = strInstructions.getText();
                        DBfunctions.insertNewMeal(idm, strm, stra, strc, stri );
                        System.out.println("save db");
                    }


                }
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editBtn) {
//                    dispose();
                    JFrame frame = new JFrame("Edit");
                    if (JOptionPane.showConfirmDialog( frame,"Edit Meal ?","MealsAPP",
                            JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                        int idm = Integer.valueOf(idMeal.getText());
                        boolean found = false;
                        System.out.println("Before Id : "+ idm + " Found : " + found);
                        DBfunctions.selectMeal(idm, found);
                        int idM = DBfunctions.getIdMeal();
                        String strM = DBfunctions.getStrMeal();
                        String strA = DBfunctions.getStrCategory();
                        String strC = DBfunctions.getStrArea();
                        String strI = DBfunctions.getStrInstructions();
                        boolean sedit = true;
                        System.out.println();

                        if (DBfunctions.getfound() == true) {
                            strMeal.setEditable(true);
                            strCate.setEditable(true);
                            strMealArea.setEditable(true);
                            strInstructions.setEditable(true);
//                            MealsInfo meal = new MealsInfo();
//                            meal.setMealInfo(idM, strM, strA, strC, strI, sedit);
//                            meal.setVisible(true);
                        }
                    }
                }
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Delete");
                if (JOptionPane.showConfirmDialog( frame,"Delete Meal from the database","MealsAPP",
                        JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    System.out.println("You pressed delete");
                    int idm = Integer.valueOf(idMeal.getText());
                    boolean found = false;
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

    public void setSearchValue(String searchValue){
        userSearchValue = searchValue;
    }

    public String getSearchValue(){
        return userSearchValue;
    }
}

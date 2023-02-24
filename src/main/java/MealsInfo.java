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

    private String userSearchValue;


    public MealsInfo() {
        setContentPane(mealsInfo);
        setTitle("Meal Information");
        setSize(650,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        strInstructions.setLineWrap(true);
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
                    int idm = Integer.valueOf(idMeal.getText());
                    String strm = strMeal.getText();
                    String stra = strMealArea.getText();
                    String strc = strCate.getText();
                    String stri = strInstructions.getText();
                    DBfunctions.UpdateMeal(idm, strm, stra, strc, stri);
                    System.out.println("update db");
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

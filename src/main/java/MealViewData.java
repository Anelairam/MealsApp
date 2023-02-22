import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealViewData extends JFrame {
    private JPanel mealViewPanel;
    private JTextField idmeal;
    private JTextField strmeal;
    private JTextField strCategory;
    private JTextField strArea;
    private JTextField strInstructions;
    private JButton saveButton;
    private JButton goBack;
    private JLabel miniLogo;
    private JLabel id;
    private JLabel mealName;
    private JLabel mealCat;
    private JLabel mealArea;
    private JLabel mealInst;
    private JTextArea textAreaInst;
    private JScrollPane scroll;


    public MealViewData() {
        setContentPane(mealViewPanel);
        setTitle("View Meal");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        idmeal.setText(String.valueOf(DBfunctions.getIdMeal()));
        strmeal.setText(DBfunctions.getStrMeal());
        strCategory.setText(DBfunctions.getStrCategory());
        strArea.setText(DBfunctions.getStrArea());
        textAreaInst.setText(DBfunctions.getStrInstructions());
        textAreaInst.setLineWrap(true);


        //System.out.println(DBfunctions.getIdMeal()+"-"+DBfunctions.getStrMeal()+"-"+DBfunctions.getStrCategory()+"-"+DBfunctions.getStrArea()+"-"+DBfunctions.getStrInstructions());
        goBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == goBack) {
                    dispose();
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }
}

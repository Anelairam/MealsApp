import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MealsInfo extends JFrame{
    private JPanel mealsInfo;
    private JLabel miniLogo;
    private JLabel strName;
    private JLabel strCat;
    private JLabel strArea;
    private JLabel strInst;
    private JTextField strMeal;
    private JTextField strCate;
    private JTextField strMealArea;
    private JTextArea strInstructions;
    private JButton btnBack;

    public MealsInfo() {
        setContentPane(mealsInfo);
        setTitle("Meal Information");
        setSize(600,500);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        strInstructions.setLineWrap(true);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

        public void setMealInfo(String name, String area, String category, String inst){
            strMeal.setText(name);
            strCate.setText(category);
            strMealArea.setText(area);
            strInstructions.setText(inst);
        };

    private void createUIComponents() {
        // TODO: place custom component creation code here
        miniLogo = new JLabel(new ImageIcon("mini_image.png"));
    }
}

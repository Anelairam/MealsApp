import java.util.List;

public class MealItems implements IGiveMealList{
    private int idmeal;
    private String strmeal;
    private String strarea;
    private String strcat;
    private String strinstr;

    static List <MealItems> mealList = nameSearchCall.getMealList();
    public MealItems(int idMeal, String strMeal, String strArea, String strCategory, String strInstructions) {
        this.idmeal = idMeal;
        this.strmeal = strMeal;
        this.strarea = strArea;
        this.strcat = strCategory;
        this.strinstr = strInstructions;
    }

    public int getIdmeal() {
        return idmeal;
    }

    public String getStrmeal() {
        return strmeal;
    }

    public String getStrarea() {
        return strarea;
    }

    public String getStrcat() {
        return strcat;
    }

    public String getStrinstr() {
        return strinstr;
    }

    @Override
    public List<MealItems> getMealList() {
        return mealList;
    }

}

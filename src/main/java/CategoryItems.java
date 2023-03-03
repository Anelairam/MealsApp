import java.util.List;

public class CategoryItems {
    private int idcategory;
    private String strcategory;

    static List<CategoryItems> catList = CategoryListCall.getCategoryList();

    public CategoryItems(int idCategory, String strCategory){
        this.idcategory = idCategory;
        this.strcategory = strCategory;
    }
    public int getIdcategory(){return idcategory;}
    public String getStrcategory(){return strcategory;}
}

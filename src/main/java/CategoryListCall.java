import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryListCall {
    static List<String> category_name = new ArrayList<>();
    static List<CategoryItems> categoryList = new ArrayList<>();

    public CategoryListCall() {
        String CategoryUrl = "https://www.themealdb.com/api/json/v1/1/categories.php";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(CategoryUrl).build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Response : " + response);
            if (response.isSuccessful() && response.body() != null) {
                String responseString = response.body().string();
                System.out.println(responseString);
                //ppep 11.02.23 - Create Json
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();

                JsonObject json = gson.fromJson(responseString, JsonObject.class);
                System.out.println(json);

                JsonArray categoriesArray = json.get("categories").getAsJsonArray();

                //initialize
                category_name.clear();
                categoryList.clear();
                //Input results from json
                for (int i = 0 ; i<= categoriesArray.size()-1;i++) {
                    JsonElement jsonElement = categoriesArray.get(i);
                    JsonObject m = jsonElement.getAsJsonObject();
                    category_name.add(m.get("strCategory").getAsString());
                    CategoryItems catitem = new CategoryItems(m.get("idCategory").getAsInt(), m.get("strCategory").getAsString());
                    categoryList.add(catitem);
                }

            } else {
                System.out.println("Not found : " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static  List<String> getCategoryResults() {return category_name;}

    public static  List<CategoryItems> getCategoryList(){return categoryList;}
}
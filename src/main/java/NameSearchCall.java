import java.io.IOException;
import java.util.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.*;

import javax.swing.*;


public class NameSearchCall {
    //Create list to save results
    static List<String> meal_name = new ArrayList<>();
    static List<MealItems> mealList = new ArrayList<>();

    public NameSearchCall(String userEntry) {
//      Setting up the API call to receive the available categories
        String baseUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String urlToCall = baseUrl + userEntry;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlToCall).build();

//      API response handling
        try (Response response = client.newCall(request).execute()) {
//          System.out.println("Response : " + response); ------------------------------->to delete
            if (response.isSuccessful() && response.body() != null) {
                String responseString = response.body().string();
//              System.out.println(responseString); ------------------------------->to delete
//              Creating JSON
                GsonBuilder builder = new GsonBuilder();
                builder.setPrettyPrinting();
                Gson gson = builder.create();

                JsonObject json = gson.fromJson(responseString, JsonObject.class);
//              System.out.println(json); ------------------------------->to delete

                JsonArray mealsArray = null;
                if (json.get("meals").isJsonNull()) {
                    //System.out.println("Data not found"); ------------------------------->to delete
                    JOptionPane.showMessageDialog(null, "Data not found");
                } else {
                    mealsArray = json.get("meals").getAsJsonArray();
                }
                //System.out.println(mealsArray); ------------------------------->to delete

                //initialize
                meal_name.clear();
                mealList.clear();
                //Input results from json
                for (int i = 0; i <= mealsArray.size() - 1; i++) {
                    JsonElement jsonElement = mealsArray.get(i);
                    JsonObject m = jsonElement.getAsJsonObject();
                    meal_name.add(m.get("strMeal").getAsString());
                    //              System.out.printf(m.get("Meals")); ------------------------------->to delete
                    //        List <MealItems> mealList = nameSearchCall. ------------------------------->?????
//                  Creating a new object of MealItems
                    MealItems mlitm = new MealItems
                            (m.get("idMeal").getAsInt(),
                                    m.get("strMeal").getAsString(),
                                    m.get("strArea").getAsString(),
                                    m.get("strCategory").getAsString(),
                                    m.get("strInstructions").getAsString());

//                  Adding the new object into the Meal List
                    mealList.add(mlitm);
                    //    System.out.println("-->" + mealList.get(i).getIdmeal()); ------------------------------->to delete
                    //    System.out.println("-->" + mealList.get(i).getStrmeal()); ------------------------------->to delete
                    //    System.out.println("-->" + mealList.get(i).getStrarea()); ------------------------------->to delete
                    //    System.out.println("-->" + mealList.get(i).getStrcat()); ------------------------------->to delete
                    //    System.out.println("-->" + mealList.get(i).getStrinstr()); ------------------------------->to delete
                    //    System.out.println("-->" + mealList); ------------------------------->to delete

                    //    System.out.println(i + "-" + meal_name.get(i)); ------------------------------->to delete
                }

            } else {
                System.out.println("Not found : " + response);

            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static List<String> get_meal_results() {
        return meal_name;
    }

    public static List<MealItems> getMealList() {
        return mealList;

    }

}

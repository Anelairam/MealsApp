import java.io.IOException;
import java.util.*;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.*;

import javax.swing.*;


public class nameSearchCall {

    //Create list to save results
    static List<String> meal_name = new ArrayList<>();
    static List<MealItems> mealList = new ArrayList<>();

    public nameSearchCall(String userEntry) {
        String baseUrl = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
        String urlToCall = baseUrl + userEntry;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlToCall).build();


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

                JsonArray mealsArray = json.get("meals").getAsJsonArray();
                //System.out.println(mealsArray);

                //initialize
                meal_name.clear();
                mealList.clear();
                //Input results from json
                for (int i = 0 ; i<= mealsArray.size()-1;i++) {
                    JsonElement jsonElement=mealsArray.get(i);
                    JsonObject m = jsonElement.getAsJsonObject();
                    meal_name.add(m.get("strMeal").getAsString());

          //        List <MealItems> mealList = nameSearchCall.
                    MealItems mlitm = new MealItems
                            (m.get("idMeal").getAsInt(),
                            m.get("strMeal").getAsString(),
                            m.get("strArea").getAsString(),
                            m.get("strCategory").getAsString(),
                            m.get("strInstructions").getAsString());

                    mealList.add(mlitm);
                //    System.out.println("-->" + mealList.get(i).getIdmeal());
                //    System.out.println("-->" + mealList.get(i).getStrmeal());
                //    System.out.println("-->" + mealList.get(i).getStrarea());
                //    System.out.println("-->" + mealList.get(i).getStrcat());
                //    System.out.println("-->" + mealList.get(i).getStrinstr());
                //    System.out.println("-->" + mealList);

                //    System.out.println(i + "-" + meal_name.get(i));
                } //ppep 11.02.23 - Create Json

            } else {
                System.out.println("Not found : " + response);

            }
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null,"Data not found");
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

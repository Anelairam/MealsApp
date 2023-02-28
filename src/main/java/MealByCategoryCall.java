import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealByCategoryCall {

    static List<String> meal_name = new ArrayList<>();

    public MealByCategoryCall(String category){
        String baseUrl = "https://www.themealdb.com/api/json/v1/1/filter.php?c=";
        String urlToCall = baseUrl + category;
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

                JsonArray mealsArray = null;
                if (json.get("meals").isJsonNull()) {
                    //System.out.println("Data not found");
                    JOptionPane.showMessageDialog(null, "Data not found");
                } else {
                    mealsArray = json.get("meals").getAsJsonArray();
                }

                meal_name.clear();
                for (int i = 0; i <= mealsArray.size() - 1; i++) {
                    JsonElement jsonElement = mealsArray.get(i);
                    JsonObject m = jsonElement.getAsJsonObject();
                    meal_name.add(m.get("strMeal").getAsString());
                }

        } else {
            System.out.println("Not found : " + response);

        }
    } catch (IOException e) {

        e.printStackTrace();
    }
}
    public static List<String> getMealNameResults() {
        return meal_name;
    }
}
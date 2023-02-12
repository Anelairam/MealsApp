import com.google.gson.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
public class CategoryListCall {
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
                //GsonBuilder builder = new GsonBuilder();
                //builder.setPrettyPrinting();
                //Gson gson = builder.create();

                //JsonObject json = gson.fromJson(responseString, JsonObject.class);
                //System.out.println(json);

                //JsonArray mealsArray = json.get("meals").getAsJsonArray();
                //System.out.println(mealsArray);

                //initialize
                //meal_name.clear();
                //Input results from json
                //for (int i = 0 ; i<= mealsArray.size()-1;i++) {
                // JsonElement jsonElement=mealsArray.get(i);
                // JsonObject m = jsonElement.getAsJsonObject();
                // meal_name.add(m.get("strMeal").getAsString()) ;
                //           System.out.println(i + "-" + meal_name.get(i));
                //}          //ppep 11.02.23 - Create Json

            } else {
                System.out.println("Not found : " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
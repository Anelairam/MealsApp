import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class nameSearchCall {

    public static void main(String[] args) {
        String urlToCall = "https://www.themealdb.com/api/json/v1/1/search.php?s=Arrabiata";
        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder().url(urlToCall).build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println("Response : " + response);
            if (response.isSuccessful() && response.body() != null) {
                String responseString=response.body().string();
                System.out.println(responseString);
            }
            else {
                System.out.println("Not found : " + response);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}
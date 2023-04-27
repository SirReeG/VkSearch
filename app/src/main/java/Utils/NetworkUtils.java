package Utils;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public static final String VK_API_BASE_URL = "https://api.vk.com";
    public static final String VK_USERS_GET = "/method/users.get";
    public static final String USER_ID = "user_Ids";
    public static final String VERSION = "v";
    public static final String ACCESS_TOKEN = "access_token";


    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
       try{
        InputStream in = urlConnection.getInputStream();
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\\A");

        boolean hasInput = scanner.hasNext();
        if(hasInput) {
            return scanner.next();
        }
        else {
             return null;
        }
       }
       finally {
           urlConnection.disconnect();
       }


        }

    public static URL generateURL (String userId)

    {

        Uri buildUri = Uri.parse(VK_API_BASE_URL + VK_USERS_GET)
                .buildUpon()
                .appendQueryParameter(USER_ID,userId)
                .appendQueryParameter(ACCESS_TOKEN,"326bced3326bced3326bced37c31780ad83326b326bced35622f995cb0fc83770252454")
                .appendQueryParameter(VERSION,"5.131")
                .build();

        URL url = null;
        try {
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
         return url;

    }

}

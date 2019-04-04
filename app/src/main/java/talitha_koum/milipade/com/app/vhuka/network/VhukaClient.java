package talitha_koum.milipade.com.app.vhuka.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TALITHA_KOUM on 3/4/2019.
 * file for the  vhuka. project
 * in talitha_koum.milipade.com.app.vhuka.network
 */
public class VhukaClient {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

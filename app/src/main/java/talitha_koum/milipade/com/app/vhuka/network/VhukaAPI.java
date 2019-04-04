package talitha_koum.milipade.com.app.vhuka.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import talitha_koum.milipade.com.app.vhuka.models.Photos;

/**
 * Created by TALITHA_KOUM on 3/4/2019.
 * file for the  vhuka. project
 * in talitha_koum.milipade.com.app.vhuka.network
 */
public interface VhukaAPI {

    @GET("photos/{age}")
    Call<Photos> getPhoto(@Path("age") int age);
}

package talitha_koum.milipade.com.app.vhuka;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import talitha_koum.milipade.com.app.vhuka.models.Photos;
import talitha_koum.milipade.com.app.vhuka.network.VhukaAPI;
import talitha_koum.milipade.com.app.vhuka.network.VhukaClient;

public class ImageActivity extends AppCompatActivity implements Callback<Photos> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String age= getIntent().getStringExtra("AGE");
        VhukaAPI API = VhukaClient.getClient().create(VhukaAPI.class);

        Call<Photos> call = API.getPhoto(Integer.parseInt(age));
        call.enqueue(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onResponse(Call<Photos> call, Response<Photos> response) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        ImageView thump = (ImageView) findViewById(R.id.imageView2);
        TextView title = findViewById(R.id.textView);
        title.setText(response.body().getTitle());
        Glide.with(this).load(response.body().getUrl()).into(imageView);
        Glide.with(this).load(response.body().getThumbnailUrl()).into(thump);
    }

    @Override
    public void onFailure(Call<Photos> call, Throwable t) {

    }
}

package talitha_koum.milipade.com.app.vhuka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
    ImageView imageView,thump;
    TextView title;
    private String fullScreenInd;
    String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        age= getIntent().getStringExtra("AGE");
        VhukaAPI API = VhukaClient.getClient().create(VhukaAPI.class);


        imageView = (ImageView) findViewById(R.id.imageView);
       thump = (ImageView) findViewById(R.id.imageView2);
        title = findViewById(R.id.textView);

        fullScreenInd = getIntent().getStringExtra("fullScreenIndicator");
        if ("y".equals(fullScreenInd)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();

            imageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            imageView.setAdjustViewBounds(false);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }



        Call<Photos> call = API.getPhoto(Integer.parseInt(age));
        call.enqueue(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ImageActivity.this, ImageActivity.class);

                if("y".equals(fullScreenInd)){
                    intent.putExtra("fullScreenIndicator", "");
                    intent.putExtra("AGE", age);
                }else{
                    intent.putExtra("AGE", age);
                    intent.putExtra("fullScreenIndicator", "y");
                }
                ImageActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onResponse(Call<Photos> call, Response<Photos> response) {

        title.setText(response.body().getTitle());
        Glide.with(this).load(response.body().getUrl()).into(imageView);
        Glide.with(this).load(response.body().getThumbnailUrl()).into(thump);
    }

    @Override
    public void onFailure(Call<Photos> call, Throwable t) {

    }
}

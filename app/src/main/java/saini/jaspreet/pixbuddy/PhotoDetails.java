package saini.jaspreet.pixbuddy;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.Gson;

import java.util.Objects;

import saini.jaspreet.pixbuddy.databinding.ActivityPhotoDetailsBinding;
import saini.jaspreet.pixbuddy.models.SinglePhoto;
import saini.jaspreet.pixbuddy.viewmodels.PhotosViewModel;

public class PhotoDetails extends AppCompatActivity {
    ActivityPhotoDetailsBinding activityPhotoDetailsBinding;
    public final static String SINGLE_PHOTO = "SINGLE_PHOTO";
    private SinglePhoto singlePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("photoTitle"));
        }

        activityPhotoDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_details);
        singlePhoto = new Gson().fromJson(getIntent().getStringExtra(SINGLE_PHOTO), SinglePhoto.class);
        activityPhotoDetailsBinding.setViewmodel(new PhotosViewModel(singlePhoto));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

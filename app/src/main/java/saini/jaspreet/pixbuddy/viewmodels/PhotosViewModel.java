package saini.jaspreet.pixbuddy.viewmodels;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import saini.jaspreet.pixbuddy.PhotoDetails;
import saini.jaspreet.pixbuddy.models.SinglePhoto;

public class PhotosViewModel extends BaseObservable {
    private SinglePhoto singlePhoto;

    public PhotosViewModel(SinglePhoto pixabayImage) {
        this.singlePhoto = pixabayImage;
    }

    public String getViews() {
        return singlePhoto.getViews();
    }

    public String getDownloads() {
        return singlePhoto.getDownloads();
    }

    public String getTags() {
        return singlePhoto.getTags();
    }

    public String getImageUrl() {
        return singlePhoto.getPreviewURL();
    }

    public String getHighResImageUrl() {
        return singlePhoto.getWebformatURL();
    }

    public String getLikes() {
        return singlePhoto.getLikes();
    }

    public String getComments() {
        return singlePhoto.getComments();
    }

    public String getFavorites() {
        return singlePhoto.getFavorites();
    }

    public String getUserName() {
        return singlePhoto.getUser();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public View.OnClickListener openDetails() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), PhotoDetails.class);
                String serialized = new Gson().toJson(singlePhoto);
                i.putExtra(PhotoDetails.SINGLE_PHOTO, serialized);
                i.putExtra("photoTitle", singlePhoto.getTags());
                v.getContext().startActivity(i);
            }
        };
    }
}

package saini.jaspreet.pixbuddy.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import saini.jaspreet.pixbuddy.R;
import saini.jaspreet.pixbuddy.databinding.PhotoListItemBinding;
import saini.jaspreet.pixbuddy.models.SinglePhoto;
import saini.jaspreet.pixbuddy.viewmodels.PhotosViewModel;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {

    private List<SinglePhoto> photosList;

    public PhotosAdapter(List<SinglePhoto> photosList) {
        this.photosList = photosList;
    }
    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PhotosAdapter.PhotosViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder photosViewHolder, int position) {
        photosViewHolder.activityMainBinding.setViewmodel(new PhotosViewModel(photosList.get(position)));
    }

    @Override
    public int getItemCount() {
        return photosList.size();
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder{
        public final PhotoListItemBinding activityMainBinding;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            activityMainBinding =PhotoListItemBinding.bind(itemView);
        }
    }
}

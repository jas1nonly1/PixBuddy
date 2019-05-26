package saini.jaspreet.pixbuddy;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import saini.jaspreet.pixbuddy.adapters.PhotosAdapter;
import saini.jaspreet.pixbuddy.api.CheckInternetConnectivity;
import saini.jaspreet.pixbuddy.api.FetchPhotosService;
import saini.jaspreet.pixbuddy.models.SinglePhoto;

public class MainActivity extends AppCompatActivity {
    TextView noResultsTxtView;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    //Query string for the category of photos to be fetched
    String queryString = "landscapes";
    SinglePhoto photoresponse = null;
    List<SinglePhoto> photosList;
    PhotosAdapter photosAdapter;
    MenuItem searchMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noResultsTxtView = findViewById(R.id.noResultsTxtView);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        photosList = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        photosAdapter = new PhotosAdapter(photosList);
        recyclerView.setAdapter(photosAdapter);

        //Fetch photos only if internet is working, otherwise display message in Snackbar
        if(CheckInternetConnectivity.internetActivelyAvailable(getApplicationContext())){
            progressBar.setVisibility(View.VISIBLE);
            fetchPhotos(queryString);
        }
        else {
            Snackbar.make(recyclerView, R.string.no_internet_msg, Snackbar.LENGTH_LONG).show();
        }
    }

    private void fetchPhotos(String query){
        //Fetch photos using all configuration data and paramters
        FetchPhotosService.photosApiConfig().getAllPhotos(FetchPhotosService.API_KEY, query, 1, 40)
                .enqueue(new Callback<SinglePhoto>() {
                    @Override
                    public void onResponse(@NonNull Call<SinglePhoto> call, @NonNull Response<SinglePhoto> response) {
                        if(response.isSuccessful()){
                            photoresponse = response.body();
                            progressBar.setVisibility(View.GONE);
                            int position = photosList.size();
                            assert response.body() != null;
                            photosList.addAll(response.body().getHits());
                            photosAdapter.notifyItemRangeInserted(position, position + 40);
                            if (photosList.isEmpty()) noResultsTxtView.setVisibility(View.VISIBLE);
                            else noResultsTxtView.setVisibility(View.GONE);
                        }else{
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SinglePhoto> call, @NonNull Throwable t) {
                        Snackbar.make(recyclerView, R.string.fetch_error, Snackbar.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(searchListener);
        return true;
    }

    private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String textQuery) {
            searchMenuItem.collapseActionView();
            queryString = textQuery;
            photosList.clear();
            photosAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.VISIBLE);
            noResultsTxtView.setVisibility(View.GONE);
            fetchPhotos(queryString);
            return true;
        }
        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

}

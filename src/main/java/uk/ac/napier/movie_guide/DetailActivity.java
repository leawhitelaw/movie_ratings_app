package uk.ac.napier.movie_guide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

/**
 * Created by leawhitelaw on 09/03/2018.
 */

public class DetailActivity extends AppCompatActivity{
    TextView nameOfMovie, plotSynopsis, userRating, releaseDate;
    ImageView imageView;

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView = findViewById(R.id.thumbnail_img_header);
        nameOfMovie = findViewById(R.id.title);
        plotSynopsis = findViewById(R.id.plotSynopsis);
        userRating = findViewById(R.id.userRating);
        releaseDate = findViewById(R.id.releaseDate);

        Intent initialIntent = getIntent();
        if(initialIntent.hasExtra("original_title")){
            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String synopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .placeholder(R.drawable.loading)
                    .into(imageView);
            nameOfMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userRating.setText(rating);
            releaseDate.setText(dateOfRelease);
        }
        else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }
    }



        private void initCollapsingToolbar(){
            final CollapsingToolbarLayout collapsingToolbarLayout =
                    findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle("");
            AppBarLayout appBarLayout = findViewById(R.id.appbar);
            appBarLayout.setExpanded(true);


            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShowing = false;
                int scrollRange = -1;

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset){
                    if(scrollRange == -1){
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if(scrollRange + verticalOffset == 0){
                        collapsingToolbarLayout.setTitle(getString(R.string.movie_details));
                        isShowing = true;
                    }
                    else if (isShowing){
                        collapsingToolbarLayout.setTitle("");
                        isShowing = false;
                    }
                }
            });
    }

}

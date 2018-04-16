package com.example.ga.movieapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Movie> {


    private static final int Details_LOADER_ID = 2;
    VideosAdapter vAdapter;
    ReviewsAdapter rAdapter;


    private String movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_d);
        Intent intent=getIntent();
        String ReceiveTitle =intent.getStringExtra("title");
        String ReceiveOverView =intent.getStringExtra("overView");
        String ReceiveRating =intent.getStringExtra("UserRating");
        String ReceiveDate =intent.getStringExtra("ReleaseDate");
        String ReceivePoster =intent.getStringExtra("MoviePoster");
        String ReceiveMovieID = intent.getStringExtra("MovieID");

        movieId =
                "https://api.themoviedb.org/3/movie/"+ReceiveMovieID+"?api_key=*******&append_to_response=videos,reviews";
        ListView videoList = (ListView)findViewById(R.id.list_video);
        vAdapter = new VideosAdapter(this,new ArrayList<Videos>());
        videoList.setAdapter(vAdapter);

        ListView ReviewList = (ListView)findViewById(R.id.list_review);
        rAdapter = new ReviewsAdapter(this,new ArrayList<Reviews>());
        ReviewList.setAdapter(rAdapter);

        LoaderManager loaderManager=getLoaderManager();
        loaderManager.initLoader(Details_LOADER_ID,null,this);


        TextView MovieName=(TextView)findViewById(R.id.MovieName);
        MovieName.setText(ReceiveTitle);
        TextView MovieOverView=(TextView)findViewById(R.id.overview);
        MovieOverView.setText(ReceiveOverView);
        TextView MovieRating=(TextView)findViewById(R.id.rating);
        MovieRating.setText(ReceiveRating);
        TextView MovieDate=(TextView)findViewById(R.id.date);
        MovieDate.setText(ReceiveDate);
        TextView MovieID =(TextView)findViewById(R.id.movieID);
        MovieID.setText(ReceiveMovieID);
        ImageView MoviePoster =(ImageView)findViewById(R.id.poster);
        Picasso.with(this)
                .load(ReceivePoster)
                .into(MoviePoster);

    }

    @Override
    public Loader<Movie> onCreateLoader(int i, Bundle bundle) {
        ReviewsAndVideosLoader reviewsAndVideosLoader = new ReviewsAndVideosLoader(this,movieId );
        return reviewsAndVideosLoader;
    }

    @Override
    public void onLoadFinished(Loader<Movie> loader, Movie movie) {
        //TODO update the adapter with movie.getReviews and movie.getVideos
        vAdapter.addAll(movie.getVideos());
        rAdapter.addAll(movie.getReviews());
    }

    @Override
    public void onLoaderReset(Loader<Movie> loader) {

    }
}

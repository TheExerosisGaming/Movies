package me.exerosis.nanodegree.movies.implementation.controller.scaffolding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.nanodegree.movies.implementation.controller.details.container.MovieDetailsContainerActivity;
import me.exerosis.nanodegree.movies.implementation.controller.movies.MoviesFragment;
import me.exerosis.nanodegree.movies.implementation.model.Movie;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffolding;
import me.exerosis.nanodegree.movies.implementation.view.scaffolding.AppScaffoldingView;

public class AppScaffoldingActivity extends AppCompatActivity implements AppScaffoldingController {
    private AppScaffolding view;

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        view = new AppScaffoldingView(this);

        MoviesFragment movies = (MoviesFragment) getSupportFragmentManager().
                findFragmentByTag(MoviesFragment.class.getName());

        if (movies == null) {
            movies = new MoviesFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(view.getFragmentContainerID(), movies, MoviesFragment.class.getName())
                    .disallowAddToBackStack().commit();
        }

        movies.setListener(this);
    }

    @Override
    public void onClick(Movie movie) {
        startActivity(new Intent(this, MovieDetailsContainerActivity.class).putExtra(MovieDetailsContainerActivity.ARG_MOVIE, movie));
    }
}
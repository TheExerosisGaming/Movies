package me.exerosis.nanodegree.movies;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.nanodegree.movies.databinding.FragmentMovieListBinding;


public class MovieListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Collection<Movie>>, SwipeRefreshLayout.OnRefreshListener {
    private FragmentMovieListBinding binding;
    private List<Movie> movies = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_list, container, false);

        //Listeners
      //  binding.swipeRefreshLayout.setOnRefreshListener(this);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this, new IntentFilter("url-change"));

        //RecyclerView
        binding.movieList.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.movie_list_columns)));
        binding.movieList.addItemDecoration(new ItemOffsetDecoration(getContext(), R.dimen.movie_list_item_offset));
        binding.movieList.setAdapter(new RecyclerView.Adapter<MovieListCard>() {
            @Override
            public MovieListCard onCreateViewHolder(ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new MovieListCard(DataBindingUtil.inflate(inflater, R.layout.movie_card, parent, false));
            }

            @Override
            public void onBindViewHolder(MovieListCard holder, int position) {
                holder.displayMovie(movies.get(position));
            }

            @Override
            public int getItemCount() {
                return movies.size();
            }
        });

        //View
        return binding.getRoot();
    }


    @Override
    public Loader<Collection<Movie>> onCreateLoader(int id, Bundle args) {
        return new MovieListLoader(this.getContext(), args);
    }

    @Override
    public void onLoadFinished(Loader<Collection<Movie>> loader, Collection<Movie> data) {
        movies = (List<Movie>) data;
        binding.movieList.getAdapter().notifyDataSetChanged();
        //binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Movie>> loader) {

    }

    @Override
    public void onRefresh() {

    }
}
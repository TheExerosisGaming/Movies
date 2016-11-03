package me.exerosis.nanodegree.movies.implementation.view.details;

import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import me.exerosis.nanodegree.movies.R;
import me.exerosis.nanodegree.movies.databinding.MovieDetailsViewBinding;
import me.exerosis.nanodegree.movies.implementation.model.Details;

public class MovieDetailsView implements MovieDetails {
    private final MovieDetailsViewBinding binding;

    public MovieDetailsView(LayoutInflater inflater, final ViewGroup parent) {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_view, parent, false);
    }


    @Override
    public void setDetails(Details details) {
        binding.movieDetailsTitle.setText(details.getMovie().getTitle());
        binding.movieDetailsTagline.setText(details.getTagline());
        binding.movieDetailsDescription.setText(details.getDescription());

        binding.movieDetailsDate.setText(details.getDate());
        binding.movieDetailsGenres.setText(details.getGenres());
        binding.movieDetailsRuntime.setText(details.getRuntime());
        binding.movieDetailsVoteAverage.setText(details.getVoteAverage());
        binding.movieDetailsPopularity.setText(details.getPopularity());

        Picasso.with(getRootView().getContext()).load(details.getBackdropURL()).into(binding.movieDetailsBackdrop);

        Picasso.with(getRootView().getContext()).load(details.getMovie().getPosterURL()).into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.movieDetailsPoster.setImageBitmap(bitmap);
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        Palette.Swatch swatch = palette.getVibrantSwatch();
                        if (swatch != null) {
                            binding.movieDetailsFab.setBackgroundTintList(ColorStateList.valueOf(swatch.getRgb()));
                            binding.movieDetailsQuickLookBar.setBackgroundColor(swatch.getRgb());

                            binding.movieDetailsPopularity.setTextColor(swatch.getTitleTextColor());
                            binding.movieDetailsVoteAverage.setTextColor(swatch.getTitleTextColor());
                            binding.movieDetailsRuntime.setTextColor(swatch.getTitleTextColor());

                            binding.movieDetailsRuntimeTitle.setTextColor(swatch.getBodyTextColor());
                            binding.movieDetailsPopularityTitle.setTextColor(swatch.getBodyTextColor());
                            binding.movieDetailsVoteAverageTitle.setTextColor(swatch.getBodyTextColor());
                        }

                        binding.movieDetailsLayout.setVisibility(View.VISIBLE);
                        binding.movieDetailsSplashScreen.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}
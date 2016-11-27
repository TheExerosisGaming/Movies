package me.exerosis.nanodegree.movies.implementation.controller.details;

import android.support.v4.app.LoaderManager;

import me.exerosis.nanodegree.movies.implementation.model.data.Details;
import me.exerosis.nanodegree.movies.implementation.view.details.holder.DetailsListener;

public interface MovieDetailsController extends LoaderManager.LoaderCallbacks<Details>, DetailsListener {
}

package com.brianrobles.payoneer_exercise.features.checkout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.brianrobles.payoneer_exercise.network.CheckoutService;

/**
 * ViewModelFactory that can build a {@link CheckoutListViewModel}.
 *
 * Defaults to {@link ViewModelProvider.AndroidViewModelFactory} for other viewModels.
 */
public class CheckoutListViewModelFactory implements ViewModelProvider.Factory {
    private final Application application;
    private final CheckoutService service;

    public CheckoutListViewModelFactory(Application application, CheckoutService service) {
        this.application = application;
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CheckoutListViewModel.class)) {
            return (T) new CheckoutListViewModel(service);
        }

        return ViewModelProvider.AndroidViewModelFactory
                .getInstance(application)
                .create(modelClass);
    }
}

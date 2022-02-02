package com.brianrobles.payoneer_exercise.di;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.ViewModelProvider;

import com.brianrobles.payoneer_exercise.domain.models.ListResult;
import com.brianrobles.payoneer_exercise.features.checkout.CheckoutListViewModelFactory;
import com.brianrobles.payoneer_exercise.network.CheckoutService;
import com.brianrobles.payoneer_exercise.network.core.Parser;
import com.google.gson.Gson;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * App-specific container for dependencies.
 */
public class AppContainer {
    private Application application;

    public AppContainer(Application application) {
        this.application = application;
    }

    private final Executor executor = Executors.newFixedThreadPool(4);
    private final Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());

    private final Gson gson = new Gson();
    private final Parser<ListResult> listResultParser =
            json -> gson.fromJson(json, ListResult.class);

    public CheckoutService checkoutService =
            new CheckoutService(executor, handler, listResultParser);

    public ViewModelProvider.Factory checkoutListViewModelFactory =
            new CheckoutListViewModelFactory(application, checkoutService);
}

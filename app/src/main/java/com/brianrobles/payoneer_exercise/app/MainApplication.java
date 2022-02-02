package com.brianrobles.payoneer_exercise.app;

import android.app.Application;

import com.brianrobles.payoneer_exercise.di.AppContainer;
import com.brianrobles.payoneer_exercise.di.AppContainerHost;

/**
 * Main application class.
 *
 * Also hosts an {@link AppContainer} for holding application-wide dependencies.
 */
public class MainApplication extends Application implements AppContainerHost {
    private final AppContainer appContainer = new AppContainer(this);

    @Override
    public AppContainer getAppContainer() {
        return appContainer;
    }
}

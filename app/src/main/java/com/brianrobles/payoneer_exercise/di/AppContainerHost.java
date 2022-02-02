package com.brianrobles.payoneer_exercise.di;

import android.app.Application;

/**
 * Interface for classes which can host an {@link AppContainer}.
 *
 * This should typically be implemented by an {@link Application}.
 */
public interface AppContainerHost {
    AppContainer getAppContainer();
}

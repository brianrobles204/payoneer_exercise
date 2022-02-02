package com.brianrobles.payoneer_exercise.network.core;

/**
 * Callback interface for synchronous blocking functions used within {@link NetworkService}.
 *
 * @param <T> The type of the callback result
 */
public interface ExecuteCallback<T> {
    /**
     * Execute the synchronous blocking function. Implementations may also throw errors; the
     * user of this interface (the {@link NetworkService}) should be able to handle thrown errors.
     *
     * @return The callback result
     */
    T executeSync();
}
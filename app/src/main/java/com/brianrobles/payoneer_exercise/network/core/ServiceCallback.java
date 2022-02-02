package com.brianrobles.payoneer_exercise.network.core;

/**
 * Callback interface for handling the result of a service call.
 *
 * @param <T> The type of the callback result
 */
public interface ServiceCallback<T> {
    void onResult(Result<T> result);
}

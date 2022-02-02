package com.brianrobles.payoneer_exercise.network.core;

/**
 * Callback interface for parsing json text into object instances.
 *
 * @param <T> The type of the resulting object
 */
public interface Parser<T> {
    T parse(String json);
}

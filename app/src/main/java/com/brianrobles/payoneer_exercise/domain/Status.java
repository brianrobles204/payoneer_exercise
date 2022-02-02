package com.brianrobles.payoneer_exercise.domain;

/**
 * Holds the current status (loading, error, data) for a resource.
 *
 * @param <T> The type of the underlying resource data
 */
public class Status<T> {
    public static class Loading<T> extends Status<T> {}

    public static class Data<T> extends Status<T> {
        public final T data;

        public Data(T data) {
            this.data = data;
        }
    }

    public static class Error<T> extends Status<T> {
        public final Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }
    }
}

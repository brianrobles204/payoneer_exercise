package com.brianrobles.payoneer_exercise.network.core;

import com.brianrobles.payoneer_exercise.domain.Status;

/**
 * Class that holds the result of a network service call
 *
 * @param <T> Type of the underlying result data
 */
abstract public class Result<T> {
    public static class Success<T> extends Result<T> {
        public final T data;

        public Success(T data) {
            this.data = data;
        }

        @Override
        public Status<T> asStatus() {
            return new Status.Data<>(data);
        }
    }

    public static class Error<T> extends Result<T> {
        public final Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }

        @Override
        public Status<T> asStatus() {
            return new Status.Error<>(error);
        }
    }

    public abstract Status<T> asStatus();
}

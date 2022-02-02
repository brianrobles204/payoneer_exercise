package com.brianrobles.payoneer_exercise.network.core;

import android.os.Handler;

import com.brianrobles.payoneer_exercise.domain.models.ListResult;
import com.brianrobles.payoneer_exercise.network.core.Result;
import com.brianrobles.payoneer_exercise.network.core.ServiceCallback;

import java.util.concurrent.Executor;

/**
 * Service that contains methods for executing blocking methods, such as network calls.
 *
 * The service will use the given {@link #executor} for running the blocking code, and will
 * subsequently post the results to the given {@link #handler}.
 */
abstract public class NetworkService {
    private final Executor executor;
    private final Handler handler;

    public NetworkService(Executor executor, Handler handler) {
        this.executor = executor;
        this.handler = handler;
    }

    protected <T> void execute(
            Handler handler,
            ExecuteCallback<T> executeCallback,
            ServiceCallback<T> serviceCallback
    ) {
        Handler actualHandler = handler == null ? this.handler : handler;
        executor.execute(() -> {
            try {
                T result = executeCallback.executeSync();
                actualHandler.post(() -> serviceCallback.onResult(new Result.Success<T>(result)));
            } catch (Throwable e) {
                actualHandler.post(() -> serviceCallback.onResult(new Result.Error<T>(e)));
            }
        });
    }
}

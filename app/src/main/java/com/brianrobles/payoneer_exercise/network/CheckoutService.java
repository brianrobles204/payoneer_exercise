package com.brianrobles.payoneer_exercise.network;

import android.os.Handler;

import com.brianrobles.payoneer_exercise.domain.models.ListResult;
import com.brianrobles.payoneer_exercise.network.core.NetworkService;
import com.brianrobles.payoneer_exercise.network.core.Parser;
import com.brianrobles.payoneer_exercise.network.core.ServiceCallback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

import lombok.SneakyThrows;

/**
 * Network service that can download {@link ListResult} data from the assigned url.
 */
public class CheckoutService extends NetworkService {
    private final Parser<ListResult> listResultParser;

    public CheckoutService(Executor executor, Handler handler, Parser<ListResult> listResultParser) {
        super(executor, handler);
        this.listResultParser = listResultParser;
    }

    public static String LIST_RESULT_URL = "https://raw.githubusercontent.com/optile/" +
            "checkout-android/develop/shared-test/lists/listresult.json";

    public void listResult(ServiceCallback<ListResult> callback) {
        listResult(null, callback);
    }

    public void listResult(Handler handler, ServiceCallback<ListResult> callback) {
        execute(handler, this::syncListResult, callback);
    }

    @SneakyThrows
    private ListResult syncListResult() {
        URL url = new URL(LIST_RESULT_URL);
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
        StringBuilder builder = new StringBuilder();

        String output;
        while ((output = reader.readLine()) != null) {
            builder.append(output);
        }

        return listResultParser.parse(builder.toString());
    }
}
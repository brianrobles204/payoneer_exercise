package com.brianrobles.payoneer_exercise.features.checkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.brianrobles.payoneer_exercise.domain.Status;
import com.brianrobles.payoneer_exercise.domain.models.ListResult;
import com.brianrobles.payoneer_exercise.network.CheckoutService;
import com.brianrobles.payoneer_exercise.network.core.Result;

/**
 * Prepares and manages the {@link ListResult} data for a {@link CheckoutListActivity}.
 */
public class CheckoutListViewModel extends ViewModel {
    final CheckoutService service;

    public CheckoutListViewModel(CheckoutService service) {
        this.service = service;
    }

    private MutableLiveData<Status<ListResult>> listResult;

    public LiveData<Status<ListResult>> getListResult() {
        if (listResult == null) {
            listResult = new MutableLiveData<>();
            reloadListResult();
        }

        return listResult;
    }

    public void reloadListResult() {
        if (listResult.getValue() instanceof Status.Loading) {
            return;
        }

        listResult.setValue(new Status.Loading<>());
        service.listResult((Result<ListResult> result) -> listResult.setValue(result.asStatus()));
    }
}

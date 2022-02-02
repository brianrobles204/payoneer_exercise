package com.brianrobles.payoneer_exercise.features.checkout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.brianrobles.payoneer_exercise.databinding.ActivityCheckoutListBinding;
import com.brianrobles.payoneer_exercise.di.AppContainer;
import com.brianrobles.payoneer_exercise.di.AppContainerHost;
import com.brianrobles.payoneer_exercise.domain.Status;
import com.brianrobles.payoneer_exercise.domain.models.ApplicableNetwork;
import com.brianrobles.payoneer_exercise.domain.models.ListResult;

import java.util.List;

/**
 * Activity which displays an input with a list of supported payment codes downloaded from
 * the assigned data url.
 */
public class CheckoutListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // View model
        AppContainer container = ((AppContainerHost) getApplication()).getAppContainer();

        CheckoutListViewModel viewModel =
                new ViewModelProvider(this, container.checkoutListViewModelFactory)
                        .get(CheckoutListViewModel.class);

        // View binding
        ActivityCheckoutListBinding binding =
                ActivityCheckoutListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Adapter
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line);

        setupButton(binding, viewModel);
        binding.checkoutListAutocomplete.setAdapter(adapter);
        bindViewModelToView(binding, viewModel, adapter);
    }

    private void setupButton(ActivityCheckoutListBinding binding, CheckoutListViewModel viewModel) {
        binding.checkoutListButton.setOnClickListener((view) -> {
            Status<ListResult> status = viewModel.getListResult().getValue();

            if (status instanceof Status.Data) {
                ListResult data = ((Status.Data<ListResult>) status).data;
                String input = binding.checkoutListAutocomplete.getText().toString();

                ApplicableNetwork network = null;
                for (ApplicableNetwork applicable : data.getNetworks().getApplicable()) {
                    if (applicable.getCode().equals(input)) {
                        network = applicable;
                        break;
                    }
                }

                if (network != null) {
                    startActivity(CheckoutInputActivity.createIntent(this, network));
                } else {
                    new CheckoutErrorDialogFragment(input)
                            .show(getSupportFragmentManager(), "ERROR");
                }
            } else {
                new CheckoutErrorDialogFragment().show(getSupportFragmentManager(), "ERROR");
            }
        });
    }

    private void bindViewModelToView(
            ActivityCheckoutListBinding binding,
            CheckoutListViewModel viewModel,
            ArrayAdapter<String> adapter
    ) {
        viewModel.getListResult().observe(this, (status) -> {
            int loadingVisibility = status instanceof Status.Loading ? View.VISIBLE : View.INVISIBLE;
            binding.checkoutListProgress.setVisibility(loadingVisibility);

            int doneVisibility = !(status instanceof Status.Loading) ? View.VISIBLE : View.INVISIBLE;
            binding.checkoutListInputLayout.setVisibility(doneVisibility);
            binding.checkoutListButton.setVisibility(doneVisibility);

            binding.checkoutListAutocomplete.setEnabled(status instanceof Status.Data);
            binding.checkoutListButton.setEnabled(status instanceof Status.Data);

            int errorVisibility = status instanceof Status.Error ? View.VISIBLE : View.INVISIBLE;
            binding.checkoutListError.setVisibility(errorVisibility);

            adapter.clear();
            if (status instanceof Status.Data) {
                List<ApplicableNetwork> applicable =
                        ((Status.Data<ListResult>) status).data.getNetworks().getApplicable();
                String[] codes = new String[applicable.size()];

                for (int i = 0; i < applicable.size(); i++) {
                    codes[i] = applicable.get(i).getCode();
                }

                adapter.addAll(codes);
            }
        });
    }
}
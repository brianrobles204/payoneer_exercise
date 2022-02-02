package com.brianrobles.payoneer_exercise.features.checkout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.brianrobles.payoneer_exercise.databinding.ActivityCheckoutInputBinding;
import com.brianrobles.payoneer_exercise.domain.models.ApplicableNetwork;
import com.brianrobles.payoneer_exercise.features.utils.parcelables.ApplicableNetworkParcelable;

/**
 * Activity which displays a list of dynamically generated inputs for an applicable network.
 */
public class CheckoutInputActivity extends AppCompatActivity {

    private static final String EXTRA_INPUTS = "CheckoutInputActivity.inputs";

    static Intent createIntent(Context context, ApplicableNetwork applicableNetwork) {
        Intent intent = new Intent(context, CheckoutInputActivity.class);
        intent.putExtra(EXTRA_INPUTS, new ApplicableNetworkParcelable(applicableNetwork));

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCheckoutInputBinding binding =
                ActivityCheckoutInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ApplicableNetworkParcelable applicableNetwork =
                getIntent().getParcelableExtra(EXTRA_INPUTS);

        CheckoutInputAdapter adapter = new CheckoutInputAdapter();
        adapter.submitList(applicableNetwork.getInputElements());

        setSupportActionBar(binding.checkoutInputToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        binding.checkoutInputRecyclerView.setAdapter(adapter);
        binding.checkoutInputToolbarLayout.setTitle(applicableNetwork.getCode());
    }
}
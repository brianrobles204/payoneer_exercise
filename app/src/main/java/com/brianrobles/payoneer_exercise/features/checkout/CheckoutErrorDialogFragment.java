package com.brianrobles.payoneer_exercise.features.checkout;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.brianrobles.payoneer_exercise.R;

/**
 * Dialog fragment which displays an error message if payment codes are unavailable
 * or if a specific payment code was not found.
 */
public class CheckoutErrorDialogFragment extends DialogFragment {
    final String code;

    public CheckoutErrorDialogFragment() {
        this.code = null;
    }

    public CheckoutErrorDialogFragment(String code) {
        this.code = code;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        String message = code == null ?
                getString(R.string.checkout_error_unavailable) :
                getString(R.string.checkout_error_notfound, code);

        builder.setMessage(message)
                .setPositiveButton(R.string.action_ok, (dialog, id) -> dialog.dismiss());

        return builder.create();

    }
}

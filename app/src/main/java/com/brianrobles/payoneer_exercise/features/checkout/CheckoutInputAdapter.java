package com.brianrobles.payoneer_exercise.features.checkout;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.brianrobles.payoneer_exercise.databinding.ItemCheckoutInputBinding;
import com.brianrobles.payoneer_exercise.features.utils.parcelables.InputElementParcelable;

/**
 * RecyclerView adapter that maps an {@link InputElementParcelable} to an input edit text view.
 */
public class CheckoutInputAdapter
        extends ListAdapter<InputElementParcelable, CheckoutInputAdapter.ViewHolder> {
    protected CheckoutInputAdapter() {
        super(new InputElementDiffCallback());
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ItemCheckoutInputBinding binding;

        public ViewHolder(ItemCheckoutInputBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCheckoutInputBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InputElementParcelable inputElement = getItem(position);
        holder.binding.itemCheckoutTextInputLayout.setHint(inputElement.getName());
    }
}

class InputElementDiffCallback extends DiffUtil.ItemCallback<InputElementParcelable> {
    @Override
    public boolean areItemsTheSame(
            @NonNull InputElementParcelable oldItem,
            @NonNull InputElementParcelable newItem
    ) {
        return oldItem.getName().equals(newItem.getName());
    }

    @Override
    public boolean areContentsTheSame(
            @NonNull InputElementParcelable oldItem,
            @NonNull InputElementParcelable newItem
    ) {
        return oldItem.getName().equals(newItem.getName());
    }
}
package com.brianrobles.payoneer_exercise.features.utils.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import com.brianrobles.payoneer_exercise.domain.models.InputElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Parcelable class for holding {@link InputElement} data.
 *
 * Note that this class only holds a subset of InputElement data relevant to the
 * app's features.
 */
@Getter
@Setter
public class InputElementParcelable implements Parcelable {
    private String name;
    private String type;

    public InputElementParcelable(InputElement inputElement) {
        name = inputElement.getName();
        type = inputElement.getType();
    }

    protected InputElementParcelable(Parcel in) {
        name = in.readString();
        type = in.readString();
    }

    public static final Creator<InputElementParcelable> CREATOR = new Creator<InputElementParcelable>() {
        @Override
        public InputElementParcelable createFromParcel(Parcel in) {
            return new InputElementParcelable(in);
        }

        @Override
        public InputElementParcelable[] newArray(int size) {
            return new InputElementParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
    }
}

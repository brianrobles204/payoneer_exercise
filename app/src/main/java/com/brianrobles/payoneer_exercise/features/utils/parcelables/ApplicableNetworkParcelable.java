package com.brianrobles.payoneer_exercise.features.utils.parcelables;

import android.os.Parcel;
import android.os.Parcelable;

import com.brianrobles.payoneer_exercise.domain.models.ApplicableNetwork;
import com.brianrobles.payoneer_exercise.domain.models.InputElement;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Parcelable class for holding {@link ApplicableNetwork} data.
 *
 * Note that this class only holds a subset of ApplicableNetwork data relevant to the
 * app's features.
 */
@Getter
@Setter
public class ApplicableNetworkParcelable implements Parcelable {
    private String code;

    private List<InputElementParcelable> inputElements;

    public ApplicableNetworkParcelable(ApplicableNetwork applicableNetwork) {
        code = applicableNetwork.getCode();

        inputElements = new ArrayList<>();
        List<InputElement> networkElements = applicableNetwork.getInputElements();
        if (networkElements == null) networkElements = new ArrayList<>();
        for (InputElement inputElement : networkElements) {
            inputElements.add(new InputElementParcelable(inputElement));
        }
    }

    protected ApplicableNetworkParcelable(Parcel in) {
        code = in.readString();

        inputElements = new ArrayList<>();
        in.readTypedList(inputElements, InputElementParcelable.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeTypedList(inputElements);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ApplicableNetworkParcelable> CREATOR = new Creator<ApplicableNetworkParcelable>() {
        @Override
        public ApplicableNetworkParcelable createFromParcel(Parcel in) {
            return new ApplicableNetworkParcelable(in);
        }

        @Override
        public ApplicableNetworkParcelable[] newArray(int size) {
            return new ApplicableNetworkParcelable[size];
        }
    };
}

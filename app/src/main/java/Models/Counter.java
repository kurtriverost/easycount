package Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Counter implements Parcelable {

    private int counterId;
    private float counterResult;
    private float weighing;

    protected Counter(Parcel in) {
        counterId = in.readInt();
        counterResult = in.readFloat();
        weighing = in.readFloat();
    }

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }

    public float getCounterResult() {
        return counterResult;
    }

    public void setCounterResult(float counterResult) {
        this.counterResult = counterResult;
    }

    public float getWeighing() {
        return weighing;
    }

    public void setWeighing(float weighing) {
        this.weighing = weighing;
    }

    public static final Creator<Counter> CREATOR = new Creator<Counter>() {
        @Override
        public Counter createFromParcel(Parcel in) {
            return new Counter(in);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(counterId);
        dest.writeFloat(counterResult);
        dest.writeFloat(weighing);
    }
}

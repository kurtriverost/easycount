package Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserProfile implements Parcelable {

    private int userId;
    private String userName;
    private ArrayList<Counter> counterArrayList = new ArrayList<>();
    private Boolean isEmpty = true;


    protected UserProfile(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        counterArrayList = in.createTypedArrayList(Counter.CREATOR);
        byte tmpIsEmpty = in.readByte();
        isEmpty = tmpIsEmpty == 0 ? null : tmpIsEmpty == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeTypedList(counterArrayList);
        dest.writeByte((byte) (isEmpty == null ? 0 : isEmpty ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserProfile> CREATOR = new Creator<UserProfile>() {
        @Override
        public UserProfile createFromParcel(Parcel in) {
            return new UserProfile(in);
        }

        @Override
        public UserProfile[] newArray(int size) {
            return new UserProfile[size];
        }
    };
}

package com.neighborpil.sampleparcelabel;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    int number;
    String message;

    public SimpleData(int num, String msg) {
        this.number = num;
        this.message = msg;
    }

    public SimpleData(Parcel src) { // read from parcel
        number = src.readInt();
        message = src.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { // write to parcel
        dest.writeInt(number);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

package com.aliya.ipc;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * IpcRequest
 *
 * @author a_liYa
 * @date 2020/9/6 11:00.
 */
public class IpcRequest implements Parcelable {

    private String action;

    private String[] args;

    public IpcRequest(String action, String[] args) {
        this.action = action;
        this.args = args;
    }

    public String getAction() {
        if (action != null)
            return action;
        return null;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.action);
        dest.writeStringArray(this.args);
    }

    protected IpcRequest(Parcel in) {
        this.action = in.readString();
        this.args = in.createStringArray();
    }

    public static final Creator<IpcRequest> CREATOR = new Creator<IpcRequest>() {
        @Override
        public IpcRequest createFromParcel(Parcel source) {
            return new IpcRequest(source);
        }

        @Override
        public IpcRequest[] newArray(int size) {
            return new IpcRequest[size];
        }
    };
}
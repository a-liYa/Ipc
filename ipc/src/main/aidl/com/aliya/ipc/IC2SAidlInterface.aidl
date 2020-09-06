package com.aliya.ipc;

// Declare any non-default types here with import statements
import android.os.Parcelable;
import com.aliya.ipc.IpcRequest;

// 客户端向服务端通讯的 Aidl Interface
interface IC2SAidlInterface {

    void invoke(in IpcRequest request);

}

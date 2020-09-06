package com.aliya.ipc;

// Declare any non-default types here with import statements
import android.os.Parcelable;
import com.aliya.ipc.IpcRequest;

// 客户端向服务端通讯的 Aidl Interface
interface IpcAidlInterface {

    void invoke(in IpcRequest request);


    void connect(IpcAidlInterface aidlStub);
}

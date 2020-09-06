package com.aliya.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * IpcService
 *
 * @author a_liYa
 * @date 2020/9/6 13:12.
 */
public abstract class IpcService extends Service {

    private IpcAidlInterface mIpcLocal; // Ipc 本地 AIDL 引用

    @Override
    public final IBinder onBind(Intent intent) {
        return new IpcAidlInterface.Stub() {
            @Override
            public void invoke(IpcRequest request) throws RemoteException {
                if (request != null) {
                    onInvoke(request);
                }
            }

            @Override
            public void connect(IpcAidlInterface aidlStub) throws RemoteException {
                mIpcLocal = aidlStub;
            }
        };
    }

    // 本地调用远程进程方法
    protected abstract void onInvoke(IpcRequest request);

    // 调用本地进程方法
    protected void invokeLocal(IpcRequest request){
        if (mIpcLocal != null) {
            try {
                mIpcLocal.invoke(request);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}

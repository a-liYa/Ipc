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

    @Override
    public final IBinder onBind(Intent intent) {
        return new IC2SAidlInterface.Stub() {
            @Override
            public void invoke(IpcRequest request) throws RemoteException {
                if (request != null) {
                    onInvoke(request);
                }
            }
        };
    }

    protected abstract void onInvoke(IpcRequest request);
}

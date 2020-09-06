package com.aliya.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Channel 管道，用来本地与远程的相互通讯
 *
 * @author a_liYa
 * @date 2020/9/6 23:31.
 */
public class IpcChannel {

    private IpcLocal mIpcLocal;
    private IpcAidlInterface mIpcServiceStub;
    private IpcAidlInterface mIpcLocalStub;

    public IpcChannel(IpcLocal ipcLocal) {
        mIpcLocal = ipcLocal;
        mIpcLocalStub = new IpcAidlInterface.Stub() {
            @Override
            public void invoke(IpcRequest request) throws RemoteException {
                if (request != null)
                    mIpcLocal.onInvoke(request);
            }

            @Override
            public void connect(IpcAidlInterface aidlStub) throws RemoteException {
                // Nothing to do
            }
        };
    }

    void bindIpc(Context context, Class<? extends IpcService> service) {
        Intent intent = new Intent(context, service);
        context.bindService(intent, new IpcServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    public void invokeService(String action, String[] args) {
        if (mIpcServiceStub != null) {
            try {
                mIpcServiceStub.invoke(new IpcRequest(action, args));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    class IpcServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIpcServiceStub = IpcAidlInterface.Stub.asInterface(service);
            try {
                mIpcServiceStub.connect(mIpcLocalStub);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIpcServiceStub = null;
        }
    }

}

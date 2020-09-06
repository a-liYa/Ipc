package com.aliya.ipc.app;


import android.util.Log;

import com.aliya.ipc.IpcRequest;
import com.aliya.ipc.IpcService;

/**
 * 进程服务继承 IpcService
 *
 * @author a_liYa
 * @date 2020/9/6 14:09.
 */
public class MediaService extends IpcService {
    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onInvoke(IpcRequest request) {
        switch (request.getAction()) {
            default:
                Log.e("TAG", "onInvoke: " + request.getAction());
                break;
        }


    }
}

package com.aliya.ipc.app.media;


import android.util.Log;

import com.aliya.ipc.IpcRequest;
import com.aliya.ipc.IpcService;

/**
 * 远程服务继承 IpcService
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
                Log.e("TAG", "服务 onInvoke: " + request.getAction());
                invokeLocal(new IpcRequest(request.getAction() + "我来自远程进程", request.getArgs()));
                break;
        }
    }
}

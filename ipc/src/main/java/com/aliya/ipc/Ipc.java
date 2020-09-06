package com.aliya.ipc;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Ipc
 *
 * @author a_liYa
 * @date 2020/9/6 15:33.
 */
public final class Ipc {

    private static ConcurrentHashMap<Class<? extends IpcService>, IpcChannel>
            sIpcChannels = new ConcurrentHashMap<>();

    public static IpcChannel connect(Context context, Class<? extends IpcService> service,
                                     IpcLocal ipcLocal) {
        IpcChannel ipcChannel;
        if ((ipcChannel = sIpcChannels.get(service)) == null) {
            ipcChannel = new IpcChannel(ipcLocal);
            ipcChannel.bindIpc(context, service);
        }
        return ipcChannel;
    }

}

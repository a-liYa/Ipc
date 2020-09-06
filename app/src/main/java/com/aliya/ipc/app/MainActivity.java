package com.aliya.ipc.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.aliya.ipc.IC2SAidlInterface;
import com.aliya.ipc.IpcRequest;
import com.aliya.ipc.app.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IC2SAidlInterface c2sAidl;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        getApplication().bindService(new Intent(this, MediaService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                c2sAidl = IC2SAidlInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, BIND_AUTO_CREATE);


        mBinding.tvInvoke.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_invoke:
                try {
                    c2sAidl.invoke(new IpcRequest("action", null));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
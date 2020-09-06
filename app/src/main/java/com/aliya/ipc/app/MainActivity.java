package com.aliya.ipc.app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aliya.ipc.Ipc;
import com.aliya.ipc.IpcChannel;
import com.aliya.ipc.IpcLocal;
import com.aliya.ipc.IpcRequest;
import com.aliya.ipc.app.databinding.ActivityMainBinding;
import com.aliya.ipc.app.media.MediaService;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private IpcChannel mIpcChannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mIpcChannel = Ipc.connect(getApplication(), MediaService.class, new IpcLocal() {
            @Override
            public void onInvoke(IpcRequest request) {
                Log.e("TAG", "本地 onInvoke: " + request.getAction());
            }
        });

        mBinding.tvInvoke.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_invoke:
                mIpcChannel.invokeService("action", null);
                break;
        }
    }
}
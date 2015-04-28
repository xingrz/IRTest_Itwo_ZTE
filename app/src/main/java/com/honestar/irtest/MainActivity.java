package com.honestar.irtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uei.driver.IRBlasterConstants;
import com.uei.driver.SerialDataComm;

import java.io.IOException;


public class MainActivity extends Activity {
    public static final String TAG = "IRTest";

    byte[] mData = new byte[]{(byte) 0x53, (byte) 0x70, (byte) 0x43, (byte) 0x62, (byte) 0x00, (byte) 0x58, (byte) 0x23, (byte) 0x80, (byte) 0x00, (byte) 0x03,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x53, (byte) 0x05, (byte) 0x19, (byte) 0x2D, (byte) 0x00, (byte) 0x02, (byte) 0x54,
            (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x00,
            (byte) 0x02, (byte) 0x05, (byte) 0x8F, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2B, (byte) 0xFF,
            (byte) 0xFF, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2D, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xA4, (byte) 0x73,
            (byte) 0x1C, (byte) 0x8B, (byte) 0xBD, (byte) 0x84, (byte) 0xCD, (byte) 0xA3, (byte) 0x29, (byte) 0x19, (byte) 0x48, (byte) 0xCC,
            (byte) 0x69, (byte) 0xC2, (byte) 0x44, (byte) 0x23, (byte) 0x45, (byte) 0x09, (byte) 0x24, (byte) 0x94, (byte) 0xF7, (byte) 0xD7,
            (byte) 0x15, (byte) 0x30, (byte) 0x00, (byte) 0x5C, (byte) 0x60, (byte) 0x82, (byte) 0x51, (byte) 0x24, (byte) 0x94, (byte) 0x8A,
            (byte) 0x49, (byte) 0x24, (byte) 0x51, (byte) 0x25, (byte) 0x22, (byte) 0x8A, (byte) 0x48, (byte) 0x97, (byte) 0x2C, (byte) 0xA0,
            (byte) 0x10, (byte) 0xC8, (byte) 0xC0, (byte) 0x3F,};

    SerialDataComm _serialPort;
    private Button mBtnFail;
    private Button mBtnSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void initView() {
        mBtnFail = (Button) findViewById(R.id.btnFail);
        mBtnSuccess = (Button) findViewById(R.id.btnSuccess);
        findViewById(R.id.btnAutoTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIR();
                mBtnFail.setEnabled(true);
                mBtnSuccess.setEnabled(true);
            }
        });
        mBtnFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fail();
            }
        });
        mBtnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success();
            }
        });
    }

    private void fail() {
        setResult(3);
        finish();
    }

    private void success() {
        setResult(1);
        finish();
    }

    private void sendIR() {
        try {
            _serialPort = new SerialDataComm(IRBlasterConstants.SERIALPORT,
                    IRBlasterConstants.BAUDRATE);

            wakeup();
            if (_serialPort.writeBytes(mData)) {
                Log.d(TAG, "send successfully");
            } else {
                Log.d(TAG, "send fail");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wakeup() {
        if (_serialPort.writeBytes(IRBlasterConstants.NEVO_CMD_WAKEUP)) {
            Log.d(TAG, "wakeup successfully");
        } else {
            Log.d(TAG, "wakeup fail");
        }
        try {
            Thread.sleep(IRBlasterConstants.BLASTER_WAKEUP_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

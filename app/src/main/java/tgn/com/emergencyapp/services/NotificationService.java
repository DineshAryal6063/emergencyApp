package tgn.com.emergencyapp.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import tgn.com.emergencyapp.aquery.NotificationParsing;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class NotificationService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity
    Handler delayhandler;
    Runnable run;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mAccelerometer,
                SensorManager.SENSOR_DELAY_UI, new Handler());
        delayhandler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                runLoop();
            }
        };
        delayhandler.postDelayed(run, 5000);

        return START_STICKY;
    }

    public void runLoop() {

        if (!new PreferenceManager(this).getBoolean("me")) {
            new NotificationParsing(this).notificationFetch();
        }
        delayhandler.postDelayed(run, 5000);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta; // perform low-cut filter
        if (mAccel > 11) {
            final PreferenceManager preferenceManager = new PreferenceManager(this);
            Log.v("UserDataPref", preferenceManager.getInt("shakedTime") + "");
            if (preferenceManager.getInt("shakedTime") > 5) {
                preferenceManager.putBoolean("me", true);
                preferenceManager.putInt("shakedTime", 0);
                 new NotificationParsing(this).notificationPush(preferenceManager.getString("full_name"), 1);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        preferenceManager.putBoolean("me",false);
                    }
                },10*1000);
            } else {
                preferenceManager.putInt("shakedTime", preferenceManager.getInt("shakedTime") + 1);
            }
        }
    }

    /**
     * show notification when Accel is more then the given int.
     */
    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }
}

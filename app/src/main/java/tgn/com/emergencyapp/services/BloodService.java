package tgn.com.emergencyapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import tgn.com.emergencyapp.aquery.BloodParsing;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class BloodService extends Service {
    Handler delayhandler;
    Runnable run;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        delayhandler = new Handler();
        run = new Runnable() {
            @Override
            public void run() {
                runLoop();
            }
        };
        delayhandler.postDelayed(run, 7000);

        return START_STICKY;
    }

    public void runLoop() {

        if (!new PreferenceManager(this).getBoolean("meForBlood")) {
            new BloodParsing(this).bloodReqPull();
        }
        delayhandler.postDelayed(run, 7000);
    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }
}

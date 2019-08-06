package tgn.com.emergencyapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import tgn.com.emergencyapp.aquery.BloodParsing;
import tgn.com.emergencyapp.aquery.CommunityParsing;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class CommunityService extends Service {
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
        delayhandler.postDelayed(run, 5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new PreferenceManager(CommunityService.this).putBoolean("meAlertGenerate", false);
            }
        }, 10 * 1000);
        return START_STICKY;

    }

    public void runLoop() {
        if (!new PreferenceManager(this).getBoolean("meAlertGenerate")) {
            new CommunityParsing(this).communityAlertPull();
        }
        delayhandler.postDelayed(run, 5000);
    }

    @Override
    public void onDestroy() {
        stopSelf();
        super.onDestroy();
    }
}

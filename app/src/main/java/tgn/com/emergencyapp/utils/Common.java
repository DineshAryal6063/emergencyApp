package tgn.com.emergencyapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;

import java.util.Locale;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.activity.MainActivity;

public class Common {
    public static TextToSpeech mTextToSpeech;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;

        return false;
    }

    public static void showEmergencyNotification(Context context, String username) {
        final NotificationManager mgr = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder note = new NotificationCompat.Builder(context);
        note.setContentTitle(username + "Help! Help!! Help!!!");
        sayText(context, username + "is in Emergency");
        note.setTicker(username + "is in Emergency");
        note.setAutoCancel(true);
        // to set default sound/light/vibrate or all
        note.setDefaults(Notification.DEFAULT_ALL);
        // Icon to be set on Notification
        note.setSmallIcon(R.drawable.ic_launcher_background);
        // This pending intent will open after notification click
        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context,
                MainActivity.class), 0);
        // set pending intent to notification builder
        note.setContentIntent(pi);
        mgr.notify(101, note.build());
    }

    public static void showBloodNotification(Context context, String phone) {
        final NotificationManager mgr = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder note = new NotificationCompat.Builder(context);
        note.setContentTitle(phone + "needs your blood");
        note.setTicker(phone + "request for help");
        note.setAutoCancel(true);
        // to set default sound/light/vibrate or all
        note.setDefaults(Notification.DEFAULT_ALL);
        String[] ary = phone.split("");
        sayText(context, ary[0] + " " + ary[1] + " " + ary[2] + " " + ary[3] + " " + ary[4] + " " + ary[5] + " " + ary[6] + " " + ary[7] + " " + ary[8] + " " + ary[9] + " " + ary[10] + " needs your blood");
        // Icon to be set on Notification
        note.setSmallIcon(R.drawable.ic_launcher_background);
        // This pending intent will open after notification click
        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context,
                MainActivity.class), 0);
        // set pending intent to notification builder
        note.setContentIntent(pi);
        mgr.notify(101, note.build());
    }

    public static void showCommunityNotification(Context context, String username) {
        final NotificationManager mgr = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder note = new NotificationCompat.Builder(context);
        note.setContentTitle(username + "in Emergency");
        note.setTicker(username + "request for help");
        note.setAutoCancel(true);
        Uri alarmSound = Uri.parse("android.resource://" + context.getPackageName() + "/raw/mynoice");
        note.setSound(alarmSound);


        // to set default sound/light/vibrate or all
        //note.setDefaults(Notification.DEFAULT_ALL);
        // Icon to be set on Notification
        note.setSmallIcon(R.drawable.ic_launcher_background);
        // This pending intent will open after notification click
        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(context,
                MainActivity.class), 0);
        // set pending intent to notification builder
        note.setContentIntent(pi);
        mgr.notify(101, note.build());
    }

    public static void sayText(Context context, final String message) {

        mTextToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                try {
                    if (mTextToSpeech != null && status == TextToSpeech.SUCCESS) {
                        mTextToSpeech.setLanguage(Locale.US);
                        mTextToSpeech.speak(message, TextToSpeech.QUEUE_ADD, null);
                    }
                } catch (Exception ex) {
                    System.out.print("Error handling TextToSpeech GCM notification " + ex.getMessage());
                }
            }
        });
    }
}

package tgn.com.emergencyapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.services.NotificationService;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class GirlSafety extends AppCompatActivity {
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl_safety);
        Switch onOffSwitch = findViewById(R.id.btnswitch);
        preferenceManager = new PreferenceManager(this);
        if (preferenceManager.getBoolean("girlsafty")) {
            Intent intent = new Intent(GirlSafety.this, NotificationService.class);
            onOffSwitch.setChecked(true);
            startService(intent);
        }
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.v("Switch State=", "" + isChecked);
                Intent intent = new Intent(GirlSafety.this, NotificationService.class);
                if (isChecked) {
                    preferenceManager.putBoolean("girlsafty", true);
                    startService(intent);
                } else {
                    preferenceManager.putBoolean("girlsafty", false);
                    stopService(intent);
                }
            }

        });
    }

}

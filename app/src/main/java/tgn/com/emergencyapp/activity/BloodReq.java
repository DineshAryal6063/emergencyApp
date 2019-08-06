package tgn.com.emergencyapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.aquery.BloodParsing;
import tgn.com.emergencyapp.services.BloodService;
import tgn.com.emergencyapp.services.NotificationService;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class BloodReq extends AppCompatActivity {
    private Button btnRequest;
    private Spinner sp_bg;
    private PreferenceManager preferenceManager;
    private Switch btnswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_req);
        btnRequest = findViewById(R.id.btn_request);
        sp_bg = findViewById(R.id.spinner_blood);
        btnswitch=findViewById(R.id.btnswitch);
        preferenceManager = new PreferenceManager(this);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BloodParsing(BloodReq.this).bloodReqPush(preferenceManager.getString("phone"), sp_bg.getSelectedItem().toString());
            }
        });
        if (preferenceManager.getBoolean("bloodDonor")) {
            Intent intent = new Intent(BloodReq.this, BloodService.class);
            btnswitch.setChecked(true);
            startService(intent);
        }
        btnswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Intent intent = new Intent(BloodReq.this, BloodService.class);
                if (isChecked) {
                    preferenceManager.putBoolean("bloodDonor", true);
                    startService(intent);
                } else {
                    preferenceManager.putBoolean("bloodDonor", false);
                    stopService(intent);
                }
            }

        });
    }
}

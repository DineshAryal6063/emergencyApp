package tgn.com.emergencyapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.aquery.CommunityParsing;
import tgn.com.emergencyapp.utils.Common;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class CommunityAlert extends AppCompatActivity {
    ImageButton ibtn_alert;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_alert);
        ibtn_alert = findViewById(R.id.ibtn_siren);
        preferenceManager = new PreferenceManager(this);
        ibtn_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // new CommunityParsing(CommunityAlert.this).communityAlertPush(preferenceManager.getString("full_name"),preferenceManager.getString("community_id"));
                Common.showCommunityNotification(CommunityAlert.this, "Dinesh");
                Common.showEmergencyNotification(CommunityAlert.this, "Dinesh");

            }
        });
    }
}

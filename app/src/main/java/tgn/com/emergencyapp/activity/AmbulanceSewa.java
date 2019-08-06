package tgn.com.emergencyapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import tgn.com.emergencyapp.R;

public class AmbulanceSewa extends AppCompatActivity {
    ImageButton iv_ambulance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_sewa);
        iv_ambulance = findViewById(R.id.btn_ambulance);
        iv_ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AmbulanceSewa.this, "Your LatLong sent to Hospital", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

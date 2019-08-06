package tgn.com.emergencyapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import tgn.com.emergencyapp.R;
import tgn.com.emergencyapp.aquery.UserParsing;

public class Home extends AppCompatActivity {
    private EditText et_fullname, et_email, et_phone, et_address, et_secphone, et_community;
    private Spinner sp_bg;
    private Button btn_submit;
    TextView btn_home;
    private RadioGroup rg_sex;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        et_fullname = findViewById(R.id.et_fullname);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_community = findViewById(R.id.et_Community);
        et_address = findViewById(R.id.et_address);
        et_secphone = findViewById(R.id.et_sec_phone);
        sp_bg = findViewById(R.id.sp_bg);
        rg_sex = findViewById(R.id.radioSex);
        btn_submit = findViewById(R.id.btn_submit);
        btn_home = findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Home.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_fullname.getText().toString().isEmpty()) {
                    et_fullname.setError("Empty Field!");
                    return;
                }
                if (et_email.getText().toString().isEmpty()) {
                    et_email.setError("Empty Field!");
                    return;
                }
                if (et_phone.getText().toString().isEmpty()) {
                    et_phone.setError("Empty Field!");
                    return;
                }
                if (et_address.getText().toString().isEmpty()) {
                    et_address.setError("Empty Field!");
                    return;
                }
                if (et_secphone.getText().toString().isEmpty()) {
                    et_secphone.setError("Empty Field!");
                    return;
                }
                if (et_community.getText().toString().isEmpty()) {
                    et_community.setError("Empty Field!");
                    return;
                }
                int rgID = rg_sex.getCheckedRadioButtonId();
                radioButton = findViewById(rgID);
                new UserParsing(Home.this).userDataPush(et_fullname.getText().toString(), et_phone.getText().toString(), et_email.getText().toString(), et_address.getText().toString(), et_secphone.getText().toString(), sp_bg.getSelectedItem().toString(), radioButton.getText().toString(), et_community.getText().toString());
            }
        });
    }
}

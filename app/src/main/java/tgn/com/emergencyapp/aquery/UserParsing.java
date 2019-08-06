package tgn.com.emergencyapp.aquery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tgn.com.emergencyapp.activity.MainActivity;
import tgn.com.emergencyapp.utils.Common;
import tgn.com.emergencyapp.utils.Constanst;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class UserParsing {
    private AQuery aQuery;
    private Context context;
    private PreferenceManager preferenceManager;

    public UserParsing(Context context) {
        this.context = context;
        preferenceManager = new PreferenceManager(context);
    }

    public void userDataPush(final String username, final String phone, final String email, final String address, final String secPhone, final String bloodGroup, String gender, final String community_id) {
        aQuery = new AQuery(context.getApplicationContext());

        final Map<String, Object> params = new HashMap<>();
        params.put("full_name", username);
        params.put("email", email);
        params.put("blood_group", bloodGroup);
        params.put("phone", phone);
        params.put("address", address);
        params.put("sec_phone", secPhone);
        params.put("gender", gender);
        params.put("community_id", community_id);
        Log.v("UserParse", params + "");
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.USER_CREATE, params, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    Log.v("UserParse", object + "");
                    if (object != null) {
                        try {
                            boolean error = object.getBoolean("error");
                            preferenceManager.putString("full_name", username);
                            preferenceManager.putString("phone", phone);
                            preferenceManager.putString("email", email);
                            preferenceManager.putString("address", address);
                            preferenceManager.putString("sec_phone", secPhone);
                            preferenceManager.putString("blood_group", bloodGroup);
                            preferenceManager.putString("gender", secPhone);
                            preferenceManager.putString("community_id", community_id);
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
        }
    }
}

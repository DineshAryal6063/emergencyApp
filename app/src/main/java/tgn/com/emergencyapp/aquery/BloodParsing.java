package tgn.com.emergencyapp.aquery;

import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import tgn.com.emergencyapp.utils.Common;
import tgn.com.emergencyapp.utils.Constanst;
import tgn.com.emergencyapp.utils.PreferenceManager;

public class BloodParsing {
    private AQuery aQuery;
    private Context context;
    private PreferenceManager preferenceManager;

    public BloodParsing(Context context) {
        this.context = context;
        preferenceManager=new PreferenceManager(context);
    }
    public void bloodReqPush(String phone, String bg) {
        aQuery = new AQuery(context.getApplicationContext());

        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        params.put("blood_group", bg);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.BLOOD_REQ, params, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    Log.v("UserDataBlood", object+"");
                    if (object != null) {
                        try {
                            boolean error = object.getBoolean("error");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
        }
    }
    public void bloodReqPull() {
        aQuery = new AQuery(context);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.BLOOD_FETCH, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);

                    if (object != null) {
                        boolean error = object.optBoolean("error");
                        if (!error) {
                            JSONArray data = object.optJSONArray("users");
                            JSONObject jsonObject=data.optJSONObject(0);
                            String phone = jsonObject.optString("phone");
                            String bloodGroup = jsonObject.optString("blood_group");

                            if (bloodGroup.equalsIgnoreCase(preferenceManager.getString("blood_group"))){
                                Common.showBloodNotification(context, phone);
                            }

                        }
                    }
                }
            });
        } else {


        }
    }

}

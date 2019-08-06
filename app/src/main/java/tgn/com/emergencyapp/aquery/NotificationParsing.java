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
import java.util.Objects;

import tgn.com.emergencyapp.utils.Common;
import tgn.com.emergencyapp.utils.Constanst;

public class NotificationParsing {
    private AQuery aQuery;
    private Context context;

    public NotificationParsing(Context context) {
        this.context = context;
    }

    public void notificationPush(String username, int status) {
        aQuery = new AQuery(context.getApplicationContext());

        Map<String, Object> params = new HashMap<>();
        params.put("user_name", username);
        params.put("status", status);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.PUSH_NOTIFICATION, params, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);

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

    public void notificationFetch() {
        aQuery = new AQuery(context);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.FETCH_NOTIFICATION, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    Log.v("UserDatagirls", object + "");
                    if (object != null) {
                        boolean error = object.optBoolean("error");
                        if (!error) {
                            JSONArray data = object.optJSONArray("users");
                            JSONObject jsonObject=data.optJSONObject(0);
                            String username = jsonObject.optString("user_name");
                            Common.showEmergencyNotification(context, username);
                        }


                    }
                }
            });
        } else {


        }
    }
}

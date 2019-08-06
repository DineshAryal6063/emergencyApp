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

public class CommunityParsing {
    private AQuery aQuery;
    private Context context;
    PreferenceManager preferenceManager;

    public CommunityParsing(Context context) {
        this.context = context;
        preferenceManager = new PreferenceManager(context);
    }

    public void communityAlertPush(String username, String community_id) {
        aQuery = new AQuery(context.getApplicationContext());

        Map<String, Object> params = new HashMap<>();
        params.put("user_name", username);
        params.put("community_id", community_id);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.COMMUNITY_NOT_PUSH, params, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);

                    if (object != null) {
                        try {
                            boolean error = object.getBoolean("error");
                            preferenceManager.putBoolean("meAlertGenerate",true);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
        }
    }

    public void communityAlertPull() {
        aQuery = new AQuery(context);
        if (Common.isNetworkAvailable(context)) {
            aQuery.ajax(Constanst.COMMUNITY_NOT_PULL, JSONObject.class, new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject object, AjaxStatus status) {
                    super.callback(url, object, status);
                    Log.v("UserDataCommunity", object + "");
                    if (object != null) {

                        boolean error = object.optBoolean("error");
                        if (!error) {
                            JSONArray data = object.optJSONArray("users");
                            JSONObject jsonObject = data.optJSONObject(0);
                            String user_name = jsonObject.optString("user_name");
                            String community_id = jsonObject.optString("community_id");
                            Log.v("UserData", object + "");
                            if (community_id.equalsIgnoreCase(preferenceManager.getString("community_id"))) {
                                Common.showCommunityNotification(context, user_name);
                            }
                        }
                    }
                }
            });
        } else {


        }
    }
}

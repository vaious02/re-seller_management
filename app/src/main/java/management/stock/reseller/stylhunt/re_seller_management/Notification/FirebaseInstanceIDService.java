package management.stock.reseller.stylhunt.re_seller_management.Notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineException;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineListener;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPManager;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "IDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.wtf(TAG,token);

        saveToken(token);
    }

    private void saveToken(String token) {

        HTTPEngineListener<BaseDao> listener = new HTTPEngineListener<BaseDao>() {
            @Override
            public void onResponse(BaseDao data, String rawData) {
                Log.wtf(TAG, rawData);
            }

            @Override
            public void onFailure(BaseDao data, String rawData, HTTPEngineException error) {

            }
        };

        HTTPManager.getInstance().insertUserToken(token, StringUtil.getDeviceToken(getApplicationContext()), listener);

    }

}

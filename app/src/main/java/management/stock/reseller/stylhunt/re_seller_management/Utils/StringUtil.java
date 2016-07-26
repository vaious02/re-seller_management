package management.stock.reseller.stylhunt.re_seller_management.Utils;

import android.content.Context;
import android.provider.Settings;

import management.stock.reseller.stylhunt.re_seller_management.Manager.Contextor;

/**
 * Created by Admin on 7/7/2016.
 */
public class StringUtil {

    // Real Server
    public static final String nodeserver = "http://www.stylhunt.com:8001";
    public static final String host = "http://www.stylhunt.com";
    private static final String database = "stock";

    //Test Server
    /*private static final String nodeserver = "http://ec2-52-77-107-31.ap-southeast-1.compute.amazonaws.com:8001";
    private static final String host = "http://ec2-52-77-107-31.ap-southeast-1.compute.amazonaws.com";
    private static final String database = "stocktest";*/

    private static final String url_getproduct = getHost() + "/re-seller_stock_management/module/";


    public static String getDatabase() {
        return database;
    }


    public static String getHost(){
        return host;
    }

    public static String getHostProduct() {
        return url_getproduct;
    }

    public static String getDeviceToken(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}

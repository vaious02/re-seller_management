package management.stock.reseller.stylhunt.re_seller_management.Manager;

import android.content.Context;

/**
 * Created by Admin on 7/7/2016.
 */
public class Contextor {
    private static Contextor instance;

    public static Contextor getInstance() {
        if (instance == null)
            instance = new Contextor();
        return instance;
    }

    private Context mContext;

    private Contextor() {

    }

    public void init(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }
}

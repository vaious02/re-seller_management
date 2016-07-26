package management.stock.reseller.stylhunt.re_seller_management.Dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/7/2016.
 */
public class BaseDao {

    @SerializedName("success") private boolean success;

    public boolean isSuccess() {
        return success;
    }



}

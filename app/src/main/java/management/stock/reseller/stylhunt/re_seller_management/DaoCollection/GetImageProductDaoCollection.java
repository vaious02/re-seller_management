package management.stock.reseller.stylhunt.re_seller_management.DaoCollection;

import com.google.gson.annotations.SerializedName;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetImageProductDao;

/**
 * Created by Admin on 7/8/2016.
 */
public class GetImageProductDaoCollection extends BaseDao {

    @SerializedName("data")
    private GetImageProductDao[] data;

    public GetImageProductDao[] getData() {
        return data;
    }

    public void setData(GetImageProductDao[] data) {
        this.data = data;
    }
}

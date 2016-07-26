package management.stock.reseller.stylhunt.re_seller_management.DaoCollection;

import com.google.gson.annotations.SerializedName;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetProductDao;

/**
 * Created by Admin on 7/7/2016.
 */
public class GetProductDaoCollection extends BaseDao {

    @SerializedName("data")
    private GetProductDao[] data;

    public GetProductDao[] getData() {
        return data;
    }

    public void setData(GetProductDao[] data) {
        this.data = data;
    }

}

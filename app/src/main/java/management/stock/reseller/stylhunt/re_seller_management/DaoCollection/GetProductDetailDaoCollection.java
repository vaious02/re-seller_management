package management.stock.reseller.stylhunt.re_seller_management.DaoCollection;

import com.google.gson.annotations.SerializedName;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetProductDetailDao;


/**
 * Created by Admin on 7/8/2016.
 */
public class GetProductDetailDaoCollection extends BaseDao {

    @SerializedName("data")
    private GetProductDetailDao[] data;

    public GetProductDetailDao[] getData() {
        return data;
    }

    public void setData(GetProductDetailDao[] data) {
        this.data = data;
    }
}

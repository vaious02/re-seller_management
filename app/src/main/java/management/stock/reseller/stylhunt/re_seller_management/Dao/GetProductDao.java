package management.stock.reseller.stylhunt.re_seller_management.Dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/7/2016.
 */
public class GetProductDao extends BaseDao {
    @SerializedName("shop_id")
    private int shop_id;

    @SerializedName("product_id")
    private int product_id;

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("product_price")
    private int product_price;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}

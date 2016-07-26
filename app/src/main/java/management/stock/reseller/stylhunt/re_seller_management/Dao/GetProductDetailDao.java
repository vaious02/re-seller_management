package management.stock.reseller.stylhunt.re_seller_management.Dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/8/2016.
 */
public class GetProductDetailDao {

    @SerializedName("product_id")
    private int product_id;

    @SerializedName("shop_id")
    private int shop_id;

    @SerializedName("stock_id")
    private int stock_id;

    @SerializedName("product_name")
    private String product_name;

    @SerializedName("product_price")
    private int product_price;

    @SerializedName("product_description")
    private String product_description;

    @SerializedName("stock_quantity")
    private int stock_quantity;

    @SerializedName("stock_description")
    private String stock_description;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
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

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }

    public String getStock_description() {
        return stock_description;
    }

    public void setStock_description(String stock_description) {
        this.stock_description = stock_description;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }
}

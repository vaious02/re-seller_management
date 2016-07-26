package management.stock.reseller.stylhunt.re_seller_management.DataModel;

/**
 * Created by Admin on 7/7/2016.
 */
public class GetProduct_Model {

    private int shop_id;
    private int product_id;
    private String product_name;
    private int product_price;

    private GetProduct_Model(){}

    public int getShop_id() {
        return shop_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public static class Builder{

        private int shop_id;
        private int product_id;
        private String product_name;
        private int product_price;

        public Builder setShop_id(int shop_id) {
            this.shop_id = shop_id;
            return this;
        }

        public Builder setProduct_id(int product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder setProduct_name(String product_name) {
            this.product_name = product_name;
            return this;
        }

        public Builder setProduct_price(int product_price) {
            this.product_price = product_price;
            return this;
        }
        public GetProduct_Model build() {
            GetProduct_Model getProduct = new GetProduct_Model();
            getProduct.shop_id = shop_id;
            getProduct.product_id = product_id;
            getProduct.product_name = product_name;
            getProduct.product_price = product_price;

            return getProduct;
        }
    }
}

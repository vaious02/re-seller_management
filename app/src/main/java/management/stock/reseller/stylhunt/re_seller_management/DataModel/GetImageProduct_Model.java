package management.stock.reseller.stylhunt.re_seller_management.DataModel;

/**
 * Created by Admin on 7/8/2016.
 */
public class GetImageProduct_Model {
    private String image_url;
    private int image_id;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public static class Builder{
        private String image_url;
        private int image_id;

        public Builder setImage_url(String image_url) {
            this.image_url = image_url;
            return this;
        }

        public Builder setImage_id(int image_id) {
            this.image_id = image_id;
            return this;
        }

        public GetImageProduct_Model build() {
            GetImageProduct_Model getImage = new GetImageProduct_Model();

            getImage.image_id = image_id;
            getImage.image_url = image_url;

            return getImage;
        }
    }
}

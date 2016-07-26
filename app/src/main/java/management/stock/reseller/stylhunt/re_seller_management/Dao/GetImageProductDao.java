package management.stock.reseller.stylhunt.re_seller_management.Dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/8/2016.
 */
public class GetImageProductDao {

    @SerializedName("image_id")
    private int image_id;

    @SerializedName("image_url")
    private String image_url;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}

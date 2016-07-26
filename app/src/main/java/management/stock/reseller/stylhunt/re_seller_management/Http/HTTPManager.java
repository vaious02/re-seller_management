package management.stock.reseller.stylhunt.re_seller_management.Http;


import android.util.Log;

import com.squareup.okhttp.Call;

import java.util.HashMap;
import java.util.Map;

import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetImageProductDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetProductDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetProductDetailDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;

public class HTTPManager {
    private static HTTPManager instance;

    public static HTTPManager getInstance() {
        if (instance == null)
            instance = new HTTPManager();
        return instance;
    }
    private HTTPManager(){
    }

    public Call getProduct(int shop_id, HTTPEngineListener<GetProductDaoCollection> listener){
        Map<String,String> postData = new HashMap<>();
//        Log.wtf("shop_id",""+shop_id);
        postData.put("shop_id",String.valueOf(shop_id));

        return HTTPEngine.getInstance().loadPostUrlwithTreadPool(
                StringUtil.getHostProduct()+"app_select_product.php",
                postData,
                listener,
                GetProductDaoCollection.class);
    }

    public Call getProductNostock (int shop_id, HTTPEngineListener<GetProductDaoCollection> listener){
        Map<String,String> postData = new HashMap<>();
        Log.wtf("shop_idNostock",""+shop_id);
        postData.put("shop_id",String.valueOf(shop_id));

        return HTTPEngine.getInstance().loadPostUrlwithTreadPool(
                StringUtil.getHostProduct()+"app_select_product_nostock.php",
                postData,
                listener,
                GetProductDaoCollection.class);
    }

    public Call getProductDetail(int product_id, HTTPEngineListener<GetProductDetailDaoCollection> listener) {
        Map<String, String> postData = new HashMap<>();
        postData.put("product_id", String.valueOf(product_id));
        return HTTPEngine.getInstance().loadPostUrlwithTreadPool(
                StringUtil.getHostProduct()+"app_select_productdetail.php",
                postData,
                listener,
                GetProductDetailDaoCollection.class
        );
    }


    public Call getProductImage(int product_id, HTTPEngineListener<GetImageProductDaoCollection> listener) {
        Map<String, String> postData = new HashMap<>();
        postData.put("product_id", String.valueOf(product_id));
        return HTTPEngine.getInstance().loadPostUrlwithTreadPool(
                StringUtil.getHostProduct()+"app_select_productimage.php",
                postData,
                listener,
                GetImageProductDaoCollection.class
        );
    }

    public Call insertUserToken(String token, String device_token, HTTPEngineListener<BaseDao> listener) {
        Map<String, String> postData = new HashMap<>();
        postData.put("firebase_token", token);
        postData.put("device_token", device_token);
        return HTTPEngine.getInstance().loadPostUrlwithTreadPool(
                StringUtil.getHostProduct()+"app_insert_usertoken.php",
                postData,
                listener,
                BaseDao.class
        );

    }


}

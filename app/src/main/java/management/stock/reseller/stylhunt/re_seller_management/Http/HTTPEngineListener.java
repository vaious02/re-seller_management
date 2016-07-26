package management.stock.reseller.stylhunt.re_seller_management.Http;


import management.stock.reseller.stylhunt.re_seller_management.Dao.BaseDao;

/**
 * Created by Admin on 3/29/2016.
 */
public interface HTTPEngineListener<T extends BaseDao> {

    void onResponse(T data, String rawData);

    void onFailure(T data, String rawData, HTTPEngineException error);

}



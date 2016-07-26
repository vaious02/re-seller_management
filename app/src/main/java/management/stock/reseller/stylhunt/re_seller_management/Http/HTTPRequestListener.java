package management.stock.reseller.stylhunt.re_seller_management.Http;

/**
 * Created by Admin on 3/29/2016.
 */
public interface HTTPRequestListener {

    void onMessageReceived(int statusCode, String messageResponse);
    void onMessageError(int statusCode, String message);

}

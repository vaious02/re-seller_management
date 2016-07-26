package management.stock.reseller.stylhunt.re_seller_management.Http;

import java.util.Map;

public class HTTPRequestData {

    public String url;
    public HTTPEngine.RequestMethod method;
    public Map<String, String> postData;
    public com.squareup.okhttp.Call call;

}
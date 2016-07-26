package management.stock.reseller.stylhunt.re_seller_management.DataModel;

/**
 * Created by Admin on 7/8/2016.
 */
public class Stock_Model {
    String stock_quantity;
    String stock_description;

   public Stock_Model(String stock_quantity, String stock_description) {
       this.stock_description = stock_description;
       this.stock_quantity = stock_quantity;
   }
}

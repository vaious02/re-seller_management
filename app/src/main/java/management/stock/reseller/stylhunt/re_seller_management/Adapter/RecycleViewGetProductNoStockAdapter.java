package management.stock.reseller.stylhunt.re_seller_management.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import management.stock.reseller.stylhunt.re_seller_management.Activity.DescriptionActivity;
import management.stock.reseller.stylhunt.re_seller_management.DataModel.GetProduct_Model;
import management.stock.reseller.stylhunt.re_seller_management.Manager.ImageLoadManager;
import management.stock.reseller.stylhunt.re_seller_management.R;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;

/**
 * Created by Admin on 7/12/2016.
 */
public class RecycleViewGetProductNoStockAdapter extends RecyclerView.Adapter<RecycleViewGetProductNoStockAdapter.RecyclerViewHolders> {

    private Context context;
    private ArrayList<GetProduct_Model> list;
    private String url;
    private DecimalFormat format = new DecimalFormat("#,###");
    private DatabaseReference ref,rootRef;

    public RecycleViewGetProductNoStockAdapter(Context context, ArrayList<GetProduct_Model> list, DatabaseReference rootRef) {
        this.context = context;
        this.list = list;
        this.rootRef = rootRef;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolders holder, final int position) {

        url = StringUtil.getHost()+"/imageshop/" + list.get(position).getShop_id() + "/image_product/" + list.get(position).getProduct_id() + "/0.jpg";
        holder.setText(list.get(position).getProduct_name(),holder.tvProductName);
        holder.setText(String.valueOf(list.get(position).getProduct_price() + " ฿"), holder.tvProductPrice);
        holder.setImage(holder.imgProduct,url);

        holder.relProductDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("product_id",list.get(position).getProduct_id());
                intent.putExtra("product_name", list.get(position).getProduct_name());
                intent.putExtra("shop_id", list.get(position).getShop_id());
                context.startActivity(intent);
            }
        });

        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DescriptionActivity.class);
                intent.putExtra("product_id",list.get(position).getProduct_id());
                intent.putExtra("product_name", list.get(position).getProduct_name());
                intent.putExtra("shop_id", list.get(position).getShop_id());
                context.startActivity(intent);
            }
        });

        ref = rootRef.child(String.valueOf(list.get(position).getProduct_id()));
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double price = Double.parseDouble(dataSnapshot.child("product_price").getValue(String.class));
                holder.tvProductStock.setText(dataSnapshot.child("stock_quantity").getValue(String.class)+" ชิ้น");
                holder.tvProductName.setText(dataSnapshot.child("product_name").getValue(String.class));
                holder.tvProductPrice.setText("฿ "+format.format(price));

                int stock = Integer.parseInt(dataSnapshot.child("stock_quantity").getValue(String.class));
                if(stock == 0) {
                    holder.imgProduct.setColorFilter(R.color.black_op);
                    holder.tvNostock.setVisibility(View.VISIBLE);

                } else{
                    holder.imgProduct.setColorFilter(null);
                    holder.tvNostock.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class RecyclerViewHolders extends RecyclerView.ViewHolder {

        private TextView tvProductName, tvProductPrice, tvProductStock, tvNostock;
        private ImageView imgProduct;
        private RelativeLayout relProductDes;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
            tvProductStock = (TextView) itemView.findViewById(R.id.tvProductStock);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            relProductDes = (RelativeLayout) itemView.findViewById(R.id.relProductDes);
            tvNostock = (TextView) itemView.findViewById(R.id.tvNostock);

        }

        public void setImage(ImageView img, String url){
            ImageLoadManager.getInstance().loadImageProduct(url,img);
        }

        public void setText (String text, TextView textView) {
            textView.setText(text);
        }

    }
}

package management.stock.reseller.stylhunt.re_seller_management.Fragment;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

import management.stock.reseller.stylhunt.re_seller_management.Adapter.ImageSlideAdapter;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetImageProductDao;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetProductDetailDao;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetImageProductDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetProductDetailDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.DataModel.GetImageProduct_Model;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineException;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineListener;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPManager;
import management.stock.reseller.stylhunt.re_seller_management.R;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;

public class DescriptionFragment extends Fragment implements View.OnClickListener, ValueEventListener{

    int product_id, shop_id;
    private String stock_des, product_des;

    private TextView productPrice, productDescription, stockQuan, stockDescription;
    private ArrayList<GetImageProduct_Model> imagelist = new ArrayList<>();
    private ImageSlideAdapter adapter;
    GetProductDetailDao childData;
    GetImageProductDao[] image;
    private String price, p_des;
    private ViewPager vpImage;
    private ProgressDialog progressDialog;
    private Button save;
    DatabaseReference ref, refDetail;
    ImageView back, forward;
    RelativeLayout relChat;
    private DecimalFormat format = new DecimalFormat("#,###");

    public DescriptionFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DescriptionFragment newInstance(int product_id, int shop_id) {
        DescriptionFragment fragment = new DescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("product_id",product_id);
        args.putInt("shop_id",shop_id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product_id = getArguments().getInt("product_id");
            shop_id = getArguments().getInt("shop_id");
            Log.wtf("product_id",""+product_id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_description, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        ref = FirebaseDatabase.getInstance().getReference();
        refDetail = ref.child(StringUtil.getDatabase()).child(String.valueOf(shop_id)).child(String.valueOf(product_id));
        productPrice = (TextView) rootView.findViewById(R.id.tvProductPrice_Des);
        productDescription = (TextView) rootView.findViewById(R.id.tvProductDescription);
        stockDescription = (TextView) rootView.findViewById(R.id.tvStockDescription);
        stockQuan = (TextView) rootView.findViewById(R.id.tvProductStock_Des);
        vpImage = (ViewPager)rootView.findViewById(R.id.vpImageSlide);
        back = (ImageView) rootView.findViewById(R.id.img_back);
        forward = (ImageView) rootView.findViewById(R.id.img_forward);
        relChat = (RelativeLayout) rootView.findViewById(R.id.relChat);
        save = (Button) rootView.findViewById(R.id.btnSave);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        loadData(product_id);
        loadImageData(product_id);

        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        relChat.setOnClickListener(this);
        save.setOnClickListener(this);

    }

    private void loadImageData(int product_id) {
        HTTPEngineListener<GetImageProductDaoCollection> listener = new HTTPEngineListener<GetImageProductDaoCollection>() {
            @Override
            public void onResponse(GetImageProductDaoCollection data, String rawData) {
                Log.wtf("loadImageData",rawData);
                if (data.isSuccess()){
                    image = data.getData();

                    for (int i=0; i<image.length; i++) {
                        imagelist.add(new GetImageProduct_Model.Builder()
                                .setImage_id(image[i].getImage_id())
                                .setImage_url(image[i].getImage_url())
                                .build());
                    }

                    adapter = new ImageSlideAdapter(getActivity(),imagelist);
                    vpImage.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(GetImageProductDaoCollection data, String rawData, HTTPEngineException error) {

            }
        };
        HTTPManager.getInstance().getProductImage(product_id, listener);
    }


    private void loadData(int product_id) {
        final HTTPEngineListener<GetProductDetailDaoCollection> listener = new HTTPEngineListener<GetProductDetailDaoCollection>() {
            @Override
            public void onResponse(GetProductDetailDaoCollection data, String rawData) {
                Log.wtf("loadData",rawData+""+data.isSuccess());
               if (data.isSuccess()){
                   childData = data.getData()[0];
                   Log.wtf("loadData",childData.getProduct_description());

                   price = String.valueOf(childData.getProduct_price());
                   p_des = childData.getProduct_description();

                   setText();

               }
            }

            @Override
            public void onFailure(GetProductDetailDaoCollection data, String rawData, HTTPEngineException error) {

            }
        };
        HTTPManager.getInstance().getProductDetail(product_id,listener);
    }

    private void setText() {
        progressDialog.cancel();

        productDescription.setText(p_des);
        refDetail.addValueEventListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.img_back :
                if(vpImage.getCurrentItem()!=0){
                    vpImage.setCurrentItem(vpImage.getCurrentItem()-1);
                }else {
                    vpImage.setCurrentItem(vpImage.getCurrentItem());
                }
                break;
            case R.id.img_forward :
                vpImage.setCurrentItem(vpImage.getCurrentItem()+1);
                break;
            case R.id.relChat :
                Uri url = Uri.parse("https://goo.gl/zM9p75");
                Intent intent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(intent);
                break;
            case R.id.btnSave :
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData data = ClipData.newPlainText("label",product_des);
                clipboard.setPrimaryClip(data);
                if(clipboard.hasPrimaryClip()) {
                    Toast.makeText(getContext(), "Copy text success", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getContext(), "Copy text failed", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        if(dataSnapshot.hasChildren()) {
            double priceD = Double.parseDouble(dataSnapshot.child("product_price").getValue(String.class));
            String price = "฿ "+format.format(priceD);
            String stockquantity = dataSnapshot.child("stock_quantity").getValue(String.class)+" ชิ้น";
            stock_des = dataSnapshot.child("stock_description").getValue(String.class);
            product_des = dataSnapshot.child("product_description").getValue(String.class);
            stockQuan.setText(stockquantity);
            stockDescription.setText(stock_des);
            productPrice.setText(price);
            productDescription.setText(product_des);
        } else{
            getActivity().finish();
        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}

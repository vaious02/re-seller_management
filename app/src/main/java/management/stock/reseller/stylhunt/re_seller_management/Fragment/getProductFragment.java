package management.stock.reseller.stylhunt.re_seller_management.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import management.stock.reseller.stylhunt.re_seller_management.Adapter.RecycleViewGetProductAdapter;
import management.stock.reseller.stylhunt.re_seller_management.Dao.GetProductDao;
import management.stock.reseller.stylhunt.re_seller_management.DaoCollection.GetProductDaoCollection;
import management.stock.reseller.stylhunt.re_seller_management.DataModel.GetProduct_Model;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineException;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPEngineListener;
import management.stock.reseller.stylhunt.re_seller_management.Http.HTTPManager;
import management.stock.reseller.stylhunt.re_seller_management.Manager.Contextor;
import management.stock.reseller.stylhunt.re_seller_management.R;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;
import management.stock.reseller.stylhunt.re_seller_management.View.ItemOffsetDecoration;

public class getProductFragment extends Fragment {
    private static final String ViewPagerCount = "page_number";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterRecycle;
    private SwipeRefreshLayout swiperefresh;
    private ArrayList<GetProduct_Model> list = new ArrayList<>();
    DatabaseReference ref, rootRef;
    private int shop_id = 38;

    public getProductFragment() {
        // Required empty public constructor
    }

    public static getProductFragment newInstance(Integer param1) {
        getProductFragment fragment = new getProductFragment();
        Bundle args = new Bundle();
        args.putInt(ViewPagerCount, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_product, container, false);
        initInstance(rootView);
        return rootView;
    }

    private void initInstance(View rootView) {
        Contextor.getInstance().init(getContext());
        ref = FirebaseDatabase.getInstance().getReference();
        rootRef = ref.child(StringUtil.getDatabase()).child(String.valueOf(shop_id));

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycleView);
        swiperefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swiperefresh);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(getContext(), R.dimen.item_offset);
        recyclerView.addItemDecoration(itemOffsetDecoration);
        loadData();

        rootRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               if(!list.isEmpty()) {
                   list.clear();
                   loadData();
               }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.clear();
                loadData();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                loadData();
            }
        });

    }

    private void loadData() {
    HTTPEngineListener<GetProductDaoCollection> listener = new HTTPEngineListener<GetProductDaoCollection>() {
        @Override
        public void onResponse(GetProductDaoCollection data, String rawData) {
            Log.wtf("GetProductDaoCollection", rawData);

            if(data.isSuccess()) {
                swiperefresh.setRefreshing(false);
                GetProductDao[] childData = data.getData();
                for (int i = 0; i<childData.length; i++) {
                    list.add(new GetProduct_Model.Builder()
                        .setShop_id(childData[i].getShop_id())
                        .setProduct_price(childData[i].getProduct_price())
                        .setProduct_name(childData[i].getProduct_name())
                        .setProduct_id(childData[i].getProduct_id())
                        .build());
                }

                adapterRecycle = new RecycleViewGetProductAdapter(getActivity(),list, rootRef);
                recyclerView.setAdapter(adapterRecycle);

            }
        }

        @Override
        public void onFailure(GetProductDaoCollection data, String rawData, HTTPEngineException error) {

        }
    };
        HTTPManager.getInstance().getProduct(shop_id,listener);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

}

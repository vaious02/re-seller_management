package management.stock.reseller.stylhunt.re_seller_management.Fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import management.stock.reseller.stylhunt.re_seller_management.Adapter.ViewPagerAdapter;
import management.stock.reseller.stylhunt.re_seller_management.R;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ViewPagerAdapter vpAdapter;
    private ViewPager vp;
    private TabLayout tabLayout;


    private OnFragmentInteractionListener mListener;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstance(rootView);

        return rootView;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void initInstance(View rootView) {


        vp = (ViewPager) rootView.findViewById(R.id.viewpager);
        vpAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        vp.setAdapter(vpAdapter);
        tabLayout.setTabTextColors(R.color.black, R.color.green);
        tabLayout.setupWithViewPager(vp);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

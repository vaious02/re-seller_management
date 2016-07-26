package management.stock.reseller.stylhunt.re_seller_management.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import management.stock.reseller.stylhunt.re_seller_management.Fragment.getProductFragment;
import management.stock.reseller.stylhunt.re_seller_management.Fragment.getProductNoStockFragment;

/**
 * Created by Admin on 7/6/2016.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "สินค้าทั้งหมด", "สินค้าที่หมด"};


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return getProductFragment.newInstance(position+1);
            case 1 : return getProductNoStockFragment.newInstance(position+1);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }

}

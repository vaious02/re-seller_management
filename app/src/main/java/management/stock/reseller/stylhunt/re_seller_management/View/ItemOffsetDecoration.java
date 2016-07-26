package management.stock.reseller.stylhunt.re_seller_management.View;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Admin on 7/8/2016.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
    private int mItemoffset;

    public ItemOffsetDecoration (int Itemoffset) {
        mItemoffset = Itemoffset;
    }
    public  ItemOffsetDecoration(@NonNull Context context, @DimenRes int Itemoffset) {
        this(context.getResources().getDimensionPixelSize(Itemoffset));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemoffset, mItemoffset, mItemoffset, mItemoffset);
    }
}

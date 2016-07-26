package management.stock.reseller.stylhunt.re_seller_management.Manager;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import management.stock.reseller.stylhunt.re_seller_management.R;

/**
 * Created by Admin on 7/7/2016.
 */
public class ImageLoadManager {

    private static ImageLoadManager instance;
    private Context context;

    public static ImageLoadManager getInstance() {
        if(instance == null)
            instance = new ImageLoadManager();
        return instance;
    }

    public ImageLoadManager() {
        context = Contextor.getInstance().getContext();
    }

    public void loadImageProduct(String Url, ImageView imageView){
        Glide.with(context)
                .load(Url)
                .placeholder(R.mipmap.load_image_product)
                .centerCrop()
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

    public void loadImageProductSliding (Context cont, String Url, ImageView imageView) {
        Glide.with(cont)
                .load(Url)
                .placeholder(R.mipmap.load_image_product)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(imageView);
    }

}

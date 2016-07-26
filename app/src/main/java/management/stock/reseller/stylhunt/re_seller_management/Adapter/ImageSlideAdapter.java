package management.stock.reseller.stylhunt.re_seller_management.Adapter;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import management.stock.reseller.stylhunt.re_seller_management.DataModel.GetImageProduct_Model;
import management.stock.reseller.stylhunt.re_seller_management.Manager.ImageLoadManager;
import management.stock.reseller.stylhunt.re_seller_management.R;
import management.stock.reseller.stylhunt.re_seller_management.Utils.StringUtil;

/**
 * Created by Admin on 7/8/2016.
 */
public class ImageSlideAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater inflater;
    ArrayList<GetImageProduct_Model> list;

    public ImageSlideAdapter(Context context, ArrayList<GetImageProduct_Model> list) {
        mContext = context;
        this.list = list;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.vp_imageproduct,container,false);

        final ImageView imageView = (ImageView) view.findViewById(R.id.image_display);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageLoadManager.getInstance().loadImageProductSliding(mContext, StringUtil.getHost()+list.get(position).getImage_url(),imageView);

        final AlertDialog.Builder al = new AlertDialog.Builder(mContext);
        al.setMessage("Save Image?");
        al.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        al.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                file_download(StringUtil.getHost()+list.get(position).getImage_url(), list.get(position).getImage_id());

            }
        });
        al.create();

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                al.show();

                return true;
            }
        });

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void file_download(String url, int image_id) {
        File direct = new File(Environment.getExternalStorageDirectory()
                + "/dhaval_files");

        if (!direct.exists()) {
            direct.mkdirs();
        }

        DownloadManager mgr = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri downloadUri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(
                downloadUri);

        request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI
                        | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false).setTitle("Demo")
                .setDescription("Something useful. No, really.")
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, image_id+".jpg");
        mgr.enqueue(request);

        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(mContext, "Save Image success", Toast.LENGTH_LONG).show();
            }
        };

        mContext.registerReceiver(onComplete, new IntentFilter(mgr.ACTION_DOWNLOAD_COMPLETE));

    }

}

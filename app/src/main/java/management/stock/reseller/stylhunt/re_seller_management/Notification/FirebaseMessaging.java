package management.stock.reseller.stylhunt.re_seller_management.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import management.stock.reseller.stylhunt.re_seller_management.Activity.DescriptionActivity;
import management.stock.reseller.stylhunt.re_seller_management.R;

/**
 * Created by Admin on 7/16/2016.
 */
public class FirebaseMessaging extends FirebaseMessagingService {

    String product_name;
    int product_id, shop_id;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();

        sendNotification(notification, data);

    }

    private void sendNotification(RemoteMessage.Notification notification, Map<String, String> data) {



        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(FirebaseMessaging.this);
        notificationBuilder.setContentTitle(notification.getTitle());
        notificationBuilder.setContentText(notification.getBody());
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationBuilder.setLargeIcon(icon);
        notificationBuilder.setColor(Color.RED);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);



        product_id = Integer.parseInt(data.get("product_id"));
        shop_id = Integer.parseInt(data.get("shop_id"));
        product_name = data.get("product_name");
        Log.wtf("dataNoti",product_name+" "+product_id+" "+shop_id);


        Intent intent = new Intent(FirebaseMessaging.this, DescriptionActivity.class);
        intent.putExtra("product_id", product_id);
        intent.putExtra("product_name", product_name);
        intent.putExtra("shop_id", shop_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(FirebaseMessaging.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());



    }


}

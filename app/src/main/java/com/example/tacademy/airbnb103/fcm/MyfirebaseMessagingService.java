package com.example.tacademy.airbnb103.fcm;


import com.example.tacademy.airbnb103.Util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 *  메시지 수신
 */

public class MyfirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    // 메시지 수신 : 수신 이벤트 발생시 자동 호출
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

       Log.getInstance().log("From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.getInstance().log("Message data payload: key1 : " + remoteMessage.getData().get("key1"));
            Log.getInstance().log("Message data payload: key2 : " + remoteMessage.getData().get("key2"));
        }
        if (remoteMessage.getNotification() != null) {
            Log.getInstance().log("Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }
    // [END receive_message]
    // 안테나 역할
    // ex) 카톡 알림 받을지 안받을지
    private void sendNotification(String messageBody) {
        /*
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//* Request code *//*, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_ic_notification)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 *//* ID of notification *//*, notificationBuilder.build());
        */
    }
}
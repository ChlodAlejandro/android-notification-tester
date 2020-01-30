package chlod.tests.notificationtester;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotification(View view) {
        RadioGroup priorityview = findViewById(R.id.rdogpPriority);
        EditText titleview = findViewById(R.id.inpHeader);
        EditText contentview = findViewById(R.id.inpContent);
        int priority = 0;
        switch(priorityview.getCheckedRadioButtonId()) {
            case R.id.rdoUrgent:
                priority = 2;
                break;
            case R.id.rdoHigh:
                priority = 1;
                break;
            case R.id.rdoNormal:
                priority = 0;
                break;
            case R.id.rdoLow:
                priority = -1;
                break;
            case R.id.rdoMin:
                priority = -2;
                break;
        }
        String title = titleview.getText().toString();
        String content = contentview.getText().toString();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channelname);
            String description = getString(R.string.channeldesc);
            NotificationChannel channel = new NotificationChannel(getString(R.string.channelname), name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(description);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, getString(R.string.channelname))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(content))
                .setPriority(priority);
        assert notificationManager != null;
        Random rand = new Random();
        notificationManager.notify(rand.nextInt(), mBuilder.build());
    }
}

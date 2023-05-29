package com.twilio.twilio_voice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twilio.voice.CallInvite;

public class IncomingCallNotificationSmallActivity extends AppCompatActivity {

    private static String TAG = "IncomingCallNotificationSmallActivity";
    private  ImageView btnReject, btnAnswer;
    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_incoming_call_notification_small);

        btnAnswer = (ImageView) findViewById(R.id.icNotificationBtnAnswer);
        btnReject = (ImageView) findViewById(R.id.icNotificationBtnReject);
        username = (TextView) findViewById(R.id.icNotificationUsername);

        Intent intent = getIntent();

        CallInvite callInvite = intent.getParcelableExtra(Constants.INCOMING_CALL_INVITE);
        int notificationId = intent.getIntExtra(Constants.INCOMING_CALL_NOTIFICATION_ID, 0);

        String fromId = callInvite.getFrom().replace("client:", "");
        username.setText(fromId);

        btnAnswer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onBtnAnswer");
                        Intent acceptIntent = new Intent(getApplicationContext(), AnswerJavaActivity.class);
                        acceptIntent.setAction(Constants.ACTION_ACCEPT);
                        acceptIntent.putExtra(Constants.ACCEPT_CALL_ORIGIN, 0);
                        acceptIntent.putExtra(Constants.INCOMING_CALL_INVITE, callInvite);
                        acceptIntent.putExtra(Constants.INCOMING_CALL_NOTIFICATION_ID, notificationId);
                        startActivity(acceptIntent);
                    }
                }
        );

        btnReject.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "onBtnReject");
                        Intent rejectIntent = new Intent(getApplicationContext(), AnswerJavaActivity.class);
                        rejectIntent.setAction(Constants.ACTION_REJECT);
                        rejectIntent.putExtra(Constants.INCOMING_CALL_INVITE, callInvite);
                        rejectIntent.putExtra(Constants.INCOMING_CALL_NOTIFICATION_ID, notificationId);
                        startActivity(rejectIntent);
                    }
                }
        );
    }
}
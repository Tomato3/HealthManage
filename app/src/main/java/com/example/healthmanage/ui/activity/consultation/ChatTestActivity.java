package com.example.healthmanage.ui.activity.consultation;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthmanage.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

public class ChatTestActivity extends AppCompatActivity implements EMMessageListener {
    private TextView mContentView;
    private EditText mInputView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //设置强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_chat);
        mContentView = findViewById(R.id.ec_text_content);
        mInputView = findViewById(R.id.ec_edit_message_input);
        button = findViewById(R.id.ec_btn_send);
        String chatId = "18852407703";
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mInputView.getText().toString();
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, chatId);
                message.setChatType(EMMessage.ChatType.Chat);
                EMClient.getInstance().chatManager().sendMessage(message);
                mContentView.setText(mContentView.getText()+"\n"+content);
                message.setMessageStatusCallback(new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        Log.i("聊天成功","发送消息成功");
                    }

                    @Override
                    public void onError(int code, String error) {
                        Log.e("聊天失败","发送消息失败"+code+error);
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
            }
        });
    }

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage message : messages){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mContentView.setText(mContentView.getText()+"\n"+((EMTextMessageBody)message.getBody()).getMessage());
                }
            });
        }
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> messages) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> messages) {

    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EMClient.getInstance().chatManager().removeMessageListener(this);
    }
}

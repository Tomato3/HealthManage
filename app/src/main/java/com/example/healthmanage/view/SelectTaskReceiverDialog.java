package com.example.healthmanage.view;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;

public class SelectTaskReceiverDialog extends Dialog implements View.OnClickListener {

    Context context;
    public String receiverName;
    public String receiverAvatar;
    public String taskContent;

    public OnEditTextDialogClickListener getOnEditTextDialogClickListener() {
        return onEditTextDialogClickListener;
    }

    public void setOnEditTextDialogClickListener(OnEditTextDialogClickListener onEditTextDialogClickListener) {
        this.onEditTextDialogClickListener = onEditTextDialogClickListener;
    }

    private OnEditTextDialogClickListener onEditTextDialogClickListener;

    public SelectTaskReceiverDialog(@NonNull Context context, String receiverName,
                                    String receiverAvatar, String taskContent) {
        super(context, R.style.EditTextDialogStyle);
        this.context = context;
        this.receiverAvatar = receiverAvatar;
        this.receiverName = receiverName;
        this.taskContent = taskContent;
        initView();
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_task_receiver,
                null);
        ImageView imageView = view.findViewById(R.id.iv_receiver_avatar);
        TextView textView = view.findViewById(R.id.tv_receiver_name);
        TextView textView1 = view.findViewById(R.id.tv_task_content);
        Glide.with(context).load(receiverAvatar).into(imageView);
        textView.setText(receiverName);
        textView1.setText(taskContent);
        super.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_send:
                onEditTextDialogClickListener.doCreate();
                break;
            case R.id.tv_cancel:
                this.dismiss();
                break;
        }
    }

    public interface OnEditTextDialogClickListener {

        void doCreate();
    }
}

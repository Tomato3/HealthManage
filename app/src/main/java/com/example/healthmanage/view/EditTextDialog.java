package com.example.healthmanage.view;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;
import com.jeremyliao.liveeventbus.LiveEventBus;

import static com.example.healthmanage.utils.Constants.HTAG;

public class EditTextDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tvCreate, tvUpdate, tvSend, tvCancel, tvTitle;
    private EditText etComment;
    private int dialogLayout;
    private String receiverName, receiverAvatar, taskContent, memberName;
    private OnEditTextDialogClickListener onEditTextDialogClickListener;

    public EditTextDialog(@NonNull Context context, @LayoutRes int dialogLayout, String memberName) {
        super(context, R.style.EditTextDialogStyle);
        this.context = context;
        this.dialogLayout = dialogLayout;
        this.memberName = memberName;
        initView();

        LiveEventBus.get("close", Boolean.class)
                .observe((LifecycleOwner) context, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean s) {
                        dismiss();
                    }
                });
    }

    public EditTextDialog(@NonNull Context context, @LayoutRes int dialogLayout, String receiverName,
                          String receiverAvatar, String taskContent) {
        super(context, R.style.EditTextDialogStyle);
        this.context = context;
        this.dialogLayout = dialogLayout;
        this.receiverAvatar = receiverAvatar;
        this.receiverName = receiverName;
        this.taskContent = taskContent;
        initView();

        LiveEventBus.get("close", Boolean.class)
                .observe((LifecycleOwner) context, new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean s) {
                        dismiss();
                        Log.d(HTAG, "onChanged==========>: ");
                    }
                });
    }

    public void initView() {
        View view = LayoutInflater.from(context).inflate(dialogLayout, null);
        etComment = view.findViewById(R.id.et_comment);
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(this::onClick);
        switch (dialogLayout) {
            case R.layout.dialog_create_task:
                tvCreate = view.findViewById(R.id.tv_create);
                tvCreate.setOnClickListener(this::onClick);
                tvTitle = view.findViewById(R.id.tv_title);
                if (memberName != null) {
                    tvTitle.setText(memberName);
                }
                break;
            case R.layout.dialog_update_task:
                tvUpdate = view.findViewById(R.id.tv_update);
                tvUpdate.setOnClickListener(this::onClick);
                break;
            case R.layout.dialog_select_task_receiver:
                ImageView imageView = view.findViewById(R.id.iv_receiver_avatar);
                TextView textView = view.findViewById(R.id.tv_receiver_name);
                TextView textView1 = view.findViewById(R.id.tv_task_content);
                Glide.with(context).load(receiverAvatar).into(imageView);
                textView.setText(receiverName);
                textView1.setText(taskContent);
                tvSend = view.findViewById(R.id.tv_send);
                tvSend.setOnClickListener(this::onClick);
                break;
        }
        super.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create:
            case R.id.tv_update:
                onEditTextDialogClickListener.doCreate(etComment.getText().toString());
                break;
            case R.id.tv_send:
                onEditTextDialogClickListener.doSend();
                break;
            case R.id.tv_cancel:
                this.dismiss();
                break;
        }
    }

    public interface OnEditTextDialogClickListener {

        void doCreate(String content);

        void doSend();
    }

    public OnEditTextDialogClickListener getOnEditTextDialogClickListener() {
        return onEditTextDialogClickListener;
    }

    public void setOnEditTextDialogClickListener(OnEditTextDialogClickListener onEditTextDialogClickListener) {
        this.onEditTextDialogClickListener = onEditTextDialogClickListener;
    }


}

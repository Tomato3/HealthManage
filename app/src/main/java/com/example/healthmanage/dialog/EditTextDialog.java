package com.example.healthmanage.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import com.bumptech.glide.Glide;
import com.example.healthmanage.R;
import com.example.healthmanage.utils.LocationUtil;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;
import com.jeremyliao.liveeventbus.LiveEventBus;

import java.util.ArrayList;
import java.util.List;

public class EditTextDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tvCreate, tvUpdate, tvSend, tvCancel, tvTitle, tvTime, tvLocation;
    private EditText etComment, etResult;
    private int dialogLayout;
    private String receiverName, receiverAvatar, taskContent, memberName, serviceItem;
    private List<String> contentList;
    private OnEditTextDialogClickListener onEditTextDialogClickListener;

    public EditTextDialog(@NonNull Context context, @LayoutRes int dialogLayout, String memberName) {
        super(context, R.style.EditTextDialogStyle);
        this.context = context;
        this.dialogLayout = dialogLayout;
        this.memberName = memberName;
        initView();

        LiveEventBus.get("close", Boolean.class)
                .observe((LifecycleOwner) context, s -> dismiss());
    }

    public EditTextDialog(@NonNull Context context, @LayoutRes int dialogLayout,
                          String memberName, String serviceItem) {
        super(context, R.style.EditTextDialogStyle);
        this.context = context;
        this.dialogLayout = dialogLayout;
        this.memberName = memberName;
        this.serviceItem = serviceItem;
        initView();

        LiveEventBus.get("close", Boolean.class)
                .observe((LifecycleOwner) context, s -> dismiss());
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
                .observe((LifecycleOwner) context, s -> dismiss());
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
            case R.layout.dialog_upload_service_result:
                TextView tvMember, tvItem, tvUpload;
                tvMember = view.findViewById(R.id.tv_member);
                tvTime = view.findViewById(R.id.tv_time);
                tvLocation = view.findViewById(R.id.tv_location);
                tvItem = view.findViewById(R.id.tv_item);
                tvUpload = view.findViewById(R.id.tv_upload);
                etResult = view.findViewById(R.id.et_result);
                if (memberName != null) {
                    tvMember.setText(memberName);
                }
                tvTime.setText("服务时间：点击获取当前时间");
                tvLocation.setText("服务地点：点击获取当前地点");
                tvItem.setText("服务项目：" + serviceItem);
                tvTime.setOnClickListener(this::onClick);
                tvLocation.setOnClickListener(this::onClick);
                tvUpload.setOnClickListener(this::onClick);
                break;
            case R.layout.dialog_update_number:
                TextView textView2 = view.findViewById(R.id.tv_update);
                textView2.setOnClickListener(this::onClick);
                break;
        }
        super.setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_create:
            case R.id.tv_update:
                contentList = new ArrayList<>();
                contentList.add(etComment.getText().toString());
                onEditTextDialogClickListener.doCreate(contentList);
                break;
            case R.id.tv_send:
                onEditTextDialogClickListener.doSend();
                break;
            case R.id.tv_time:
                if (tvTime != null) {
                    tvTime.setText("服务时间：" + ToolUtil.getCurrentTime());
                }
                break;
            case R.id.tv_location:
                LocationUtil.getAddress(context);
                if (tvLocation != null && LocationUtil.address.getValue() != null) {
                    tvLocation.setText("服务地点：" + LocationUtil.address.getValue());
                }
                break;
            case R.id.tv_upload:
                if (TextUtils.isEmpty(etResult.getText())) {
                    ToastUtil.showShort("请添加服务结果");
                } else {
                    contentList = new ArrayList<>();
                    contentList.add(tvTime.getText().toString().substring(5));
                    contentList.add(tvLocation.getText().toString().substring(5));
                    contentList.add(etResult.getText().toString());
                    onEditTextDialogClickListener.doCreate(contentList);
                }
                break;
            case R.id.tv_cancel:
                this.dismiss();
                break;
        }
    }

    public interface OnEditTextDialogClickListener {

        void doCreate(List<String> content);

        void doSend();

    }

    public OnEditTextDialogClickListener getOnEditTextDialogClickListener() {
        return onEditTextDialogClickListener;
    }

    public void setOnEditTextDialogClickListener(OnEditTextDialogClickListener onEditTextDialogClickListener) {
        this.onEditTextDialogClickListener = onEditTextDialogClickListener;
    }


}

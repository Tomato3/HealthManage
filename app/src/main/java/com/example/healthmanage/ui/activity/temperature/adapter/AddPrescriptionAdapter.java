package com.example.healthmanage.ui.activity.temperature.adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.healthmanage.R;
import com.example.healthmanage.ui.activity.temperature.InsertPrescriptionBean;
import com.example.healthmanage.ui.activity.temperature.PopItem;
import com.example.healthmanage.ui.activity.temperature.ReceivePatientBean;
import com.example.healthmanage.utils.ToastUtil;
import com.example.healthmanage.utils.ToolUtil;

import java.util.ArrayList;
import java.util.List;

public class AddPrescriptionAdapter extends RecyclerView.Adapter<AddPrescriptionAdapter.ViewHolder> {
    private AppCompatActivity context;
    private OnListRemovedListener mListener;
    //这两个静态变量来区分是normalView还是footView。
    private static final int NORMAL_VIEW = 0;
    private static final int FOOT_VIEW = 1;
    private PopupWindow popupWindow;
    private ItemPopAdapter popAdapter;
    private List<PopItem> units = new ArrayList<>();
    private List<PopItem> modes = new ArrayList<>();
    private List<PopItem> times = new ArrayList<>();
    private List<PopItem> frequency = new ArrayList<>();
    String numType;
    public List<ReceivePatientBean.DrugListBean> list = new ArrayList<>();
    int oldPosition = -1;

    public AddPrescriptionAdapter(AppCompatActivity context, List<ReceivePatientBean.DrugListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnListRemovedListener(OnListRemovedListener listener){
        this.mListener=listener;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 ){
            return FOOT_VIEW;
        }
        return NORMAL_VIEW;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL_VIEW){
            View view = LayoutInflater.from(context).inflate(R.layout.item_rp,parent,false);
            return new AddPrescriptionAdapter.ViewHolder(view,viewType);
        }else {
            View footView = LayoutInflater.from(context).inflate(R.layout.footview,parent,false);
            return new AddPrescriptionAdapter.ViewHolder(footView,viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (getItemViewType(position) == NORMAL_VIEW){
            holder.editDrugName.setTag(position);
            holder.editDrugName.addTextChangedListener(new MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, ViewHolder holder) {
                    int position = (int) holder.editDrugName.getTag();
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(position);
                    prescriptionBean.setName(s.toString());
                    list.set(position,prescriptionBean);
                }
            });
            holder.editDrugNum.setTag(position);
            holder.editDrugNum.addTextChangedListener(new MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, ViewHolder holder) {
                    int position = (int) holder.editDrugNum.getTag();
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(position);
                    if (s.toString().isEmpty()){
                        prescriptionBean.setNumber(0);
                    }else {
                        prescriptionBean.setNumber(Integer.valueOf(s.toString()));
                    }
                    list.set(position,prescriptionBean);
                }
            });

            holder.tvChooseDrugNumType.setTag(position);
            int typeTag = (int) holder.tvChooseDrugNumType.getTag();
            ReceivePatientBean.DrugListBean prescriptionBean = list.get(typeTag);
            prescriptionBean.setUnit(list.get((int) holder.tvChooseDrugNumType.getTag()).getUnit());
            list.set(typeTag,prescriptionBean);
            holder.tvChooseDrugNumType.setText(list.get((int) holder.tvChooseDrugNumType.getTag()).getUnit());

            holder.tvChooseDrugNumType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (units != null && units.size()>0){
                        units.clear();
                    }
                    units.add(new PopItem("盒"));
                    units.add(new PopItem("包"));
                    units.add(new PopItem("瓶"));
                    units.add(new PopItem("管"));
                    units.add(new PopItem("支"));
                    units.add(new PopItem("袋"));
                    popWindow(holder,list.get((int)holder.tvChooseDrugNumType.getTag()).getUnit(),0);
                }
            });
            holder.tvChooseTakeMethod.setTag(position);
            int methodTag = (int) holder.tvChooseTakeMethod.getTag();
            ReceivePatientBean.DrugListBean prescriptionBean1 = list.get(typeTag);
            prescriptionBean1.setUseMode(list.get((int) holder.tvChooseTakeMethod.getTag()).getUseMode());
            list.set(methodTag,prescriptionBean1);
            holder.tvChooseTakeMethod.setText(list.get((int) holder.tvChooseTakeMethod.getTag()).getUseMode());
            holder.tvChooseTakeMethod.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (modes != null && modes.size()>0){
                        modes.clear();
                    }
                    modes.add(new PopItem("口服"));
                    modes.add(new PopItem("涂擦"));
                    modes.add(new PopItem("滴眼"));
                    modes.add(new PopItem("滴耳"));
                    modes.add(new PopItem("嚼服"));
                    modes.add(new PopItem("口含"));
                    popWindow(holder,list.get((int) holder.tvChooseTakeMethod.getTag()).getUseMode(),1);
                }
            });
            holder.tvChooseTakeTime.setTag(position);
            int timeTag = (int) holder.tvChooseTakeTime.getTag();
            ReceivePatientBean.DrugListBean prescriptionBean2 = list.get(timeTag);
            prescriptionBean2.setUseTime(list.get((int) holder.tvChooseTakeTime.getTag()).getUseTime());
            list.set(timeTag,prescriptionBean2);
            holder.tvChooseTakeTime.setText(list.get((int) holder.tvChooseTakeTime.getTag()).getUseTime());
            holder.tvChooseTakeTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (times != null && times.size()>0){
                        times.clear();
                    }
                    times.add(new PopItem("睡前"));
                    times.add(new PopItem("饭前"));
                    times.add(new PopItem("饭后"));
                    times.add(new PopItem("吃饭时"));
                    times.add(new PopItem("需要时"));
                    times.add(new PopItem("必要时"));
                    times.add(new PopItem("上午(7-12)"));
                    times.add(new PopItem("中午(12-17)"));
                    times.add(new PopItem("晚上(17-23)"));
                    popWindow(holder,list.get((int) holder.tvChooseTakeTime.getTag()).getUseTime(),2);
                }
            });
            holder.tvChooseTakeRate.setTag(position);
            int rateTag = (int) holder.tvChooseTakeRate.getTag();
            ReceivePatientBean.DrugListBean prescriptionBean3 = list.get(rateTag);
            prescriptionBean3.setUseFrequency(list.get((int) holder.tvChooseTakeRate.getTag()).getUseFrequency());
            list.set(rateTag,prescriptionBean3);
            holder.tvChooseTakeRate.setText(list.get((int) holder.tvChooseTakeRate.getTag()).getUseFrequency());
            holder.tvChooseTakeRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (frequency != null && frequency.size()>0){
                        frequency.clear();
                    }
                    frequency.add(new PopItem("一天三次"));
                    frequency.add(new PopItem("一天一次"));
                    frequency.add(new PopItem("一天两次"));
                    frequency.add(new PopItem("一天四次"));
                    frequency.add(new PopItem("需要时"));
                    frequency.add(new PopItem("必要时"));
                    popWindow(holder,list.get((int) holder.tvChooseTakeRate.getTag()).getUseFrequency(),3);
                }
            });
            holder.tvDel.setTag(position);
            holder.tvDel.setOnClickListener(new MyOnClickListener(holder) {
                @Override
                public void onClick(View v, ViewHolder holder) {
                    if (list.size()>1){
                        if (mListener != null){
                            int position = (int) holder.tvDel.getTag();
                            list.remove(position);
                            mListener.onRemoved();
                        }
                    }else {
                        ToastUtil.showShort("模版不能删除");
                    }

                }
            });

            holder.editDrugName.setText(prescriptionBean.getName());
            if (prescriptionBean.getNumber()==0){
                holder.editDrugNum.setText(null);
            }else {
                holder.editDrugNum.setText(String.valueOf(prescriptionBean.getNumber()));
            }
        }else {
            holder.footView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.add(new ReceivePatientBean.DrugListBean("盒","口服","睡前","一天三次"));
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void popWindow(ViewHolder holder, String unit, int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_take_rb, null, false);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.style.Animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popupWindow.showAtLocation(context.getWindow().getDecorView(), Gravity.BOTTOM, 0,
                ToolUtil.getNavigationBarHeight(context));
        popupWindow.setFocusable(true);
        bgAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                bgAlpha(1);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_pop);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        if (type==0){
            for (int i = 0; i < units.size(); i++) {
                if (unit.equals(units.get(i).getName())){
                    units.get(i).setSelect(true);
                }else {
                    units.get(i).setSelect(false);
                }
            }
            popAdapter = new ItemPopAdapter(units,context);
            recyclerView.setAdapter(popAdapter);
            popAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position != 0){
                        units.get(0).setSelect(false);
                    }
                    if (oldPosition != -1 && oldPosition!=position){
                        units.get(oldPosition).setSelect(false);
                    }
                    units.get(position).setSelect(true);
                    oldPosition = position;
                    int tag = (int) holder.tvChooseDrugNumType.getTag();
                    numType = units.get(position).getName();
                    holder.tvChooseDrugNumType.setText(numType);
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(tag);
                    prescriptionBean.setUnit(numType);
                    list.set(tag,prescriptionBean);
                    popupWindow.dismiss();
                }
            });
        }else if (type == 1){
            for (int i = 0; i < modes.size(); i++) {
                if (unit.equals(modes.get(i).getName())){
                    modes.get(i).setSelect(true);
                }else {
                    modes.get(i).setSelect(false);
                }
            }
            popAdapter = new ItemPopAdapter(modes,context);
            recyclerView.setAdapter(popAdapter);
            popAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position != 0){
                        modes.get(0).setSelect(false);
                    }
                    if (oldPosition != -1 && oldPosition!=position){
                        modes.get(oldPosition).setSelect(false);
                    }
                    modes.get(position).setSelect(true);
                    oldPosition = position;
                    int tag = (int) holder.tvChooseDrugNumType.getTag();
                    numType = modes.get(position).getName();
                    holder.tvChooseTakeMethod.setText(numType);
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(tag);
                    prescriptionBean.setUseMode(numType);
                    list.set(tag,prescriptionBean);
                    popupWindow.dismiss();
                }
            });
        }else if (type == 2){
            for (int i = 0; i < times.size(); i++) {
                if (unit.equals(times.get(i).getName())){
                    times.get(i).setSelect(true);
                }else {
                    times.get(i).setSelect(false);
                }
            }
            popAdapter = new ItemPopAdapter(times,context);
            recyclerView.setAdapter(popAdapter);
            popAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position != 0){
                        times.get(0).setSelect(false);
                    }
                    if (oldPosition != -1 && oldPosition!=position){
                        times.get(oldPosition).setSelect(false);
                    }
                    times.get(position).setSelect(true);
                    oldPosition = position;
                    int tag = (int) holder.tvChooseDrugNumType.getTag();
                    numType = times.get(position).getName();
                    holder.tvChooseDrugNumType.setText(numType);
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(tag);
                    prescriptionBean.setUseTime(numType);
                    list.set(tag,prescriptionBean);
                    popupWindow.dismiss();
                }
            });
        }else {
            for (int i = 0; i < frequency.size(); i++) {
                if (unit.equals(frequency.get(i).getName())){
                    frequency.get(i).setSelect(true);
                }else {
                    frequency.get(i).setSelect(false);
                }
            }
            popAdapter = new ItemPopAdapter(frequency,context);
            recyclerView.setAdapter(popAdapter);
            popAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (position != 0){
                        frequency.get(0).setSelect(false);
                    }
                    if (oldPosition != -1 && oldPosition!=position){
                        frequency.get(oldPosition).setSelect(false);
                    }
                    frequency.get(position).setSelect(true);
                    oldPosition = position;
                    int tag = (int) holder.tvChooseDrugNumType.getTag();
                    numType = frequency.get(position).getName();
                    holder.tvChooseDrugNumType.setText(numType);
                    ReceivePatientBean.DrugListBean prescriptionBean = list.get(tag);
                    prescriptionBean.setUseFrequency(numType);
                    list.set(tag,prescriptionBean);
                    popupWindow.dismiss();
                }
            });
        }

    }

    private void bgAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    @Override
    public int getItemCount() {
        if (list.size()>0){
            return list.size()+1;
        }else {
            return list.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        EditText editDrugName;
        EditText editDrugNum;
        TextView tvChooseDrugNumType;
        TextView tvChooseTakeMethod;
        TextView tvChooseTakeTime;
        TextView tvChooseTakeRate;
        TextView tvDel;
        ConstraintLayout footView;

        public ViewHolder(@NonNull View itemView,int viewType) {
            super(itemView);
            if (viewType == NORMAL_VIEW){
                editDrugName = itemView.findViewById(R.id.edit_drugs_name);
                editDrugNum = itemView.findViewById(R.id.edit_drugs_num);
                tvChooseDrugNumType = itemView.findViewById(R.id.tv_select_num_type);
                tvChooseTakeMethod = itemView.findViewById(R.id.tv_take_method);
                tvChooseTakeTime = itemView.findViewById(R.id.tv_take_time);
                tvChooseTakeRate = itemView.findViewById(R.id.tv_take_rate);
                tvDel = itemView.findViewById(R.id.tv_delete_rp);
            }else if (viewType == FOOT_VIEW){
                footView = (ConstraintLayout) itemView;
            }
        }
    }

    private abstract class MyTextWatcher implements TextWatcher {
        private ViewHolder mHolder;

        public MyTextWatcher(ViewHolder holder) {
            this.mHolder=holder;
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            afterTextChanged(s, mHolder);
        }
        public abstract void afterTextChanged(Editable s, ViewHolder holder);
    }

    private abstract class MyOnClickListener implements View.OnClickListener {

        private ViewHolder mHolder;

        public MyOnClickListener(ViewHolder holder) {
            this.mHolder=holder;
        }

        @Override
        public void onClick(View v) {
            onClick(v, mHolder);
        }
        public abstract void onClick(View v, ViewHolder holder);

    }

    //删除操作回调
    public interface OnListRemovedListener{
        public void onRemoved();
    }
}

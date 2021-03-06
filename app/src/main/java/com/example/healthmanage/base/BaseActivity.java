 package com.example.healthmanage.base;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.healthmanage.utils.DialogUtil;
import com.example.healthmanage.utils.ToastUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Map;

/**
 * BaseActivity
 *
 * @param <DB>
 * @param <VM>
 */
public abstract class BaseActivity<DB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IView {

    protected DB dataBinding;
    protected VM viewModel;
    protected int viewModelId;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //传入数据
        initViewParameters();

        //初始化DataBinding和ViewModel
        initDataBindingAndViewModel(savedInstanceState);

        //监听
        initViewListener();

        //初始化数据
        initData();

        //注册观察者，观察UIChange事件，以便做出响应
        registerUIChangeEventObserver();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        //设置强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void initViewListener() {

    }

    protected abstract void initData();

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

//    /**
//     * 跳转容器页面
//     *
//     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
//     */
//    public void startContainerActivity(String canonicalName) {
//        startContainerActivity(canonicalName, null);
//    }
//
//    /**
//     * 跳转容器页面
//     *
//     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
//     * @param bundle        跳转所携带的信息
//     */
//    public void startContainerActivity(String canonicalName, Bundle bundle) {
//        Intent intent = new Intent(this, ContainerActivity.class);
//        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
//        if (bundle != null) {
//            intent.putExtra(ContainerActivity.BUNDLE, bundle);
//        }
//        startActivity(intent);
//    }

    protected void registerUIChangeEventObserver() {

        viewModel.getUiChangeEvent().getToastTxt().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast toast = Toast.makeText(BaseActivity.this, s,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        viewModel.getUiChangeEvent().getDialogShowEvent().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                DialogUtil.showDialogWait1(BaseActivity.this, s);
            }
        });

        viewModel.getUiChangeEvent().getDialogDismissEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                DialogUtil.stopProgressDlg();
            }
        });

        viewModel.getUiChangeEvent().getStartActivityEvent().observe(this, new Observer<Map<String, Object>>() {
            @Override
            public void onChanged(Map<String, Object> stringObjectMap) {
                Class<?> clz = (Class<?>) stringObjectMap.get(BaseViewModel.ParameterField.CLASS);
                Bundle bundle = (Bundle) stringObjectMap.get(BaseViewModel.ParameterField.BUNDLE);
                startActivity(clz, bundle);
            }
        });

        viewModel.getUiChangeEvent().getToastType().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case 0:
                        ToastUtil.showSucceedTipToast(getBaseContext(),
                                viewModel.getUiChangeEvent().getToastTxt().getValue());
                        break;
                    case 1:
                        ToastUtil.showFailedTipToast(getBaseContext(),
                                viewModel.getUiChangeEvent().getToastTxt().getValue());
                        break;
                    case 10000:
                        ToastUtil.showLoadingTipToast(getBaseContext(),
                                viewModel.getUiChangeEvent().getToastTxt().getValue());
                        break;
                }
            }
        });

//        //跳入ContainerActivity
//        viewModel.getUiChangeEvent().getStartContainerActivityEvent().observe(this, new Observer<Map<String,
//                Object>>() {
//            @Override
//            public void onChanged(@Nullable Map<String, Object> params) {
//                String canonicalName = (String) params.get(BaseViewModel.ParameterField.CANONICAL_NAME);
//                Bundle bundle = (Bundle) params.get(BaseViewModel.ParameterField.BUNDLE);
//                startContainerActivity(canonicalName, bundle);
//            }
//        });
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    protected abstract int initVariableId();

    protected VM initViewModel() {
        return null;
    }

    /**
     * 初始化根布局
     *
     * @param savedInstanceState
     * @return 布局layout的Id
     */
    protected abstract @LayoutRes
    int setContentViewSrc(Bundle savedInstanceState);

    @Override
    public void initViewParameters() {
    }

    @Override
    public void initDataBindingAndViewModel(Bundle savedInstanceState) {
        //初始化DataBinding
        dataBinding = DataBindingUtil.setContentView(this, setContentViewSrc(savedInstanceState));
        viewModelId = initVariableId();
        //初始化LiveData
        viewModel = initViewModel();
        if (viewModel == null) {
            Class classType;
            Type genericSuperclass = getClass().getGenericSuperclass();
            if (genericSuperclass instanceof ParameterizedType) {
                classType = (Class) ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1];
            } else {
                classType = BaseViewModel.class;
            }
            viewModel = (VM) ViewModelProviders.of(this).get(classType);
        }
        //dataBinding与viewModel关联，使dataBinding与viewModel关联，dataBinding能不通过反射
        // 访问到ViewModel中的值
        dataBinding.setVariable(viewModelId, viewModel);
        //将当前当前activity设为生命周期的拥有者
        dataBinding.setLifecycleOwner(this);
    }


}

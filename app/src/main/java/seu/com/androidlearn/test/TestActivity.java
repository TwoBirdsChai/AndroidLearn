package seu.com.androidlearn.test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.MyApplication;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/22.
 */
@Route(path = "/test/testactivity")
public class TestActivity extends AppCompatActivity {

    @BindView(R.id.btnRecreate)
    Button btnRecreate;
    @BindView(R.id.btnTestShade)
    Button btnTestShade;
    @BindView(R.id.viewShade)
    View viewShade;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        Log.e("Tag", "onCreate: " + this.hashCode() + " : " +this.btnRecreate);
//        testPackageManager();
        testIntent();
    }

    @OnClick(R.id.viewShade)
    public void clickShadeView() {
        Toast.makeText(this, "shade View", Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.btnTestShade)
    public void clickShadeButton() {
        Toast.makeText(this, "shade button", Toast.LENGTH_LONG).show();
    }
    private void testIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            Log.e("Tag", "bundle can be null");
        } else {
            Log.e("Tag", "bundle will not be null");
        }
    }

    /**
     * 获取本地不存在的包名，直接奔溃，这就导致了downloadmanager奔溃的问题，有些手机就直接阉割了。。。
     *
     */
    public void testPackageManager() {
        PackageManager manager = MyApplication.getInstance().getPackageManager();
        int state = manager.getApplicationEnabledSetting("com.android.providers.downloads");
        int state1 = manager.getApplicationEnabledSetting("com.seu.cc");
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
            Log.e("Tag", "error");
        }
        Log.e("Tag", "true");
    }

    @Override
    protected void onDestroy() {
        Log.e("Tag", "onDestroy: " + this.btnRecreate);
        super.onDestroy();
    }

    @Override
    public void recreate() {
        Log.e("Tag", "recreate: " + this.btnRecreate);
        super.recreate();
    }

    @OnClick(R.id.btnRecreate)
    public void clickRecreate() {
        recreate();
    }
}

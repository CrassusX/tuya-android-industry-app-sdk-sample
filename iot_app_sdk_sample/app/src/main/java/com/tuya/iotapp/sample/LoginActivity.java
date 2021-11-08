package com.tuya.iotapp.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;

import com.tuya.iotapp.common.kv.KvManager;
import com.tuya.iotapp.common.utils.L;
import com.tuya.iotapp.jsonparser.api.JsonParser;
import com.tuya.iotapp.network.api.RegionHostConst;
import com.tuya.iotapp.network.api.TYNetworkManager;
import com.tuya.iotapp.network.interceptor.token.AccessTokenManager;
import com.tuya.iotapp.network.interceptor.token.bean.TokenBean;
import com.tuya.iotapp.network.response.BizResponse;
import com.tuya.iotapp.network.response.ResultListener;
import com.tuya.iotapp.sample.env.Constant;
import com.tuya.iotapp.sample.env.EnvUtils;
import com.tuya.iotapp.user.api.TYUserManager;

/**
 * LoginActivity
 *
 * @author xiaoxiao <a href="mailto:developer@tuya.com"/>
 * @since 2021/3/20 2:43 PM
 */
public class LoginActivity extends AppCompatActivity {
    private EditText mEtUserName;
    private EditText mEtPassword;

    private Button mBtnLogin;
    private TokenBean mTokenBean;
    private Context context;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //todo disable switch
        L.setLogSwitcher(true);

        if (!TextUtils.isEmpty(AccessTokenManager.INSTANCE.getUid())) {
            startActivity(new Intent(this, MainManagerActivity.class));
            finish();
        }

        context = this;
        initView();
    }

    private void initView() {
        mEtUserName = (EditText) findViewById(R.id.et_user_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);

        Toolbar toolbar = findViewById(R.id.topAppBar);
        userName = mEtUserName.getText().toString();
        password = mEtPassword.getText().toString();

        AppCompatSpinner spEndpoint = findViewById(R.id.sp_region);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.region_host,
                android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEndpoint.setAdapter(adapter);
        spEndpoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EnvUtils.ChooseRegionHost(view.getContext(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = mEtUserName.getText().toString();
                password = mEtPassword.getText().toString();

                TYNetworkManager.setRegionHost(RegionHostConst.REGION_HOST_CN);

                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(v.getContext(), "userName can not null", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(v.getContext(), "password can not null", Toast.LENGTH_SHORT).show();
                }
                TYUserManager.getUserBusiness().login(userName, password, new ResultListener<BizResponse>() {
                    @Override
                    public void onFailure(String s, String s1) {
                        L.d("login", "fail code: " + s + " msg:" + s1);
                        Toast.makeText(v.getContext(), "login fail : " + s1, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(BizResponse bizResponse) {
                        L.d("login", "success : ");
                        String convertString = JsonParser.convertUnderLineToHump(bizResponse.getResult().toString());
                        mTokenBean = JsonParser.parseAny(convertString, TokenBean.class);
                        Intent intent = new Intent(context, MainManagerActivity.class);

                        // Store Token
                        AccessTokenManager.INSTANCE.storeInfo(mTokenBean,
                                bizResponse.getT());
                        KvManager.set(Constant.KV_USER_NAME, userName);

                        intent.putExtra(Constant.INTENT_KEY_USER_NAME, userName);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}

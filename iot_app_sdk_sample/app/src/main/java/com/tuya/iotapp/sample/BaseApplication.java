package com.tuya.iotapp.sample;

import android.app.Application;

import com.tuya.iotapp.componet.TuyaIoTSDK;
import com.tuya.iotapp.network.api.RegionHostConst;

/**
 * Base Application
 *
 * @author 乾启 <a href="mailto:sunrw@tuya.com">Contact me.</a>
 * @since 2021/3/24 11:05 AM
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //todo:replace appId and appSecret
        TuyaIoTSDK.builder().init(this, "wujyju7ub2oildvk6hxp", "ad606b6ce4a245a589c2b4a376ed4f24")
                .hostConfig(RegionHostConst.REGION_HOST_CN)
                .debug(true)
                .build();

    }
}

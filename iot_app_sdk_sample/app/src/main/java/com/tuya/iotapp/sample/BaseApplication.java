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
        TuyaIoTSDK.builder().init(this, "jyekwgamb2vlb98nbjtt", "6ce3d1cc05a543cc82a6d9b62a379da0")
                .hostConfig(RegionHostConst.REGION_HOST_CN)
                .debug(true)
                .build();

    }
}

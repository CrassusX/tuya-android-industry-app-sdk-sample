# Android Industry App SDK Sample

[English](README.md) | [中文版](README_zh.md)

## Functional Overview

This Industry App SDK Sample provides examples of basic functions such as device network configuration, login and registration, and asset management. Sample is based on the Restful API interface of [Tuya Open API](https://developer.tuya.com/en/docs/cloud/) to implement related functions. Tuya Industry App SDK is an important part of the Tuya SaaS Development Framework product series.

Industry App SDK Sample has the following functions:

- User module (login, logout)
- Asset module (asset query, selection)
- Equipment distribution module (AP, EZ, wired, zigbee subdevice, scan code, NB scan code mode)
- Equipment module (equipment query, equipment unbinding)

## API Reference Docs

Android Industry App SDK API Reference: https://tuya.github.io/tuya-android-industry-app-sdk-sample/

## Quick Start

### Step 1: Obtain the SHA1 key

1. Download the Github sample project.

2. Generate a **keyStore** file for the Sample and record the file password. The operation steps are as follows:
	> **Note:** The suffix of the currently generated file is `.jks` instead of the previous `.keyStore`.
	1. Click Build.
	![image.png](https://airtake-public-data-1254153901.cos.ap-shanghai.myqcloud.com/goat/20210326/3c802b06784d4a6fac9a7a744f35803d.png)
	2. Select **Generate Signed APK** in the drop-down box.
	3. Select APK and follow the prompts to create a **keyStore** file.
	4. According to the information in keyStore file, configure **signingConfigs** in the **build.gradle**. (storeFile specifies the storage location of `.jks`)
	![image.png](https://images.tuyacn.com/app/iotappsample/key_sign.png)
	
3. Execute the command in the same level directory to filter out the generated `.jks` files.

	```java
	keytool -list -v -keystore xx.jks
	enter password
	```

4. Obtain the SHA1 key in the file.

	```java
	Valid from Tue Mar 16 10:22:14 CST 2021 to Sun Mar 06 10:22:14 CST 2061
	Certificate fingerprint:
	MD5: 6B:8C:94:15:35:7C:2E:E8:6E:76:7F:8C:F9:4B:05:BC
	SHA1: 8F:AC:5D:50:65:22:C2:2A:E4:96:3D:8F:9E:DC:5B:43:11:49:55:B1
	SHA256: 0B:98:89:D8:D5:FC:B1:23:9A:76:B1:2B:8F:4C:5E:24:BF:E7:60:E8:FF:EF:E9:40: 48:14:60:D3:62:00:5C:5F
	Signature algorithm name: SHA256withRSA
	Subject public key algorithm: 2048-bit RSA key
	Version: 3
	```

5. Get the package name (as shown below: you can also customize the package name)

   <img src="https://images.tuyacn.com/app/iotappsample/applicationId.png" style="zoom:50%;" />

### Step 2: Configure Cloud Development Project

1. Log in to Tuya Smart [IoT Platform](https://iot.tuya.com/cloud/) and verify your account with enterprise authentication.

2. Create **Industry Solutions** type project.
	1. On the **Projects** page, click **Create**.
	![Create Project](https://images.tuyacn.com/app/iotappsample/en/cr_product_new.png)
	2. On the **Create Project** page, configure **Project Name**, **Project Type**, **Description** and **Industry**.
	
       >**Description:** Select **Industry Solutions** in **Industry Type** . That is, based on assets and user systems, build IoT SaaS projects in any industry scenario. Multiple applications can be created under industry projects to share the same assets and user resources.
		
	3. Click **OK** to complete the project creation.

3. Create an application.
	1. In the **Projects**>**My Project** area, click the target project.
	2. In the top navigation bar, click **Applications**>**App**.
		![image.png](https://images.tuyacn.com/app/iotappsample/en/cr_app_new.png)
	3. In the **Add Application** window, configure the application information. Select the Android application type.
		![image.png](https://images.tuyacn.com/app/iotappsample/en/cr_app_info_new.png)
		- SHA1: Copy SHA1 from Step 1-4.
		- Application id:`com.tuya.iotapp.sample`.

4. Create users.
	1. In the **Projects**>**My Project** area, click the target project.
	2. In the top navigation bar, click **Users**.
	![image.png](https://images.tuyacn.com/app/iotappsample/en/cr_user_new.png)
	3. Click **Add User**.
	4. In the **Add User** window, enter the user account and password, and click **OK**.

5. Create assets.
	1. In the **Projects**>**My Project** area, click the target project.
	2. In the top navigation bar, click **Assets**.
	![image.png](https://images.tuyacn.com/app/iotappsample/en/addAsset.png)
	3. Click **New Asset**.
	4. In the **New Asset** window, enter the asset name and click **OK**.

6. Asset authorized users.
	1. In the **Projects**>**My Project** area, click the target project.
	2. In the top navigation bar, click **Assets**.
	![image.png](https://images.tuyacn.com/app/iotappsample/en/cr_auth_new.png)
	3. Click **Manage** under the **Operation** column of the target project.
	4. On the **Authorized User** tab, click **Add Authorization**.
	5. In the **Add Authorized User** window, add the account information to be authorized, and click **OK**.

### Step 3: Project configuration (Client ID and Client Secret)

1. Obtain the Client ID and Client Secret of the created Andriod application on the **Applications**>**App**.
<img src="https://images.tuyacn.com/app/iotappsample/en/client_secret_new.png" style="zoom:50%;" />
2. Paste it to the corresponding position of the Sample code, as shown in the figure below.
<img src="https://images.tuyacn.com/app/iotappsample/client_init.png" style="zoom:50%;" />

### Step 4: API Products Subscription

**1.** Go to **Projects** > **API Products** > **All Products**, click **Industry Project**, and subscribe to your desired API products.
![image.png](https://images.tuyacn.com/app/Hanh/APIproducts.png)
	
**You need to subscribe to these API Products to use this sample:**
	
![image.png](https://images.tuyacn.com/app/hass/open_api_products.jpg)
	
**2.** Go to **Projects** > **API Products** > **Subscribed Products**. Click one of the API products to subscribe.
	
![image.png](https://images.tuyacn.com/app/Hanh/buyapi.png)
	
**3.** Click **Project** > **New Authorization** to authorize your project to use this API.

![image.png](https://images.tuyacn.com/app/Hanh/tip.png)
![image.png](https://images.tuyacn.com/app/Hanh/newauthorization.png)
![image.png](https://images.tuyacn.com/app/Hanh/apiproductauthorization.png)

## Sample Features Overview

### Account login

<img src="https://images.tuyacn.com/app/iotappsample/en/login_region.png" width="30%" /> 
<img src="https://images.tuyacn.com/app/iotappsample/en/choose_login_region.png" width="30%" /> 

<img src="https://images.tuyacn.com/app/IoTAppSDK/androidApp_main_manager.png" width="30%" />

### Asset selection

<img src="https://images.tuyacn.com/app/iotappsample/en/asset_list.png" width="30%" /> 
<img src="https://images.tuyacn.com/app/iotappsample/en/asset_choose.png" width="30%" />

### Distribution network operation

- AP Pairing

	<img src="https://images.tuyacn.com/app/iotappsample/en/ap.png" width="30%" />

- EZ Pairing

 	<img src="https://images.tuyacn.com/app/IoTAppSDK/androidApp_EZ.png" width="30%" />

- NB Pairing

 	<img src="https://images.tuyacn.com/app/IoTAppSDK/androidApp_NB.png" width="30%" />

- Wired Pairing

 	<img src="https://images.tuyacn.com/app/IoTAppSDK/androidApp_wired.png" width="30%" />

- Zigbee Sub device Pairing

 	<img src="https://images.tuyacn.com/app/IoTAppSDK/androidApp_zigbee_sub.png" width="30%" />

- QR Code Binding

	<img src="https://images.tuyacn.com/app/iotappsample/en/qr.png" width="30%" />
	<img src="https://images.tuyacn.com/fe-static/docs/img/6b827d69-919a-4913-a30b-7a67f15fecd4.png" width="30%" />

- Distribution network results page

	<img src="https://images.tuyacn.com/app/iotappsample/en/loading.png" width="30%"/> 
	<img src="https://images.tuyacn.com/app/iotappsample/activato_result.png" width="33%"/>

### Device query unbinding

<img src="https://images.tuyacn.com/app/iotappsample/en/device_list.png" width="30%" /> 
<img src="https://images.tuyacn.com/app/iotappsample/en/unbind_device.png" width="30%" />

### Device controler

<img src="https://images.tuyacn.com/app/iotappsample/en/device_control_clcik.png" width="30%"/> 

<img src="https://images.tuyacn.com/app/iotappsample/en/c_d_en.png" width="30%"/>

<img src="https://images.tuyacn.com/app/iotappsample/en/controler_comand.png" width="30%"/>
	
Issue Feedback
---

You can provide feedback on your issue via **Github Issue** or [Technical Ticket](https://service.console.tuya.com).

License
---
Tuya Android Industry App SDK Sample is available under the MIT license. Please see the [LICENSE](LICENSE) file for more info.


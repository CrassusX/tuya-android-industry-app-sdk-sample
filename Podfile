source 'https://cdn.cocoapods.org/'
source 'https://github.com/TuyaInc/TuyaPublicSpecs.git'

target 'TuyaIoTAppSDKSample-iOS-Swift' do
  pod 'SVProgressHUD'
  pod 'TuyaIoTAppSDK', '1.0.0'
end

post_install do |installer|
  `cd TuyaIoTAppSDKSample-iOS-Swift; [[ -f AppKey.swift ]] || cp AppKey.swift.default AppKey.swift;`
end


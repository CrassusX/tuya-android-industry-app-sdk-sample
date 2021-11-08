//
//  APModeTableViewController.swift
//  TuyaAppSDKSample-iOS-Swift
//
//  Copyright (c) 2014-2021 Tuya Inc. (https://developer.tuya.com/)

import UIKit
import TuyaIoTAppSDK

class APModeTableViewController: UITableViewController {
    // MARK: - IBOutlet
    @IBOutlet weak var ssidTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    
    var ssid: String = ""
    var password: String = ""
    var pairingToken: String = ""
    var token: String = ""
    private var isSuccess = false
    private var timer: DispatchSourceTimer! = nil
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        requestToken()
        
    }
    
    override func viewDidDisappear(_ animated: Bool) {
        super.viewDidDisappear(animated)
        stopConfigWifi()
    }
    
    // MARK: - IBAction
    @IBAction func searchTapped(_ sender: UIBarButtonItem) {
        startConfiguration()
    }
    
    // MARK: - Private Method
    private func requestToken() {
        guard let assetID = UserModel.shared.asset?.id else {
            SVProgressHUD.showInfo(withStatus: "Please login again")
            return
        }
        
        TYDeviceRegistrationManager().generateToken(for: .AP, uid: TYUserInfo.uid!, assetID: assetID) { [weak self] (deviceRegistrationToken, error) in
            guard let self = self else { return }
            if deviceRegistrationToken != nil {
                self.pairingToken = deviceRegistrationToken!.pairingToken
                self.token = deviceRegistrationToken!.token
                SVProgressHUD.showInfo(withStatus: "ssid:\(self.ssid), pwd:\(self.password)")
            } else {
                SVProgressHUD.showError(withStatus: error?.localizedDescription)
            }
        }
        
    }
    
    private func startConfiguration() {
        SVProgressHUD.show(withStatus: NSLocalizedString("Configuring", comment: ""))
        TYAPActivator(SSID: ssid, password: password, pairingToken: pairingToken).start()
        DispatchQueue.global().asyncAfter(deadline: .now() + 5) {
            self.requestConfigResult()
        }
    }
    
    private func stopConfigWifi() {
        timer?.cancel()
        timer = nil
        SVProgressHUD.dismiss()
    }
    
    private func requestConfigResult() {
        timer = DispatchSource.makeTimerSource()
        var number = 0
        timer.schedule(deadline: .now(), repeating: .seconds(1), leeway: .nanoseconds(1))
        timer.setEventHandler {
            number += 1
            if number < 100 {
                TYDeviceRegistrationManager().queryRegistrationResult(of: self.token) { (result, error) in
                    if result != nil && result?.succeedDevices != nil && (result?.succeedDevices.count)! > 0 {
                        SVProgressHUD.showInfo(withStatus: "Pairing Succeed")
                        self.navigationController?.popToRootViewController(animated: true)
                    }
                }
            } else {
                self.timer.cancel()
                SVProgressHUD.show(withStatus: "Timeout")
            }
        }
        timer.activate()
    }
}



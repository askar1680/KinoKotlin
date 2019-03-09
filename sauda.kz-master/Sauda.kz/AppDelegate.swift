//
//  AppDelegate.swift
//  Sauda.kz
//
//  Created by Adilbek Mailanov on 3/7/19.
//  Copyright © 2019 Adilbek Mailanov. All rights reserved.
//

import UIKit
import CoreData

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let navigationController = NavigationController()
        _ = LoginRouter(navigationController: navigationController)
        
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.makeKeyAndVisible()
        window?.rootViewController = navigationController
        
        return true
    }
}


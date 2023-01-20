//
//  AppDelegate.swift
//  iosApp
//
//  Created by Ayodele Kehinde on 20/01/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import realtime


class AppDelegate: NSObject, UIApplicationDelegate{
    
    func application(_ application: UIApplication,
                         didFinishLaunchingWithOptions launchOptions:
                            [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        print("application")
        
        
            return true
        }
    
    func applicationWillEnterForeground(_ application: UIApplication) {
        print("Foregrounf")
    }
    func applicationWillResignActive(_ application: UIApplication) {
        print("Resign")
    }
}

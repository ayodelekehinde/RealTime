import SwiftUI
import realtime


@main
struct iOSApp: App {
    @Environment(\.scenePhase) private var scenePhase
    //@UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    init(){
      RealtimeKt.configureApp(baseurl: "baseUrl", username: "iOS")
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }.onChange(of: scenePhase) { phase in
            if phase == .active{
                initialize()
            }
        }
    }
    
    func initialize(){
        
    }
}

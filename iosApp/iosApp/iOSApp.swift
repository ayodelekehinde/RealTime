import SwiftUI
import realtime


@main
struct iOSApp: App {
    @Environment(\.scenePhase) private var scenePhase
    //@UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
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
        RealtimeKt.connect{ data in
            print(data)
        } onError: { error in
            if error != nil {
                print("Error \(error ?? "Error")")
            }
        }
    }
}

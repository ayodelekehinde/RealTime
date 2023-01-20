import SwiftUI
import realtime

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
        VStack{
            Text(greet)
            Button(action: {
                RealtimeKt.sendMessage(msg: "07033128603")
            }) {
                Text("Send message")
            }
        }
		
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

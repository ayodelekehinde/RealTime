// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/github/cherrio/realtime/realtime-kmmbridge/0.1.1/realtime-kmmbridge-0.1.1.zip"
let remoteKotlinChecksum = "80272b95ecf4a509389e99ec7aed0ebbd7ed4dbbf571cb8eea3f154ec58a27fb"
let packageName = "realtime"

let package = Package(
    name: packageName,
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
        ,
    ]
)
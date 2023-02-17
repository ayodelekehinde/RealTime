// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/ayodelekehinde/RealTime/com/dilivva/realtime-kmmbridge/0.1.10/realtime-kmmbridge-0.1.10.zip"
let remoteKotlinChecksum = "e921b04a5f19b998ed5573d2585cd0f59aaffea58dd59b2c2308c6633eca5e50"
let packageName = "RealTime"

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
// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/ayodelekehinde/RealTime/com/dilivva/realtime-kmmbridge/0.1.11/realtime-kmmbridge-0.1.11.zip"
let remoteKotlinChecksum = "8d92064805c12964c63576b592f3d702c2fd085e836f5b043c3080c1647a92b0"
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
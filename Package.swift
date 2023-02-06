// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/com/dilivva/realtime-kmmbridge/0.1.8/realtime-kmmbridge-0.1.8.zip"
let remoteKotlinChecksum = "681f91ba1e1c9b3cb686eadc8c6e44a4aa148026b91528fce6ba6a846667065a"
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
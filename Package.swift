// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/github/cherrio/realtime-kmmbridge/0.1.4/realtime-kmmbridge-0.1.4.zip"
let remoteKotlinChecksum = "1a76b8bd3fed2fc0c27feb6941346305f2d2e8973fea3067ed8847e3aa8ca54c"
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
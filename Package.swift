// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/github/cherrio/realtime-kmmbridge/0.1.5/realtime-kmmbridge-0.1.5.zip"
let remoteKotlinChecksum = "05f7b333f75688a65e071dc720bf338dfc502fa5996f5af94dec78c333c413e5"
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
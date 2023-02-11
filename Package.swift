// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/com/dilivva/realtime-kmmbridge/0.1.9/realtime-kmmbridge-0.1.9.zip"
let remoteKotlinChecksum = "7f2fd8be4d80af608780654a61cbb5a926f8319dfab6fdde9f813d4679cd41ae"
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
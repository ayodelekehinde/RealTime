// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/com/dilivva/realtime-kmmbridge/0.1.7/realtime-kmmbridge-0.1.7.zip"
let remoteKotlinChecksum = "2cbf5cde8e784aee2cf968bc8e19b373255778195790e5323c36e3dce61063b8"
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
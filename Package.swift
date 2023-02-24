// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/ayodelekehinde/RealTime/com/dilivva/realtime-kmmbridge/0.1.12/realtime-kmmbridge-0.1.12.zip"
let remoteKotlinChecksum = "d637544643756bc4b2fd8b47d660cb5f65f7ab32276dc796990e3d5488e195a3"
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
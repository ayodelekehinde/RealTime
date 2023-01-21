// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/github/cherrio/realtime-kmmbridge/0.1.3/realtime-kmmbridge-0.1.3.zip"
let remoteKotlinChecksum = "d2cfc0be8c31ba99aaca8fa5af5640da60eddd795356b27995a9afda2073adf5"
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
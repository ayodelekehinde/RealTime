// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/github/cherrio/realtime-kmmbridge/0.1.2/realtime-kmmbridge-0.1.2.zip"
let remoteKotlinChecksum = "af1a801b9fca12b7c9e7814ba8f46015e73a03d0fb97e3f792911cc8556228ee"
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
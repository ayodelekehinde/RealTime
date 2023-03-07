// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/ayodelekehinde/RealTime/com/dilivva/realtime-kmmbridge/0.1.13/realtime-kmmbridge-0.1.13.zip"
let remoteKotlinChecksum = "cd83f8f62ad20dcd04e9f3cd6b216902d8cd92b305df1ac0b59781bcf8d97267"
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
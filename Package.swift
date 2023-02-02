// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://maven.pkg.github.com/Cherrio-LLC/RealTime/com/dilivva/realtime-kmmbridge/0.1.6/realtime-kmmbridge-0.1.6.zip"
let remoteKotlinChecksum = "61e124440502b60edb74c05548ff6c85585d0ab5bc763734452bbe49bc062c76"
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
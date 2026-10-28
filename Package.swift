// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "EmmausCapacitorPluginAndroidInsets",
    platforms: [.iOS(.v15)],
    products: [
        .library(name: "EmmausCapacitorPluginAndroidInsets", targets: ["AndroidInsetsPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", from: "8.0.0")
    ],
    targets: [
        .target(
            name: "AndroidInsetsPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Plugin",
            exclude: ["AndroidInsetsPlugin.m", "Info.plist"]
        )
    ]
)

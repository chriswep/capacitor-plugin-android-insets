import Foundation
import Capacitor

/** Preserves the iOS zero-inset stub for this Android-specific plugin. */
@objc(AndroidInsetsPlugin)
public class AndroidInsetsPlugin: CAPPlugin, CAPBridgedPlugin {
    public let identifier = "AndroidInsetsPlugin"
    public let jsName = "AndroidInsets"
    public let pluginMethods: [CAPPluginMethod] = [
        CAPPluginMethod(name: "top", returnType: CAPPluginReturnPromise)
    ]
    private let implementation = AndroidInsets()

    @objc func top(_ call: CAPPluginCall) {
        call.resolve([
            "value": implementation.top()
        ])
    }
}

package systems.emmaus.plugin.android.insets;

import android.app.Activity;
import android.view.DisplayCutout;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "AndroidInsets")
public class AndroidInsetsPlugin extends Plugin {

    private AndroidInsets implementation;

    @Override
    public void load() {
        this.implementation = new AndroidInsets(getActivity());
    }

    @PluginMethod
    public void getDisplayInfo(PluginCall call) {
        JSObject ret = new JSObject();

        String rotation = implementation.getRotation();
        boolean isGestureMode = implementation.isNavigationBarGestureMode();
        int[] insets = implementation.getNormalizedSafeInsets();
        int statusbarHeight = implementation.getStatusbarHeight();
        AndroidInsets.NavbarInfo navbarInfo = implementation.getNavbarInfo();

        ret.put("rotation", rotation);
        ret.put("isGestureMode", isGestureMode);
        ret.put("statusbarHeight", statusbarHeight);
        ret.put("navbarPosition", navbarInfo.position);
        ret.put("navbarSize", navbarInfo.size);
        ret.put("insetTop", insets[0]);
        ret.put("insetBottom", insets[1]);
        ret.put("insetLeft", insets[2]);
        ret.put("insetRight", insets[3]);

        call.resolve(ret);
    }

    @PluginMethod
    public void setNavbarBackgroundColor(PluginCall call) {
        JSObject ret = new JSObject();
        implementation.setNavbarBackgroundColor(
            call.getInt("r"),
            call.getInt("g"),
            call.getInt("b"),
            call.getInt("a"),
            call.getBoolean("isDark")
        );
        call.resolve(ret);
    }
}

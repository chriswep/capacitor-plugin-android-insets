package com.owlsdepartment.plugin.android.insets;

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
    public void top(PluginCall call) {
        float statusBarHeight = implementation.getTop();
        JSObject ret = new JSObject();

        ret.put("value", statusBarHeight);
        call.resolve(ret);
    }

    @PluginMethod
    public void getInsets(PluginCall call) {
        JSObject ret = new JSObject();

        DisplayCutout displayCutout = implementation.getCutout();
        float density = this.getBridge().getActivity().getResources().getDisplayMetrics().density;

        float statusBarHeight = implementation.getTop();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            ret.put("top", statusBarHeight);
            ret.put("top_inset", Math.round(displayCutout.getSafeInsetTop() / density));
            ret.put("bottom", Math.round(displayCutout.getSafeInsetBottom() / density));
            ret.put("left", Math.round(displayCutout.getSafeInsetLeft() / density));
            ret.put("right", Math.round(displayCutout.getSafeInsetRight() / density));
        }

        call.resolve(ret);
    }
}

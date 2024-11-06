package systems.emmaus.plugin.android.insets;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.DisplayCutout;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.view.WindowMetrics;

import androidx.appcompat.app.AppCompatActivity;


public class AndroidInsets {

    private AppCompatActivity activity;
    private DisplayMetrics metrics;
    private Resources resources;
//    private boolean isSmartphone;
    private boolean isGestureMode;

    public class NavbarInfo {
        public String position;
        public int size;

        public NavbarInfo(String position, int size) {
            this.position = position;
            this.size = size;
        }
    }

    public AndroidInsets(AppCompatActivity activity) {
        this.activity = activity;
        this.resources = activity.getResources();
        this.metrics = resources.getDisplayMetrics();
        int resourceId = resources.getIdentifier("config_navBarInteractionMode", "integer", "android");
        int mode = resourceId > 0 ? resources.getInteger(resourceId) : 0;
        this.isGestureMode = mode == 2;
        // // devices with the smallest side less than 600 are considered smartphones
        // this.isSmartphone = resources.getConfiguration().smallestScreenWidthDp < 600;
    }

    public boolean isNavigationBarGestureMode() {
        return isGestureMode;
    }

    @SuppressLint("SwitchIntDef")
    public String getRotation() {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();

        return switch (rotation) {
            case Surface.ROTATION_90 -> "landscape-left";
            case Surface.ROTATION_270 -> "landscape-right";
            default -> "portrait";
        };
    }

    public int getStatusbarHeight() {
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        float titleBarHeight = 0;

        if (resourceId > 0) {
            titleBarHeight = resources.getDimensionPixelSize(resourceId);
        }

        return Math.round(titleBarHeight / metrics.density);
    }

    public int[] getNormalizedSafeInsets() {
        WindowInsets windowInsets = activity.getWindow().getDecorView().getRootWindowInsets();
        if (windowInsets == null) {
            return new int[]{0, 0, 0, 0};
        }

        DisplayCutout displayCutout = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            displayCutout = windowInsets.getDisplayCutout();

            if (displayCutout == null) {
                return new int[]{0, 0, 0, 0};
            }

            int safeInsetTop = Math.round(displayCutout.getSafeInsetTop() / metrics.density);
            int safeInsetBottom = Math.round(displayCutout.getSafeInsetBottom() / metrics.density);
            int safeInsetLeft = Math.round(displayCutout.getSafeInsetLeft() / metrics.density);
            int safeInsetRight = Math.round(displayCutout.getSafeInsetRight() / metrics.density);

            return new int[]{safeInsetTop, safeInsetBottom, safeInsetLeft, safeInsetRight};
        }

        return new int[]{0, 0, 0, 0};
    }

    public NavbarInfo getNavbarInfo() {
        if (Build.VERSION.SDK_INT >= 30) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            WindowInsets windowInsets = windowMetrics.getWindowInsets();

            Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());

            if (insets.bottom > 0) {
                return new NavbarInfo("BOTTOM", Math.round(insets.bottom / metrics.density));
            } else if (insets.right > 0) {
                return new NavbarInfo("RIGHT", Math.round(insets.right / metrics.density));
            } else if (insets.left > 0) {
                return new NavbarInfo("LEFT", Math.round(insets.left / metrics.density));
            }
        } else {
            Rect rectangle = new Rect();
            Window window = activity.getWindow();
            window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
            int screenHeight = window.getDecorView().getRootView().getHeight();
            int navbarHeight = screenHeight - rectangle.bottom;

            if (navbarHeight > 0) {
                return new NavbarInfo("BOTTOM", Math.round(navbarHeight / metrics.density));
            }
        }

        return new NavbarInfo("UNKNOWN", 0);
    }

    public void setNavbarBackgroundColor(int red, int green, int blue, int alpha, boolean isDark) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Window window = activity.getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    int color = Color.argb(alpha, red, green, blue);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setNavigationBarColor(color);

                    // Set the dark/light property of the navbar buttons
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            window.setNavigationBarContrastEnforced(true);
                            WindowInsetsController controller = window.getInsetsController();
                            if (isDark) {
                                controller.setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS);
                            } else {
                                controller.setSystemBarsAppearance(WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS, WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS);
                            }
                        } else {
                            int flags = window.getDecorView().getSystemUiVisibility();
                            if (isDark) {
                                flags &= ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                            } else {
                                flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
                            }
                            window.getDecorView().setSystemUiVisibility(flags);
                        }
                    }
                }
            }
        });
    }

    /* Deprecated
    public boolean hasNavBar ()
    {
        int id = resources.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0)
            return resources.getBoolean(id);
        else
            return false;
    }

    public int getNavbarHeight ()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            WindowInsets windowInsets = windowMetrics.getWindowInsets();

            Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
            return Math.round(insets.bottom / metrics.density);
        } else {

            if (!hasNavBar())
                return 0;

            int orientation = resources.getConfiguration().orientation;

            // on smartphones in landscape mode the navigation bar is on the side and thus has no height
            if (isSmartphone && Configuration.ORIENTATION_LANDSCAPE == orientation && !isGestureMode)
                return 0;

            int id = resources
                    .getIdentifier(orientation == Configuration.ORIENTATION_PORTRAIT ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
            if (id > 0)
                return Math.round(resources.getDimensionPixelSize(id) / metrics.density);

            return 0;
        }
    }

    public int getNavbarWidth ()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            WindowInsets windowInsets = windowMetrics.getWindowInsets();

            Insets insets = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars());
            return Math.round(Math.max(insets.left, insets.right) / metrics.density);
        } else {
            if (!hasNavBar())
                return 0;

            int orientation = resources.getConfiguration().orientation;
            // on smartphones in landscape mode the navigation bar is on the side and thus has a width
            if (orientation == Configuration.ORIENTATION_LANDSCAPE && isSmartphone && !isGestureMode) {
                int id = resources.getIdentifier("navigation_bar_width", "dimen", "android");
                if (id > 0)
                    return Math.round(resources.getDimensionPixelSize(id) / metrics.density);
            }

            return 0;
        }
    }
    */

}

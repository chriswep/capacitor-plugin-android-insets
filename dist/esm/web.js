import { WebPlugin } from '@capacitor/core';
export class AndroidInsetsWeb extends WebPlugin {
    async getDisplayInfo() {
        return {
            isGestureMode: false,
            rotation: 'unknown',
            statusbarHeight: 0,
            navbarPosition: 'UNKNOWN',
            navbarSize: 0,
            insetTop: 0,
            insetRight: 0,
            insetBottom: 0,
            insetLeft: 0,
        };
    }
    async setNavbarBackgroundColor(_options) {
        return;
    }
}
//# sourceMappingURL=web.js.map
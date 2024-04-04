var capacitorAndroidInsets = (function (exports, core) {
    'use strict';

    const AndroidInsets = core.registerPlugin('AndroidInsets', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.AndroidInsetsWeb()),
    });

    class AndroidInsetsWeb extends core.WebPlugin {
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

    var web = /*#__PURE__*/Object.freeze({
        __proto__: null,
        AndroidInsetsWeb: AndroidInsetsWeb
    });

    exports.AndroidInsets = AndroidInsets;

    Object.defineProperty(exports, '__esModule', { value: true });

    return exports;

})({}, capacitorExports);
//# sourceMappingURL=plugin.js.map

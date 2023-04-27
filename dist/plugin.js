var capacitorAndroidInsets = (function (exports, core) {
    'use strict';

    const AndroidInsets = core.registerPlugin('AndroidInsets', {
        web: () => Promise.resolve().then(function () { return web; }).then(m => new m.AndroidInsetsWeb()),
    });

    class AndroidInsetsWeb extends core.WebPlugin {
        async top() {
            return { value: 0 };
        }
        async getInsets() {
            return { top: 0, right: 0, bottom: 0, left: 0 };
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

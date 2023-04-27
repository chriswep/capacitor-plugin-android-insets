import { registerPlugin } from '@capacitor/core';
const AndroidInsets = registerPlugin('AndroidInsets', {
    web: () => import('./web').then(m => new m.AndroidInsetsWeb()),
});
export * from './definitions';
export { AndroidInsets };
//# sourceMappingURL=index.js.map
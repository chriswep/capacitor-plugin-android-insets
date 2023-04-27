import { WebPlugin } from '@capacitor/core';
export class AndroidInsetsWeb extends WebPlugin {
    async top() {
        return { value: 0 };
    }
    async getInsets() {
        return { top: 0, right: 0, bottom: 0, left: 0 };
    }
}
//# sourceMappingURL=web.js.map
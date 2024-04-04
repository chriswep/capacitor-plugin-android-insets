import { WebPlugin } from '@capacitor/core';
import type { AndroidInsetsPlugin, GetDisplayInfoReturn } from './definitions';
export declare class AndroidInsetsWeb extends WebPlugin implements AndroidInsetsPlugin {
    getDisplayInfo(): Promise<GetDisplayInfoReturn>;
    setNavbarBackgroundColor(_options: {
        r: number;
        g: number;
        b: number;
        a: number;
    }): Promise<void>;
}

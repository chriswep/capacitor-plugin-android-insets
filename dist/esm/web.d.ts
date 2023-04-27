import { WebPlugin } from '@capacitor/core';
import type { AndroidInsetsPlugin, GetInsetsReturn, TopReturn } from './definitions';
export declare class AndroidInsetsWeb extends WebPlugin implements AndroidInsetsPlugin {
    top(): Promise<TopReturn>;
    getInsets(): Promise<GetInsetsReturn>;
}

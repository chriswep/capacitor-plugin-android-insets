import { WebPlugin } from '@capacitor/core';

import type { AndroidInsetsPlugin, GetInsetsReturn, TopReturn } from './definitions';

export class AndroidInsetsWeb extends WebPlugin implements AndroidInsetsPlugin {
  async top(): Promise<TopReturn> {
    return { value: 0 };
  }
  async getInsets(): Promise<GetInsetsReturn> {
    return { top: 0, right: 0, bottom: 0, left: 0 };
  }
}

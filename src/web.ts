import { WebPlugin } from '@capacitor/core';

import type { AndroidInsetsPlugin, GetDisplayInfoReturn } from './definitions';

export class AndroidInsetsWeb extends WebPlugin implements AndroidInsetsPlugin {
  async getDisplayInfo(): Promise<GetDisplayInfoReturn> {
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

  async setNavbarBackgroundColor(_options: { r:number, g:number, b:number, a:number }): Promise<void> {
    return;
  }
}

export interface AndroidInsetsPlugin {
  getDisplayInfo(): Promise<GetDisplayInfoReturn>;
  setNavbarBackgroundColor(options: { r: number; g: number; b: number; a: number }): Promise<void>;
}

export interface GetDisplayInfoReturn {
  isGestureMode: boolean;
  rotation: 'portrait' | 'landscape-left' | 'landscape-right' | 'unknown';
  statusbarHeight: number;
  navbarPosition: 'BOTTOM' | 'RIGHT' | 'LEFT' | 'UNKNOWN', 
  navbarSize: number;
  insetTop: number;
  insetRight: number;
  insetBottom: number;
  insetLeft: number;
}

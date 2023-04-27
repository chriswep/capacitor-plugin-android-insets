export interface AndroidInsetsPlugin {
    /**
     * Returns top offset of the status bar
     */
    top(): Promise<TopReturn>;
    getInsets(): Promise<GetInsetsReturn>;
}
export interface TopReturn {
    value: number;
}
export interface GetInsetsReturn {
    top: number;
    right: number;
    bottom: number;
    left: number;
}

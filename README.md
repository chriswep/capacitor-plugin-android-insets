# @emmaus/capacitor-plugin-android-insets

Capacitor plugin for retrieving proper top offset of Android status bar.

This repo is published version with changed name of https://github.com/jorisbertomeu/capacitor-insets-v2, which was based on archived https://github.com/igorcd/capacitor-insets-plugin/. It should work perfectly fine in `Capacitor@4`.

## Install

```bash
# with npm
npm install --save @emmaus/capacitor-plugin-android-insets
# with yarn
yarn add @emmaus/capacitor-plugin-android-insets
# after any install
npx cap sync
```

## Why?

This plugin is required only on Android when using `StatusBar.setOverlaysWebView({ overlay: true })`. There is a problem with top offset counting, when status bar is transparent. Related issue: https://github.com/ionic-team/capacitor/issues/2840.

## Usage

```ts
import { AndroidInsets } from '@emmaus/capacitor-plugin-android-insets';

const { value } = await AndroidInsets.top();
```

## API

<docgen-index>

* [`getDisplayInfo()`](#getdisplayinfo)
* [`setNavbarBackgroundColor(...)`](#setnavbarbackgroundcolor)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getDisplayInfo()

```typescript
getDisplayInfo() => Promise<GetDisplayInfoReturn>
```

**Returns:** <code>Promise&lt;<a href="#getdisplayinforeturn">GetDisplayInfoReturn</a>&gt;</code>

--------------------


### setNavbarBackgroundColor(...)

```typescript
setNavbarBackgroundColor(options: { r: number; g: number; b: number; a: number; }) => Promise<void>
```

| Param         | Type                                                         |
| ------------- | ------------------------------------------------------------ |
| **`options`** | <code>{ r: number; g: number; b: number; a: number; }</code> |

--------------------


### Interfaces


#### GetDisplayInfoReturn

| Prop                  | Type                                                                          |
| --------------------- | ----------------------------------------------------------------------------- |
| **`isGestureMode`**   | <code>boolean</code>                                                          |
| **`rotation`**        | <code>'portrait' \| 'landscape-left' \| 'landscape-right' \| 'unknown'</code> |
| **`statusbarHeight`** | <code>number</code>                                                           |
| **`navbarPosition`**  | <code>'BOTTOM' \| 'RIGHT' \| 'LEFT' \| 'UNKNOWN'</code>                       |
| **`navbarSize`**      | <code>number</code>                                                           |
| **`insetTop`**        | <code>number</code>                                                           |
| **`insetRight`**      | <code>number</code>                                                           |
| **`insetBottom`**     | <code>number</code>                                                           |
| **`insetLeft`**       | <code>number</code>                                                           |

</docgen-api>

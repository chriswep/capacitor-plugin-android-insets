# Contributing

This guide provides instructions for contributing to this Capacitor plugin.

## Developing

### Local Setup

1. Fork and clone the repo.
1. Install the dependencies.

    ```shell
    npm install
    ```

1. Install SwiftLint if you're on macOS.

    ```shell
    brew install swiftlint
    ```

### Scripts

#### `npm run build`

Build the plugin web assets and generate plugin API documentation using [`@capacitor/docgen`](https://github.com/ionic-team/capacitor-docgen).

It will compile the TypeScript code from `src/` into ESM JavaScript in `dist/esm/`. These files are used in apps with bundlers when your plugin is imported.

Then, Rollup will bundle the code into a single file at `dist/plugin.js`. This file is used in apps without bundlers by including it as a script in `index.html`.

#### `npm run verify`

Build and validate the web and native projects.

This is useful to run in CI to verify that the plugin builds for all platforms.

#### `npm run lint` / `npm run fmt`

Check formatting and code quality, autoformat/autofix if possible.

This template is integrated with ESLint, Prettier, and SwiftLint. Using these tools is completely optional, but the [Capacitor Community](https://github.com/capacitor-community/) strives to have consistent code style and structure for easier cooperation.

## Publishing

Releases are published from GitHub Actions through npm trusted publishing; no npm token is stored in the repository. The npm package belongs to `emmaus`, while the GitHub repository belongs to `chriswep`.

The npm trusted publisher must use owner `chriswep`, repository `capacitor-plugin-android-insets`, workflow `publish.yml`, no environment name, and allow `npm publish`.

Before a release:

1. Update the package and lockfile version together. Major 8 targets Capacitor 8.
2. Run `npm ci --ignore-scripts`, `npm run build`, the native verification commands, and `npm pack --dry-run`. Verify the packed plugin in the consuming app, including an optimized Android release build and iOS SPM.
3. Commit and review all source, lockfile and generated `dist/` changes, then merge to `main`.
4. Create a GitHub release with tag `v` followed by the exact package version (for example, `v8.0.0`) at that reviewed commit.
5. Publishing the GitHub release triggers `.github/workflows/publish.yml`. It checks the version and package contents, then publishes publicly to npm with provenance. Confirm the workflow succeeds and the exact npm version exists before updating the app.

A draft GitHub release does not publish. A stable version cannot be republished; use a new version for corrections. Local `npm publish` is still possible with an authorized npm login, but is not the normal release path.

> **Note**: The [`files`](https://docs.npmjs.com/cli/v7/configuring-npm/package-json#files) array in `package.json` specifies which files get published. If you rename files/directories or add files elsewhere, you may need to update it.

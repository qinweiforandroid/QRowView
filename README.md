# QRowView

行view框架

## How to
To get a Git project into your build:

**Step 1**. Add the JitPack repository to your build file

``` groovy
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2.** Add the dependency

[![](https://jitpack.io/v/qinweiforandroid/QRowView.svg)](https://jitpack.io/#qinweiforandroid/QRowView)

``` gr
dependencies {
        implementation 'com.github.qinweiforandroid.QRowView:row:1.0.11271'
}
```
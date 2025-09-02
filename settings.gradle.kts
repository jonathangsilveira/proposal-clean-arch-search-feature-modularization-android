pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Search Clean Arch Modularized by Feature App"
include(":app")
include(":app:features")
include(":app:features:search")
include(":app:features:search:public")
include(":app:features:search:public-android")
include(":app:features:search:impl")
include(":app:features:home")
include(":app:features:home:impl")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        resolutionStrategy {
            eachPlugin {
                when (requested.id.id) {
                    "kotlinx-serialization"->{
                        useModule("org.jetbrains.kotlinx:kotlinx-gradle-serialization-plugin:${requested.version}")
                    }
                }
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "jass-app"
include(":app")
 
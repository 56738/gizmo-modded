rootProject.name = "gizmo-modded"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.fabricmc.net")
        mavenCentral()
        gradlePluginPortal()
    }
}

include("gizmo-fabric")
include("gizmo-modded")
include("gizmo-modded-loom")
include("gizmo-neoforge")

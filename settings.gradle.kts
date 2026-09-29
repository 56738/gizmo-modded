rootProject.name = "gizmo-modded"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        maven("https://maven.fabricmc.net")
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("net.fabricmc.fabric-loom-repositories") version "1.18-SNAPSHOT"
    id("net.neoforged.moddev.repositories") version "2.0.148"
}

dependencyResolutionManagement {
    versionCatalogs {
        create("fabricApiLibs") {
            from("net.fabricmc.fabric-api:fabric-api-catalog:0.161.0+26.3")
        }
    }

    repositories {
        maven("https://repo.56738.me")
    }
}

include("gizmo-fabric")
include("gizmo-modded-common")
include("gizmo-neoforge")

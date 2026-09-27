plugins {
    id("gizmo-modded.base")
    id("net.neoforged.moddev")
    alias(libs.plugins.resource.factory.neoforge.convention)
}

neoForge {
    enable {
        version = libs.versions.neoforge.get()
    }
    mods {
        register("gizmo") {
            sourceSet(sourceSets["main"])
        }
    }
}

dependencies {
    implementation(project(":gizmo-modded-common"))
    accessTransformers(project(":gizmo-modded-common"))
    jarJar(project(":gizmo-modded-common"))
    jarJar(libs.gizmo.common)
}

tasks {
    jar {
        manifest {
            attributes["Automatic-Module-Name"] = "me.m56738.gizmo.neoforge"
        }
        from(configurations.accessTransformers) {
            rename { "META-INF/accesstransformer.cfg" }
        }
    }
}

neoForgeModsToml {
    license = "GNU GPLv3"
    issueTrackerUrl = "https://github.com/56738/gizmo-modded/issues"
    mod("gizmo") {
        version = project.version.toString()
        displayName = "Gizmo"
        displayUrl = "https://github.com/56738/gizmo-modded"
        authors = "56738"
        description = "Gizmo utility library"
        dependencies {
            required("neoforge", "[${libs.versions.neoforge.get()},)")
            required("minecraft", versionRange = libs.versions.minecraft.get())
        }
    }
}

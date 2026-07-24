plugins {
    id("gizmo-modded.base")
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)

    implementation(project(":gizmo-modded-common"))

    implementation(libs.fabric.loader)
    implementation(libs.fabric.api)

    include(project(":gizmo-modded-common"))
    include(libs.gizmo.common)
}

loom {
    splitEnvironmentSourceSets()
    accessWidenerPath = file("src/main/resources/gizmo.accesswidener")
    mods {
        register("gizmo") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

tasks {
    processResources {
        val props = mapOf("version" to project.version)
        inputs.properties(props)
        filesMatching("fabric.mod.json") {
            expand(props)
        }
    }
}

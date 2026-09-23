plugins {
    id("gizmo-modded.base")
    id("net.fabricmc.fabric-loom")
}

dependencies {
    minecraft(libs.minecraft)

    implementation(project(":gizmo-modded-common"))

    implementation(libs.fabric.loader)
    implementation(fabricApiLibs.data.attachment.api.v1)

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

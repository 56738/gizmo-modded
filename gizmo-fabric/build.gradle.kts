plugins {
    id("gizmo-modded.base")
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)
    mappings(@Suppress("UnstableApiUsage") loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchment.minecraft.get()}:${libs.versions.parchment.mappings.get()}@zip")
    })

    implementation(project(":gizmo-modded"))

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)

    include(project(":gizmo-modded-loom"))
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

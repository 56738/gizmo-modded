plugins {
    id("gizmo-modded.base")
    id("gizmo-modded.bundle")
    alias(libs.plugins.fabric.loom)
}

dependencies {
    minecraft(libs.minecraft)
    mappings(@Suppress("UnstableApiUsage") loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-${libs.versions.parchment.minecraft.get()}:${libs.versions.parchment.mappings.get()}@zip")
    })
    compileOnly(libs.fabric.loader)
    bundle(project(":gizmo-modded"))
}

tasks {
    jar {
        manifest {
            attributes["Fabric-Loom-Remap"] = true
        }
    }
}

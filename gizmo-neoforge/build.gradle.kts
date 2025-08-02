plugins {
    id("gizmo-modded.base")
    alias(libs.plugins.moddev)
}

neoForge {
    enable {
        version = libs.versions.neoforge.get()
    }
    parchment {
        minecraftVersion = libs.versions.parchment.minecraft
        mappingsVersion = libs.versions.parchment.mappings
    }
    mods {
        register("gizmo") {
            sourceSet(sourceSets["main"])
        }
    }
}

dependencies {
    implementation(project(":gizmo-modded"))
    implementation(libs.neoforge)

    accessTransformers(project(":gizmo-modded"))
    jarJar(project(":gizmo-modded"))
    jarJar(libs.gizmo.common)
}

val generateModMetadata by tasks.registering(ProcessResources::class) {
    val props = mapOf(
        "version" to version,
        "neoforgeVersion" to libs.versions.neoforge.get(),
        "minecraftVersion" to libs.versions.minecraft.get()
    )
    inputs.properties(props)
    expand(props)
    from(layout.projectDirectory.dir("src/main/templates"))
    into(layout.buildDirectory.dir("generated/sources/modMetadata"))
}

sourceSets {
    main {
        resources {
            srcDir(generateModMetadata)
        }
    }
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

neoForge.ideSyncTask(generateModMetadata)

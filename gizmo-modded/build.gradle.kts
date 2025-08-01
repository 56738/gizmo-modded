plugins {
    id("gizmo-modded.base")
    alias(libs.plugins.moddev)
}

neoForge {
    validateAccessTransformers = true
    enable {
        neoFormVersion = libs.versions.neoform.get()
    }
    parchment {
        minecraftVersion = libs.versions.parchment.minecraft
        mappingsVersion = libs.versions.parchment.mappings
    }
    accessTransformers {
        publish(file("src/main/resources/META-INF/accesstransformer.cfg"))
    }
}

dependencies {
    api(libs.gizmo.common)
}

tasks {
    jar {
        manifest {
            attributes["FMLModType"] = "GAMELIBRARY"
        }
    }
}

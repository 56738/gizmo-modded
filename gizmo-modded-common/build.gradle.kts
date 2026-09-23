plugins {
    id("gizmo-modded.base")
    id("net.neoforged.moddev")
}

neoForge {
    validateAccessTransformers = true
    enable {
        neoFormVersion = libs.versions.neoform.get()
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
            attributes["Automatic-Module-Name"] = "me.m56738.gizmo.modded"
            attributes["FMLModType"] = "GAMELIBRARY"
        }
    }
}

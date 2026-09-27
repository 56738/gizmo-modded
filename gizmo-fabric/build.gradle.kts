import org.spongepowered.configurate.objectmapping.ConfigSerializable
import xyz.jpenilla.resourcefactory.fabric.Environment

plugins {
    id("gizmo-modded.base")
    id("net.fabricmc.fabric-loom")
    alias(libs.plugins.resource.factory.fabric.convention)
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
    accessWidenerPath = file("src/main/resources/gizmo.classtweaker")
    mods {
        register("gizmo") {
            sourceSet("main")
            sourceSet("client")
        }
    }
}

fabricModJson {
    id = "gizmo"
    name = "Gizmo"
    description = "Gizmo utility library"
    accessWidener = "gizmo.classtweaker"
    author("56738")
    mainEntrypoint("me.m56738.gizmo.fabric.GizmoMod")
    clientEntrypoint("me.m56738.gizmo.fabric.GizmoModClient")
    mixin("gizmo.mixins.json")
    mixin("gizmo.client.mixins.json") {
        environment = Environment.CLIENT
    }
    license("GPL-3.0-or-later")
    contact {
        sources = "https://github.com/56738/gizmo-modded"
    }
    depends("fabricloader", ">=" + libs.versions.fabric.loader.get())
    depends("java", ">=" + java.toolchain.languageVersion.get().asInt())
    depends("minecraft", "~" + libs.versions.minecraft.get())
    depends("fabric-data-attachment-api-v1", "*")
    custom(
        "modmenu", complexCustomValue(
            ModMenu(
                badges = listOf("library")
            )
        )
    )
}

@ConfigSerializable
data class ModMenu(
    @get:Input
    val badges: List<String>
)

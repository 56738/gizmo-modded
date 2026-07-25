package me.m56738.gizmo.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(GizmoMod.MOD_ID)
public class GizmoMod {
    public static final String MOD_ID = "gizmo";

    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);

    public static final Supplier<AttachmentType<Boolean>> GIZMO_MARKER = ATTACHMENT_TYPES.register("gizmo",
            () -> AttachmentType.builder(() -> false).build());

    public GizmoMod(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }
}

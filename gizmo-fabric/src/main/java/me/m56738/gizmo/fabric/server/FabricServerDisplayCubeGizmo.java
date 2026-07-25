package me.m56738.gizmo.fabric.server;

import com.mojang.serialization.Codec;
import me.m56738.gizmo.modded.server.ServerDisplayCubeGizmo;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Display;
import org.jetbrains.annotations.NotNull;

public class FabricServerDisplayCubeGizmo extends ServerDisplayCubeGizmo {
    public static final AttachmentType<Boolean> GIZMO_MARKER = AttachmentRegistry.createPersistent(Identifier.fromNamespaceAndPath("gizmo", "marker"), Codec.BOOL);

    public FabricServerDisplayCubeGizmo(ServerPlayer viewer) {
        super(viewer);
    }

    @Override
    protected void configure(Display.@NotNull BlockDisplay entity) {
        entity.setAttached(GIZMO_MARKER, true);
        super.configure(entity);
    }
}

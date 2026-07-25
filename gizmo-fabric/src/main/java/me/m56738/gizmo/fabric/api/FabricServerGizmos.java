package me.m56738.gizmo.fabric.api;

import me.m56738.gizmo.api.GizmoFactory;
import me.m56738.gizmo.fabric.server.FabricServerDisplayCubeGizmo;
import me.m56738.gizmo.fabric.server.FabricServerGizmoFactory;
import me.m56738.gizmo.modded.api.ModdedServerGizmos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class FabricServerGizmos implements ModdedServerGizmos {
    private FabricServerGizmos() {
    }

    public static FabricServerGizmos create() {
        return new FabricServerGizmos();
    }

    @Override
    public @NotNull GizmoFactory player(@NotNull ServerPlayer player) {
        return new FabricServerGizmoFactory(player);
    }

    @Override
    public boolean isGizmo(Entity entity) {
        return entity.getAttachedOrElse(FabricServerDisplayCubeGizmo.GIZMO_MARKER, false);
    }

    @Override
    public void close() {
    }
}

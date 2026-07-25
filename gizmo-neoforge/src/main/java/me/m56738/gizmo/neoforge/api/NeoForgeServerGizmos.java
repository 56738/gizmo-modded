package me.m56738.gizmo.neoforge.api;

import me.m56738.gizmo.api.GizmoFactory;
import me.m56738.gizmo.modded.api.ModdedServerGizmos;
import me.m56738.gizmo.neoforge.GizmoMod;
import me.m56738.gizmo.neoforge.server.NeoForgeServerGizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class NeoForgeServerGizmos implements ModdedServerGizmos {
    private NeoForgeServerGizmos() {
    }

    public static @NotNull NeoForgeServerGizmos create() {
        return new NeoForgeServerGizmos();
    }

    @Override
    public @NotNull GizmoFactory player(@NotNull ServerPlayer player) {
        return new NeoForgeServerGizmoFactory(player);
    }

    @Override
    public boolean isGizmo(Entity entity) {
        return entity.getExistingData(GizmoMod.GIZMO_MARKER).orElse(false);
    }

    @Override
    public void close() {
    }
}

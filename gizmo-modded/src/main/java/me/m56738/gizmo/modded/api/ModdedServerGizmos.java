package me.m56738.gizmo.modded.api;

import me.m56738.gizmo.api.GizmoFactory;
import me.m56738.gizmo.modded.server.ServerGizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;

public final class ModdedServerGizmos implements Closeable {
    private ModdedServerGizmos() {
    }

    public static ModdedServerGizmos create() {
        return new ModdedServerGizmos();
    }

    public @NotNull GizmoFactory player(@NotNull ServerPlayer player) {
        return new ServerGizmoFactory(player);
    }

    @Override
    public void close() {
    }
}

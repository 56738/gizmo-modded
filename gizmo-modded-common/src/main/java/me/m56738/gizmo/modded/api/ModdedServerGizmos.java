package me.m56738.gizmo.modded.api;

import me.m56738.gizmo.api.GizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.io.Closeable;

public interface ModdedServerGizmos extends Closeable {
    @NotNull GizmoFactory player(@NotNull ServerPlayer player);

    boolean isGizmo(Entity entity);

    @Override
    void close();
}

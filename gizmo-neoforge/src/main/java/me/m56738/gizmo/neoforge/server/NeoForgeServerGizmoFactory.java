package me.m56738.gizmo.neoforge.server;

import me.m56738.gizmo.cube.CubeGizmo;
import me.m56738.gizmo.modded.server.ServerGizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("UnstableApiUsage")
public class NeoForgeServerGizmoFactory extends ServerGizmoFactory {
    public NeoForgeServerGizmoFactory(@NotNull ServerPlayer player) {
        super(player);
    }

    @Override
    protected @NotNull CubeGizmo createCube(@NotNull ServerPlayer player) {
        return new NeoForgeServerDisplayCubeGizmo(player);
    }
}

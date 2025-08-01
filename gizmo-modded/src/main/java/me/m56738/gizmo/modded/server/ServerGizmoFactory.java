package me.m56738.gizmo.modded.server;

import me.m56738.gizmo.cube.CubeGizmo;
import me.m56738.gizmo.cube.CubeGizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
@SuppressWarnings("UnstableApiUsage")
public class ServerGizmoFactory implements CubeGizmoFactory {
    private final @NotNull ServerPlayer player;

    public ServerGizmoFactory(@NotNull ServerPlayer player) {
        this.player = player;
    }

    @Override
    public @NotNull CubeGizmo createCube() {
        return new ServerDisplayCubeGizmo(player);
    }

    @Override
    public boolean isVisibleThroughWalls() {
        return true;
    }
}

package me.m56738.gizmo.fabric.server;

import me.m56738.gizmo.cube.CubeGizmo;
import me.m56738.gizmo.modded.server.ServerGizmoFactory;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
@SuppressWarnings("UnstableApiUsage")
public class FabricServerGizmoFactory extends ServerGizmoFactory {
    public FabricServerGizmoFactory(@NotNull ServerPlayer player) {
        super(player);
    }

    @Override
    protected @NotNull CubeGizmo createCube(@NotNull ServerPlayer player) {
        return new FabricServerDisplayCubeGizmo(player);
    }
}

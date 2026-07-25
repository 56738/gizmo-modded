package me.m56738.gizmo.neoforge.server;

import me.m56738.gizmo.modded.server.ServerDisplayCubeGizmo;
import me.m56738.gizmo.neoforge.GizmoMod;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Display;
import org.jetbrains.annotations.NotNull;

public class NeoForgeServerDisplayCubeGizmo extends ServerDisplayCubeGizmo {
    public NeoForgeServerDisplayCubeGizmo(ServerPlayer viewer) {
        super(viewer);
    }

    @Override
    protected void configure(Display.@NotNull BlockDisplay entity) {
        entity.setData(GizmoMod.GIZMO_MARKER, true);
        super.configure(entity);
    }
}

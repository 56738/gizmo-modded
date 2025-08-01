package me.m56738.gizmo.modded.server;

import com.mojang.math.Transformation;
import me.m56738.gizmo.AbstractCubeGizmo;
import me.m56738.gizmo.api.color.GizmoColor;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Brightness;
import net.minecraft.world.entity.Display.BillboardConstraints;
import net.minecraft.world.entity.Display.BlockDisplay;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.joml.Quaternionf;
import org.joml.Vector3dc;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ApiStatus.Internal
@SuppressWarnings("UnstableApiUsage")
public class ServerDisplayCubeGizmo extends AbstractCubeGizmo {
    private static final Map<GizmoColor, BlockState> COLORS = new HashMap<>();

    static {
        COLORS.put(GizmoColor.WHITE, Blocks.WHITE_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.RED, Blocks.RED_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.GREEN, Blocks.LIME_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.BLUE, Blocks.BLUE_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.YELLOW, Blocks.YELLOW_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.GRAY, Blocks.LIGHT_GRAY_CONCRETE.defaultBlockState());
        COLORS.put(GizmoColor.AQUA, Blocks.LIGHT_BLUE_CONCRETE.defaultBlockState());
    }

    private final ServerPlayer viewer;
    private BlockDisplay entity;
    private ServerEntity serverEntity;

    protected ServerDisplayCubeGizmo(ServerPlayer viewer) {
        this.viewer = viewer;
    }

    private void broadcast(Packet<?> packet) {
        viewer.connection.send(packet);
    }

    private void broadcastWithIgnore(Packet<?> packet, List<UUID> ignored) {
        if (!ignored.contains(viewer.getUUID())) {
            viewer.connection.send(packet);
        }
    }

    @Override
    public void show() {
        if (entity != null) {
            return;
        }
        ServerLevel level = viewer.level();
        EntityType<BlockDisplay> type = EntityType.BLOCK_DISPLAY;
        entity = new BlockDisplay(type, level);
        configure(entity);
        update(entity);
        serverEntity = new ServerEntity(level, entity, type.updateInterval(), type.trackDeltas(),
                this::broadcast, this::broadcastWithIgnore);
        serverEntity.addPairing(viewer);
        checkAndClearDirty();
    }

    @Override
    public void update() {
        if (entity == null) {
            return;
        }
        if (checkAndClearDirty()) {
            update(entity);
            entity.setOldPosAndRot();
            entity.tickCount++;
            entity.tick();
            serverEntity.sendChanges();
        }
    }

    @Override
    public void hide() {
        if (entity == null) {
            return;
        }
        serverEntity.removePairing(viewer);
        serverEntity = null;
        entity = null;
    }

    private void configure(@NotNull BlockDisplay entity) {
        entity.setBrightnessOverride(Brightness.FULL_BRIGHT);
        entity.setGlowingTag(true);
        entity.setTransformationInterpolationDuration(3);
        entity.setPosRotInterpolationDuration(3);
    }

    private void update(@NotNull BlockDisplay entity) {
        Vector3dc position = getPosition();
        GizmoColor color = getColor();
        entity.setPos(position.x(), position.y(), position.z());
        entity.setBlockState(COLORS.get(color));
        entity.setGlowColorOverride(color.asRGB());
        entity.setTransformation(new Transformation(
                new Vector3f(getOffset()),
                new Quaternionf(getRotation()),
                new Vector3f(getScale()),
                new Quaternionf()));
        entity.setBillboardConstraints(isBillboard() ? BillboardConstraints.CENTER : BillboardConstraints.FIXED);
        entity.setTransformationInterpolationDelay(0);
    }
}

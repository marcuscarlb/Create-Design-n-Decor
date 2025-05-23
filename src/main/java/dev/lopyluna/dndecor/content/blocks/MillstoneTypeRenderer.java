package dev.lopyluna.dndecor.content.blocks;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.millstone.MillstoneBlockEntity;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import dev.lopyluna.dndecor.DnDecor;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class MillstoneTypeRenderer extends KineticBlockEntityRenderer<MillstoneBlockEntity> {
    String id;
    public MillstoneTypeRenderer(String id, BlockEntityRendererProvider.Context context) {
        super(context);
        this.id = id;
    }

    @Override
    protected SuperByteBuffer getRotatedModel(MillstoneBlockEntity be, BlockState state) {
        return CachedBuffers.partial(PartialModel.of(DnDecor.loc("block/" + id + "_millstone/inner")), state);
    }
}

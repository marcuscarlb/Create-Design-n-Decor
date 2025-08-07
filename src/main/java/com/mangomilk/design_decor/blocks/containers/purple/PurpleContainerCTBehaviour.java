package com.mangomilk.design_decor.blocks.containers.purple;

import org.jetbrains.annotations.Nullable;

import com.mangomilk.design_decor.registry.CDDSpriteShifts;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.foundation.block.connected.CTSpriteShiftEntry;
import com.simibubi.create.foundation.block.connected.ConnectedTextureBehaviour;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class PurpleContainerCTBehaviour extends ConnectedTextureBehaviour.Base {

	@Override
	public CTSpriteShiftEntry getShift(BlockState state, Direction direction, @Nullable TextureAtlasSprite sprite) {
		Axis containerBlockAxis = PurpleContainerBlock.getContainerBlockAxis(state);
		boolean small = !PurpleContainerBlock.isLarge(state);
		if (containerBlockAxis == null)
			return null;

		if (direction.getAxis() == containerBlockAxis)
			return CDDSpriteShifts.PURPLE_CONTAINER_FRONT.get(small);
		if (direction == Direction.UP)
			return CDDSpriteShifts.PURPLE_CONTAINER_TOP.get(small);
		if (direction == Direction.DOWN)
			return CDDSpriteShifts.PURPLE_CONTAINER_BOTTOM.get(small);

		return CDDSpriteShifts.PURPLE_CONTAINER_SIDE.get(small);
	}

	@Override
	protected Direction getUpDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
		Axis containerBlockAxis = PurpleContainerBlock.getContainerBlockAxis(state);
		boolean alongX = containerBlockAxis == Axis.X;
		if (face.getAxis()
			.isVertical() && alongX)
			return super.getUpDirection(reader, pos, state, face).getClockWise();
		if (face.getAxis() == containerBlockAxis || face.getAxis()
			.isVertical())
			return super.getUpDirection(reader, pos, state, face);
		return Direction.fromAxisAndDirection(containerBlockAxis, alongX ? AxisDirection.POSITIVE : AxisDirection.NEGATIVE);
	}

	@Override
	protected Direction getRightDirection(BlockAndTintGetter reader, BlockPos pos, BlockState state, Direction face) {
		Axis containerBlockAxis = PurpleContainerBlock.getContainerBlockAxis(state);
		if (face.getAxis()
			.isVertical() && containerBlockAxis == Axis.X)
			return super.getRightDirection(reader, pos, state, face).getClockWise();
		if (face.getAxis() == containerBlockAxis || face.getAxis()
			.isVertical())
			return super.getRightDirection(reader, pos, state, face);
		return Direction.fromAxisAndDirection(Axis.Y, face.getAxisDirection());
	}

	public boolean buildContextForOccludedDirections() {
		return super.buildContextForOccludedDirections();
	}

	@Override
	public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos,
		BlockPos otherPos, Direction face) {
		return state == other && ConnectivityHandler.isConnected(reader, pos, otherPos); //ItemVaultConnectivityHandler.isConnected(reader, pos, otherPos);
	}

}

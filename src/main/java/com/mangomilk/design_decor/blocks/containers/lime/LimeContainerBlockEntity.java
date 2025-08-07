package com.mangomilk.design_decor.blocks.containers.lime;

import java.util.List;

import javax.annotation.Nullable;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.api.connectivity.ConnectivityHandler;
import com.simibubi.create.content.logistics.vault.ItemVaultBlock;
import com.simibubi.create.content.logistics.vault.ItemVaultBlockEntity;
import com.simibubi.create.foundation.blockEntity.IMultiBlockEntityContainer;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.blockEntity.behaviour.inventory.VersionedInventoryWrapper;
import com.simibubi.create.infrastructure.config.AllConfigs;

import io.github.fabricators_of_create.porting_lib.transfer.item.ItemStackHandler;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.CombinedStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LimeContainerBlockEntity extends ItemVaultBlockEntity implements IMultiBlockEntityContainer.Inventory {

	protected Storage<ItemVariant> itemCapability;

	protected ItemStackHandler inventory;
	protected BlockPos controller;
	protected BlockPos lastKnownPos;
	protected boolean updateConnectivity;
	public int radius;
	public int length;
	protected Axis axis;

	public LimeContainerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);

		inventory = new ItemStackHandler(AllConfigs.server().logistics.vaultCapacity.get()) {
			@Override
			protected void onContentsChanged(int slot) {
				super.onContentsChanged(slot);
				updateComparators();
			}
		};

		this.itemCapability = null;
		radius = 1;
		length = 1;
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {}

	protected void updateConnectivity() {
		updateConnectivity = false;
		if (level.isClientSide())
			return;
		if (!isController())
			return;
		ConnectivityHandler.formMulti(this);

	}

	protected void updateComparators() {
		LimeContainerBlockEntity controllerBE = getControllerBE();
		if (controllerBE == null)
			return;

		level.blockEntityChanged(controllerBE.worldPosition);

		BlockPos pos = controllerBE.getBlockPos();
		for (int y = 0; y < controllerBE.radius; y++) {
			for (int z = 0; z < (controllerBE.axis == Axis.X ? controllerBE.radius : controllerBE.length); z++) {
				for (int x = 0; x < (controllerBE.axis == Axis.Z ? controllerBE.radius : controllerBE.length); x++) {
					level.updateNeighbourForOutputSignal(pos.offset(x, y, z), getBlockState().getBlock());
				}
			}
		}
	}

	@Override
	@SuppressWarnings({})
	public void tick() {
		super.tick();


		if (lastKnownPos == null)
			lastKnownPos = getBlockPos();
		else if (!lastKnownPos.equals(worldPosition) && worldPosition != null) {
			onPositionChanged();
			return;
		}


		if (updateConnectivity)
			updateConnectivity();
	}

	@Override
	public BlockPos getLastKnownPos() {
		return lastKnownPos;
	}

	@Override
	public boolean isController() {
		return controller == null || worldPosition.getX() == controller.getX()
			&& worldPosition.getY() == controller.getY() && worldPosition.getZ() == controller.getZ();
	}

	private void onPositionChanged() {
		removeController(true);
		lastKnownPos = worldPosition;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LimeContainerBlockEntity getControllerBE() {
		if (isController())
			return this;
		BlockEntity blockEntity = level.getBlockEntity(controller);
		if (blockEntity instanceof LimeContainerBlockEntity)
			return (LimeContainerBlockEntity) blockEntity;
		return null;
	}

	public void removeController(boolean keepContents) {
		if (level.isClientSide())
			return;
		updateConnectivity = true;
		controller = null;
		radius = 1;
		length = 1;

		BlockState state = getBlockState();



			if (LimeContainerBlock.isContainer(state)) {
				state = state.setValue(LimeContainerBlock.LARGE, false);
				getLevel().setBlock(worldPosition, state, 22);
			}


		this.itemCapability = null;
		setChanged();
		sendData();
	}

	@Override
	public void setController(BlockPos controller) {
		if (level.isClientSide && !isVirtual())
			return;
		if (controller.equals(this.controller))
			return;
		this.controller = controller;
		this.itemCapability = null;
		setChanged();
		sendData();
	}

	@Override
	public BlockPos getController() {
		return isController() ? worldPosition : controller;
	}

	@Override
	protected void read(CompoundTag compound, boolean clientPacket) {
		super.read(compound, clientPacket);

		BlockPos controllerBefore = controller;
		int prevSize = radius;
		int prevLength = length;

		updateConnectivity = compound.contains("Uninitialized");
		controller = null;
		lastKnownPos = null;

		if (compound.contains("LastKnownPos"))
			lastKnownPos = NbtUtils.readBlockPos(compound.getCompound("LastKnownPos"));
		if (compound.contains("Controller"))
			controller = NbtUtils.readBlockPos(compound.getCompound("Controller"));

		if (isController()) {
			radius = compound.getInt("Size");
			length = compound.getInt("Length");
		}

		if (!clientPacket) {
			inventory.deserializeNBT(compound.getCompound("Inventory"));
			return;
		}

		boolean changeOfController =
			controllerBefore == null ? controller != null : !controllerBefore.equals(controller);
		if (hasLevel() && (changeOfController || prevSize != radius || prevLength != length))
			level.setBlocksDirty(getBlockPos(), Blocks.AIR.defaultBlockState(), getBlockState());
	}

	@Override
	protected void write(CompoundTag compound, boolean clientPacket) {
		if (updateConnectivity)
			compound.putBoolean("Uninitialized", true);
		if (lastKnownPos != null)
			compound.put("LastKnownPos", NbtUtils.writeBlockPos(lastKnownPos));
		if (!isController())
			compound.put("Controller", NbtUtils.writeBlockPos(controller));
		if (isController()) {
			compound.putInt("Size", radius);
			compound.putInt("Length", length);
		}


		if (!clientPacket) {
			compound.putString("StorageType", "CombinedInv");
			compound.put("Inventory", inventory.serializeNBT());
		}
	}

	public ItemStackHandler getInventoryOfBlock() {
		return inventory;
	}

	public void applyInventoryToBlock(ItemStackHandler handler) {
		for (int i = 0; i < inventory.getSlotCount(); i++)
			inventory.setStackInSlot(i, i < handler.getSlotCount() ? handler.getStackInSlot(i) : ItemStack.EMPTY);
	}

	@Nullable
	public Storage<ItemVariant> getItemStorage(@Nullable Direction face) {
		this.initCapability();
		return this.itemCapability;
	}

	protected void initCapability() {
		if (itemCapability == null) {
			if (!this.isController()) {
				LimeContainerBlockEntity controllerBE = this.getControllerBE();
				if (controllerBE != null) {
					controllerBE.initCapability();
					itemCapability = controllerBE.itemCapability;
				}
			} else {
				boolean alongZ = ItemVaultBlock.getVaultBlockAxis(this.getBlockState()) == Axis.Z;
				ItemStackHandler[] invs = new ItemStackHandler[this.length * this.radius * this.radius];

				for(int yOffset = 0; yOffset < this.length; ++yOffset) {
					for(int xOffset = 0; xOffset < this.radius; ++xOffset) {
						for(int zOffset = 0; zOffset < this.radius; ++zOffset) {
							BlockPos vaultPos = alongZ ? this.worldPosition.offset(xOffset, zOffset, yOffset) : this.worldPosition.offset(yOffset, xOffset, zOffset);
							LimeContainerBlockEntity vaultAt = (LimeContainerBlockEntity) ConnectivityHandler.partAt((BlockEntityType) AllBlockEntityTypes.ITEM_VAULT.get(), this.level, vaultPos);
							invs[yOffset * this.radius * this.radius + xOffset * this.radius + zOffset] = vaultAt != null ? vaultAt.inventory : new ItemStackHandler();
						}
					}
				}

				Storage<ItemVariant> combinedInvWrapper = new CombinedStorage(List.of(invs));
				combinedInvWrapper = new VersionedInventoryWrapper(combinedInvWrapper);
				this.itemCapability = combinedInvWrapper;
			}
		}
	}


	public static int getMaxLength(int radius) {
		return radius * 3;
	}

	@Override
	public void preventConnectivityUpdate() { updateConnectivity = false; }

	@Override
	public void notifyMultiUpdated() {
		BlockState state = this.getBlockState();

			if (LimeContainerBlock.isContainer(state)) { // safety
				level.setBlock(getBlockPos(), state.setValue(LimeContainerBlock.LARGE, radius > 2), 6);
			}

		this.itemCapability = null;
		setChanged();
	}

	@Override
	public Axis getMainConnectionAxis() { return getMainAxisOf(this); }

	@Override
	public int getMaxLength(Axis longAxis, int width) {
		if (longAxis == Axis.Y) return getMaxWidth();
		return getMaxLength(width);
	}

	@Override
	public int getMaxWidth() {
		return 3;
	}

	@Override
	public int getHeight() { return length; }

	@Override
	public int getWidth() { return radius; }

	@Override
	public void setHeight(int height) { this.length = height; }

	@Override
	public void setWidth(int width) { this.radius = width; }

	@Override
	public boolean hasInventory() { return true; }
}

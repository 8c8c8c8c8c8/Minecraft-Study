package zz.dbrvkf.minecraft_study.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.MinecraftStudy;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.custom.GemPolishingStationBlock;
import zz.dbrvkf.minecraft_study.item.NewItems;
import zz.dbrvkf.minecraft_study.screen.GemPolishingStationMenu;

import javax.crypto.spec.PSource;
import java.util.stream.IntStream;

public class GemPolishingStationBlockEntity extends BlockEntity implements MenuProvider {
    private int progress = 0;
    private int maxProgress = 78;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final ItemStackHandler ITEM_HANDLER = new ItemStackHandler(2);
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;

    protected final ContainerData data;

    public GemPolishingStationBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(NewBlockEntities.GEM_POLISHING_STATION.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> progress = pValue;
                    case 1 -> maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> ITEM_HANDLER);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public Component getDisplayName() {
        String key = String.format("block.%s.%s",
                MinecraftStudy.MOD_ID, NewBlocks.GEM_POLISHING_STATION.getId().getPath());
        return Component.translatable(key);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pInv, Player pPlayer) {
        return new GemPolishingStationMenu(pContainerId, pInv, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", ITEM_HANDLER.serializeNBT());
        pTag.putInt("gem_polishing_station.progress", progress);
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        ITEM_HANDLER.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("gem_polishing_station.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(ITEM_HANDLER.getSlots());
        IntStream.of(ITEM_HANDLER.getSlots()).forEach(index -> inventory.setItem(index, ITEM_HANDLER.getStackInSlot(index)));
        Containers.dropContents(level, worldPosition, inventory);
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if (!hasRecipe())
            return;
        increaseCraftingProgress();
        setChanged(pLevel, pPos, pState);
        if (hasProgressFinished()) {
            craftItem();
        }
        resetProgress();
    }

    private void craftItem() {
        ItemStack result = new ItemStack(NewItems.SAPPHIRE.get(), 1);
        ITEM_HANDLER.extractItem(INPUT_SLOT, 1, false);
        ITEM_HANDLER.setStackInSlot(OUTPUT_SLOT, new ItemStack(result.getItem(),
                ITEM_HANDLER.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasProgressFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(NewItems.SAPPHIRE.get());
        return hasCraftingItem() && canInsertAmountIntoOutputSlot(result.getCount())
                && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        ItemStack output = ITEM_HANDLER.getStackInSlot(OUTPUT_SLOT);
        return output.isEmpty() || output.is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        ItemStack output = ITEM_HANDLER.getStackInSlot(OUTPUT_SLOT);
        return output.getCount() + count <= output.getMaxStackSize();
    }

    private boolean hasCraftingItem() {
        return ITEM_HANDLER.getStackInSlot(INPUT_SLOT).getItem() == NewItems.RAW_SAPPHIRE.get();
    }

    private void resetProgress() {
        progress = 0;
    }
}

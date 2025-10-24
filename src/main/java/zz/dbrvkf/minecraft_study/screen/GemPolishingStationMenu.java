package zz.dbrvkf.minecraft_study.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.SlotItemHandler;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.entity.GemPolishingStationBlockEntity;

import java.util.stream.IntStream;

public class GemPolishingStationMenu extends AbstractContainerMenu {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int SLOT_SIZE = 2;
    private final Level level;
    private final ContainerData data;
    public final GemPolishingStationBlockEntity blockEntity;

    public GemPolishingStationMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(SLOT_SIZE));
    }

    public GemPolishingStationMenu(int pContainerId, Inventory inv, BlockEntity entity, ContainerData data) {
        super(NewMenuTypes.GEM_POLISHING_MENU.get(), pContainerId);
        checkContainerSize(inv, SLOT_SIZE);
        this.blockEntity = (GemPolishingStationBlockEntity) entity;
        this.level = inv.player.level();
        this.data = data;
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
        blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            addSlot(new SlotItemHandler(handler, INPUT_SLOT, 80, 11));
            addSlot(new SlotItemHandler(handler, OUTPUT_SLOT, 80, 59));
        });
        addDataSlots(data);
    }

    // CREDIT GOES TO: diesieben07 | https://github.com/diesieben07/SevenCommons
    // must assign a slot number to each of the slots used by the GUI.
    // For this container, we can see both the tile inventory's slots as well as the player inventory slots and the hotbar.
    // Each time we add a Slot to the container, it automatically increases the slotIndex, which means
    //  0 - 8 = hotbar slots (which will map to the InventoryPlayer slot numbers 0 - 8)
    //  9 - 35 = player inventory slots (which map to the InventoryPlayer slot numbers 9 - 35)
    //  36 - 44 = TileInventory slots, which map to our TileEntity slot numbers 0 - 8)

    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        int hotbarSlotCount = 9;
        int playerInventoryRowCount = 3;
        int playerInventoryColumnCount = 9;
        int playerInventorySlotCount = playerInventoryColumnCount * playerInventoryRowCount;
        int vanillaSlotCount = hotbarSlotCount + playerInventorySlotCount;
        int vanillaFirstSlotIndex = 0;
        int teInventoryFirstSlotIndex = vanillaFirstSlotIndex + vanillaSlotCount;
        int teInventorySlotCount = SLOT_SIZE;  // must be the number of slots you have!

        Slot sourceSlot = slots.get(pIndex);
        if (!sourceSlot.hasItem())
            return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();

        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < vanillaFirstSlotIndex + vanillaSlotCount) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if (!moveItemStackTo(sourceStack, teInventoryFirstSlotIndex, teInventoryFirstSlotIndex
                    + teInventorySlotCount, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        } else if (pIndex < teInventoryFirstSlotIndex + teInventorySlotCount) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, vanillaFirstSlotIndex, vanillaFirstSlotIndex + vanillaSlotCount, false)) {
                return ItemStack.EMPTY;
            }
        } else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, NewBlocks.GEM_POLISHING_STATION.get());
    }

    public boolean isCrafting() {
        int progress = data.get(INPUT_SLOT);
        return progress > 0;
    }

    public int getScaledProgress() {
        int progress = data.get(INPUT_SLOT);
        int maxProgress = data.get(OUTPUT_SLOT);
        int progressArrowSize = 26;
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    private void addPlayerInventory(Inventory pInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlot(new Slot(pInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory pInventory) {
        int hotBarStart = 0;
        int hotBarEnd = 9;
        IntStream.range(hotBarStart, hotBarEnd).forEach(i -> addSlot(new Slot(pInventory, i, 8 + i * 18, 142)));
    }
}

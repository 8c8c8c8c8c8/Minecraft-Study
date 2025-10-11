package zz.dbrvkf.minecraft_study.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import zz.dbrvkf.minecraft_study.sound.NewSounds;
import zz.dbrvkf.minecraft_study.util.NewTags;

import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            BlockState state;
            for (int i = 0; i <= posClicked.getY() + 64; i++) {
                state = pContext.getLevel().getBlockState(posClicked.below(i));
                if (isValuableBlock(state)) {
                    printValuableCoordinates(posClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    pContext.getLevel().playSeededSound(null,
                            posClicked.getX(), posClicked.getY(), posClicked.getZ(),
                            NewSounds.METAL_DETECTOR_FOUND_ORE.get(), SoundSource.BLOCKS,
                            1f, 1f, 0);
                    break;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No Valuables Found!"));
            }
        }
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.minecraft_study.metal_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void printValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        String msg = String.format("Found: %s at (%s, %s, %s)", I18n.get(block.getDescriptionId()),
                blockPos.getX(), blockPos.getY(), blockPos.getZ());
        player.sendSystemMessage(Component.literal(msg));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(NewTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}

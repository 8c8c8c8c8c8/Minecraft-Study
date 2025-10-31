package zz.dbrvkf.minecraft_study.world.portal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.util.ITeleporter;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.custom.NewPortalBlock;

import java.util.function.Function;

public class NewTeleporter implements ITeleporter {
    public static BlockPos thisPos = BlockPos.ZERO;
    public static boolean insideDim = true;

    public NewTeleporter(BlockPos pos, boolean insideDimension) {
        thisPos = pos;
        insideDim = insideDimension;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw,
                              Function<Boolean, Entity> repositionEntity) {
        Entity entity2 = repositionEntity.apply(false);
        int y = 61;
        if (!insideDim) y = thisPos.getY();

        BlockPos destPos = new BlockPos(thisPos.getX(), y, thisPos.getZ());

        int tries = 0;
        while ((destWorld.getBlockState(destPos).getBlock() != Blocks.AIR) &&
                !destWorld.getBlockState(destPos).canBeReplaced(Fluids.WATER) &&
                (destWorld.getBlockState(destPos.above()).getBlock() != Blocks.AIR) &&
                !destWorld.getBlockState(destPos).canBeReplaced(Fluids.WATER) &&
                (tries < 25)) {
            destPos = destPos.above(2);
            tries++;
        }
        entity2.setPos(destPos.getX(), destPos.getY(), destPos.getZ());

        if (!insideDim)
            return entity2;

        boolean doSetBlock = true;
        for (BlockPos pos : BlockPos.betweenClosed(destPos.below(10).west(10), destPos.above(10).east(10))) {
            if (destWorld.getBlockState(pos).getBlock() instanceof NewPortalBlock) {
                doSetBlock = false;
                break;
            }
        }
        if (doSetBlock) {
            destWorld.setBlock(destPos, NewBlocks.NEW_PORTAL.get().defaultBlockState(), 3);
        }
        return entity2;
    }
}

package zz.dbrvkf.minecraft_study.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import zz.dbrvkf.minecraft_study.world.dimension.NewDimensions;
import zz.dbrvkf.minecraft_study.world.portal.NewTeleporter;

public class NewPortalBlock extends Block {
    public NewPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handleDbrvkfPortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.CONSUME;
    }

    private void handleDbrvkfPortal(Player player, BlockPos pos) {
        if (!(player.level() instanceof ServerLevel)) return;

        MinecraftServer mcServer = player.level().getServer();
        ResourceKey<Level> levelKey = getLevelKey(player);
        ServerLevel portalDimension = mcServer.getLevel(levelKey);

        if (portalDimension == null || player.isPassenger()) return;

        boolean insideDimension = false;
        if (levelKey == NewDimensions.DBRVKF_LEVEL_KEY) insideDimension = true;

        player.changeDimension(portalDimension, new NewTeleporter(pos, insideDimension));
    }

    private ResourceKey<Level> getLevelKey(Player player) {
        if (player.level().dimension() == NewDimensions.DBRVKF_LEVEL_KEY)
            return Level.OVERWORLD;
        return NewDimensions.DBRVKF_LEVEL_KEY;
    }

}

package zz.dbrvkf.minecraft_study.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.block.custom.DiceBlock;
import zz.dbrvkf.minecraft_study.entity.NewEntities;
import zz.dbrvkf.minecraft_study.item.NewItems;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DiceProjectileEntity(Level pLevel) {
        super(NewEntities.DICE_PROJECTILE.get(), pLevel);
    }

    public DiceProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(NewEntities.DICE_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return NewItems.DICE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!level().isClientSide()) {
            level().broadcastEntityEvent(this, (byte) 3);
            level().setBlock(blockPosition(), ((DiceBlock) NewBlocks.DICE.get()).getRandomBlockState(), 3);
        }
        this.discard();
        super.onHitBlock(pResult);
    }
}

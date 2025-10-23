package zz.dbrvkf.minecraft_study.item.custom;

import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import zz.dbrvkf.minecraft_study.entity.custom.NewBoatEntity;
import zz.dbrvkf.minecraft_study.entity.custom.NewChestBoatEntity;

import java.util.List;
import java.util.function.Predicate;

public class NewBoatItem extends Item {
    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final NewBoatEntity.Type type;
    private final boolean hasChest;

    public NewBoatItem(boolean pHasChest, NewBoatEntity.Type pType, Item.Properties pProperties) {
        super(pProperties);
        this.hasChest = pHasChest;
        this.type = pType;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pHand);
        HitResult hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);
        if (hitResult.getType() == HitResult.Type.MISS)
            return InteractionResultHolder.pass(itemStack);

        Vec3 vec3 = pPlayer.getViewVector(1.0F);
        double d0 = 5.0D;
        List<Entity> list = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(vec3.scale(d0)).inflate(1.0D), ENTITY_PREDICATE);
        if (list.isEmpty())
            return InteractionResultHolder.pass(itemStack);

        Vec3 vec31 = pPlayer.getEyePosition();
        for (Entity entity : list) {
            AABB aabb = entity.getBoundingBox().inflate((double) entity.getPickRadius());
            if (aabb.contains(vec31))
                return InteractionResultHolder.pass(itemStack);
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            Boat boat = this.getBoat(pLevel, hitResult);
            if (boat instanceof NewChestBoatEntity newBoat) {
                newBoat.setVariant(this.type);
            }
            if (boat instanceof NewBoatEntity newBoat) {
                newBoat.setVariant(this.type);
            }
            boat.setYRot(pPlayer.getYRot());
            if (!pLevel.noCollision(boat, boat.getBoundingBox())) {
                return InteractionResultHolder.fail(itemStack);
            }
            pPlayer.awardStat(Stats.ITEM_USED.get(this));
            if (pLevel.isClientSide)
                return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());

            pLevel.addFreshEntity(boat);
            pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitResult.getLocation());
            if (!pPlayer.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
            return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
        }
        return InteractionResultHolder.pass(itemStack);
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        Vec3 location = hitResult.getLocation();
        if (hasChest)
            return new NewChestBoatEntity(level, location.x, location.y, location.z);
        return new NewBoatEntity(level, location.x, location.y, location.z);
    }
}

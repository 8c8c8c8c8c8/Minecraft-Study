package zz.dbrvkf.minecraft_study.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import zz.dbrvkf.minecraft_study.block.NewBlocks;
import zz.dbrvkf.minecraft_study.entity.NewEntities;
import zz.dbrvkf.minecraft_study.item.NewItems;

import java.util.function.IntFunction;

public class NewChestBoatEntity extends ChestBoat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(NewChestBoatEntity.class, EntityDataSerializers.INT);

    public NewChestBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public NewChestBoatEntity(Level pLevel, double pX, double pY, double pZ) {
        this(NewEntities.CHEST_BOAT.get(), pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;

    }

    @Override
    public Item getDropItem() {
        return switch (getNewVariant()) {
            case PINE -> NewItems.PINE_CHEST_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getNewVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, NewChestBoatEntity.Type.PINE.ordinal());
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getNewVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public static enum Type implements StringRepresentable {
        PINE(NewBlocks.PINE_PLANKS.get(), "pine");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<NewChestBoatEntity.Type> CODEC = StringRepresentable.fromEnum(NewChestBoatEntity.Type::values);
        private static final IntFunction<NewChestBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

        public static NewChestBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static NewChestBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, PINE);
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

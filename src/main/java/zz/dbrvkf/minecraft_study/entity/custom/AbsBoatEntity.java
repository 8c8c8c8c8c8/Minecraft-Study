package zz.dbrvkf.minecraft_study.entity.custom;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import zz.dbrvkf.minecraft_study.block.NewBlocks;

import java.util.function.IntFunction;

public abstract class AbsBoatEntity extends Boat {

    public AbsBoatEntity(EntityType<? extends Boat> pEntityType, Level pLevel, double pX, double pY, double pZ) {
        super(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return super.getDropItem();
    }

    public AbsBoatEntity.Type getVariant(String a) {
        return null;
    }

    public static enum Type implements StringRepresentable {
        PINE(NewBlocks.PINE_PLANKS.get(), "pine");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<AbsBoatEntity.Type> CODEC = StringRepresentable.fromEnum(AbsBoatEntity.Type::values);
        private static final IntFunction<AbsBoatEntity.Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static AbsBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static AbsBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, PINE);
        }
    }
}

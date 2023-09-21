package pers.zlc.stairchair.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChairEntity extends Entity {

    public final BlockPos stairPos;

    public ChairEntity(EntityType<?> type, World world, BlockPos stairPos) {
        super(type, world);
        this.stairPos = stairPos;
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }
}

package pers.zlc.stairchair.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pers.zlc.stairchair.ChairStorage;
import pers.zlc.stairchair.entity.ChairEntity;

@Mixin(StairsBlock.class)
public class StairsBlockMixin {

    @Inject(at = @At("HEAD"), method = "onUse")
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (player.getMainHandStack().isEmpty() && state.get(StairsBlock.HALF).equals(BlockHalf.BOTTOM)) {
            ChairEntity chair = new ChairEntity(EntityType.ITEM_DISPLAY, world, pos);
            Vec3d chairPos = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5);
            chair.setPosition(chairPos);
            chair.setNoGravity(true);
            chair.setInvisible(true);
            world.spawnEntity(chair);
            ((ChairStorage) player.getServer()).stairChair$getChairList().add(chair);
            player.startRiding(chair);
        }
    }
}

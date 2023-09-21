package pers.zlc.stairchair.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import pers.zlc.stairchair.ChairStorage;
import pers.zlc.stairchair.entity.ChairEntity;

import java.util.LinkedList;
import java.util.List;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin implements ChairStorage {

    @Unique
    public final List<ChairEntity> chairList = new LinkedList<>();

    @Override
    public List<ChairEntity> stairChair$getChairList() {
        return chairList;
    }
}

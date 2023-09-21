package pers.zlc.stairchair;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.StairsBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import pers.zlc.stairchair.entity.ChairEntity;

import java.util.Iterator;
import java.util.List;

public class StairChair implements ModInitializer, ServerTickEvents.StartTick {

    @Override
    public void onInitialize() {
        ServerTickEvents.START_SERVER_TICK.register(this);
    }

    @Override
    public void onStartTick(MinecraftServer server) {
        List<ChairEntity> chairList = ((ChairStorage) server).stairChair$getChairList();
        if (chairList.isEmpty()) {
            return;
        }
        for (Iterator<ChairEntity> iterator = chairList.iterator(); iterator.hasNext(); ) {
            ChairEntity chair = iterator.next();
            if (!chair.hasPassengers() || !(chair.getWorld().getBlockState(chair.stairPos).getBlock() instanceof StairsBlock)) {
                chair.stopRiding();
                chair.remove(Entity.RemovalReason.DISCARDED);
                iterator.remove();
            }
        }
    }
}

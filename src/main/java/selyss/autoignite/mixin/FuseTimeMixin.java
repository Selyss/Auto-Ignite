package selyss.autoignite.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public abstract class FuseTimeMixin {
    @Inject(at = @At("TAIL"), method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V")
    private void onTntEntityCreate(World world, double x, double y, double z, @Nullable LivingEntity igniter, CallbackInfo ci) {
        // make sure its single player (is this needed)
        if (!MinecraftClient.getInstance().isInSingleplayer()) {
            return;
        }
        TntEntity tntEntity = (TntEntity) (Object) this;
        tntEntity.setPosition(x, y + 0.5, z);
        tntEntity.setFuse(20); // 1 second
    }
}

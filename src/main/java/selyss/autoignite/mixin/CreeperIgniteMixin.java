package selyss.autoignite.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperIgniteMixin {
    @Shadow
    private static TrackedData<Boolean> IGNITED;

    @Shadow
    private int fuseTime;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInit(EntityType entityType, World world, CallbackInfo ci) {
        CreeperEntity creeper = (CreeperEntity) (Object) this;
        creeper.getDataTracker().set(IGNITED, true);
        this.fuseTime = 20;
    }
}

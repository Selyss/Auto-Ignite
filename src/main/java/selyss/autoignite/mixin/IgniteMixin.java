package selyss.autoignite.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TntEntity.class)
public class IgniteMixin {

    @Inject(at = @At("TAIL"), method = "<init>*")
    private void onTntEntityCreate(World world, double x, double y, double z, @Nullable LivingEntity igniter, CallbackInfo ci) {
        TntEntity tntEntity = (TntEntity) (Object) this;

        tntEntity.setFuse(20); // 1 second
    }
}
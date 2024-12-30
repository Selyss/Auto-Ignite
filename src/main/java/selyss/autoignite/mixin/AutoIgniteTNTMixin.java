package selyss.autoignite.mixin;

import net.minecraft.block.TntBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TntBlock.class)
public class AutoIgniteTNTMixin {
	@Redirect(method = "onBlockAdded", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isReceivingRedstonePower(Lnet/minecraft/util/math/BlockPos;)Z"))
	private boolean igniteTnt(World instance, BlockPos blockPos)
	{
		// make sure its single player (is this needed)
        return MinecraftClient.getInstance().isInSingleplayer();
    }
}


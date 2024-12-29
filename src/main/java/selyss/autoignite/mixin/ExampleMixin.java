package selyss.autoignite.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.TntBlock;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(BlockItem.class)
public class ExampleMixin {
	@Inject(at = @At("HEAD"), method = "useOnBlock")
	private void onUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
		World world = context.getWorld();
		BlockPos pos = context.getBlockPos();
		PlayerEntity user = context.getPlayer();
		Hand hand = context.getHand();
//
//		if (!world.isClient) {
//			ServerWorld serverWorld = (ServerWorld) world;
//			if (user.getStackInHand(hand).getItem() == Items.TNT) {
//				ItemStack itemStack = user.getStackInHand(hand);
//
//				TntEntity tntEntity = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), user);
//				serverWorld.spawnEntity(tntEntity);
//				tntEntity.setFuse(20); // 1 second
//				itemStack.decrementUnlessCreative(1, user);
//			}
//		}
	}
}


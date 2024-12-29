package selyss.autoignite;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoIgnite implements ModInitializer {
	public static final String MOD_ID = "auto-ignite";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	@Override
	public void onInitialize() {
		// Register the block use callback
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClient) {
				BlockPos pos = hitResult.getBlockPos();
				if (world.getBlockState(pos).getBlock() == Blocks.TNT) {
					// Replace the TNT block with a primed TNT entity
					igniteTNT(world, pos, player);
					return ActionResult.SUCCESS;
				}
			}
			return ActionResult.PASS;
		});
	}

	private void igniteTNT(World world, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
		// Remove the TNT block
		world.setBlockState(pos, Blocks.AIR.getDefaultState());

		// Spawn a primed TNT entity
		TntEntity tntEntity = new TntEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, player);
		tntEntity.setFuse(20); // Set fuse time (20 ticks = 1 second)
		world.spawnEntity(tntEntity);
	}
}

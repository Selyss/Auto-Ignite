package selyss.autoignite;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoIgnite implements ModInitializer {
	public static final String MOD_ID = "auto-ignite";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Detect TNT block placement
		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			if (!world.isClient()) {
				if (player.getStackInHand(hand).getItem() != Blocks.TNT.asItem()) {
					return ActionResult.PASS;
				}
				BlockPos pos = hitResult.getBlockPos();
				player.sendMessage(Text.of("X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()), false);

				ServerWorld serverWorld = (ServerWorld) world;
				TntEntity tntEntity = new TntEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, player);
				serverWorld.spawnEntity(tntEntity);
				tntEntity.setFuse(20); // 1 second
				player.sendMessage(Text.of("Auto-igniting TNT"), false);
			}
			return ActionResult.PASS;
		});
	}
}
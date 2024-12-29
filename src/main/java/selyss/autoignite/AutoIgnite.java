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
				BlockPos pos = hitResult.getBlockPos();
				ServerWorld serverWorld = (ServerWorld) world;


				if (player.getStackInHand(hand).getItem() == Blocks.TNT.asItem()) {
					player.sendMessage(Text.of("X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ()), false);
					spawnTnt(serverWorld, pos, player);
				}

			}
			return ActionResult.PASS;
		});
	}
	private void spawnTnt(ServerWorld world, BlockPos pos, PlayerEntity player) {
		TntEntity tntEntity = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), player);
		world.spawnEntity(tntEntity);
		tntEntity.setFuse(20); // 1 second
		player.sendMessage(Text.of("Auto-igniting TNT"), false);
	}
}
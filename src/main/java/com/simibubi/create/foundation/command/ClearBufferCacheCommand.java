package com.simibubi.create.foundation.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.simibubi.create.CreateClient;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class ClearBufferCacheCommand {

	static ArgumentBuilder<CommandSourceStack, ?> register() {
		return Commands.literal("clearRenderBuffers")
			.requires(cs -> cs.hasPermission(0))
			.executes(ctx -> {
				DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> ClearBufferCacheCommand::execute);
				ctx.getSource()
					.sendSuccess(new TextComponent("Cleared rendering buffers."), true);
				return 1;
			});
	}

	@OnlyIn(Dist.CLIENT)
	private static void execute() {
		CreateClient.invalidateRenderers();
	}
}

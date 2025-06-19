package com.moepus.createbetterfps.renderer;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.caffeinemc.mods.sodium.api.vertex.attributes.common.*;
import net.caffeinemc.mods.sodium.api.vertex.format.VertexFormatDescription;
import net.caffeinemc.mods.sodium.api.vertex.format.VertexFormatRegistry;

public class EntityVertex {
	public static final VertexFormatDescription FORMAT;
	public static final int STRIDE = 36;

	public EntityVertex() {
	}

	public static void write(long ptr, float x, float y, float z, int color, float u, float v, int overlay, int light, int normal) {
		PositionAttribute.put(ptr + 0L, x, y, z);
		ColorAttribute.set(ptr + 12L, color);
		TextureAttribute.put(ptr + 16L, u, v);
		OverlayAttribute.set(ptr + 24L, overlay);
		LightAttribute.set(ptr + 28L, light);
		NormalAttribute.set(ptr + 32L, normal);
	}

	static {
		FORMAT = VertexFormatRegistry.instance().get(DefaultVertexFormat.NEW_ENTITY);
	}
}

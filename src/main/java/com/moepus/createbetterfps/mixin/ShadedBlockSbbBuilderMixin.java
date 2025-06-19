package com.moepus.createbetterfps.mixin;

import com.moepus.createbetterfps.renderer.SodiumByteBuffer;
import com.mojang.blaze3d.vertex.BufferBuilder;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.createmod.catnip.render.MutableTemplateMesh;
import net.createmod.catnip.render.SuperByteBuffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import net.createmod.catnip.render.ShadedBlockSbbBuilder;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ShadedBlockSbbBuilder.class, remap = false)
public abstract class ShadedBlockSbbBuilderMixin {
    @Shadow
    @Final
    protected BufferBuilder bufferBuilder;

    @Shadow
    @Final
    protected IntList shadeSwapVertices = new IntArrayList();

    @Inject(method = "end", at = @At(value = "HEAD"), cancellable = true, require = 0)
    public void onEnd(CallbackInfoReturnable<SuperByteBuffer> cir) {
        BufferBuilder.RenderedBuffer data = bufferBuilder.end();
        MutableTemplateMesh mesh = new MutableTemplateMesh(data);
        cir.setReturnValue(new SodiumByteBuffer(mesh.toImmutable(), shadeSwapVertices.toIntArray()));
    }
}

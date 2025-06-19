package com.moepus.createbetterfps.mixin;

import com.moepus.createbetterfps.renderer.SodiumByteBuffer;
import com.mojang.blaze3d.vertex.BufferBuilder;
import net.createmod.catnip.render.MutableTemplateMesh;
import net.createmod.catnip.render.SuperBufferFactory;
import net.createmod.catnip.render.SuperByteBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SuperBufferFactory.class, remap = false)
public abstract class SuperBufferFactoryMixin {
    @Inject(method = "create", at = @At(value = "HEAD"), cancellable = true, require = 0)
    public void onCreate(BufferBuilder.RenderedBuffer builder, CallbackInfoReturnable<SuperByteBuffer> cir) {
        cir.setReturnValue(new SodiumByteBuffer(new MutableTemplateMesh(builder).toImmutable()));
    }
}

package com.moepus.createbetterfps.mixin;

import com.moepus.createbetterfps.renderer.SodiumByteBuffer;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.createmod.catnip.render.MutableTemplateMesh;
import net.createmod.catnip.render.SuperByteBuffer;
import net.createmod.catnip.render.SuperByteBufferBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SuperByteBufferBuilder.class, remap = false)
public abstract class SuperByteBufferBuilderMixin {
    @Shadow
    @Final
    protected MutableTemplateMesh mesh = new MutableTemplateMesh();

    @Shadow
    @Final
    protected IntList shadeSwapVertices = new IntArrayList();

    @Inject(method = "build", at = @At(value = "HEAD"), cancellable = true, require = 0)
    public void onBuild(CallbackInfoReturnable<SuperByteBuffer> cir) {
        cir.setReturnValue(new SodiumByteBuffer(this.mesh.toImmutable(), this.shadeSwapVertices.toIntArray()));
    }
}

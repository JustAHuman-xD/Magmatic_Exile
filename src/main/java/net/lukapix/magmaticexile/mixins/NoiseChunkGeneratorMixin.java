package net.lukapix.magmaticexile.mixins;

import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseChunkGeneratorMixin {
    @Inject(method = "createFluidPicker", at = @At("HEAD"), cancellable = true)
    private static void mc237017fix$modifyLavaFluidLevelSampler(NoiseGeneratorSettings p_249264_, CallbackInfoReturnable<Aquifer.FluidPicker> cir) {
        Aquifer.FluidStatus fluidStatus = new Aquifer.FluidStatus(p_249264_.seaLevel(), p_249264_.defaultFluid());
        cir.setReturnValue((p_224274_, p_224275_, p_224276_) -> fluidStatus);
    }
}
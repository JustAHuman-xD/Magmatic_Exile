package net.lukapix.mixins;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.core.Registry;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({NoiseBasedChunkGenerator.class})
public class NoiseChunkGeneratorMixin {

    @Shadow @Final protected Holder<NoiseGeneratorSettings> settings;

    @ModifyVariable(method = "<init>",
            at = @At(value = "STORE"))
    private Aquifer.FluidStatus mc237017fix$modifyLavaFluidLevelSampler(Aquifer.FluidStatus fluidLevel) {
        NoiseGeneratorSettings chunkGeneratorSettings = this.settings.value();
        return new Aquifer.FluidStatus(chunkGeneratorSettings.seaLevel(), chunkGeneratorSettings.defaultFluid());
    }
}
package net.babanovec.energycraft.datagen;

import net.babanovec.energycraft.Energycraft;
import net.babanovec.energycraft.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output,  ExistingFileHelper exFileHelper) {
        super(output, Energycraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.STORAGE_BLOCK);
        horizontalBlock(ModBlocks.CRUSHER.get(),overlayBlockModel(modLoc("block/casing/red_casing"), modLoc("block/machine/crusher/crusher_front")));
        simpleBlockItem(ModBlocks.CRUSHER.get(),overlayBlockModel(modLoc("block/casing/red_casing"), modLoc("block/machine/crusher/crusher_front")));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private ModelFile overlayBlockModel( ResourceLocation all, ResourceLocation overlay) {
        return models().withExistingParent("block", mcLoc("block/block"))
                .texture("particle", all)
                    .texture("all", all)
                    .texture("overlay", overlay)
                    .element()
                            .from(0f,0f,0f).to(16f,16f,16f)
                            .allFaces((dir, face) -> face.texture("#all"))
                            .end()
                    .element()
                            .from(0f,0f,0f).to(16f,16f,16f)
                            .face(Direction.NORTH).texture("#overlay").end()
                    .end();

    }


}

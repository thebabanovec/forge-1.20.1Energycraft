package net.babanovec.energycraft.datagen;

import net.babanovec.energycraft.Energycraft;
import net.babanovec.energycraft.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Energycraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL);
        this.tag(BlockTags.NEEDS_STONE_TOOL);
        this.tag(Tags.Blocks.NEEDS_GOLD_TOOL);
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CRUSHER.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);
        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CRUSHER.get());
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.STORAGE_BLOCK.get());
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL);
        this.tag(BlockTags.MINEABLE_WITH_HOE);

    }
}

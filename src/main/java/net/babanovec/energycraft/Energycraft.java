package net.babanovec.energycraft;

import com.mojang.logging.LogUtils;
import net.babanovec.energycraft.block.ModBlocks;
import net.babanovec.energycraft.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(Energycraft.MOD_ID) // The value here should match an entry in the META-INF/mods.toml file
public class Energycraft {

    public static final String MOD_ID = "energycraft";   // Define mod id in a common place for everything to reference

    private static final Logger LOGGER = LogUtils.getLogger();     // Directly reference a slf4j logger

    public Energycraft(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup); // Register the commonSetup method for modloading// Register the commonSetup method for modloading

        MinecraftForge.EVENT_BUS.register(this); // Register ourselves for server and other game events we are interested in

        modEventBus.addListener(this::addCreative);  // Register the item to a creative tab

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC); // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CIRCUIT_BOARD);

        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.STORAGE_BLOCK);

        }

        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.CRUSHER);
        }
    }


    @SubscribeEvent // You can use SubscribeEvent and let the Event Bus discover methods to call
    public void onServerStarting(ServerStartingEvent event) {
         // Do something when the server starts
    }


    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT) // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    public static class ClientModEvents { // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
             // Some client setup code
        }
    }
}

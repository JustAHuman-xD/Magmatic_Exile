package net.lukapix.magmaticexile;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.concurrent.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MagmaticExile.MOD_ID)
public class MagmaticExile
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "magmaticexile";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public MagmaticExile()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("[MagmaticExile] Loading..");
    }

    public class ExecutorTest{
        public static void main(String args[]){

            int numberOfTasks = Integer.parseInt(args[0]);
            ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            try{
                for ( int i=0; i < numberOfTasks; i++){
                    executor.execute(new MyRunnable(i));
                }
            }catch(Exception err){
                err.printStackTrace();
            }
            executor.shutdown(); // once you are done with ExecutorService
        }
    }

    public static class MyRunnable implements Runnable{
        int id;
        public MyRunnable(int i){
            this.id = i;
        }
        public void run(){
            try{
                System.out.println(" ");
                System.out.println("Pix Log Mirror started on id:"+id);
                System.out.println("Run: "+ Thread.currentThread().getName());

                String WorkingDirectory = Common.main();
                String LatestLogFile = WorkingDirectory + "\\logs\\latest.log";
                String LatestOutFile = WorkingDirectory + "\\logs\\latest.txt";

                BufferedReader input = new BufferedReader(new FileReader(LatestLogFile));
                String last, line;

                System.out.println(" ");
                System.out.println("Pix Log Mirror ended on id:"+id);
                System.out.println(" ");

                while (true) {

                    while ((line = input.readLine()) != null) {
                        last = line;

                        PrintWriter out = new PrintWriter(LatestOutFile);
                        out.println(last);
                        out.close();

                    }
                }
            }catch(Exception err){
                err.printStackTrace();
            }
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

            LOGGER.info("Hello from MagmaticExile, now proceeding to disembowel your Game..");

            System.out.println("---PIX-19-MULTITHREADING---");
            String WorkingDirectory = Common.main();
            System.out.println("PIX19-Threading Working Directory is: " + WorkingDirectory);
            var t = new Thread(new MagmaticExile.MyRunnable(1));
            t.setDaemon(true);
            t.run();
        }
    }
}

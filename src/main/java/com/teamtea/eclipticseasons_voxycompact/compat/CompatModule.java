package com.teamtea.eclipticseasons_voxycompact.compat;


import com.teamtea.eclipticseasons.compat.Platform;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.loading.FMLLoader;


public class CompatModule {
    private static boolean voxy = false;
    //@Getter
    //private static boolean voxyTest = false;


    public static boolean isVoxy() {
        return voxy;
    }

    /**
     * Used for mod init detect.
     **/
    public static void init() {
        voxy = Platform.isModLoaded("voxy");
    }

    /**
     * Used for mod init event register.
     **/
    public static void register(IEventBus gameBus, IEventBus modBus) {
        if (isVoxy() && FMLLoader.getDist() == Dist.CLIENT) {
            try {
                Class<?> handler = Class.forName("com.teamtea.eclipticseasons_voxycompact.compat.voxy.VoxyEsHandler");
                gameBus.register(handler.getField("INSTANCE").get(null));
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static class CommonConfig {
        public static ForgeConfigSpec.BooleanValue voxyTest;
        public static ForgeConfigSpec.BooleanValue voxyLODAutoReload;
        public static ForgeConfigSpec.BooleanValue voxyReloadWhenSeasonChanged;

        public static void load(ForgeConfigSpec.Builder builder) {
            builder.push("Compat");
            if (isVoxy()) {
                voxyTest = builder
                        .worldRestart()
                        .comment("""
                                .
                                Just for test.
                                .""".strip()
                        ).define("VoxyTest", true);

                voxyLODAutoReload = builder
                        //.worldRestart()
                        .comment("""
                                .
                                Just for test.
                                .""".strip()
                        ).define("VoxyLODAutoReload", false);


                voxyReloadWhenSeasonChanged = builder
                        //.worldRestart()
                        .comment("""
                                .
                                Just for test.
                                .""".strip()
                        ).define("VoxyReloadWhenSeasonChanged", false);
            }
            builder.pop();
        }
    }

}

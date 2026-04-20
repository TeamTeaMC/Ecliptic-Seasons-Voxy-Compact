package com.teamtea.eclipticseasons_voxycompact.compat;


import com.teamtea.eclipticseasons.compat.Platform;
import lombok.Getter;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.ModConfigSpec;


public class CompatModule {

    private static boolean ctm = false;
    private static boolean continuity = false;
    @Getter
    private static boolean fabric_renderer_indigo = false;
    @Getter
    private static boolean sodium = false;
    @Getter
    private static boolean iris = false;
    @Getter
    private static boolean modernui = false;
    @Getter
    private static boolean distanthorizons = false;

    @Getter
    private static boolean voxy = false;
    //@Getter
    //private static boolean voxyTest = false;


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
        public static ModConfigSpec.BooleanValue voxyTest;
        public static ModConfigSpec.BooleanValue voxyLODAutoReload;
        public static ModConfigSpec.BooleanValue voxyReloadWhenSeasonChanged;

        public static void load(ModConfigSpec.Builder builder) {
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

package com.teamtea.eclipticseasons_voxycompact.compat;


import com.teamtea.eclipticseasons.compat.Platform;
import lombok.Getter;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.ModConfigSpec;


public class CompatModule {
    private static boolean voxy = false;

    @Getter
    private static boolean roxyTest = false;
    //@Getter
    // private static boolean voxyTest = false;


    public static boolean isVoxy() {
        return voxy;
    }

    /**
     * Used for mod init detect.
     **/
    public static void init() {
        voxy = Platform.isModLoaded("voxy");
        roxyTest = Platform.isVersionSatisfied("voxy", "[0.2.16-beta]");
    }

    /**
     * Used for mod init event register.
     **/
    public static void register(IEventBus gameBus, IEventBus modBus) {
        if (isVoxy() && FMLLoader.getDist() == Dist.CLIENT) {
            try {
                Class<?> handler = Class.forName("com.teamtea.eclipticseasons_voxycompact.compat.voxy.client.VoxyEsHandler");
                gameBus.register(handler.getField("INSTANCE").get(null));
            } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }


    public static class CommonConfig {
        public static ModConfigSpec.BooleanValue voxyCompatibility;
        public static ModConfigSpec.BooleanValue voxyAutoRefresh;
        public static ModConfigSpec.BooleanValue voxyRefreshOnSolarTermChange;

        public static void load(ModConfigSpec.Builder builder) {
            builder.push("Compat");
            if (isVoxy()) {
                voxyCompatibility = builder
                        .worldRestart()
                        .comment("Enables compatibility with Voxy.")
                        .define("VoxyCompatibility", true);

                voxyAutoRefresh = builder
                        .comment("Automatically updates distant LODs when snow coverage changes.")
                        .define("VoxyAutoRefresh", true);

                voxyRefreshOnSolarTermChange = builder
                        .comment("Updates seasonal LODs when the solar term changes.")
                        .define("VoxyRefreshOnSolarTermChange", true);
            }
            builder.pop();
        }
    }

}

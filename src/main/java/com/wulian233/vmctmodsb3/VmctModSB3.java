package com.wulian233.vmctmodsb3;

import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Mod("vmctmodsb3")
public class VmctModSB3 {

    public VmctModSB3() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            MinecraftForge.EVENT_BUS.register(this);
        });
    }

    @SubscribeEvent
    public void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getPlayer();
        File configDir = new File(FMLPaths.CONFIGDIR.get().toFile(), "./");
        File vmctConfig = new File(configDir, "vmct.txt");

        String localVersion = readFile(vmctConfig);
        String onlineVersion = getOnlineVersion();

        if (!localVersion.equals(onlineVersion)) {
            player.sendMessage(new TextComponent("[VM 汉化组]：你好§6"+player.getDisplayName().getString()+"§f，你的汉化补丁已经过时！当前版本"+readFile(vmctConfig)+"，最新版为"+getOnlineVersion()+"。\n请前往§bhttps://vmct-cn.top/sb3 §f下载新版本。"), Util.NIL_UUID);
        }
    }

    private String readFile(File file) {
        if (!file.exists()) {
            return "";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private String getOnlineVersion() {
        try {
            URL url = new URL("https://vmct-cn.top/sb3/update.txt");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return reader.readLine();
            }
        } catch (IOException e) {
            return "";
        }
    }
}
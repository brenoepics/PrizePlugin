package com.brenoepic;

import com.brenoepic.command.UpdateLevelsCommand;
import com.brenoepic.level.LevelManager;
import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.CommandHandler;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;


import com.brenoepic.command.PrizeCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrizePlugin extends HabboPlugin implements EventListener {

    PrizePlugin INSTANCE;
     public static final Logger LOGGER = LoggerFactory.getLogger(PrizePlugin.class);
     public static LevelManager levels;

    public void onEnable(){
        Emulator.getPluginManager().registerEvents(this, this);
        INSTANCE = this;
        LOGGER.info("[prizeplugin 1.0] was successfully Loaded! Discord: BrenoEpic#9671");
    }

    public void onDisable(){

    }

    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }

    @EventHandler
    public void onEmulatorLoaded(EmulatorLoadedEvent event){
        levels = new LevelManager();
        // Load texts
        Emulator.getTexts().register("commands.description.cmd_prize", ":prize <username>");
        Emulator.getTexts().register("commands.description.cmd_update_prize", ":update_prize");
        Emulator.getTexts().register("commands.keys.cmd_update_prize", "update_prize");
        Emulator.getTexts().register("commands.keys.cmd_prize", "prize");
        Emulator.getTexts().register("commands.cmd_prize.none", "Missing the winning username!");
        Emulator.getTexts().register("commands.cmd_prize.not-found", "User not found or offline!!");
        Emulator.getTexts().register("commands.cmd_prize.not-self", "You can't reward yourself!");
        Emulator.getTexts().register("commands.cmd_prize.successfully", "You have successfully awarded user %USERNAME%!");
        Emulator.getTexts().register("commands.cmd_prize.update-successfully", "You have successfully updated rewards!");
        Emulator.getTexts().register("commands.cmd_prize.update-error", "Oops, Something went wrong!");
        Emulator.getTexts().register("prizeplugin.bubblealert-message", "The user %WINNER% won an event");
        Emulator.getTexts().register("prizeplugin.webhook-message", "The user %WINNER% won an event and reached level %LEVEL%");

        Emulator.getTexts().register("prizeplugin.webhook.title", "A user has just won an event!");

        // Load config
        Emulator.getConfig().register("prizeplugin.webhook.hotel-url", "https://habbo.com");
        Emulator.getConfig().register("prizeplugin.prize_badge-prefix", "LVL");
        Emulator.getConfig().register("prizeplugin.bubblealert-image", "${image.library.url}notifications/fig/%LOOK%.png");
        Emulator.getConfig().register("prizeplugin.webhook-thumbnail", "https://habbo.com/habbo-imaging/avatarimage?figure=%LOOK%&head_direction=4&headonly=1");
        Emulator.getConfig().register("prizeplugin.prize_level.type", "504");
        Emulator.getConfig().register("prizeplugin.kickonwin", "1");

        Emulator.getConfig().register("prizeplugin.logging_discord", "0");
        Emulator.getConfig().register("prizeplugin.logging.discord-webhook.url", "DISCORDWEBHOOKURL");


        // Commands
        CommandHandler.addCommand(new PrizeCommand("cmd_prize", Emulator.getTexts().getValue("commands.keys.cmd_prize").split(";")));
        CommandHandler.addCommand(new UpdateLevelsCommand("cmd_update_prize", Emulator.getTexts().getValue("commands.keys.cmd_update_prize").split(";")));

    }

    public static LevelManager getLevels(){
        return levels;
    }

        public static void main(String[] args) {
        System.out.println("Hello world!");
    }

}

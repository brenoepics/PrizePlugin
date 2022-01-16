package com.brenoepic.command;

import com.brenoepic.PrizePlugin;
import com.brenoepic.utils.Functions;
import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.generic.alerts.BubbleAlertComposer;


import static com.eu.habbo.Emulator.*;


public class UpdateLevelsCommand extends Command {

    public UpdateLevelsCommand(String permission, String[] keys)
    {
        super(permission, keys);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] params)
    {
            boolean loaded = PrizePlugin.getLevels().load();
            if(loaded)
            gameClient.getHabbo().whisper(Emulator.getTexts().getValue("commands.cmd_prize.update-successfully"));
            else gameClient.getHabbo().whisper(Emulator.getTexts().getValue("commands.cmd_prize.update-error"));
        return true;
    }
}
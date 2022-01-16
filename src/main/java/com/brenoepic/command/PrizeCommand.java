package com.brenoepic.command;

import com.brenoepic.PrizePlugin;
import com.brenoepic.utils.Functions;
import com.eu.habbo.habbohotel.commands.Command;
import com.eu.habbo.habbohotel.gameclients.GameClient;
import com.eu.habbo.habbohotel.rooms.Room;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.messages.outgoing.generic.alerts.BubbleAlertComposer;


import static com.eu.habbo.Emulator.*;


public class PrizeCommand extends Command {

    public PrizeCommand(String permission, String[] keys)
    {
        super(permission, keys);
    }

    @Override
    public boolean handle(GameClient gameClient, String[] params)
    {
        if (params.length < 2){
            gameClient.getHabbo().whisper(getTexts().getValue("commands.cmd_prize.none"));
            return false;
        }else{

            Habbo habbo = getGameEnvironment().getHabboManager().getHabbo(params[1]);
            if(habbo == null){
                gameClient.getHabbo().whisper(getTexts().getValue("commands.cmd_prize.not-found"));
                return false;
            }

                if (habbo == gameClient.getHabbo()) {
                    gameClient.getHabbo().whisper(getTexts().getValue("commands.cmd_prize.not-self"));
                    return false;
                }
            final int level = habbo.getHabboInfo().getCurrencyAmount(getConfig().getInt("prizeplugin.prize_level.type", 504));
                final int NewLevel = (level + 1);

                 boolean rewarded = PrizePlugin.getLevels().giveReward(habbo.getHabboInfo().getId(), NewLevel);

                    getGameServer().getGameClientManager().sendBroadcastResponse(new BubbleAlertComposer("prizeplugin", Functions.BubbleAlert(habbo)));

                    final Room room = habbo.getHabboInfo().getCurrentRoom();
                    if (room != null && getConfig().getBoolean("prizeplugin.kickonwin")) {
                        room.kickHabbo(habbo.getHabboInfo().getCurrentRoom().getHabbo(habbo.getHabboInfo().getUsername()), false);
                    }
            if(rewarded) {
                gameClient.getHabbo().whisper(getTexts().getValue("commands.cmd_prize.successfully").replace("%USERNAME%", habbo.getHabboInfo().getUsername()));
                Functions.DiscordEmbed(habbo, gameClient.getHabbo());
            }else{
                gameClient.getHabbo().whisper(getTexts().getValue("commands.cmd_prize.update-error"));
            }
        }
    return true;
    }
}
package com.brenoepic.utils;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.send.AllowedMentions;
import club.minnced.discord.webhook.send.WebhookEmbed;
import club.minnced.discord.webhook.send.WebhookEmbedBuilder;
import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import gnu.trove.map.hash.THashMap;

import static com.eu.habbo.Emulator.getConfig;
import static com.eu.habbo.Emulator.getTexts;


public class Functions {


    public static THashMap<String, String> BubbleAlert(Habbo winner) {

        THashMap<String, String> notification = new THashMap<>();
        notification.put("display", "BUBBLE");
        notification.put("image", getConfig().getValue("prizeplugin.bubblealert-image").replace("%LOOK%", winner.getHabboInfo().getLook()));
        notification.put("message", getTexts().getValue("prizeplugin.bubblealert-message").replace("%WINNER%", winner.getHabboInfo().getUsername()));
        return notification;
    }

    public static void DiscordEmbed(Habbo winner, Habbo staff) {
        if (getConfig().getBoolean("prizeplugin.logging_discord", true)) {
            WebhookClient client = WebhookClient.withUrl(Emulator.getConfig().getValue("prizeplugin.logging.discord-webhook.url"));
            WebhookMessageBuilder message = new WebhookMessageBuilder()
                    .setAllowedMentions(AllowedMentions.none());
            WebhookEmbed embed = new WebhookEmbedBuilder()
                    .setColor(0x03A9F4)
                    .setAuthor(new WebhookEmbed.EmbedAuthor(getConfig().getValue("hotel.name"), null, getConfig().getValue("prizeplugin.webhook.hotel-url")))
                    .setTitle(new WebhookEmbed.EmbedTitle(getTexts().getValue("prizeplugin.webhook.title"), null))
                    .setDescription(getTexts().getValue("prizeplugin.webhook-message").replace("%WINNER%", winner.getHabboInfo().getUsername()).replace("%LEVEL%", "" + winner.getHabboInfo().getCurrencyAmount(getConfig().getInt("prizeplugin.prize_level.type", 504))))
                    .setThumbnailUrl(getConfig().getValue("prizeplugin.webhook-thumbnail").replace("%LOOK%", winner.getHabboInfo().getLook()))
                    .setFooter(new WebhookEmbed.EmbedFooter(staff.getHabboInfo().getUsername(), null))
            .build();
            message.append("[PRIZE-PLUGIN]");
            message.addEmbeds(embed);
            client.send(message.build());
        }

    }
}
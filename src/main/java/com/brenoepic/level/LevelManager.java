package com.brenoepic.level;


import com.brenoepic.PrizePlugin;
import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.items.Item;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.habbohotel.users.HabboItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.eu.habbo.Emulator.getConfig;

public class LevelManager
{
    private final Map<Integer, Level> levels;

    public LevelManager() {
        this.levels = new HashMap<>();
        this.load();
    }

    public boolean load() {
        this.dispose();
        try (final Connection connection = Emulator.getDatabase().getDataSource().getConnection();
             final PreparedStatement statement = connection.prepareStatement("SELECT * FROM `prize_plugin`");
             final ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                final Level reward = new Level(set);
                this.levels.put(set.getInt("level"), reward);
            }
        }
        catch (SQLException e) {
            PrizePlugin.LOGGER.error("[prizeplugin]", e);
            return false;
        }
        return true;
    }

    public void dispose() {
        this.levels.clear();
    }

    public boolean Exists(final int level) {
        return this.levels.containsKey(level);
    }

    public Level getLevel(final int level) {
        return this.levels.get(level);
    }

    public boolean giveReward(final int userId, final int level) {
        final Habbo habbo = Emulator.getGameEnvironment().getHabboManager().getHabbo(userId);
        if (habbo == null) return false;
        Level reward;
        if (!this.Exists(level)) reward = this.getLevel(-1); else reward = this.getLevel(level);
            if(reward == null) return false;
            if (reward.getCredits() > 0) {
                habbo.giveCredits(reward.getCredits());
            }
            if (reward.getDiamonds() > 0) {
                habbo.givePoints(5, reward.getDiamonds());
            }
            if (reward.getPixels() > 0) {
                habbo.givePoints(0, reward.getPixels());
            }
            if (reward.getFurniture() > -1) {
                int furni_id = reward.getFurniture();
                final Item baseItem = Emulator.getGameEnvironment().getItemManager().getItem(furni_id);
                if (baseItem != null) {
                    final HabboItem item = Emulator.getGameEnvironment().getItemManager().createItem(habbo.getHabboInfo().getId(), baseItem, 0, 0, "");
                    habbo.addFurniture(item);
                }
            }
            if (reward.getPoints() > 0) {
                habbo.givePoints(reward.getPointsType(), reward.getPoints());
            }
        habbo.givePoints(getConfig().getInt("prizeplugin.prize_level.type", 504), 1);
        habbo.addBadge(getConfig().getValue("prizeplugin.prize_badge-prefix") + level);
        return true;
    }
}

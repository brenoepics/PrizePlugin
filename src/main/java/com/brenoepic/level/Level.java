package com.brenoepic.level;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Level
{
    private final int level;
    private final int credits;
    private final int diamonds;
    private final int pixels;
    private final int points;
    private final int points_type;
    private final int furni;
    public Level(final ResultSet result) throws SQLException {
        this.level = result.getInt("level");
        this.credits = result.getInt("credits");
        this.diamonds = result.getInt("diamonds");
        this.points = result.getInt("points");
        this.points_type = result.getInt("points_type");
        this.furni = result.getInt("item");
        this.pixels = result.getInt("pixels");
    }

    public int getLevel() {
        return this.level;
    }

    public int getCredits() {
        return this.credits;
    }

    public int getDiamonds() {
        return this.diamonds;
    }

    public int getPixels(){
        return this.pixels;
    }
    public int getPoints() {
        return this.points;
    }

    public int getPointsType() {
        return this.points_type;
    }

    public int getFurniture() {
        return this.furni;
    }
}

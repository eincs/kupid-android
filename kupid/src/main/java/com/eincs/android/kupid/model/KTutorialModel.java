package com.eincs.android.kupid.model;

public class KTutorialModel implements IModel {

    private int description;

    private int background;

    public KTutorialModel(int description, int background) {
        this.description = description;
        this.background = background;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }
}

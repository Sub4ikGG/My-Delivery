package ru.spoonbill.droid.autoslider.IndicatorView.animation.data.type;

import ru.spoonbill.droid.autoslider.IndicatorView.animation.data.Value;

public class SlideAnimationValue implements Value {

    private int coordinate;

    public int getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(int coordinate) {
        this.coordinate = coordinate;
    }
}

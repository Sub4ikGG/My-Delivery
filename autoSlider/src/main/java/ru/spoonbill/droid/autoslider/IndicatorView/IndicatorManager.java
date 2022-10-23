package ru.spoonbill.droid.autoslider.IndicatorView;

import androidx.annotation.Nullable;

import ru.spoonbill.droid.autoslider.IndicatorView.animation.AnimationManager;
import ru.spoonbill.droid.autoslider.IndicatorView.animation.controller.ValueController;
import ru.spoonbill.droid.autoslider.IndicatorView.animation.data.Value;
import ru.spoonbill.droid.autoslider.IndicatorView.draw.DrawManager;
import ru.spoonbill.droid.autoslider.IndicatorView.draw.data.Indicator;

public class IndicatorManager implements ValueController.UpdateListener {

    private DrawManager drawManager;
    private AnimationManager animationManager;
    private Listener listener;

    interface Listener {
        void onIndicatorUpdated();
    }

    IndicatorManager(@Nullable Listener listener) {
        this.listener = listener;
        this.drawManager = new DrawManager();
        this.animationManager = new AnimationManager(drawManager.indicator(), this);
    }

    public AnimationManager animate() {
        return animationManager;
    }

    public Indicator indicator() {
        return drawManager.indicator();
    }

    public DrawManager drawer() {
        return drawManager;
    }

    @Override
    public void onValueUpdated(@Nullable Value value) {
        drawManager.updateValue(value);
        if (listener != null) {
            listener.onIndicatorUpdated();
        }
    }
}

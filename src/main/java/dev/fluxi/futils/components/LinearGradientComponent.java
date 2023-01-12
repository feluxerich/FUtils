package dev.fluxi.futils.components;

import dev.fluxi.futils.colors.LinearGradient;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

public class LinearGradientComponent {
    private String text;
    private TextColor fromColor;
    private TextColor toColor;
    private Component gradientComponent;

    public LinearGradientComponent(String text, TextColor fromColor, TextColor toColor) {
        setText(text);
        setFromColor(fromColor);
        setToColor(toColor);

        setGradientComponent(createGradientComponent());
    }

    private Component createGradientComponent() {
        TextComponent.Builder component = Component.text();
        LinearGradient gradient = new LinearGradient(getFromColor(), getToColor(), getText().length());
        for (int i = 0; i < getText().length(); i++) {
            component.append(Component.text(getText().charAt(i)).color(gradient.getColorByPosition(i)));
        }
        return component.build();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextColor getFromColor() {
        return fromColor;
    }

    public void setFromColor(TextColor fromColor) {
        this.fromColor = fromColor;
    }

    public TextColor getToColor() {
        return toColor;
    }

    public void setToColor(TextColor toColor) {
        this.toColor = toColor;
    }

    public Component getGradientComponent() {
        return gradientComponent;
    }

    public void setGradientComponent(Component gradientComponent) {
        this.gradientComponent = gradientComponent;
    }
}

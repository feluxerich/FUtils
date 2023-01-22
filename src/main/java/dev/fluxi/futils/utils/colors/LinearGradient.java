package dev.fluxi.futils.utils.colors;

import net.kyori.adventure.text.format.TextColor;

import java.util.ArrayList;
import java.util.List;

public class LinearGradient {
    private final List<TextColor> gradient = new ArrayList<>();

    public LinearGradient(TextColor fromColor, TextColor toColor, int steps) {
        int redDifference = fromColor.red() - toColor.red();
        int greenDifference = fromColor.green() - toColor.green();
        int blueDifference = fromColor.blue() - toColor.blue();
        for (int i = 0; i < steps; i++) {
            int red = fromColor.red() - (redDifference / steps * i);
            int green = fromColor.green() - (greenDifference / steps * i);
            int blue = fromColor.blue() - (blueDifference / steps * i);

            gradient.add(i, TextColor.color(red, green, blue));
        }
    }

    public TextColor getColorByPosition(int position) {
        if (position >= gradient.size()) {
            position -= gradient.size();
        }
        return gradient.get(position);
    }
}

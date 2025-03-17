package org.yunxi.EveningLament.util;

import me.shedaniel.rei.impl.client.gui.text.TextTransformations;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.StringSplitter;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public final class ColorHelper {
    private ColorHelper() {}

    public static Component applyRainbow(Component component, int x, int y) {
        FormattedCharSequence baseSequence = component.getVisualOrderText();
        // 应用彩虹效果
        FormattedCharSequence rainbowSequence = TextTransformations.applyRainbow(baseSequence, x, y);

        // 利用 FormattedCharSequence 的 accept 方法构造新的 Component
        return getComponent(rainbowSequence);
    }

    public static Component applyWhiteToGold(Component component, int x, int y) {
        FormattedCharSequence baseSequence = component.getVisualOrderText();
        // 应用彩虹效果
        FormattedCharSequence rainbowSequence = applyGoldWhiteGradient(baseSequence, x, y);

        // 利用 FormattedCharSequence 的 accept 方法构造新的 Component

        return getComponent(rainbowSequence);
    }

    @NotNull
    private static Component getComponent(FormattedCharSequence rainbowSequence) {
        MutableComponent result = Component.literal("");
        rainbowSequence.accept((charIndex, style, codePoint) -> {
            // 将每个字符转换为字符串，并以该字符当前样式创建一个文本组件
            String s = new String(Character.toChars(codePoint));
            // 将该组件附加到结果中
            result.append(Component.literal(s).withStyle(s1 -> s1.withColor(style.getColor())));
            return true;
        });

        return result;
    }

    public static FormattedCharSequence applyGoldWhiteGradient(FormattedCharSequence sequence, int x, int y) {
        return (sink) -> {
            // Current system time in milliseconds - this will be different each frame
            final long currentTime = Util.getMillis();

            // Current position tracker for each character
            final int[] posX = {x};

            return sequence.accept((charIndex, style, codePoint) -> {
                // Reset position for first character
                if (charIndex == 0) {
                    posX[0] = x;
                }

                // Base gold color (RGB: 255, 215, 0)
                int goldColor = 0xFFD700;

                // Animation phase (0.0 to 1.0) based purely on time
                // 4000ms cycle time - slow animation
                // Using negative value to reverse direction (right to left)
                float animationPhase = -((currentTime % 1000L) / 1000.0F);

                // Character position offset based on its x position
                // Using a larger divisor (50.0F) to make the wave longer and move slower
                float waveOffset = (float) posX[0] / 50.0F;

                // The wave effect (value between 0.0 and 1.0)
                // This creates the periodic white gradient
                float waveValue = (float) Math.sin(Math.PI * 2 * (animationPhase + waveOffset)) * 0.5F + 0.5F;

                // Create the gradient blend factor (0.0 to 1.0)
                // Higher values mean more white in the blend
                float whiteFactor = waveValue * waveValue * 0.8F;

                // Blend gold with white based on the whiteFactor
                int r = (int) (((goldColor >> 16) & 0xFF) + (255 - ((goldColor >> 16) & 0xFF)) * whiteFactor);
                int g = (int) (((goldColor >> 8) & 0xFF) + (255 - ((goldColor >> 8) & 0xFF)) * whiteFactor);
                int b = (int) ((goldColor & 0xFF) + (255 - (goldColor & 0xFF)) * whiteFactor);

                // Create the final color
                int resultColor = (r << 16) | (g << 8) | b;

                // Update position for next character
                posX[0] += Minecraft.getInstance().font.width(String.valueOf((char)codePoint));

                // Return the character with modified color
                return sink.accept(charIndex, style.withColor(TextColor.fromRgb(resultColor)), codePoint);
            });
        };
    }





    private static int getRgb(int y, int[] combinedX) {
        float progress = (float)((Util.getMillis() - (long)(combinedX[0] * 10) - (long)(y * 10)) % 2000L) / 2000.0F;
        int red = (int)(255 * (1 - progress) + 255 * progress);
        int green = (int)(255 * (1 - progress) + 215 * progress);
        int blue = (int)(255 * (1 - progress) + 0 * progress);
        int rgb = (red << 16) | (green << 8) | blue; // Combine the RGB values to get the final color
        return rgb;
    }


}

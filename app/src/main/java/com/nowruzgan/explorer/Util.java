package com.nowruzgan.explorer;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Util {

    public static Drawable sizedDrawable(final Drawable drawable, int size) {
        int drawableSize = Math.max(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        float scale = (float) size / drawableSize;
        final int width = (int) (scale * drawable.getIntrinsicWidth());
        final int height = (int) (scale * drawable.getIntrinsicHeight());

        return new Drawable() {

            @Override
            public int getIntrinsicWidth() {
                return width;
            }

            @Override
            public int getIntrinsicHeight() {
                return height;
            }

            @Override
            protected void onBoundsChange(Rect bounds) {
                super.onBoundsChange(bounds);
                drawable.setBounds(bounds);
            }

            @Override
            public void draw(@NonNull Canvas canvas) {
                drawable.draw(canvas);
            }

            @Override
            public void setAlpha(int alpha) {
                drawable.setAlpha(alpha);
            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {
                drawable.setColorFilter(colorFilter);
            }

            @Override
            public int getOpacity() {
                return drawable.getOpacity();
            }

        };
    }

}

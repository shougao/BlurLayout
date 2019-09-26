package com.revosleap.blurrylayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class BlurryLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private ImageView imageView;
    private Drawable imageDrawable;
    Drawable DEFAULT_IMAGE;

    private static final int DEFAULT_BLUR_COLOR = -1;
    private static int BLUR_COLOR;
    private static final int DEFAULT_BLUR_RADIUS = 10;
    private static int BLUR_RADIUS = 10;
    private static final float DEFAULT_ALPHA = 0.3F;
    private static float ALPHA = 10.0F;

    public BlurryLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurryLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflateView = inflate(getContext(), R.layout.blurry_layout, this);
        linearLayout = inflateView.findViewById(R.id.linearLayout);
        imageView = inflateView.findViewById(R.id.imageView);
        DEFAULT_IMAGE = ContextCompat.getDrawable(getContext(), R.drawable.image);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.BlurryLayout, defStyleAttr, 0);
        BLUR_COLOR = typedArray.getColor(R.styleable.BlurryLayout_blurColor, DEFAULT_BLUR_COLOR);
        ALPHA = typedArray.getFloat(R.styleable.BlurryLayout_blurOpacity, DEFAULT_ALPHA);
        BLUR_RADIUS = typedArray.getInt(R.styleable.BlurryLayout_blurRadius, DEFAULT_BLUR_RADIUS);
        imageDrawable = typedArray.getDrawable(R.styleable.BlurryLayout_blurImage);
        typedArray.recycle();
        setImageBg();
    }

    private void setImageBg() {
        Drawable bg;
        if (imageDrawable != null) {
            //To get a good effect from user image
            setDrawableBlur(imageDrawable);
        } else {
            bg = DEFAULT_IMAGE;
            Bitmap image = ((BitmapDrawable) bg).getBitmap();
            try {
                imageView.setImageBitmap(GaussianBlur.blurred(getContext(), image, BLUR_RADIUS));
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        linearLayout.setBackgroundColor(BLUR_COLOR);
        linearLayout.setAlpha(ALPHA);
    }


    public void blurColor(int blurColor) {
        linearLayout.setBackgroundColor(blurColor);
    }

    public void blurOpacity(float bluropacity) {
        linearLayout.setAlpha(bluropacity);
    }

    public void setDrawableBlur(Drawable imageDrawable, int radius, int blurPercentage) {
        Bitmap image = ((BitmapDrawable) imageDrawable).getBitmap();
        int height = image.getHeight();
        int width = image.getWidth();
        Bitmap background = ThumbnailUtils.extractThumbnail(image, width * blurPercentage / 100, height * blurPercentage / 100);
        imageView.setImageBitmap(GaussianBlur.blurred(getContext(), background, radius));
    }

    public final void setDrawableBlur(Drawable imageDrawable, int radius) {
        this.setDrawableBlur(imageDrawable, radius, 10);
    }

    public final void setDrawableBlur(Drawable imageDrawable) {
        this.setDrawableBlur(imageDrawable, 10, 10);
    }


    public void setBitmapBlur(Bitmap bitmapBlur, int radius, int blurPercentage) {
        int height = bitmapBlur.getHeight();
        int width = bitmapBlur.getWidth();
        Bitmap bg = ThumbnailUtils.extractThumbnail(bitmapBlur, width * blurPercentage / 100, height * blurPercentage / 100);
        imageView.setImageBitmap(GaussianBlur.blurred(getContext(), bg, radius));
    }

    public final void setBitmapBlur(Bitmap bitmapBlur, int radius) {
        this.setBitmapBlur(bitmapBlur, radius, 10);
    }

    public final void setBitmapBlur(Bitmap bitmapBlur) {
        this.setBitmapBlur(bitmapBlur, 10, 10);
    }

    public class compaion {
        private int DEFAULT_BLUR_COLOR = Color.WHITE;
        private int BLUR_COLOR = 0;
        private int DEFAULT_BLUR_RADIUS = 10;
        private int BLUR_RADIUS = 10;
        private double DEFAULT_ALPHA = 0.3f;
        private float ALPHA = 10F;
    }
}

package com.ljj.commonlib.kit.androidspan;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.LocaleSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by james on 13/10/15.
 */
public class SpanOptions {
    private List<Object> listSpan;

    public SpanOptions() {
        listSpan = new ArrayList<Object>();
    }


    /**
     * 下划线
     *
     * @return
     */
    public SpanOptions addUnderlineSpan() {
        UnderlineSpan span = new UnderlineSpan();
        listSpan.add(span);
        return this;
    }

    public SpanOptions addBulletSpan(int gapWidth, int color) {
        BulletSpan span = new BulletSpan(gapWidth, color);
        listSpan.add(span);
        return this;
    }

    /**
     * URL效果
     * 需要实现textView.setMovementMethod(LinkMovementMethod.getInstance());
     *
     * @param url 格式为：电话：tel:18721850636，邮箱：mailto:1119117546@qq.com，网站：http://www.baidu.com,短信：mms:4155551212，彩信：mmsto:18721850636,地图：geo:38.899533,-77.036476
     * @return
     */
    public SpanOptions addURLSpan(String url) {
        URLSpan Urlspan = new URLSpan(url);
        listSpan.add(Urlspan);
        return this;
    }

    public SpanOptions addQuoteSpan(int color) {
        QuoteSpan span = new QuoteSpan(color);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addAlignmentSpan(Layout.Alignment align) {
        AlignmentSpan.Standard span = new AlignmentSpan.Standard(align);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addStrikethroughSpan() {
        StrikethroughSpan span = new StrikethroughSpan();
        listSpan.add(span);
        return this;
    }

    public SpanOptions addBackgroundColorSpan(int color) {
        BackgroundColorSpan span = new BackgroundColorSpan(color);
        listSpan.add(span);
        return this;
    }

    /**
     * @param density
     * @param style   BlurMaskFilter.Blur.NORMAL
     * @return
     */
    public SpanOptions addMaskFilterSpan(float density, BlurMaskFilter.Blur style) {
        MaskFilterSpan span = new MaskFilterSpan(new BlurMaskFilter(density, style));
        listSpan.add(span);
        return this;
    }

    public SpanOptions addSubscriptSpan() {
        SubscriptSpan span = new SubscriptSpan();
        listSpan.add(span);
        return this;
    }

    public SpanOptions addSuperscriptSpan() {
        SuperscriptSpan span = new SuperscriptSpan();
        listSpan.add(span);
        return this;
    }

    /**
     * @param style Typeface.BOLD | Typeface.ITALIC
     * @return
     */
    public SpanOptions addStyleSpan(int style) {
        StyleSpan span = new StyleSpan(style);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addAbsoluteSizeSpan(int size, boolean dip) {
        AbsoluteSizeSpan span = new AbsoluteSizeSpan(size, dip);
        listSpan.add(span);
        return this;
    }

    /**
     * 同比放大索小
     *
     * @return
     */
    public SpanOptions addRelativeSizeSpan(float proportion) {
        RelativeSizeSpan span = new RelativeSizeSpan(proportion);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addTextAppearanceSpan(Context context, int appearance) {
        TextAppearanceSpan span = new TextAppearanceSpan(context, appearance);
        listSpan.add(span);
        return this;
    }

    /**
     * @param locale Locale.CHINESE
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public SpanOptions addLocaleSpan(Locale locale) {
        LocaleSpan span = new LocaleSpan(locale);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addScaleXSpan(float proportion) {
        ScaleXSpan span = new ScaleXSpan(proportion);
        listSpan.add(span);
        return this;
    }

    /**
     * @param typeface serif
     * @return
     */
    public SpanOptions addTypefaceSpan(String typeface) {
        TypefaceSpan span = new TypefaceSpan(typeface);
        listSpan.add(span);
        return this;
    }

    public SpanOptions addImageSpan(Context context, int imgId) {
        ImageSpan span = new ImageSpan(context, imgId);
        listSpan.add(span);
        return this;
    }


    /**
     * 文本颜色
     *
     * @return
     */
    public SpanOptions addForegroundColor(int color) {
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        listSpan.add(span);
        return this;
    }


    /**
     * 自定义Span
     *
     * @param object
     * @return
     */

    public SpanOptions addSpan(Object object) {
        listSpan.add(object);
        return this;
    }

    public List<Object> getListSpan() {
        return listSpan;
    }




    public SpanOptions addClickSpan(View.OnClickListener mListener) {
        listSpan.add(new Clickable(mListener));
        return this;
    }
    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener mListener) {
            this.mListener = mListener;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ds.linkColor);
            ds.setUnderlineText(false);    //去除超链接的下划线
        }
    }
}

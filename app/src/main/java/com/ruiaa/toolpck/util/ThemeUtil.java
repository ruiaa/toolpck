package com.ruiaa.toolpck.util;

import android.app.Activity;
import android.content.res.TypedArray;

import com.ruiaa.toolpck.R;

/**
 * Created by ruiaa on 2016/12/12.
 */

public class ThemeUtil {

    private static final String THEME_COLOR = "theme_color";
    private static final String THEME_NIGHT = "theme_night";

    private static int colorPrimary=-1;
    private static int colorPrimaryDark=-1;
    private static int colorAccent=-1;

    public static int getColorAccent() {
        return colorAccent==-1?ResUtil.getColor(R.color.colorAccent):colorAccent;
    }

    public static int getColorPrimary() {
        return colorPrimary==-1?ResUtil.getColor(R.color.colorPrimary):colorPrimary;
    }

    public static int getColorPrimaryDark() {
        return colorPrimaryDark==-1?ResUtil.getColor(R.color.colorPrimaryDark):colorPrimaryDark;
    }

    public static int setCurrentTheme(Activity activity) {
        int themId=getCurrentThemeRes();
        activity.setTheme(themId);
        TypedArray typedArray = activity.obtainStyledAttributes(new int[]{
                R.attr.colorPrimary,
                R.attr.colorPrimaryDark,
                R.attr.colorAccent});
        colorPrimary = typedArray.getColor(0, 0xffffff);
        colorPrimaryDark=typedArray.getColor(1,0xffffff);
        colorAccent=typedArray.getColor(2,0xffffff);
        typedArray.recycle();
        return themId;
    }

    public static int getCurrentThemeRes(){
        return themeEnum2ThemeRes(Theme.valueOf(KeyStorage.get(THEME_COLOR, Theme.Red.name())));
    }

    public static void saveNightModeSwitch(boolean night) {
        KeyStorage.put(THEME_NIGHT, night);
    }

    public static boolean getNightModeSwitch() {
        return KeyStorage.get(THEME_NIGHT, false);
    }

    public static void saveThemeSelected(int colorPrimary) {
        KeyStorage.put(THEME_COLOR, color2ThemeEnum(colorPrimary).name());
    }

    public static int colorPrimary2ThemeRes(int colorPrimary) {
        return themeEnum2ThemeRes(color2ThemeEnum(colorPrimary));
    }

    private static int themeEnum2ThemeRes(Theme theme) {
        boolean n = getNightModeSwitch();
        switch (theme) {
            case Blue:
                return n ? R.style.BlueTheme_Night : R.style.BlueTheme;
            case Red:
                return n ? R.style.RedTheme_Night : R.style.RedTheme;
            case Brown:
                return n ? R.style.BrownTheme_Night : R.style.BrownTheme;
            case Green:
                return n ? R.style.GreenTheme_Night : R.style.GreenTheme;
            case Purple:
                return n ? R.style.PurpleTheme_Night : R.style.PurpleTheme;
            case Teal:
                return n ? R.style.TealTheme_Night : R.style.TealTheme;
            case Pink:
                return n ? R.style.PinkTheme_Night : R.style.PinkTheme;
            case DeepPurple:
                return n ? R.style.DeepPurpleTheme_Night : R.style.DeepPurpleTheme;
            case Orange:
                return n ? R.style.OrangeTheme_Night : R.style.OrangeTheme;
            case Indigo:
                return n ? R.style.IndigoTheme_Night : R.style.IndigoTheme;
            case LightGreen:
                return n ? R.style.LightGreenTheme_Night : R.style.LightGreenTheme;
            case Lime:
                return n ? R.style.LimeTheme_Night : R.style.LimeTheme;
            case DeepOrange:
                return n ? R.style.DeepOrangeTheme_Night : R.style.DeepOrangeTheme;
            case Cyan:
                return n ? R.style.CyanTheme_Night : R.style.CyanTheme;
            case BlueGrey:
                return n ? R.style.BlueGreyTheme_Night : R.style.BlueGreyTheme;
        }
        return n ? R.style.PinkTheme_Night : R.style.PinkTheme;
    }

    private static Theme color2ThemeEnum(int colorPrimary) {
        if (colorPrimary == ResUtil.getColor(R.color.colorBluePrimary)) return Theme.Blue;
        if (colorPrimary == ResUtil.getColor(R.color.colorRedPrimary)) return Theme.Red;
        if (colorPrimary == ResUtil.getColor(R.color.colorBrownPrimary)) return Theme.Brown;
        if (colorPrimary == ResUtil.getColor(R.color.colorPurplePrimary)) return Theme.Purple;
        if (colorPrimary == ResUtil.getColor(R.color.colorTealPrimary)) return Theme.Teal;
        if (colorPrimary == ResUtil.getColor(R.color.colorGreenPrimary)) return Theme.Green;
        if (colorPrimary == ResUtil.getColor(R.color.colorPinkPrimary)) return Theme.Pink;
        if (colorPrimary == ResUtil.getColor(R.color.colorOrangePrimary)) return Theme.Orange;
        if (colorPrimary == ResUtil.getColor(R.color.colorDeepOrangePrimary))
            return Theme.DeepOrange;
        if (colorPrimary == ResUtil.getColor(R.color.colorIndigoPrimary)) return Theme.Indigo;
        if (colorPrimary == ResUtil.getColor(R.color.colorCyanPrimary)) return Theme.Cyan;
        if (colorPrimary == ResUtil.getColor(R.color.colorLightGreenPrimary))
            return Theme.LightGreen;
        if (colorPrimary == ResUtil.getColor(R.color.colorLimePrimary)) return Theme.Lime;
        if (colorPrimary == ResUtil.getColor(R.color.colorBlueGreyPrimary)) return Theme.BlueGrey;
        return Theme.Pink;
    }

    private enum Theme {
        Blue,
        BlueGrey,
        Red,
        Brown,
        Purple,
        DeepPurple,
        Teal,
        Green,
        Pink,
        Orange,
        DeepOrange,
        Indigo,
        Cyan,
        LightGreen,
        Lime
    }
}

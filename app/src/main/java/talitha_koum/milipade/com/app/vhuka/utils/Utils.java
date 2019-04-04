/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package talitha_koum.milipade.com.app.vhuka.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {



    //Email Validation pattern
    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

    public static final String regExCell = "\\+263+\\s[7][13478]{3}+\\s[0-9]{3}+\\s[0-9]{3}$";

    //Fragments Tags
    public static final String Login_fragment = "Login_fragment";
    public static final String Signup_fragment = "Signup_fragment";
    public static final String ForgotPassword_fragment = "ForgotPassword_fragment";
    private static Map<String, Typeface> TYPEFACE = new HashMap<String, Typeface>();

    public static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url.toLowerCase());
        return m.matches();
    }

    public static Typeface getFonts(Context context, String fontName) {
        Typeface typeface = TYPEFACE.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/"
                    + fontName);
            TYPEFACE.put(fontName, typeface);
        }
        return typeface;
    }



}

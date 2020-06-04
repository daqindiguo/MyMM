package com.scott.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.widget.Toast;

public class StringUtils {
    public static void showShortToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }
    public static void showLongToast(Context context, String msg){
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }
    public static boolean isMobileNO(String mobile) {
//        /**
//         * 判断字符串是否符合手机号码格式
//         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
//         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
//         * 电信号段: 133,149,153,170,173,177,180,181,189
//         * @param str
//         * @return 待检测的字符串
//         */
//        // "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
////        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(19[8,9])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
//        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
//        if (TextUtils.isEmpty(mobile))
//            return false;
//        else
//            return mobile.matches(regex);
        return mobile.length() == 11 &&  !mobile.matches("^1\\\\d{10}$");
    }
    /**
     * 必须同时包含大小写字母及数字
     * 是否包含
     * @param str
     * @return
     */
    public static boolean isContainAll(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        return isDigit && isLowerCase && isUpperCase && str.matches(regex);
    }

    /**
     * 获取手机名称
     */
    public static String mobileName(){
        return  android.os.Build.BRAND+"-"+android.os.Build.BRAND;
    }
    /**
     * 包含大小写字母及数字且在6-12位
     * 是否包含
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{6,12}$";
        return isDigit && isLetter && str.matches(regex);
    }
    public static boolean cardCodeVerifySimple(String cardcode) {
        //第一代身份证正则表达式(15位)
        String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        String isIDCard2 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

        //验证身份证
        if (cardcode.matches(isIDCard1) || cardcode.matches(isIDCard2)) {
            return true;
        }
        return false;
    }
    /**
     * 将银行卡中间八个字符隐藏为*
     */
    public static String getHideBankCardNum(String bankCardNum) {
        try {
            if (bankCardNum == null) return "未绑定";

            int length = bankCardNum.length();

            if (length > 4) {
                String startNum = bankCardNum.substring(0, 4);
                String endNum = bankCardNum.substring(length - 4, length);
                bankCardNum = startNum + " **** **** " + endNum;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bankCardNum;
    }
    public static SpannableString spanText(Context context, String text){
        return spanText(context, text, 12);
    }
    public static SpannableString spanText(Context context, String text, float size){
        if (text.length()==0){
            return new SpannableString("");
        }
        SpannableString span= new SpannableString(text);
        span.setSpan(new AbsoluteSizeSpan(sp2px(context, size)),0,1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return span;
    }

    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }


}

package com.ruiaa.toolpck.tool.calculation.calculator;

import com.ruiaa.toolpck.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ruiaa on 2016/12/16.
 */

public class CalculateUtil {

    public static final ArrayList<String> NUMBER_LIST = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    public static final ArrayList<String> OPERATOR_LIST = new ArrayList<>(Arrays.asList("+", "-", "*", "/", "(", ")"));
    public static final ArrayList<String> OPERATOR_ADD_SUBTRACT_MULTIPLY_DIVIDE = new ArrayList<>(Arrays.asList("+", "-", "*", "/"));

    /*
     *  ( )
     *  + -
     *  * /
     *
     *  测试
     *  (1+2-5)+(1*3/6-6/5*7)+(1*-3/+5-(9/+-5/+3)-+2*-7)+(-1*(1*8/9-10)+1+(2-(3+9)*9)*9/(1+1))
     *  double i=(1d+2d-5d)+(1d*3d/6d-6d/5d*7d)+(1d*-3d/+5d-(9d/+-5d/+3d)-+2d*-7d)+(-1d*(1d*8d/9d-10d)+1d+(2d-(3d+9d)*9d)*9d/(1d+1d));
     *
     *  (1+2-5)                                 //加减
     *  +(1*3/6-6/5*7)                          //乘除
     *  +(1*-3/+5-(9/+-5/+3)-+2*-7)             //正负号
     *  +(-1*(1*8/9-10)+1+(2-(3+9)*9)*9/(1+1))  //混合
     */
    public static double calculate(String s) throws SyntaxErrorException {
        LogUtil.v("calculate--计算" + s);
        try {
            return checkBracketAndCal(s);
        } catch (NumberFormatException e) {
            LogUtil.e("calculate--", e);
            throw new SyntaxErrorException("数字解析出错");
        } catch (ArithmeticException e){
            LogUtil.e("calculate####",e);
            throw new SyntaxErrorException("除数为零");
        }
    }

    public static boolean isNumber(String s) {
        return s != null && NUMBER_LIST.contains(s);
    }

    public static boolean isNumber(char c) {
        return NUMBER_LIST.contains(String.valueOf(c));
    }

    public static boolean isPoint(String s) {
        return s != null && ".".equals(s);
    }

    public static boolean isPoint(char c) {
        return '.' == c;
    }

    public static boolean isOperator(String s) {
        return s != null && OPERATOR_LIST.contains(s);
    }

    public static boolean isOperator(char c) {
        return OPERATOR_LIST.contains(String.valueOf(c));
    }

    public static boolean isOperator_add_subtract_multiply_divide(String s) {
        return s != null && OPERATOR_ADD_SUBTRACT_MULTIPLY_DIVIDE.contains(s);
    }

    public static boolean isOperator_add_subtract_multiply_divide(char c) {
        return isOperator_add_subtract_multiply_divide(String.valueOf(c));
    }

    public static int getBracketLeftCount(String s){
        if (s == null || s.isEmpty()) return 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left = left + 1;
            }
        }
        return left;
    }
    public static int getBracketRightCount(String s){
        if (s == null || s.isEmpty()) return 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                right = right + 1;
            }
        }
        return right;
    }

    private static boolean OpenLog = false;

    private static double checkBracketAndCal(String s) throws SyntaxErrorException {
        if (s == null || s.isEmpty()) return 0;
        if (isOperator_add_subtract_multiply_divide(s.charAt(s.length() - 1))) {
            checkBracketAndCal(s.substring(0, s.length() - 1));
        }

        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left = left + 1;
            } else if (s.charAt(i) == ')') {
                right = right + 1;
            }
        }
        if (left > right) {
            StringBuilder builder = new StringBuilder(s);
            for (int i = 0; i < left-right; i++) {
                builder.append(")");
            }
            return calBracket(builder.toString());
        } else if (left < right) {
            throw new SyntaxErrorException("右括号比左括号多");
        } else {
            return calBracket(s);
        }
    }

    //括号
    private static double calBracket(String s) throws SyntaxErrorException {
        if (s == null || s.isEmpty()) return 0;
        if (OpenLog) {
            LogUtil.v("****calBracket####" + s);
        }

        int pLeft = 0;
        int pRight = 0;
        pRight = s.indexOf(")");
        if (pRight == -1) {
            return calAddAndSubtract(s);
        } else {
            pLeft = s.substring(0, pRight + 1).lastIndexOf("(");
            if (pLeft == -1) {
                throw new SyntaxErrorException("括号次序");
            } else {
                return calBracket(s.substring(0, pLeft) + calAddAndSubtract(s.substring(pLeft + 1, pRight)) + s.substring(pRight + 1));
            }
        }
    }

    //正负
    private static double calAddAndSubtract(String s) throws SyntaxErrorException {
        if (s == null || s.isEmpty()) return 0;
        if (OpenLog) {
            LogUtil.v("****calAddAndSubtract####" + s);
        }

        if (s.startsWith("+") || s.startsWith("-")) s = "0" + s;
        if (s.endsWith("+") || s.endsWith("-")) s = s + "0";

        StringBuilder builder = new StringBuilder();
        boolean isAdd = true;
        boolean requestSymbol = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                requestSymbol = true;
            } else if (s.charAt(i) == '-') {
                isAdd = !isAdd;
                requestSymbol = true;
            } else {
                if (requestSymbol) {
                    char c = builder.charAt(builder.length() - 1);
                    if (NUMBER_LIST.contains(String.valueOf(c))) {
                        builder.append(isAdd ? "+" : "+-");
                    } else if (!isAdd) {
                        builder.append("-");
                    }

                    requestSymbol = false;
                    isAdd = true;
                }
                builder.append(s.charAt(i));
            }
        }

        return calAdd(builder.toString());
    }

    //加法
    private static double calAdd(String s) throws SyntaxErrorException {
        if (s == null || s.isEmpty()) return 0;
        if (OpenLog) {
            LogUtil.v("****calAdd####" + s);
        }


        int pAdd = s.lastIndexOf("+");

        if (pAdd == -1) {
            return calMultiply(s);
        } else {
            return calAdd(s.substring(0, pAdd)) + calMultiply(s.substring(pAdd + 1));
        }
    }

    //乘 除
    private static double calMultiply(String s) throws SyntaxErrorException {
        if (s == null || s.isEmpty()) return 0;
        if (OpenLog) {
            LogUtil.v("****calMultiply####" + s);
        }

        if (s.startsWith("*") || s.startsWith("/")) throw new SyntaxErrorException("以*或/开头或重复");
        if (s.endsWith("*") || s.endsWith("/")) throw new SyntaxErrorException("以*或/结尾或重复");

        int pMultiply = s.lastIndexOf("*");
        int pDivision = s.lastIndexOf("/");
        if (pMultiply == -1 && pDivision == -1) {
            return Double.parseDouble(s);
        } else {
            if ((pMultiply != -1 && pMultiply > pDivision) || pDivision == -1) {
                return calMultiply(s.substring(0, pMultiply)) * Double.parseDouble(s.substring(pMultiply + 1));
            } else {
                return calMultiply(s.substring(0, pDivision)) / Double.parseDouble(s.substring(pDivision + 1));
            }
        }
    }

    //语法错误
    public static class SyntaxErrorException extends Exception {
        public SyntaxErrorException() {
            super("syntax error");
        }

        public SyntaxErrorException(String msg) {
            super(msg);
        }
    }


    private static class Calculator {
        public static int cal(String text) {
            // 规则，只能出现数字和加减乘除符号，最前和最后都是数字，即字符串能有效计算的
//            String text = "30*1+4*2*10-10+40/20";

            // 计算内容分割
            List<String> numList = new ArrayList<String>();
            int splitIndex = 0;
            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                if (c == '+' || c == '-' || c == '*' || c == '/') {
                    numList.add(text.substring(splitIndex, i));
                    numList.add(c + "");
                    splitIndex = i + 1;
                }
            }
            // 因为使用符号做判断，增加前一位和符号，所以最后一位数字不会在循环里处理
            numList.add(text.substring(splitIndex, text.length()));

            //System.out.println("====分割后====");
            for (int i = 0; i < numList.size(); i++) {
                System.out.println(i + " -> " + numList.get(i));
            }

            // 先做乘除计算
            List<String> list = new ArrayList<String>();
            Integer temp = null; // 用于做乘除计算临时变量
            for (int i = 1; i < numList.size(); i += 2) { // 这里只循环运算符号
                if ("+".equals(numList.get(i)) || "-".equals(numList.get(i))) {
                    if (null != temp) { // 存在临时变量，说明前面进行过乘除计算
                        list.add(temp.toString());
                        temp = null;
                    } else {
                        list.add(numList.get(i - 1));
                    }
                    list.add(numList.get(i)); // 把符号加进去
                    if (i == numList.size() - 2) { // 处理到最后时遇到直接处理

                        list.add(numList.get(i + 1));


                    }
                } else if ("*".equals(numList.get(i))) {
                    if (null == temp) {
                        temp = Integer.parseInt(numList.get(i - 1)) * Integer.parseInt(numList.get(i + 1));
                    } else {
                        temp = temp * Integer.parseInt(numList.get(i + 1));
                    }
                    if (i == numList.size() - 2) { // 处理到最后时遇到直接处理
                        list.add(temp.toString());
                        temp = null;
                    }
                } else if ("/".equals(numList.get(i))) {
                    if (null == temp) {
                        temp = Integer.parseInt(numList.get(i - 1)) / Integer.parseInt(numList.get(i + 1));
                    } else {
                        temp = temp / Integer.parseInt(numList.get(i + 1));
                    }
                    if (i == numList.size() - 2) { // 处理到最后时遇到直接处理
                        list.add(temp.toString());
                        temp = null;
                    }
                }
            }
            //System.out.println("====乘除后====");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + " -> " + list.get(i));
            }

            // 再做加减计算
            Integer sum = Integer.parseInt(list.get(0)); // 第一位不会在循环里处理
            for (int i = 1; i < list.size(); i += 2) { // 这里只循环运算符号
                if ("+".equals(list.get(i))) {
                    sum += Integer.parseInt(list.get(i + 1));
                } else if ("-".equals(list.get(i))) {
                    sum -= Integer.parseInt(list.get(i + 1));
                }
            }

            //System.out.println("====最终值====");
            // 打印结果
            //System.out.println(sum);
            return sum;
        }
    }
}

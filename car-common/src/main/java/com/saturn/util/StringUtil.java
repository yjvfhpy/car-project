package com.saturn.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 此类中封装一些常用的字符串操作。 所有方法都是静态方法，不需要生成此类的实例， 为避免生成此类的实例，构造方法被申明为private类型的。
 * 
 * @since 0.1
 */
public class StringUtil
{
    /**
     * 私有构造方法，防止类的实例化，因为工具类不需要实例化。
     */
    private StringUtil() {
    }
    /**
     * 判断字符串是否为空
     * 
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        return text == null || "".equals(text.trim());
    }

    /**
     * 判断字符串是否不为空
     * 
     * @param text
     * @return
     */
    public static boolean isNotEmpty(String text) {
        if (text == null || "".equals(text.trim())) {
            return false;
        }

        return true;
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组。
     * 
     * @param source
     *            需要进行划分的原字符串
     * @param delim
     *            单词的分隔字符串
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组，
     *         如果delim为null则使用逗号作为分隔字符串。
     * @since 0.1
     */
    public static String[] split(String source, String delim) {
        String[] wordLists;
        if (source == null) {
            wordLists = new String[1];
            wordLists[0] = source;
            return wordLists;
        }
        if (delim == null) {
            delim = ",";
        }
        StringTokenizer st = new StringTokenizer(source, delim);
        int total = st.countTokens();
        wordLists = new String[total];
        for (int i = 0; i < total; i++) {
            wordLists[i] = st.nextToken();
        }
        return wordLists;
    }

    /**
     * 此方法将给出的字符串source使用delim划分为单词数组。
     * 
     * @param source
     *            需要进行划分的原字符串
     * @param delim
     *            单词的分隔字符
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     * @since 0.2
     */
    public static String[] split(String source, char delim) {
        return split(source, String.valueOf(delim));
    }

    /**
     * 此方法将给出的字符串source使用逗号划分为单词数组。
     * 
     * @param source
     *            需要进行划分的原字符串
     * @return 划分以后的数组，如果source为null的时候返回以source为唯一元素的数组。
     * @since 0.1
     */
    public static String[] split(String source) {
        return split(source, ",");
    }

    /**
     * 循环打印字符串数组。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
     * 
     * @param strings
     *            字符串数组
     * @param delim
     *            分隔符
     * @param out
     *            打印到的输出流
     * @since 0.4
     */
    public static void printStrings(String[] strings, String delim, OutputStream out) {
        try {
            if (strings != null) {
                int length = strings.length - 1;
                for (int i = 0; i < length; i++) {
                    if (strings[i] != null) {
                        if (strings[i].indexOf(delim) > -1) {
                            out.write(("\"" + strings[i] + "\"" + delim).getBytes());
                        } else {
                            out.write((strings[i] + delim).getBytes());
                        }
                    } else {
                        out.write("null".getBytes());
                    }
                }
                if (strings[length] != null) {
                    if (strings[length].indexOf(delim) > -1) {
                        out.write(("\"" + strings[length] + "\"").getBytes());
                    } else {
                        out.write(strings[length].getBytes());
                    }
                } else {
                    out.write("null".getBytes());
                }
            } else {
                out.write("null".getBytes());
            }
            out.write(",".getBytes());
        } catch (IOException e) {

        }
    }

    /**
     * 循环打印字符串数组到标准输出。 字符串数组的各元素间以指定字符分隔，如果字符串中已经包含指定字符则在字符串的两端加上双引号。
     * 
     * @param strings
     *            字符串数组
     * @param delim
     *            分隔符
     * @since 0.4
     */
    public static void printStrings(String[] strings, String delim) {
        printStrings(strings, delim, System.out);
    }

    /**
     * 循环打印字符串数组。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
     * 
     * @param strings
     *            字符串数组
     * @param out
     *            打印到的输出流
     * @since 0.2
     */
    public static void printStrings(String[] strings, OutputStream out) {
        printStrings(strings, ",", out);
    }

    /**
     * 循环打印字符串数组到系统标准输出流System.out。 字符串数组的各元素间以逗号分隔，如果字符串中已经包含逗号则在字符串的两端加上双引号。
     * 
     * @param strings
     *            字符串数组
     * @since 0.2
     */
    public static void printStrings(String[] strings) {
        printStrings(strings, ",", System.out);
    }

    /**
     * 将字符串中的变量使用values数组中的内容进行替换。 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
     * 
     * @param prefix
     *            变量前缀字符串
     * @param source
     *            带参数的原字符串
     * @param values
     *            替换用的字符串数组
     * @return 替换后的字符串。 如果前缀为null则使用“%”作为前缀；
     *         如果source或者values为null或者values的长度为0则返回source；
     *         如果values的长度大于参数的个数，多余的值将被忽略；
     *         如果values的长度小于参数的个数，则后面的所有参数都使用最后一个值进行替换。
     * @since 0.2
     */
    public static String getReplaceString(String prefix, String source, String[] values) {
        String result = source;
        if (source == null || values == null || values.length < 1) {
            return source;
        }
        if (prefix == null) {
            prefix = "%";
        }

        for (int i = 0; i < values.length; i++) {
            String argument = prefix + Integer.toString(i + 1);
            int index = result.indexOf(argument);
            if (index != -1) {
                String temp = result.substring(0, index);
                if (i < values.length) {
                    temp += values[i];
                } else {
                    temp += values[values.length - 1];
                }
                temp += result.substring(index + 2);
                result = temp;
            }
        }
        return result;
    }

    /**
     * 将字符串中的变量（以“%”为前导后接数字）使用values数组中的内容进行替换。
     * 替换的过程是不进行嵌套的，即如果替换的内容中包含变量表达式时不会替换。
     * 
     * @param source
     *            带参数的原字符串
     * @param values
     *            替换用的字符串数组
     * @return 替换后的字符串
     * @since 0.1
     */
    public static String getReplaceString(String source, String[] values) {
        return getReplaceString("%", source, values);
    }

    /**
     * 字符串数组中是否包含指定的字符串。
     * 
     * @param strings
     *            字符串数组
     * @param string
     *            字符串
     * @param caseSensitive
     *            是否大小写敏感
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean contains(String[] strings, String string, boolean caseSensitive) {
        for (int i = 0; i < strings.length; i++) {
            if (caseSensitive == true) {
                if (strings[i].equals(string)) {
                    return true;
                }
            } else {
                if (strings[i].equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 字符串数组中是否包含指定的字符串。大小写敏感。
     * 
     * @param strings
     *            字符串数组
     * @param string
     *            字符串
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean contains(String[] strings, String string) {
        return contains(strings, string, true);
    }

    /**
     * 不区分大小写判定字符串数组中是否包含指定的字符串。
     * 
     * @param strings
     *            字符串数组
     * @param string
     *            字符串
     * @return 包含时返回true，否则返回false
     * @since 0.4
     */
    public static boolean containsIgnoreCase(String[] strings, String string) {
        return contains(strings, string, false);
    }

    /**
     * 将字符串数组使用指定的分隔符合并成一个字符串。
     * 
     * @param array
     *            字符串数组
     * @param delim
     *            分隔符，为null的时候使用""作为分隔符（即没有分隔符）
     * @return 合并后的字符串
     * @since 0.4
     */
    public static String combineStringArray(String[] array, String delim) {
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }
        StringBuffer result = new StringBuffer(length * 8);
        for (int i = 0; i < length; i++) {
            result.append(array[i]);
            result.append(delim);
        }
        result.append(array[length]);
        return result.toString();
    }

    /**
     * 以指定的字符和长度生成一个该字符的指定长度的字符串。
     * 
     * @param c
     *            指定的字符
     * @param length
     *            指定的长度
     * @return 最终生成的字符串
     * @since 0.6
     */
    public static String fillString(char c, int length) {
        String ret = "";
        for (int i = 0; i < length; i++) {
            ret += c;
        }
        return ret;
    }

    /**
     * 去除左边多余的空格。
     * 
     * @param value
     *            待去左边空格的字符串
     * @return 去掉左边空格后的字符串
     * @since 0.6
     */
    public static String trimLeft(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int index = -1;
        for (int i = 0; i < ch.length; i++) {
            if (Character.isWhitespace(ch[i])) {
                index = i;
            } else {
                break;
            }
        }
        if (index != -1) {
            result = result.substring(index + 1);
        }
        return result;
    }

    /**
     * 去除右边多余的空格。
     * 
     * @param value
     *            待去右边空格的字符串
     * @return 去掉右边空格后的字符串
     * @since 0.6
     */
    public static String trimRight(String value) {
        String result = value;
        if (result == null)
            return result;
        char ch[] = result.toCharArray();
        int endIndex = -1;
        for (int i = ch.length - 1; i > -1; i--) {
            if (Character.isWhitespace(ch[i])) {
                endIndex = i;
            } else {
                break;
            }
        }
        if (endIndex != -1) {
            result = result.substring(0, endIndex);
        }
        return result;
    }

    /**
     * 根据转义列表对字符串进行转义。
     * 
     * @param source
     *            待转义的字符串
     * @param escapeCharMap
     *            转义列表
     * @return 转义后的字符串
     * @since 0.6
     */
    public static String escapeCharacter(String source, HashMap<String, String> escapeCharMap) {
        if (source == null || source.length() == 0)
            return source;
        if (escapeCharMap.size() == 0)
            return source;
        StringBuffer sb = new StringBuffer();
        StringCharacterIterator sci = new StringCharacterIterator(source);
        for (char c = sci.first(); c != StringCharacterIterator.DONE; c = sci.next()) {
            String character = String.valueOf(c);
            if (escapeCharMap.containsKey(character))
                character = (String) escapeCharMap.get(character);
            sb.append(character);
        }
        return sb.toString();
    }

    /**
     * 得到字符串的字节长度。
     * 
     * @param source
     *            字符串
     * @return 字符串的字节长度
     * @since 0.6
     */
    public static int getByteLength(String source) {
        int len = 0;
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            int highByte = c >>> 8;
            len += highByte == 0 ? 1 : 2;
        }
        return len;
    }

    /**
     * 得到字符串中的子串的个数。
     * 
     * @param source
     *            字符串
     * @param sub
     *            子串
     * @return 字符串中的子串的个数
     * @since 0.6
     */
    public static int getSubtringCount(String source, String sub) {
        if (source == null || source.length() == 0) {
            return 0;
        }
        int count = 0;
        int index = source.indexOf(sub);
        while (index >= 0) {
            count++;
            index = source.indexOf(sub, index + 1);
        }
        return count;
    }

    /**
     * 将日期的字符类型转为数字
     * 
     * @param date
     * @return
     * 
     */
    public static Integer stringDate2Number(String date) {
        if (date == null)
            throw new IllegalArgumentException("Date can not be null");

        date = date.replace("/", "");
        date = date.replace("-", "");
        return Integer.valueOf(date);

    }

    /**
     * 格式化文件路径<br>
     * 目前仅兼容window和linux<br>
     * 可将\/,\\,//归整为操作系统文件分隔符<br>
     * 
     * @param filePath {@link String} 格式化前的文件路径
     * @return {@link String} 格式化后的文件路径
     */
    static public String formatFilePath(String filePath) {
        // 系统文件分隔符
        String fileSeparator = System.getProperty("file.separator");
        // 替换正则表达式
        String pattenRegxString = "";
        // 替换目标值
        String pattenReplaceValue = "";

        // 入参为空时直接返回
        if (null == File.separator || 0 == filePath.trim().length()) {
            return filePath;
        }
        // 替换非操作系统文件分隔符
        // 目前仅兼容window和linux
        if ("\\".equals(fileSeparator))// window
        {
            filePath = filePath.replaceAll("/", fileSeparator + fileSeparator);
            pattenRegxString = fileSeparator + fileSeparator + fileSeparator + fileSeparator;
            pattenReplaceValue = fileSeparator + fileSeparator;
        } else if ("/".equals(fileSeparator))// linux
        {
            filePath = filePath.replaceAll("\\\\", fileSeparator);
            pattenRegxString = fileSeparator + fileSeparator;
            pattenReplaceValue = fileSeparator;
        }

        // 替换文件分隔符
        while (true) {
            if (null == pattenRegxString || 0 == pattenRegxString.trim().length() || null == pattenReplaceValue
                    || 0 == pattenReplaceValue.trim().length()) {
                break;
            }

            // 在同一位置出现双分隔符，此时将双个分隔符替换为单分隔符
            if (filePath.indexOf(fileSeparator + fileSeparator) != -1) {
                filePath = filePath.replaceAll(pattenRegxString, pattenReplaceValue);
            } else // 未检查到双分隔符时退出循环
            {
                break;
            }// end if
        }

        return filePath;
    }
    
    public static int[] stringformatArray(String ids){
        //对数据进行转换
        String[] temp=split(ids);
        int[] datas = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            datas[i] = Integer.parseInt(temp[i]);
        }
        return datas;
    }
    public static String formatMobi (String mobi)
    {
        if(mobi==null) return null;
        String result = mobi.replaceAll ("\\+", "").replaceAll (" ", "");
        if(result.indexOf("86")==0){
            result = result.substring(2);
        }
        if(result.length()>11)
        	result = result.substring(0,11);
        return result;
    }
    
    public static List<Integer> toIntArray( String str )
    {
        List<Integer> result = null;
        Integer n = null;
        if ( str != null )
        {
            String[] strArray = str.split( "," );
            if ( strArray.length > 0 )
            {
                result = new ArrayList<Integer>();
                for ( int i = 0; i < strArray.length; i++ )
                {
                    try
                    {
                        n = Integer.valueOf( strArray[i] );
                        if ( n != null )
                        {
                            result.add( n );
                        }
                    }
                    catch ( Exception e )
                    {
                    }
                }
            }
        }
        return result;
    }

    public static String ListToString( List<?> list )
    {
        StringBuffer sb = new StringBuffer();
        if ( list != null && list.size() > 0 )
        {
            sb.append( "[" );
            for ( int i = 0; i < list.size(); i++ )
            {
                if ( list.get( i ) == null || list.get( i ) == "" )
                {
                    continue;
                }
                // 如果值是list类型则调用自己
                if ( list.get( i ) instanceof List )
                {
                    sb.append( ListToString( (List<?>) list.get( i ) ) );
                }
                else if ( list.get( i ) instanceof Map )
                {
                    sb.append( MapToString( (Map<?, ?>) list.get( i ) ) );
                }
                else
                {
                    sb.append( list.get( i ) );
                }
                if ( i != list.size() - 1 )
                {
                    sb.append( "," );
                }
            }
            sb.append( "]" );
        }
        return sb.toString();
    }

    public static String StringArrayToString( String[] array )
    {
        StringBuffer sb = new StringBuffer();
        if ( array != null && array.length > 0 )
        {
            sb.append( "[" );
            for ( int i = 0; i < array.length; i++ )
            {
                sb.append( array[i] );
                if ( i != array.length - 1 )
                {
                    sb.append( "," );
                }
            }
            sb.append( "]" );
        }
        return sb.toString();
    }

    public static String MapToString( Map<?, ?> map )
    {
        StringBuffer sb = new StringBuffer();
        if ( map != null && !map.isEmpty() )
        {
            sb.append( "{" );
            int i = 0;
            for ( Object obj : map.keySet() )
            {
                i++;
                if ( obj == null )
                {
                    continue;
                }
                Object key = obj;
                Object value = map.get( key );
                if ( value instanceof List<?> )
                {
                    sb.append( key.toString() + ":" + ListToString( (List<?>) value ) );
                }
                else if ( value instanceof Map<?, ?> )
                {
                    sb.append( key.toString() + ":" + MapToString( (Map<?, ?>) value ) );
                }
                else if ( value instanceof String[] )
                {
                    sb.append( key.toString() + ":" + StringArrayToString( (String[]) value ) );
                }
                else
                {
                    sb.append( key.toString() + " - " + value.toString() );
                }
                if ( i != map.size() )
                {
                    sb.append( "," );
                }
            }
            sb.append( "}" );
        }
        return sb.toString();
    }
}

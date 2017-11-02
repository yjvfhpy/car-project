package com.saturn.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class BeanUtil
{
    /**
     * Object对象数据 注入clazz类
     * 
     * @param Object
     * @param clazz
     * @return
     */
    public static <T> T copy( Object object, Class<T> clazz )
    {
        @SuppressWarnings( "unchecked" )
        Class<T> sources = (Class<T>) object.getClass();
        T toObject;
        if ( clazz == null || object == null )
        {
            return null;
        }
        try
        {
            // 设定当前对象为clazz拷贝对象
            toObject = clazz.newInstance();
            if ( toObject != null )
            {
                Field[] clazzfields = clazz.getDeclaredFields();
                Field[] fields = sources.getDeclaredFields();
                Method[] methods = clazz.getDeclaredMethods();
                Method[] Objectmethods = sources.getDeclaredMethods();
                for ( Field f : fields )
                {
                    try
                    {
                        String fieldName = f.getName();
                        String getMethodName = parseMethodName( fieldName, "get" );
                        if(!haveMethod(Objectmethods,getMethodName)){
                            continue;
                        }
                        Method getMethod = sources.getMethod( getMethodName, new Class[] {} );
                        Object value = getMethod.invoke( object, new Object[] {} );
                        for ( Field f1 : clazzfields )
                        {
                            String fieldName1 = f1.getName();
                            // 确定是两个类中的相同属性且声明类型一样
                            if ( !fieldName1.equals( fieldName ) || !f.getType().equals( f1.getType() ) )
                            {
                                continue;
                            }
                            String setMethodName = parseMethodName( fieldName1, "set" );
                            if(!haveMethod(methods,setMethodName)){
                                continue;
                            }
                            for ( Method method : methods )
                            {
                                // 判断是否属性和set/get方法都存在
                                if ( !setMethodName.equals( method.getName() ) )
                                {
                                    continue;
                                }
                                for ( Method me : Objectmethods )
                                {
                                    if ( !me.getReturnType().equals( method.getReturnType() ) )
                                    {
                                        continue;
                                    }
                                    Method setMethod = clazz.getMethod( setMethodName, new Class[] { f1.getType() } );
                                    setMethod.invoke( toObject, new Object[] { value } );
                                }

                            }
                        }
                    }
                    catch ( Exception e )
                    {
                        e.printStackTrace();
                    }
                }
                return toObject;
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return null;
    }

    // 将get/set名称和属性名称结合
    public static String parseMethodName( String fieldName, String methodType )
    {
        if ( null == fieldName || "".equals( fieldName ) )
        {
            return null;
        }
        return methodType + fieldName.substring( 0, 1 ).toUpperCase() + fieldName.substring( 1 );
    }
    public static boolean haveMethod( Method[] methods, String fieldMethod )
    {
        for ( Method met : methods )
        {
            if ( fieldMethod.equals( met.getName() ) )
            {
                return true;
            }
        }
        return false;
    }

    // public static void main(String[] args) throws Exception {
    // ArrayList<String> list = new ArrayList<String>();
    // list.add("1"); list.add("2"); list.add("3"); list.add("4");
    // User user = new User(3,"admin","minad");
    // TestLoginUser l= new TestLoginUser("584703362@qq.com",21,list,user);
    // LoginUser test = BeanUtil.copy(l, LoginUser.class);
    // System.out.println(test.getAge());
    // System.out.println(test.getList());
    // System.out.println(test.getUser());
    // System.out.println(test.getUser().getId());
    // }

}

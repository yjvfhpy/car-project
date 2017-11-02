package com.saturn.json;

import java.io.Serializable;

/**
 * 包含一个className，用于自动解析json对应的类型
 * 
 * @author tajo
 */
@SuppressWarnings( "serial" )
public abstract class AutoJson
    implements Serializable
{
    private String type;

    public String getType()
    {
        return type;
    }

    public void setType( String type )
    {
        this.type = type;
    }

}

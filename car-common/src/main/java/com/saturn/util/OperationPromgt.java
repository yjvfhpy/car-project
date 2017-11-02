package com.saturn.util;

/**
 * 用于spring mvc，json格式返回消息信息
 * @author DELL
 *
 */
public  class OperationPromgt
{
    private boolean success;
    private String msg;
    
    public OperationPromgt()
    {
    }
    public OperationPromgt(boolean success , String msg)
    {
        this.success = success;
        this.msg = msg;
        
    }
    public boolean getSuccess()
    {
        return success;
    }
    public void setSuccess( boolean success )
    {
        this.success = success;
    }
    public String getMsg()
    {
        return msg;
    }
    public void setMsg( String msg )
    {
        this.msg = msg;
    }

   

}

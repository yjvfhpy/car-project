package com.saturn.util;
/**
 * 用于spring mvc，json格式返回消息信息
 * @author DELL
 *
 */
public  class OperationCode
{
	private Integer code;
    private String msg;
    
    public OperationCode()
    {
    }
    public OperationCode(Integer code , String msg)
    {
        this.code = code;
        this.msg = msg;
        
    }
    public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
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

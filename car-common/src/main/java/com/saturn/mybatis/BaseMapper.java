package com.saturn.mybatis;

import java.util.List;

public interface BaseMapper<T>
{
    public int insert( T t );

    public int update( T t );

    public int delete( T t );
    
    public T selectOne( T t );
    
    public List<T> selectList( T t );
    
    public List<T> selectAll();
}

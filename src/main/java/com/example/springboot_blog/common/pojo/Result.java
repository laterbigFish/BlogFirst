package com.example.springboot_blog.common.pojo;

import com.example.springboot_blog.common.Enum.ResultEnum;
import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;


  public static <T> Result<T> success(T Date){

      Result result = new Result();
      result.setCode(ResultEnum.USCCESS.getCode());
      result.setData(Date);
      return result;
  }
  public static <T> Result<T> fail(String error){
      Result result = new Result();
      result.setData(null);
      result.setCode(ResultEnum.FAIL.getCode());
      result.setMessage(error);
     return result;
  }
    public static <T> Result<T> fail(){
        Result result = new Result();
        result.setData(null);
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMessage(null);
        return result;
    }
}

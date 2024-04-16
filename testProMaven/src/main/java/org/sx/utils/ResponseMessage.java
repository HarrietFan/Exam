package org.sx.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    private Integer status;
    private String msg;
    private Object data;

    public ResponseMessage(Integer status,String msg){
        this.status = status;
        this.msg = msg;
    }
}

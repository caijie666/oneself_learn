package com.cj.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseEntity<T> {

    @ApiModelProperty("响应是否成功")
    private Boolean success;

    @ApiModelProperty(
            value = "响应码,1表示成功",
            example = "1"
    )
    private String responseCode;

    @ApiModelProperty("响应消息")
    private String responseMessage;

    @ApiModelProperty("返回值")
    private T data;

    private ResponseEntity(T data) {
        super();
        this.data = data;
    }

    private ResponseEntity() {

    }



    /**
     * ------------使用链式编程，返回类本身-----------
     **/

    /**
     * 自定义返回结果
     * @param success
     * @return
     */
    public ResponseEntity success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 自定义状态码
     * @param responseCode
     * @return
     */
    public ResponseEntity responseCode(String responseCode) {
        this.setResponseCode(responseCode);
        return this;
    }

    /**
     * 自定义状态码
     * @param responseCode
     * @return
     */
    public ResponseEntity responseCode(Integer responseCode) {
        this.setResponseCode(String.valueOf(responseCode));
        return this;
    }

    /**
     * 自定义状态信息
     * @param responseMessage
     * @return
     */
    public ResponseEntity responseMessage(String responseMessage) {
        this.setResponseMessage(responseMessage);
        return this;
    }

    /**
     * 设置返回值
     * @param data
     * @return
     */
    public ResponseEntity data(T data) {
        this.setData(data);
        return this;
    }

}



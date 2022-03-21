package com.donaldy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author donald
 * @date 2022/02/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultipartParamDTO {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 块大小
     */
    private Integer blockSize;
    /**
     * 资源类型
     */
    private Integer resourceType;
}

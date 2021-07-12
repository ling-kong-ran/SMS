package com.ling.stums.dto;

import java.awt.*;

/**
 * @Author Lingkongran
 * @Date 2020/12/2 0002 14:10
 * @Version 1.0
 */

public class OptionsDto<T> {//下拉框选项dto 这里泛型主要是为了有的value值类型不同而设置的
    private T value;
    private String label;

    public OptionsDto() {
    }

    public OptionsDto(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

package com.zcw.webservice.model.lis;

import com.alibaba.fastjson.annotation.JSONField;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Title: Bacteria
 * Description:细菌信息
 *
 * @author:zhou
 * @date:2016/8/2 15:34
 * @version:
 */
public class Bacteria  implements Serializable {
    private static final long serialVersionUID = 2952674011347452920L;
    @JSONField(name = "Code")
    private String code;            //编号

    @JSONField(name = "Name")
    private String name;            //名称

    @JSONField(name = "Alias")
    private String alias;           //别名;

    @JSONField(name = "Channel")
    private String channel;         //通道编码

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

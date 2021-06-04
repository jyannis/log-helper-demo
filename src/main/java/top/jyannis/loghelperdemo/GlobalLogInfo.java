package top.jyannis.loghelperdemo;

import top.jyannis.loghelper.domain.LogInfo;

/**
 * @author Jyannis
 * @version 1.0 update on 2021/6/4
 */
public class GlobalLogInfo extends LogInfo {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

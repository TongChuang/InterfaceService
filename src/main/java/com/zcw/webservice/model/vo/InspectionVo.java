package com.zcw.webservice.model.vo;

import com.zcw.webservice.model.lis.InspectionInfo;
import com.zcw.webservice.model.lis.InspectionItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zcw on 2016/9/29.
 */
public class InspectionVo  implements Serializable {
    private static final long serialVersionUID = -6816714161513517853L;
    private InspectionInfo inspectionInfo;
    private List<InspectionItem> inspectionItemList;

    public InspectionInfo getInspectionInfo() {
        return inspectionInfo;
    }

    public void setInspectionInfo(InspectionInfo inspectionInfo) {
        this.inspectionInfo = inspectionInfo;
    }

    public List<InspectionItem> getInspectionItemList() {
        return inspectionItemList;
    }

    public void setInspectionItemList(List<InspectionItem> inspectionItemList) {
        this.inspectionItemList = inspectionItemList;
    }
}

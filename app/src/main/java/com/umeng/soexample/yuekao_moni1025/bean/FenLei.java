package com.umeng.soexample.yuekao_moni1025.bean;

import java.util.List;

/**
 * Created by 择木 on 2018/10/25.
 */

public class FenLei {

    /**
     * cid : 1
     * list : [{"icon":"http://120.27.23.105/images/icon.png","name":"月饼","pcid":1,"pscid":1},{"icon":"http://120.27.23.105/images/icon.png","name":"坚果炒货","pcid":1,"pscid":2},{"icon":"http://120.27.23.105/images/icon.png","name":"糖巧","pcid":1,"pscid":3},{"icon":"http://120.27.23.105/images/icon.png","name":"休闲零食","pcid":1,"pscid":4},{"icon":"http://120.27.23.105/images/icon.png","name":"肉干肉脯","pcid":1,"pscid":5},{"icon":"http://120.27.23.105/images/icon.png","name":"饼干蛋糕","pcid":1,"pscid":6},{"icon":"http://120.27.23.105/images/icon.png","name":"蜜饯果干","pcid":1,"pscid":7},{"icon":"http://120.27.23.105/images/icon.png","name":"无糖食品","pcid":1,"pscid":8}]
     * name : 休闲零食
     * pcid : 1
     */

    private String cid;
    private String name;
    private String pcid;
    private List<ListBean> list;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * icon : http://120.27.23.105/images/icon.png
         * name : 月饼
         * pcid : 1
         * pscid : 1
         */

        private String icon;
        private String name;
        private int pcid;
        private int pscid;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPcid() {
            return pcid;
        }

        public void setPcid(int pcid) {
            this.pcid = pcid;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }
    }
}

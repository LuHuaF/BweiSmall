package com.umeng.soexample.bweismall.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by android_lhf：2019/1/3
 */
public class ReXiaoBean {

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        private List<RxxpBean> rxxp;
        private List<PzshBean> pzsh;
        private List<MlssBean> mlss;

        public List<RxxpBean> getRxxp() {
            return rxxp;
        }

        public void setRxxp(List<RxxpBean> rxxp) {
            this.rxxp = rxxp;
        }

        public List<PzshBean> getPzsh() {
            return pzsh;
        }

        public void setPzsh(List<PzshBean> pzsh) {
            this.pzsh = pzsh;
        }

        public List<MlssBean> getMlss() {
            return mlss;
        }

        public void setMlss(List<MlssBean> mlss) {
            this.mlss = mlss;
        }

        public static class RxxpBean {

            private int id;
            private String name;
            private List<CommodityListBean> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBean> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBean> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBean implements Parcelable {


                private int commodityId;
                private String commodityName;
                private String masterPic;
                private int price;
                private int saleNum;

                public CommodityListBean() {
                }

                public CommodityListBean(Parcel in) {
                    commodityId = in.readInt();
                    commodityName = in.readString();
                    masterPic = in.readString();
                    price = in.readInt();
                    saleNum = in.readInt();
                }

                public static final Creator<CommodityListBean> CREATOR = new Creator<CommodityListBean>() {
                    @Override
                    public CommodityListBean createFromParcel(Parcel in) {
                        return new CommodityListBean(in);
                    }

                    @Override
                    public CommodityListBean[] newArray(int size) {
                        return new CommodityListBean[size];
                    }
                };

                public int getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(int commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(int saleNum) {
                    this.saleNum = saleNum;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(commodityId);
                    dest.writeString(commodityName);
                    dest.writeString(masterPic);
                    dest.writeInt(price);
                    dest.writeInt(saleNum);
                }
            }
        }

        public static class PzshBean {


            private int id;
            private String name;
            private List<CommodityListBeanX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanX implements Parcelable{


                private int commodityId;
                private String commodityName;
                private String masterPic;
                private int price;
                private int saleNum;

                public CommodityListBeanX() {
                }

                protected CommodityListBeanX(Parcel in) {
                    commodityId = in.readInt();
                    commodityName = in.readString();
                    masterPic = in.readString();
                    price = in.readInt();
                    saleNum = in.readInt();
                }

                public static final Creator<CommodityListBeanX> CREATOR = new Creator<CommodityListBeanX>() {
                    @Override
                    public CommodityListBeanX createFromParcel(Parcel in) {
                        return new CommodityListBeanX(in);
                    }

                    @Override
                    public CommodityListBeanX[] newArray(int size) {
                        return new CommodityListBeanX[size];
                    }
                };

                public int getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(int commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(int saleNum) {
                    this.saleNum = saleNum;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(commodityId);
                    dest.writeString(commodityName);
                    dest.writeString(masterPic);
                    dest.writeInt(price);
                    dest.writeInt(saleNum);
                }
            }
        }

        public static class MlssBean {

            private int id;
            private String name;
            private List<CommodityListBeanXX> commodityList;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<CommodityListBeanXX> getCommodityList() {
                return commodityList;
            }

            public void setCommodityList(List<CommodityListBeanXX> commodityList) {
                this.commodityList = commodityList;
            }

            public static class CommodityListBeanXX implements Parcelable{

                private int commodityId;
                private String commodityName;
                private String masterPic;
                private int price;
                private int saleNum;

                public CommodityListBeanXX() {
                }

                protected CommodityListBeanXX(Parcel in) {
                    commodityId = in.readInt();
                    commodityName = in.readString();
                    masterPic = in.readString();
                    price = in.readInt();
                    saleNum = in.readInt();
                }

                public static final Creator<CommodityListBeanXX> CREATOR = new Creator<CommodityListBeanXX>() {
                    @Override
                    public CommodityListBeanXX createFromParcel(Parcel in) {
                        return new CommodityListBeanXX(in);
                    }

                    @Override
                    public CommodityListBeanXX[] newArray(int size) {
                        return new CommodityListBeanXX[size];
                    }
                };

                public int getCommodityId() {
                    return commodityId;
                }

                public void setCommodityId(int commodityId) {
                    this.commodityId = commodityId;
                }

                public String getCommodityName() {
                    return commodityName;
                }

                public void setCommodityName(String commodityName) {
                    this.commodityName = commodityName;
                }

                public String getMasterPic() {
                    return masterPic;
                }

                public void setMasterPic(String masterPic) {
                    this.masterPic = masterPic;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getSaleNum() {
                    return saleNum;
                }

                public void setSaleNum(int saleNum) {
                    this.saleNum = saleNum;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(commodityId);
                    dest.writeString(commodityName);
                    dest.writeString(masterPic);
                    dest.writeInt(price);
                    dest.writeInt(saleNum);
                }
            }
        }
    }
}

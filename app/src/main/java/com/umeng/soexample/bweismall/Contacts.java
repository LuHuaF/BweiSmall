package com.umeng.soexample.bweismall;

/**
 * Created by android_lhf：2019/1/5
 */
public class Contacts {
    //总接口前缀
    public static final String BASE_URL = "http://172.17.8.100/small/";

    public static final String USER_UPDATENAME = "user/verify/v1/modifyUserNick";

    public static final String USER_UPDATPWD = "user/verify/v1/modifyUserPwd";

    public static final String SHOU_XIANGQING = "commodity/v1/findCommodityDetailsById";

    //圈子点赞
    public static final String QUAN_DIAN = "circle/verify/v1/addCircleGreat";


    public static final String SHOU_PINLIN = "commodity/v1/CommodityCommentList";

    public static final String SHOU_PINGLUN= "commodity/verify/v1/addCommodityComment";
    //加入购物车
    public static final String JIA_CART= "order/verify/v1/syncShoppingCart";

    public static final String CHA_CART= "order/verify/v1/findShoppingCart";

    public static final String XIN_SHOUHUO= "user/verify/v1/addReceiveAddress";

    public static final String CHA_SHOUHUO= "user/verify/v1/receiveAddressList";

    public static final String MOREN_ADDRES= "user/verify/v1/setDefaultReceiveAddress";

    public static final String BASE_CREAT_DAN="order/verify/v1/createOrder";

    public static final String BASE_CREAT_QUERY="order/verify/v1/findOrderListByStatus";


}

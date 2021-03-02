package com.miles.oa.global;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/3 20:16
 */
public class Constant {
    public static final String POST_STAFF="员工";
    public static final String POST_DM ="部门经理";
    public static final String POST_GM="总经理";
    public static final String POST_CASHIER="财务";

    public static List<String> getPosts(){
        ArrayList<String> list = new ArrayList<String>();
        list.add(POST_STAFF);
        list.add(POST_DM);
        list.add(POST_GM);
        list.add(POST_CASHIER);
        return list;
    }

    public static List<String> getItems(){
        ArrayList<String> list = new ArrayList<String>();
        list.add("交通");
        list.add("餐饮");
        list.add("住宿");
        list.add("办公");
        return list;
    }

    public static final String VOUCHER_CREATED="已创建";
    public static final String VOUCHER_SUBMIT="已提交";
    public static final String VOUCHER_APPROVED="已审核";
    public static final String VOUCHER_BACK="已打回";
    public static final String VOUCHER_TERMINATED="已终止";
    public static final String VOUCHER_RECHECK="待复审";
    public static final String VOUCHER_PAID="已打款";

    public static final String DEAL_CREATE="创建";
    public static final String DEAL_SUBMIT="提交";
    public static final String DEAL_UPDATE="修改";
    public static final String DEAL_BACK="打回";
    public static final String DEAL_REJECT="拒绝";
    public static final String DEAL_PASS="通过";
    public static final String DEAL_PAID="打款";

    public static final double LIMIT_CHECK=5000.00;

}

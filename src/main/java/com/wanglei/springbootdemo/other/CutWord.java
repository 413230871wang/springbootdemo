package com.wanglei.springbootdemo.other;

import java.util.Arrays;

/**
 * @ClassName CutWord
 * @Description
 * @Author lay
 * @Date 2019/12/4 1:41 ????
 * @Version 1.0
 **/
public class CutWord {
    private final String a = "中国";

    public static void main(String[] args) {
        String[] strs = {"loan_id",
                "cur_date",
                "leader",
                "partner",
                "cust_name",
                "cert_type",
                "cert_no",
                "apply_date",
                "start_date",
                "end_date",
                "seq_no",
                "encash_amt",
                "currency",
                "repay_mode",
                "repay_cycle",
                "total_terms",
                "grace_day",
                "fund_status",
                "fail_type",
                "partner_loan_id",
                "order_id",
                "card_no",
                "prod_no",
                "iscfcpay"};
        StringBuffer sb = null;
        for(String str:strs) {
            sb = new StringBuffer();
            String[] temp = str.split("_");
            sb.append(temp[0]);
            for (int i = 1; i < temp.length; i++) {
                temp[i] = temp[i].substring(0,1).toUpperCase()+temp[i].substring(1);
                sb.append(temp[i]);
            }
            System.out.println(sb);
        }
    }
}

package com.miles.oa.dto;

import com.miles.oa.entity.ClaimVoucher;
import com.miles.oa.entity.ClaimVoucherItem;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 19:01
 */
public class ClaimVoucherInfo {
    private ClaimVoucher claimVoucher;
    private List<ClaimVoucherItem> items;


    public ClaimVoucher getClaimVoucher() {
        return claimVoucher;
    }

    public void setClaimVoucher(ClaimVoucher claimVoucher) {
        this.claimVoucher = claimVoucher;
    }

    public List<ClaimVoucherItem> getItems() {
        return items;
    }

    public void setItems(List<ClaimVoucherItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ClaimVoucherInfo{" +
                "claimVoucher=" + claimVoucher +
                ", items=" + items +
                '}';
    }
}

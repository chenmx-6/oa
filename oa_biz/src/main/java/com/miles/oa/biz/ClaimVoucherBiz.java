package com.miles.oa.biz;

import com.miles.oa.entity.ClaimVoucher;
import com.miles.oa.entity.ClaimVoucherItem;
import com.miles.oa.entity.DealRecord;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 18:47
 */
public interface ClaimVoucherBiz {
    void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items);

    ClaimVoucher get(int id);

    List<ClaimVoucherItem> getItems(int cvId);

    List<DealRecord> getRecorders(int cvId);

    List<ClaimVoucher> getForSelf(String sn);

    List<ClaimVoucher> getForDeal(String nextDealSn);

    void submit(int id);

    void deal(DealRecord dealRecord);
}

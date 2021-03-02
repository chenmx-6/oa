package com.miles.oa.biz.impl;

import com.miles.oa.biz.ClaimVoucherBiz;
import com.miles.oa.dao.ClaimVoucherDao;
import com.miles.oa.dao.ClaimVoucherItemDao;
import com.miles.oa.dao.DealRecorderDao;
import com.miles.oa.dao.EmployeeDao;
import com.miles.oa.entity.ClaimVoucher;
import com.miles.oa.entity.ClaimVoucherItem;
import com.miles.oa.entity.DealRecord;
import com.miles.oa.entity.Employee;
import com.miles.oa.global.Constant;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.springframework.scheduling.config.ContextLifecycleScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 18:50
 */
@Service("claimVoucherBiz")
public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
    @Resource
    private ClaimVoucherDao claimVoucherDao;
    @Resource
    private ClaimVoucherItemDao claimVoucherItemDao;
    @Resource
    private DealRecorderDao dealRecorderDao;
    @Resource
    private EmployeeDao employeeDao;

    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setCreateTime(new Date());
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.VOUCHER_CREATED);
        claimVoucherDao.insert(claimVoucher);
        for (ClaimVoucherItem item : items) {
            Integer id = claimVoucher.getId();
            item.setClaimVoucherId(id);
            System.out.println(id);
            claimVoucherItemDao.insert(item);
        }
    }

    public void edit(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        Integer id = claimVoucher.getId();
        ClaimVoucher old = claimVoucherDao.select(id);
        claimVoucher.setCreateSn(old.getCreateSn());
        claimVoucher.setCreateTime(old.getCreateTime());
        claimVoucher.setNextDealSn(old.getNextDealSn());
        claimVoucher.setStatus(old.getStatus());
        claimVoucherDao.update(claimVoucher);
        List<ClaimVoucherItem> oldItems = claimVoucherItemDao.selectByClaimVoucher(id);
        ArrayList<Integer> idList = new ArrayList<Integer>();
        for (ClaimVoucherItem oldItem : oldItems) {
            idList.add(oldItem.getId());
        }
        for (Integer integer : idList) {
            System.out.println(integer);
        }
        for (ClaimVoucherItem item : items) {
            item.setClaimVoucherId(id);
            Integer itemId = item.getId();
            if (itemId != null) {
                claimVoucherItemDao.update(item);
                for (int i = 0; i < idList.size(); i++) {
                    Integer integer = idList.get(i);
                    if (itemId.equals(integer)) {
                        idList.remove(integer);
                        break;
                    }
                }
            } else {
                claimVoucherItemDao.insert(item);
            }
        }
        for (Integer integer : idList) {
            claimVoucherItemDao.delete(integer);
        }
    }

    public ClaimVoucher get(int id) {
        return claimVoucherDao.select(id);
    }

    public List<ClaimVoucherItem> getItems(int cvId) {
        return claimVoucherItemDao.selectByClaimVoucher(cvId);
    }

    public List<DealRecord> getRecorders(int cvId) {
        return dealRecorderDao.selectByClaimVoucher(cvId);
    }

    public List<ClaimVoucher> getForSelf(String sn) {
        List<ClaimVoucher> claimVouchers = claimVoucherDao.selectByCreateSn(sn);
        return claimVouchers;
    }

    public List<ClaimVoucher> getForDeal(String nextDealSn) {
        return claimVoucherDao.selectByNextDealSn(nextDealSn);
    }

    public void submit(int id) {
        ClaimVoucher voucher = claimVoucherDao.select(id);
        String createSn = voucher.getCreateSn();
        Employee creator = employeeDao.select(createSn);
        voucher.setStatus(Constant.VOUCHER_SUBMIT);
        Employee departmentManager = employeeDao.selectByDepartmentAndPost(creator.getDepartmentSn(), Constant.POST_DM).get(0);
        String sn = departmentManager.getSn();
        voucher.setNextDealSn(sn);
        claimVoucherDao.update(voucher);

        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Constant.DEAL_SUBMIT);
        dealRecord.setDealSn(createSn);
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Constant.VOUCHER_SUBMIT);
        dealRecord.setDealTime(new Date());
        dealRecord.setComment("无");
        dealRecorderDao.insert(dealRecord);
    }

    public void deal(DealRecord dealRecord) {
        ClaimVoucher voucher = claimVoucherDao.select(dealRecord.getClaimVoucherId());
        dealRecord.setDealTime(new Date());
        String createSn = dealRecord.getDealSn();
        Employee employee = employeeDao.select(createSn);

        if(dealRecord.getDealWay().equals(Constant.DEAL_PASS)){
            if(voucher.getTotalAmount()<=Constant.LIMIT_CHECK||employee.getPost().equals(Constant.POST_GM)){
                voucher.setStatus(Constant.VOUCHER_APPROVED);
                voucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_CASHIER).get(0).getSn());
                dealRecord.setDealResult(Constant.VOUCHER_APPROVED);
            }else {
                voucher.setStatus(Constant.VOUCHER_RECHECK);
                voucher.setNextDealSn(employeeDao.selectByDepartmentAndPost(null,Constant.POST_GM).get(0).getSn());
                dealRecord.setDealResult(Constant.VOUCHER_RECHECK);
            }
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_BACK)){
            voucher.setStatus(Constant.VOUCHER_BACK);
            voucher.setNextDealSn(voucher.getCreateSn());
            dealRecord.setDealResult(Constant.VOUCHER_BACK);
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_REJECT)){
            voucher.setStatus(Constant.VOUCHER_TERMINATED);
            voucher.setNextDealSn(null);
            dealRecord.setDealResult(Constant.VOUCHER_TERMINATED);
        }else if(dealRecord.getDealWay().equals(Constant.DEAL_PAID)){
            voucher.setStatus(Constant.VOUCHER_PAID);
            voucher.setNextDealSn(null);
            dealRecord.setDealResult(Constant.VOUCHER_PAID);
        }
        claimVoucherDao.update(voucher);
        dealRecorderDao.insert(dealRecord);
    }
}

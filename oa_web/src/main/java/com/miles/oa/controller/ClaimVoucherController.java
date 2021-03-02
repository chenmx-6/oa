package com.miles.oa.controller;

import com.miles.oa.biz.ClaimVoucherBiz;
import com.miles.oa.dto.ClaimVoucherInfo;
import com.miles.oa.entity.ClaimVoucher;
import com.miles.oa.entity.ClaimVoucherItem;
import com.miles.oa.entity.DealRecord;
import com.miles.oa.entity.Employee;
import com.miles.oa.global.Constant;
import com.sun.deploy.panel.ITreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author Miles
 * @date 2021/2/8 19:03
 */
@Controller("claimVoucherController")
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {
    @Resource
    private ClaimVoucherBiz claimVoucherBiz;

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map) {
        map.put("items", Constant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @RequestMapping("/add")
    public String add(HttpSession session, ClaimVoucherInfo info) {
        Employee employee = (Employee) session.getAttribute("employee");
        ClaimVoucher claimVoucher = info.getClaimVoucher();
        claimVoucher.setCreateSn(employee.getSn());
        claimVoucherBiz.save(claimVoucher, info.getItems());
        System.out.println(info);
        return "redirect:deal";
    }

    @RequestMapping("/detail")
    public String detail(int id, Map<String, Object> map) {
        map.put("claimVoucher", claimVoucherBiz.get(id));
        map.put("items", claimVoucherBiz.getItems(id));
        map.put("records", claimVoucherBiz.getRecorders(id));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        String sn = employee.getSn();
        List<ClaimVoucher> vouchers = claimVoucherBiz.getForSelf(sn);
        for (ClaimVoucher voucher : vouchers) {
            System.out.println(voucher);
        }
        map.put("list", vouchers);
        return "claim_voucher_self";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String, Object> map) {
        Employee employee = (Employee) session.getAttribute("employee");
        String sn = employee.getSn();
        List<ClaimVoucher> vouchers = claimVoucherBiz.getForDeal(sn);
        map.put("list", vouchers);
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(int id, Map<String, Object> map) {
        ClaimVoucher claimVoucher = claimVoucherBiz.get(id);
        List<ClaimVoucherItem> items = claimVoucherBiz.getItems(id);
        ClaimVoucherInfo claimVoucherInfo = new ClaimVoucherInfo();
        claimVoucherInfo.setClaimVoucher(claimVoucher);
        claimVoucherInfo.setItems(items);
        map.put("info", claimVoucherInfo);
        map.put("items", Constant.getItems());
        return "claim_voucher_update";
    }

    @RequestMapping("/update")
    public String update(ClaimVoucherInfo info) {
        claimVoucherBiz.edit(info.getClaimVoucher(), info.getItems());
        return "redirect:detail?id=" + info.getClaimVoucher().getId();
    }

    @RequestMapping("/submit")
    public String submit(int id) {
        claimVoucherBiz.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String toCheck(int id, Map<String, Object> map) {
        ClaimVoucher voucher = claimVoucherBiz.get(id);
        map.put("claimVoucher", voucher);
        List<ClaimVoucherItem> items = claimVoucherBiz.getItems(id);
        map.put("items", items);
        List<DealRecord> recorders = claimVoucherBiz.getRecorders(id);
        map.put("recorders", recorders);
        System.out.println(voucher);
        System.out.println(items);
        System.out.println(recorders);
        DealRecord record = new DealRecord();
        record.setClaimVoucherId(id);
        map.put("record", record);
        return "claim_voucher_check";
    }

    @RequestMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord) {
        Employee employee = (Employee) session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherBiz.deal(dealRecord);
        return "redirect:deal";
    }


}

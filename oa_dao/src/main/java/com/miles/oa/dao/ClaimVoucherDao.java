package com.miles.oa.dao;

import com.miles.oa.entity.ClaimVoucher;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 14:37
 */
@Repository("claimVoucherDao")
public interface ClaimVoucherDao {
    @Insert("insert into claim_voucher(id,cause,create_sn,create_time,next_deal_sn,total_amount,status) values(#{id},#{cause},#{createSn},#{createTime},#{nextDealSn},#{totalAmount},#{status})")
    @Options(useGeneratedKeys=true,keyProperty = "id",keyColumn = "id")
    void insert(ClaimVoucher claimVoucher);

    @Update("update claim_voucher set cause=#{cause},create_sn=#{createSn},create_time=#{createTime},next_deal_sn=#{nextDealSn},total_amount=#{totalAmount},status=#{status} where id=#{id}")
    void update(ClaimVoucher claimVoucher);

    @Delete("delete from claim_voucher where id=#{id}")
    void delete(int id);

    @Results(
            id = "claimVoucher",
            value = {
                    @Result(column = "id", property = "id", id = true),
                    @Result(column = "cause", property = "cause"),
                    @Result(column = "create_sn", property = "createSn"),
                    @Result(column = "create_time", property = "createTime"),
                    @Result(column = "next_deal_sn", property = "nextDealSn"),
                    @Result(column = "total_amount", property = "totalAmount"),
                    @Result(column = "status", property = "status"),
                    @Result(column = "create_sn", property = "creator", one = @One(select = "com.miles.oa.dao.EmployeeDao.select", fetchType = FetchType.DEFAULT)),
                    @Result(column = "next_deal_sn", property = "dealer", one = @One(select = "com.miles.oa.dao.EmployeeDao.select", fetchType = FetchType.DEFAULT)),
            }
    )
    @Select("select * from claim_voucher where id=#{id}")
    ClaimVoucher select(int id);

    @ResultMap("claimVoucher")
    @Select("select * from claim_voucher where create_sn=#{csn} order by create_time desc")
    List<ClaimVoucher> selectByCreateSn(String csn);

    @ResultMap("claimVoucher")
    @Select("select * from claim_voucher where next_deal_sn=#{ndsn} order by create_time desc")
    List<ClaimVoucher> selectByNextDealSn(String ndsn);
}

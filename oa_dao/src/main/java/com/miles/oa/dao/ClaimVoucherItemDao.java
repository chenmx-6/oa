package com.miles.oa.dao;

import com.miles.oa.entity.ClaimVoucher;
import com.miles.oa.entity.ClaimVoucherItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 18:10
 */
@Repository("claimVoucherItemDao")
public interface ClaimVoucherItemDao {
    @Insert("insert into claim_voucher_item (id,claim_voucher_id,item,amount,comment) values(#{id},#{claimVoucherId},#{item},#{amount},#{comment})")
    void insert(ClaimVoucherItem claimVoucherItem);

    @Delete("delete from claim_voucher_item where id=#{id}")
    void delete(int id);

    @Update("update claim_voucher_item set id=#{id},claim_voucher_id=#{claimVoucherId},item=#{item},amount=#{amount},comment=#{comment} where id=#{id}")
    void update(ClaimVoucherItem claimVoucherItem);

    @Results(
            {
                    @Result(column = "id", property = "id", id = true),
                    @Result(column = "claim_voucher_id", property = "claimVoucherId"),
                    @Result(column = "item",property = "item"),
                    @Result(column = "amount",property = "amount"),
                    @Result(column = "comment",property = "comment")
            }
    )
    @Select("select * from claim_voucher_item where claim_voucher_id=#{cvId}")
    List<ClaimVoucherItem> selectByClaimVoucher(int cvId);
}

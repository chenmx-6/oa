package com.miles.oa.dao;

import com.miles.oa.entity.DealRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import sun.awt.SunHints;

import java.util.List;

/**
 * @author Miles
 * @date 2021/2/8 18:13
 */
@Repository("dealRecorderDao")
public interface DealRecorderDao {
    @Results(id = "dealRecord",
            value = {
                    @Result(column = "id", property = "id", id = true),
                    @Result(column = "claim_voucher_id", property = "claimVoucherId"),
                    @Result(column = "deal_sn", property = "dealSn"),
                    @Result(column = "deal_time", property = "dealTime"),
                    @Result(column = "deal_way", property = "dealWay"),
                    @Result(column = "deal_result", property = "dealResult"),
                    @Result(column = "comment", property = "comment"),
                    @Result(column = "deal_sn", property = "dealer", one = @One(select = "com.miles.oa.dao.EmployeeDao.select",fetchType = FetchType.DEFAULT)),
            }
    )
    @Select("select * from deal_record where claim_voucher_id=#{cvId}")
    List<DealRecord> selectByClaimVoucher(int cvId);

    @Insert("insert into deal_record (id,claim_voucher_id,deal_sn,deal_time,deal_way,deal_result,comment) values(#{id},#{claimVoucherId},#{dealSn},#{dealTime},#{dealWay},#{dealResult},#{comment})")
    void insert(DealRecord dealRecord);
}

package com.codeagles.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Codeagles
 * Date: 2020/2/20
 * Time: 11:16 AM
 * <p>
 * Description: 用户中心 我的订单列表VO
 */
@Data
public class MyOrdersVO {

    private String orderId;
    private Date createdTime;
    private Integer payMethod;
    private Integer realPayAmount;
    private Integer postAmount;
    private Integer isComment;
    private Integer orderStatus;

    private List<MySubOrderItemVO> subOrderItemList;

}

package com.yuhs.utils.trade;

import com.yuhs.utils.string.StringUtils;

import java.util.UUID;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class TradeUtils {

    /**
     * 获取指定长度流水号
     * @return
     */
    public static String getSysJournalNo(){
        return getSysJournalNo(20, true);
    }


    /**
     * 获取流水号
     * (通过uuid，超过指定长度，进行截取。不满指定长度，补充随机数字)
     * @param length 指定长度
     * @param isNumber 是否全为数字
     * @return
     */
    public static String getSysJournalNo(int length, boolean isNumber){
        //replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        if(uuid.length() > length){
            uuid = uuid.substring(0, length);
        }else if(uuid.length() < length){
            for(int i=0; i<length-uuid.length(); i++){
                uuid = uuid + Math.round(Math.random()*9);
            }
        }
        if(isNumber){
            return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
        }else{
            return uuid;
        }
    }

    /**
     * 金额元转分
     * 注意:该方法可处理贰仟万以内的金额,且若有小数位(保留两位小数)
     * 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算结果会出现异常
     *
     * @param amount 金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static String moneyYuanToFen(String amount) {
        if (StringUtils.isEmpty(amount) && StringUtils.isAllSpace(amount)) {
            return amount;
        }
        //传入的金额字符串代表的是一个整数
        if (-1 == amount.indexOf(".")) {
            return Integer.parseInt(amount) * 100 + "";
        }
        //传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        //取到小数点后面的字符串
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        //amount=12.3
        if (pointBehind.length() == 1) {
            return money_fen + Integer.parseInt(pointBehind) * 10 + "";
        }
        //小数点后面的第一位字符串的整数表示
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        //小数点后面的第二位字符串的整数表示
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //amount==12.03,amount=12.00,amount=12.30
        if (pointString_1 == 0) {
            return money_fen + pointString_2 + "";
        } else {
            return money_fen + pointString_1 * 10 + pointString_2 + "";
        }
    }

    /**
     * 金额元转分
     * 注意:该方法会将金额中小数点后面的数值,四舍五入后只保留两位
     * 注意:该方法可处理贰仟万以内的金额,且若有小数位(保留两位小数)
     * 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算结果会出现异常
     *
     * @param amount 金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static String moneyYuanToFenByRound(String amount){
        if (StringUtils.isEmpty(amount) && StringUtils.isAllSpace(amount)) {
            return amount;
        }
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 + "";
        }
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 + "";
        }
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //下面这种方式用于处理pointBehind=245,286,295,298,995,998等需要四舍五入的情况
        if(pointBehind.length() > 2){
            int pointString_3 = Integer.parseInt(pointBehind.substring(2, 3));
            if(pointString_3 >= 5){
                if(pointString_2 == 9){
                    if(pointString_1 == 9){
                        money_fen = money_fen + 100;
                        pointString_1 = 0;
                        pointString_2 = 0;
                    }else{
                        pointString_1 = pointString_1 + 1;
                        pointString_2 = 0;
                    }
                }else{
                    pointString_2 = pointString_2 + 1;
                }
            }
        }
        if(pointString_1 == 0){
            return money_fen + pointString_2 + "";
        }else{
            return money_fen + pointString_1*10 + pointString_2 + "";
        }
    }

    /**
     * 金额分转元
     * 注意:如果传入的参数中含小数点,则直接原样返回
     * 注意:该方法返回的金额字符串格式为<code>00.00</code>,其整数位有且至少有一个,小数位有且长度固定为2
     * @param amount 金额的分进制字符串
     * @return String 金额的元进制字符串
     */
    public static String moneyFenToYuan(String amount){
        if (StringUtils.isEmpty(amount) && StringUtils.isAllSpace(amount)) {
            return amount;
        }
        if(amount.indexOf(".") > -1){
            return amount;
        }
        if(amount.length() == 1){
            return "0.0" + amount;
        }else if(amount.length() == 2){
            return "0." + amount;
        }else{
            return amount.substring(0, amount.length()-2) + "." + amount.substring(amount.length()-2);
        }
    }
}

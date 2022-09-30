package com.seewo.binlogsql;

import com.seewo.binlogsql.vo.CommonFilter;
import com.seewo.binlogsql.vo.DbInfoVo;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.seewo.binlogsql.tool.DateUtil.addDate;
import static com.seewo.binlogsql.tool.DateUtil.getTruncatedDate;

/**
 * @author linxixin@cvte.com
 * @since 1.0
 */
@Slf4j
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        Date date = new Date();
        Date newDate = getTruncatedDate(date, new SimpleDateFormat("yyyy-MM-dd"));
        Date lastDate = addDate(newDate, Calendar.DATE, -1);
        //long startTime = System.currentTimeMillis();
        long startTime = lastDate.getTime();

        log.error("#############");
        DbInfoVo dbInfoVo = new DbInfoVo();
        dbInfoVo.setHost("10.0.14.243");
        dbInfoVo.setPort(3306);
        dbInfoVo.setUsername("root");
        dbInfoVo.setPassword("123456");
        new BinlogListenSql(dbInfoVo)
                .setFilter(new CommonFilter().setStartTime(startTime))
                .connectAndListen();
    }
}

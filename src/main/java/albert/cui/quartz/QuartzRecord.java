package albert.cui.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author albert.cui
 * @date 2019/2/27 21:53
 */
@Component(value = "quartzRecord")
public class QuartzRecord {
    public void execute() {
        System.out.println(new Date() + "定时执行任务!");
    }
}

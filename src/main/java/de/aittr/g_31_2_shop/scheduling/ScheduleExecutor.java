package de.aittr.g_31_2_shop.scheduling;

import de.aittr.g_31_2_shop.domain.dto.ProductDto;
import de.aittr.g_31_2_shop.domain.jpa.JpaProduct;
import de.aittr.g_31_2_shop.domain.jpa.Task;
import de.aittr.g_31_2_shop.services.jpa.JpaProductService;
import de.aittr.g_31_2_shop.services.jpa.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@EnableScheduling
@EnableAsync
public class ScheduleExecutor {
    private final TaskService taskService;
    private final JpaProductService jpaProductService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleExecutor.class);

    public ScheduleExecutor(TaskService taskService, JpaProductService jpaProductService) {
        this.taskService = taskService;
        this.jpaProductService = jpaProductService;
    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        taskService.createTask("Fixed delay task");
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayTask() {
//        taskService.createTask("Fixed delay task 3000 ms");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    public void fixedRateTask() {
//        taskService.createTask("Fixed rate task 3000 ms");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedRate = 5000)
//    @Async
//    public void fixedRateAsyncTask() {
//        taskService.createTask("Fixed rate task 7000 ms");
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
//    @Async
//    public void initialDelayTask() {
//        taskService.createTask("Fixed delay task");
//    }

    // 2 hours -> 7200000 milliseconds -> PT2H
//    @Scheduled(fixedDelayString = "PT2S")
//    @Async
//    public void initialDelayTask() {
//        taskService.createTask("Another delay format task");
//    }

    // 55 * * * * * -> каждую минуту в момент времени 55 секунд
    // 0 10,20 * * * * -> каждый час в 10 минут и в 20 минут
    // 0 15 9-17 * * MON-FRI -> по будням с 9 до 17 в 15 минут каждого часа

//    @Scheduled(cron = "10,30 * * * * *")
//    public void cronExpressionTask() {
//        taskService.createTask("Cron expression task");
//    }

//    public static void scheduleAndExecuteTask(Task task) {
//        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
//        scheduler.schedule(
//                () -> logger.info(task.getDescription()),
//                new CronTrigger("0,10,20,30,40,50 * * * * *")
//        );
//    }

    public static void scheduleAndExecuteTask(Task task) {
        TaskScheduler scheduler = new DefaultManagedTaskScheduler();
        Instant instant = Instant.now().plusSeconds(30);
        scheduler.schedule(
                () -> logger.info(task.getDescription()),
                instant
        );
    }

    @Scheduled(fixedRate = 30000)
    @Async
    public void printFiveLastTasks() {
        List<Task> tasks = taskService.getTasks();
        System.out.println(tasks.subList(tasks.size() - 5, tasks.size()));
    }

    @Scheduled(cron = "15,45 * * * * *")
    @Async
    public void printLastAddedProduct() {
        List<ProductDto> products = jpaProductService.getAllActiveProducts();
        ProductDto lastProduct = products.get(products.size() - 1);
        taskService.createTask(String.format("Последний добавленный в БД продукт - %s", lastProduct.getName()));
    }
}

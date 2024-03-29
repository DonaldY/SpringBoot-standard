package com.donaldy.service.impl;

import com.donaldy.utils.ExecutorServiceUtils;
import com.donaldy.config.handler.AsyncException;
import com.donaldy.model.User;
import com.donaldy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.async.DeferredResult;

import javax.validation.constraints.NotBlank;
import java.util.concurrent.*;

@Slf4j
@Service
@Validated
public class UserServiceImpl implements UserService {

    @Async
    @Override
    public void scanQrCode(DeferredResult<String> result) {

        log.info("---> 开始业务调用");

        // 模拟多次查询
        // 例如：每秒查询1次 redis
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("---> 结束业务调用");

        result.setResult("扫描成功");
    }

    @Async
    @Override
    public void executeUserInfo(Integer userId) {
        log.info("enter executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
            if (userId == 2) {
                throw new AsyncException(1, "123");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (AsyncException e) {
            e.printStackTrace();
        }
        log.info("exit executeUserInfo userId : {}, threadId : {}", userId, Thread.currentThread().getId());
    }

    @Override
    public User getUser(String username, User _user) {
        System.out.println("input user : " + _user);
        System.out.println("username : " + username);
        User user = new User();
        user.setUsername("DonaldY");
        user.setPassword("123");

        return user;
    }

    @Override
    public User getUserFriend(String username) {
        // validateUserName(username);

        User user = new User();
        user.setUsername("go away, gay.");

        return user;
    }

    private void validateUserName(@NotBlank(message = "用户名不能为空")
                                  @Length(min = 3, max = 10, message = "用户名长度大于3小于等于10") String username) {
        System.out.println("go out, you are a gay.");
    }

    /**
     * 改为同步非阻塞
     */
    @Override
    public void recommendUser() throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();

        ExecutorService executorService = ExecutorServiceUtils.getInstance();

        Future<User> familyFuture = executorService.submit(this::findUserFamily, User.newBuilder().build());

        Future<User> friendsFuture = executorService.submit(this::findUserFriends, User.newBuilder().build());

        Future<User> strangerFuture = executorService.submit(this::findStranger);

        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("load() 总耗时：" + costTime + " 毫秒");
    }

    public CompletableFuture<Object> recommendUser2() {

        final CompletableFuture<User> cacheFuture = CompletableFuture.supplyAsync(this::findUserFamily);

        final CompletableFuture<User> dbFuture = CompletableFuture.supplyAsync(this::findUserFriends);

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(cacheFuture, dbFuture);

        try {
            objectCompletableFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return CompletableFuture.anyOf(cacheFuture, dbFuture);
    }

    public static void main(String[] args) throws InterruptedException {
        long millis = System.currentTimeMillis();

        ExecutorService executorService = ExecutorServiceUtils.getInstance();

        Future<User> userFuture = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("some thing bad happened.");
            }
            return User.newBuilder().build();
        });

        User user = null;

        try {
            user = userFuture.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException | TimeoutException e) {
            userFuture.cancel(true);
            System.out.println("some bad happened");
        } finally {
            executorService.shutdownNow();
        }

        System.out.println(ObjectUtils.isEmpty(user));

        System.out.println("加载完成 + " + (System.currentTimeMillis() - millis));
    }

    public User findStranger() {

        loadMock("stranger", 3);

        log.info("stranger");

        System.out.println("stranger");

        return User.newBuilder().username("stranger").build();
    }

    private User findUserFriends() {

        loadMock("friend", 2);

        log.info("friend");

        System.out.println("friend");

        return User.newBuilder().username("friend").build();
    }

    private User findUserFamily() {

        loadMock("family", 1);

        log.info("family");

        System.out.println("family");

        return User.newBuilder().username("family").build();
    }

    public final void load() {
        long startTime = System.currentTimeMillis(); // 开始时间
        doLoad(); // 具体执行
        long costTime = System.currentTimeMillis() - startTime; // 消耗时间
        System.out.println("load() 总耗时：" + costTime + " 毫秒");
    }

    public void doLoad() {  // 并行计算
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 创建线程池
        CompletionService completionService = new ExecutorCompletionService(executorService);
        completionService.submit(this::loadConfigurations, null);      //  耗时 >= 1s
        completionService.submit(this::loadUsers, null);               //  耗时 >= 2s
        completionService.submit(this::loadOrders, null);              //  耗时 >= 3s

        int count = 0;
        while (count < 3) { // 等待三个任务完成
            if (completionService.poll() != null) {
                count++;
            }
        }
        executorService.shutdown();
    }

    protected final void loadConfigurations() {
        loadMock("loadConfigurations()", 1);
    }

    protected final void loadUsers() {
        loadMock("loadUsers()", 2);
    }

    protected final void loadOrders() {
        loadMock("loadOrders()", 3);
    }

    private void loadMock(String source, int seconds) {
        try {
            long startTime = System.currentTimeMillis();
            long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
            Thread.sleep(milliseconds);
            long costTime = System.currentTimeMillis() - startTime;
            System.out.printf("[线程 : %s] %s 耗时 :  %d 毫秒\n",
                    Thread.currentThread().getName(), source, costTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

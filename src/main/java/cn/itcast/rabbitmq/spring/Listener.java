package cn.itcast.rabbitmq.spring;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 监听器，作为监听者
 */
@Component
public class Listener {

    /**
     * 声明是监听器注解
     * @param msg
     */
    @RabbitListener(
            bindings = @QueueBinding(
                value = @Queue(
                        value = "spring.test.queue",  //指定绑定队列
                        durable = "true"   //是否持久化
                ),
                exchange = @Exchange(
                        value = "spring.test.exchange", //指定交换机
                        ignoreDeclarationExceptions = "true", //是否忽视异常（假如有已有交换机会报异常）
                        type = ExchangeTypes.TOPIC    //类型
                ),
                key = {"#.#"}
            )
    )
    public void listen(String msg){
        System.out.println("接收到消息：" + msg);
    }
}

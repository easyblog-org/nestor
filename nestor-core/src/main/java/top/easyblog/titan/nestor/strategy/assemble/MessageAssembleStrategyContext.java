package top.easyblog.titan.nestor.strategy.assemble;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: frank.huang
 * @date: 2023-02-11 21:58
 */
@Slf4j
@Component
public class MessageAssembleStrategyContext {

    @Autowired
    private List<MessageAssembleStrategy> messageAssembleStrategies;

    private static Map<Byte, MessageAssembleStrategy> messageAssembleStrategyHashMap = new HashMap<>();

    @PostConstruct
    private void initMessageSendStrategyMap() {
        log.info("Start init message assemble strategy....");
        messageAssembleStrategyHashMap = messageAssembleStrategies.stream()
                .collect(Collectors.toMap(MessageAssembleStrategy::getPushType, Function.identity(), (x, y) -> x));
        log.info("Init message assemble strategy successfully!");
    }

    public static MessageAssembleStrategy getMessageAssembleStrategy(Byte sendType) {
        return messageAssembleStrategyHashMap.get(sendType);
    }

}

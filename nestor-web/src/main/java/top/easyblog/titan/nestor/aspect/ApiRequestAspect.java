package top.easyblog.titan.nestor.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import top.easyblog.titan.nestor.exception.BusinessException;
import top.easyblog.titan.nestor.request.BaseRequest;
import top.easyblog.titan.nestor.response.NestorResultCode;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author frank.huang
 * @date 2022/01/29 14:45
 */
@Aspect
public class ApiRequestAspect {

    @Pointcut("execution(public * top.easyblog.titan.nestor.controller..*.*(..)) && " +
            "(@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object processApiRequest(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Arrays.stream(args).filter(arg -> Objects.nonNull(arg) && arg instanceof BaseRequest).forEach(arg -> {
            if (!((BaseRequest) arg).validate()) {
                throw new BusinessException(NestorResultCode.PARAMETER_VALIDATE_FAILED, "please check parameter:" + arg);
            }
        });
        return pjp.proceed(pjp.getArgs());
    }
}

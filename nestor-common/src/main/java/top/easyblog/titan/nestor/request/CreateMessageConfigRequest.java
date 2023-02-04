package top.easyblog.titan.nestor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessageConfigRequest extends BaseRequest {

    private String code;

    @NotBlank(message = "Required param 'type' is not present")
    private String type;

    @NotBlank(message = "Required param 'name' is not present")
    private String name;

    private CreateTemplateValueConfigRequest templateValueConfig;

    @Override
    public boolean validate() {
        return Objects.nonNull(templateValueConfig) &&
                Objects.nonNull(templateValueConfig.getType()) &&
                StringUtils.isNotBlank(templateValueConfig.getExpression()) &&
                StringUtils.isNotBlank(templateValueConfig.getUrl());
    }
}

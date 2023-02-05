package top.easyblog.titan.nestor.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.easyblog.titan.nestor.enums.TemplateValueConfigType;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * @author: frank.huang
 * @date: 2023-02-04 19:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMessageConfigRequest extends BaseRequest {

    @NotBlank(message = "Required param 'name' is not present")
    private String name;

    private Boolean deleted;

    private UpdateTemplateValueConfigRequest templateValueConfig;

    @Override
    public boolean validate() {
        if(Objects.nonNull(templateValueConfig) && Objects.equals(TemplateValueConfigType.codeOf(templateValueConfig.getType()), TemplateValueConfigType.INTERFACE_DIRECT_VALUE) ||
                Objects.equals(TemplateValueConfigType.codeOf(templateValueConfig.getType()), TemplateValueConfigType.INTERFACE_JSON_VALUE)){
            assertNotEmpty(templateValueConfig.getUrl(), "Required param 'templateValueConfig.url' is not present");
        }
        return super.validate();
    }
}

package top.easyblog.titan.nestor.dao.custom.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.easyblog.titan.nestor.bean.MessageConfigBean;
import top.easyblog.titan.nestor.request.QueryMessageConfigsRequest;

import java.util.List;

/**
 * @author: frank.huang
 * @date: 2023-02-05 14:08
 */
@Mapper
public interface MyMessageConfigMapper {

    long countByRequest(@Param("request") QueryMessageConfigsRequest request);

    List<MessageConfigBean> selectListByRequest(@Param("request") QueryMessageConfigsRequest request);

}

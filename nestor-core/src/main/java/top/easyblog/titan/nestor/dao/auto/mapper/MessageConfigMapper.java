package top.easyblog.titan.nestor.dao.auto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfig;
import top.easyblog.titan.nestor.dao.auto.model.MessageConfigExample;

@Mapper
public interface MessageConfigMapper {
    @SelectProvider(type=MessageConfigSqlProvider.class, method="countByExample")
    long countByExample(MessageConfigExample example);

    @DeleteProvider(type=MessageConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageConfigExample example);

    @Delete({
        "delete from message_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message_config (id, code, ",
        "type, name, template_value_config_id, ",
        "deleted, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{templateValueConfigId,jdbcType=BIGINT}, ",
        "#{deleted,jdbcType=BIT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(MessageConfig record);

    @InsertProvider(type=MessageConfigSqlProvider.class, method="insertSelective")
    int insertSelective(MessageConfig record);

    @SelectProvider(type=MessageConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_value_config_id", property="templateValueConfigId", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MessageConfig> selectByExample(MessageConfigExample example);

    @Select({
        "select",
        "id, code, type, name, template_value_config_id, deleted, create_time, update_time",
        "from message_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_value_config_id", property="templateValueConfigId", jdbcType=JdbcType.BIGINT),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MessageConfig selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageConfig record, @Param("example") MessageConfigExample example);

    @UpdateProvider(type=MessageConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageConfig record, @Param("example") MessageConfigExample example);

    @UpdateProvider(type=MessageConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageConfig record);

    @Update({
        "update message_config",
        "set code = #{code,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "template_value_config_id = #{templateValueConfigId,jdbcType=BIGINT},",
          "deleted = #{deleted,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MessageConfig record);
}
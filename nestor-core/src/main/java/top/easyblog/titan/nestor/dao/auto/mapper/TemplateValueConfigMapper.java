package top.easyblog.titan.nestor.dao.auto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfig;
import top.easyblog.titan.nestor.dao.auto.model.TemplateValueConfigExample;

@Mapper
public interface TemplateValueConfigMapper {
    @SelectProvider(type=TemplateValueConfigSqlProvider.class, method="countByExample")
    long countByExample(TemplateValueConfigExample example);

    @DeleteProvider(type=TemplateValueConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(TemplateValueConfigExample example);

    @Delete({
        "delete from template_value_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into template_value_config (id, type, ",
        "expression, url, ",
        "deleted, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{type,jdbcType=TINYINT}, ",
        "#{expression,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, ",
        "#{deleted,jdbcType=BIT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(TemplateValueConfig record);

    @InsertProvider(type=TemplateValueConfigSqlProvider.class, method="insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()",keyColumn = "id",keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(TemplateValueConfig record);

    @SelectProvider(type=TemplateValueConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="expression", property="expression", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TemplateValueConfig> selectByExample(TemplateValueConfigExample example);

    @Select({
        "select",
        "id, type, expression, url, deleted, create_time, update_time",
        "from template_value_config",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.TINYINT),
        @Result(column="expression", property="expression", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    TemplateValueConfig selectByPrimaryKey(Long id);

    @UpdateProvider(type=TemplateValueConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TemplateValueConfig record, @Param("example") TemplateValueConfigExample example);

    @UpdateProvider(type=TemplateValueConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") TemplateValueConfig record, @Param("example") TemplateValueConfigExample example);

    @UpdateProvider(type=TemplateValueConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TemplateValueConfig record);

    @Update({
        "update template_value_config",
        "set type = #{type,jdbcType=TINYINT},",
          "expression = #{expression,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(TemplateValueConfig record);
}
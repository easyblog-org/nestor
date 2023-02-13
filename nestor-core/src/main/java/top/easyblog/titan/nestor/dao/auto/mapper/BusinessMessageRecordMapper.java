package top.easyblog.titan.nestor.dao.auto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.easyblog.titan.nestor.dao.auto.model.BusinessMessageRecord;
import top.easyblog.titan.nestor.dao.auto.model.BusinessMessageRecordExample;

@Mapper
public interface BusinessMessageRecordMapper {
    @SelectProvider(type=BusinessMessageRecordSqlProvider.class, method="countByExample")
    long countByExample(BusinessMessageRecordExample example);

    @DeleteProvider(type=BusinessMessageRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(BusinessMessageRecordExample example);

    @Delete({
        "delete from business_message_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into business_message_record (id, business_id, ",
        "business_module, business_event, ",
        "status, retry_times, ",
        "fail_reason, deleted, ",
        "create_time, update_time, ",
        "business_message)",
        "values (#{id,jdbcType=BIGINT}, #{businessId,jdbcType=VARCHAR}, ",
        "#{businessModule,jdbcType=VARCHAR}, #{businessEvent,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=TINYINT}, #{retryTimes,jdbcType=INTEGER}, ",
        "#{failReason,jdbcType=VARCHAR}, #{deleted,jdbcType=BIT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{businessMessage,jdbcType=LONGVARCHAR})"
    })
    int insert(BusinessMessageRecord record);

    @InsertProvider(type=BusinessMessageRecordSqlProvider.class, method="insertSelective")
    int insertSelective(BusinessMessageRecord record);

    @SelectProvider(type=BusinessMessageRecordSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="business_id", property="businessId", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_module", property="businessModule", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_event", property="businessEvent", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="retry_times", property="retryTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="fail_reason", property="failReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="business_message", property="businessMessage", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BusinessMessageRecord> selectByExampleWithBLOBs(BusinessMessageRecordExample example);

    @SelectProvider(type=BusinessMessageRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="business_id", property="businessId", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_module", property="businessModule", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_event", property="businessEvent", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="retry_times", property="retryTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="fail_reason", property="failReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<BusinessMessageRecord> selectByExample(BusinessMessageRecordExample example);

    @Select({
        "select",
        "id, business_id, business_module, business_event, status, retry_times, fail_reason, ",
        "deleted, create_time, update_time, business_message",
        "from business_message_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="business_id", property="businessId", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_module", property="businessModule", jdbcType=JdbcType.VARCHAR),
        @Result(column="business_event", property="businessEvent", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="retry_times", property="retryTimes", jdbcType=JdbcType.INTEGER),
        @Result(column="fail_reason", property="failReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="deleted", property="deleted", jdbcType=JdbcType.BIT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="business_message", property="businessMessage", jdbcType=JdbcType.LONGVARCHAR)
    })
    BusinessMessageRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=BusinessMessageRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BusinessMessageRecord record, @Param("example") BusinessMessageRecordExample example);

    @UpdateProvider(type=BusinessMessageRecordSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") BusinessMessageRecord record, @Param("example") BusinessMessageRecordExample example);

    @UpdateProvider(type=BusinessMessageRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BusinessMessageRecord record, @Param("example") BusinessMessageRecordExample example);

    @UpdateProvider(type=BusinessMessageRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BusinessMessageRecord record);

    @Update({
        "update business_message_record",
        "set business_id = #{businessId,jdbcType=VARCHAR},",
          "business_module = #{businessModule,jdbcType=VARCHAR},",
          "business_event = #{businessEvent,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "retry_times = #{retryTimes,jdbcType=INTEGER},",
          "fail_reason = #{failReason,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "business_message = #{businessMessage,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(BusinessMessageRecord record);

    @Update({
        "update business_message_record",
        "set business_id = #{businessId,jdbcType=VARCHAR},",
          "business_module = #{businessModule,jdbcType=VARCHAR},",
          "business_event = #{businessEvent,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "retry_times = #{retryTimes,jdbcType=INTEGER},",
          "fail_reason = #{failReason,jdbcType=VARCHAR},",
          "deleted = #{deleted,jdbcType=BIT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(BusinessMessageRecord record);
}
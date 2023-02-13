package top.easyblog.titan.nestor.dao.auto.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecord;
import top.easyblog.titan.nestor.dao.auto.model.MessageSendRecordExample;

@Mapper
public interface MessageSendRecordMapper {
    @SelectProvider(type=MessageSendRecordSqlProvider.class, method="countByExample")
    long countByExample(MessageSendRecordExample example);

    @DeleteProvider(type=MessageSendRecordSqlProvider.class, method="deleteByExample")
    int deleteByExample(MessageSendRecordExample example);

    @Delete({
        "delete from message_send_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into message_send_record (id, code, ",
        "receiver, sender, ",
        "status, failed_reason, ",
        "retry_time, title, ",
        "channel, content, ",
        "template_code, create_time, ",
        "update_time)",
        "values (#{id,jdbcType=BIGINT}, #{code,jdbcType=VARCHAR}, ",
        "#{receiver,jdbcType=VARCHAR}, #{sender,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=TINYINT}, #{failedReason,jdbcType=VARCHAR}, ",
        "#{retryTime,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{channel,jdbcType=TINYINT}, #{content,jdbcType=VARCHAR}, ",
        "#{templateCode,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(MessageSendRecord record);

    @InsertProvider(type=MessageSendRecordSqlProvider.class, method="insertSelective")
    int insertSelective(MessageSendRecord record);

    @SelectProvider(type=MessageSendRecordSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="sender", property="sender", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="failed_reason", property="failedReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="retry_time", property="retryTime", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="channel", property="channel", jdbcType=JdbcType.TINYINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_code", property="templateCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<MessageSendRecord> selectByExample(MessageSendRecordExample example);

    @Select({
        "select",
        "id, code, receiver, sender, status, failed_reason, retry_time, title, channel, ",
        "content, template_code, create_time, update_time",
        "from message_send_record",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="receiver", property="receiver", jdbcType=JdbcType.VARCHAR),
        @Result(column="sender", property="sender", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="failed_reason", property="failedReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="retry_time", property="retryTime", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="channel", property="channel", jdbcType=JdbcType.TINYINT),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="template_code", property="templateCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    MessageSendRecord selectByPrimaryKey(Long id);

    @UpdateProvider(type=MessageSendRecordSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") MessageSendRecord record, @Param("example") MessageSendRecordExample example);

    @UpdateProvider(type=MessageSendRecordSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") MessageSendRecord record, @Param("example") MessageSendRecordExample example);

    @UpdateProvider(type=MessageSendRecordSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MessageSendRecord record);

    @Update({
        "update message_send_record",
        "set code = #{code,jdbcType=VARCHAR},",
          "receiver = #{receiver,jdbcType=VARCHAR},",
          "sender = #{sender,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=TINYINT},",
          "failed_reason = #{failedReason,jdbcType=VARCHAR},",
          "retry_time = #{retryTime,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "channel = #{channel,jdbcType=TINYINT},",
          "content = #{content,jdbcType=VARCHAR},",
          "template_code = #{templateCode,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(MessageSendRecord record);
}
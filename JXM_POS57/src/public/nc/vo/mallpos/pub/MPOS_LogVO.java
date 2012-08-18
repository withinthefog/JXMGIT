package nc.vo.mallpos.pub;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

public class MPOS_LogVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_log; // 主键

	private String stage; // 业务阶段

	private UFBoolean success; // 是否成功

	private String content; // 日志内容

	private UFDate log_date;// 日志日期
	
	private String log_type;//日志类型

	public String getPKFieldName()
	{
		return "pk_log";
	}

	public String getParentPKFieldName()
	{
		return null;
	}

	public String getTableName()
	{
		return "mpos_log";
	}

	/**
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * @return the log_date
	 */
	public UFDate getLog_date()
	{
		return log_date;
	}

	/**
	 * @param log_date
	 *            the log_date to set
	 */
	public void setLog_date(UFDate log_date)
	{
		this.log_date = log_date;
	}

	/**
	 * @return the stage
	 */
	public String getStage()
	{
		return stage;
	}

	/**
	 * @param stage
	 *            the stage to set
	 */
	public void setStage(String stage)
	{
		this.stage = stage;
	}

	/**
	 * @return the success
	 */
	public UFBoolean getSuccess()
	{
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(UFBoolean success)
	{
		this.success = success;
	}

	/**
	 * @return the pk_log
	 */
	public String getPk_log()
	{
		return pk_log;
	}

	/**
	 * @param pk_log
	 *            the pk_log to set
	 */
	public void setPk_log(String pk_log)
	{
		this.pk_log = pk_log;
	}

	/**
	 * @return the log_type
	 */
	public String getLog_type()
	{
		return log_type;
	}

	/**
	 * @param log_type the log_type to set
	 */
	public void setLog_type(String log_type)
	{
		this.log_type = log_type;
	}
}

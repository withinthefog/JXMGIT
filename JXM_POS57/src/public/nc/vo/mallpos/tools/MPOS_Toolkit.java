package nc.vo.mallpos.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.bs.pub.billcodemanage.BillcodeGenerater;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.billcodemanage.BillCodeObjValueVO;

/**
 * 工具类
 * 
 * @author chenjun
 * @date 2011-3-23 上午10:31:18
 */
public class MPOS_Toolkit
{

	/**
	 * 字符串数组转换为字符串,使用逗号分隔
	 * 
	 * @param str
	 * @param spliter
	 * @return String 如：'str','str','str'
	 */
	public static String split(String[] str, String spliter)
	{
		if (str == null || str.length <= 0)
		{
			return "";
		}
		StringBuffer strBuf = new StringBuffer();
		for (String s : str)
		{
			strBuf.append("'" + s + "'").append(spliter);
		}
		strBuf.deleteCharAt(strBuf.lastIndexOf(spliter));
		return strBuf.toString();
	}

	/**
	 * 查询参数组合,不包含where关键字 field,value为空串或是null时构造条件设置失效
	 * 
	 * @param field
	 *            查询字段
	 * @param value
	 *            查询值
	 * @param condition
	 *            查询条件 ex. =,like等
	 * @param flg
	 *            连接符：ex. and,or等
	 * @return 条件字符串
	 */
	public static String putProperties(String field, String value, String condition, String flg)
	{
		if (value == null)
			return "";
		String tempValue = value.replace("'", "");
		tempValue = tempValue.replace("%", "");
		if (field == null || "".equals(field) || value == null || "".equals(tempValue) || "null".equals(tempValue))
		{
			return "";
		}
		StringBuffer sbf = new StringBuffer(" ");
		sbf.append(flg);
		sbf.append(" ");
		sbf.append(field);
		sbf.append(" ");
		sbf.append(condition);
		if ("in".equals(condition))
		{
			sbf.append(" (");
		} else
		{
			sbf.append(" ");
		}
		sbf.append(value);
		if ("in".equals(condition))
		{
			sbf.append(") ");
		}
		return sbf.toString();

	}

	/**
	 * 查询参数组合,不包含where关键字,默认以and连接 field,value为空串或是null时构造条件设置失效
	 * 
	 * @param field
	 *            查询字段
	 * @param value
	 *            查询值
	 * @param condition
	 *            查询条件 ex. =,like等
	 * @return 条件字符串
	 */
	public static String putProperties(String field, String value, String condition)
	{
		return putProperties(field, value, condition, " and ");
	}

	/**
	 * 比较两个时间的大小
	 * 
	 * @param dateFrom
	 *            开始日期
	 * @param dateTo
	 *            结束日期
	 * @return 如果FROM在TO之前则返回小于0的值， 等于返回0 FROM在TO之后则返回大于0的值
	 */
	public static int compareDateFromAfterTo(String dateFrom, String dateTo)
	{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		int rtValue = 0;
		try
		{
			Date date1 = df.parse(dateFrom);
			Date date2 = df.parse(dateTo);
			rtValue = date1.compareTo(date2);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return rtValue;
	}

	/**
	 * 创建单据编码
	 * 
	 * @param p_pk_corp
	 *            公司主键
	 * @param p_billtype
	 *            单据类型编码
	 * @return 单据编码
	 */
	public static String createBillCode(String p_pk_corp, String p_billtype) throws Exception
	{
		String billcode = null;
		BillCodeObjValueVO vo = new BillCodeObjValueVO();
		try
		{
			billcode = (new BillcodeGenerater()).getBillCode(p_billtype, p_pk_corp, null, vo);
		} catch (ValidationException e)
		{
			e.printStackTrace();
			throw new ValidationException("生成销售流水号出错："
					+ e.getMessage());
		} catch (BusinessException e)
		{
			e.printStackTrace();
			throw new BusinessException("生成销售流水号出错："
					+ e.getMessage());
		}
		return billcode;
	}
}

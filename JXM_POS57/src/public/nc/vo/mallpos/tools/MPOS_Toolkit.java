package nc.vo.mallpos.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nc.bs.pub.billcodemanage.BillcodeGenerater;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ValidationException;
import nc.vo.pub.billcodemanage.BillCodeObjValueVO;

/**
 * ������
 * 
 * @author chenjun
 * @date 2011-3-23 ����10:31:18
 */
public class MPOS_Toolkit
{

	/**
	 * �ַ�������ת��Ϊ�ַ���,ʹ�ö��ŷָ�
	 * 
	 * @param str
	 * @param spliter
	 * @return String �磺'str','str','str'
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
	 * ��ѯ�������,������where�ؼ��� field,valueΪ�մ�����nullʱ������������ʧЧ
	 * 
	 * @param field
	 *            ��ѯ�ֶ�
	 * @param value
	 *            ��ѯֵ
	 * @param condition
	 *            ��ѯ���� ex. =,like��
	 * @param flg
	 *            ���ӷ���ex. and,or��
	 * @return �����ַ���
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
	 * ��ѯ�������,������where�ؼ���,Ĭ����and���� field,valueΪ�մ�����nullʱ������������ʧЧ
	 * 
	 * @param field
	 *            ��ѯ�ֶ�
	 * @param value
	 *            ��ѯֵ
	 * @param condition
	 *            ��ѯ���� ex. =,like��
	 * @return �����ַ���
	 */
	public static String putProperties(String field, String value, String condition)
	{
		return putProperties(field, value, condition, " and ");
	}

	/**
	 * �Ƚ�����ʱ��Ĵ�С
	 * 
	 * @param dateFrom
	 *            ��ʼ����
	 * @param dateTo
	 *            ��������
	 * @return ���FROM��TO֮ǰ�򷵻�С��0��ֵ�� ���ڷ���0 FROM��TO֮���򷵻ش���0��ֵ
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
	 * �������ݱ���
	 * 
	 * @param p_pk_corp
	 *            ��˾����
	 * @param p_billtype
	 *            �������ͱ���
	 * @return ���ݱ���
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
			throw new ValidationException("����������ˮ�ų���"
					+ e.getMessage());
		} catch (BusinessException e)
		{
			e.printStackTrace();
			throw new BusinessException("����������ˮ�ų���"
					+ e.getMessage());
		}
		return billcode;
	}
}

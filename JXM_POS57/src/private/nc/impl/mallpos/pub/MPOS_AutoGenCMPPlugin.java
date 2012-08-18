package nc.impl.mallpos.pub;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.pa.IBusinessPlugin;
import nc.bs.pub.pa.html.IAlertMessage;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_LogVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.pa.Key;

/**
 * ���ɸ��
 * 
 * @author chenjun
 * @date 2011-11-23 ����10:04:15
 */
public class MPOS_AutoGenCMPPlugin implements IBusinessPlugin
{

	public int getImplmentsType()
	{
		return 0;
	}

	public Key[] getKeys()
	{
		return null;
	}

	public String getTypeDescription()
	{
		return null;
	}

	public String getTypeName()
	{
		return null;
	}

	public IAlertMessage implementReturnFormatMsg(Key[] arg0, String arg1, UFDate arg2) throws BusinessException
	{
		return null;
	}

	public String implementReturnMessage(Key[] arg0, String arg1, UFDate arg2) throws BusinessException
	{
		MPOS_PubManageImpl manageImpl = new MPOS_PubManageImpl();
		BaseDAO dao = new BaseDAO();

		MPOS_PubQueryImpl queryImpl = new MPOS_PubQueryImpl();

		UFDate prePaydate = null;// �ϴθ�������
		String lrr = null;
		try
		{
			MPOS_ArgConfigVO cvo = queryImpl.queryFirst();
			if (cvo == null)
				return null;
			prePaydate = queryImpl.queryPrePayDate();
			if (prePaydate == null)// �״θ���
			{
				prePaydate = cvo.getFirst_paydate().getDateBefore(cvo.getPay_cycle());
			}
			// �������������ڼ��ϸ�����������������ʱ����֮���򷵻�
			// System.out.println(prePaydate.getDateAfter(cvo.getPay_cycle()));
			if (prePaydate.getDateAfter(cvo.getPay_cycle()).compareTo(arg2) > 0)
			{
				return null;
			}
			lrr = cvo.getPk_userid();

		} catch (Exception e1)
		{
			e1.printStackTrace();
			// д��־
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("��ʼ���Զ����ɸ������");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("��ѯ�������ó���" + e1.getMessage());
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		if (arg0 == null || arg0.length <= 0 || arg0[0].getValue() == null || "".equals(arg0[0].getValue()))
		{
			// д��־
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("��ʼ���Զ����ɸ������");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("��˾�������Ϊ�գ�");
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		String pk_corp = arg0[0].getValue().toString();
		if (StringUtils.isEmpty(pk_corp))
		{
			// д��־
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("��ʼ���Զ����ɸ������");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("��˾���������Ӧ�Ĺ�˾�����ڣ�");
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		try
		{

			if (StringUtils.isEmpty(lrr))
			{
				// д��־
				MPOS_LogVO logvo = new MPOS_LogVO();
				logvo.setStage("��ʼ���Զ����ɸ������");
				logvo.setLog_date(new UFDate(new Date()));
				logvo.setSuccess(new UFBoolean("N"));
				logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
				logvo.setContent("Ĭ���Ƶ���Ϊ��");
				dao.insertVO(logvo);
				return null;
			}
			MPOS_SaleDaySumVO[] saleVos = queryImpl.querySale(pk_corp," and isnull(mpos_sale_daysum.billstate,0) = 0 and mpos_sale_daysum.billdate<='" + prePaydate + "'");
			manageImpl.createCMPBill(lrr, saleVos, pk_corp, arg2);
		} catch (Exception e)
		{
			e.printStackTrace();
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("���ɸ��");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent(e.getMessage());
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
		}

		return null;
	}

	public Object implementReturnObject(Key[] arg0, String arg1, UFDate arg2) throws BusinessException
	{
		return null;
	}

	public boolean implementWriteFile(Key[] arg0, String arg1, String arg2, UFDate arg3) throws BusinessException
	{
		return false;
	}
}

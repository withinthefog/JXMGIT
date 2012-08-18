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
 * 生成付款单
 * 
 * @author chenjun
 * @date 2011-11-23 上午10:04:15
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

		UFDate prePaydate = null;// 上次付款日期
		String lrr = null;
		try
		{
			MPOS_ArgConfigVO cvo = queryImpl.queryFirst();
			if (cvo == null)
				return null;
			prePaydate = queryImpl.queryPrePayDate();
			if (prePaydate == null)// 首次付款
			{
				prePaydate = cvo.getFirst_paydate().getDateBefore(cvo.getPay_cycle());
			}
			// 如果最近付款日期加上付款周期在任务运行时日期之后则返回
			// System.out.println(prePaydate.getDateAfter(cvo.getPay_cycle()));
			if (prePaydate.getDateAfter(cvo.getPay_cycle()).compareTo(arg2) > 0)
			{
				return null;
			}
			lrr = cvo.getPk_userid();

		} catch (Exception e1)
		{
			e1.printStackTrace();
			// 写日志
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("初始化自动生成付款单任务");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("查询付款配置出错！" + e1.getMessage());
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		if (arg0 == null || arg0.length <= 0 || arg0[0].getValue() == null || "".equals(arg0[0].getValue()))
		{
			// 写日志
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("初始化自动生成付款单任务");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("公司编码参数为空！");
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		String pk_corp = arg0[0].getValue().toString();
		if (StringUtils.isEmpty(pk_corp))
		{
			// 写日志
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("初始化自动生成付款单任务");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent("公司编码参数对应的公司不存在！");
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
			dao.insertVO(logvo);
			return null;
		}

		try
		{

			if (StringUtils.isEmpty(lrr))
			{
				// 写日志
				MPOS_LogVO logvo = new MPOS_LogVO();
				logvo.setStage("初始化自动生成付款单任务");
				logvo.setLog_date(new UFDate(new Date()));
				logvo.setSuccess(new UFBoolean("N"));
				logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
				logvo.setContent("默认制单人为空");
				dao.insertVO(logvo);
				return null;
			}
			MPOS_SaleDaySumVO[] saleVos = queryImpl.querySale(pk_corp," and isnull(mpos_sale_daysum.billstate,0) = 0 and mpos_sale_daysum.billdate<='" + prePaydate + "'");
			manageImpl.createCMPBill(lrr, saleVos, pk_corp, arg2);
		} catch (Exception e)
		{
			e.printStackTrace();
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("生成付款单");
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

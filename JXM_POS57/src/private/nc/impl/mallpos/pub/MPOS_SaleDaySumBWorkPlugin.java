package nc.impl.mallpos.pub;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import nc.bs.dao.BaseDAO;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_LogVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * 后台任务执行：按商户识别号、销售日期、支付方式、收银员统计一天的汇总数
 * 
 * @author chenjun
 * @date 2012-1-12 下午03:46:56
 */
public class MPOS_SaleDaySumBWorkPlugin implements IBackgroundWorkPlugin
{

	public String executeTask(BgWorkingContext bgwc) throws BusinessException
	{
		UFDate date = new UFDate(new Date());
		BaseDAO dao = new BaseDAO();
		try
		{
			String pk_corp = bgwc.getKeyMap().get("公司主键").toString();
			if(StringUtils.isEmpty(pk_corp))
				throw new Exception("公司主键为空!");
			MPOS_PubQueryImpl queryImpl = new MPOS_PubQueryImpl();
			MPOS_SaleDaySumVO[] vos = queryImpl.querySaleDaySum(pk_corp,date);
			if (vos != null && vos.length > 0)
			{
				MPOS_PubManageImpl manageImpl =  new MPOS_PubManageImpl();
				manageImpl.saveSaleVO(vos,date);
			}
		} catch (Exception e)
		{
			// 写日志
			MPOS_LogVO logvo = new MPOS_LogVO();
			logvo.setStage("生成商户每天销售汇总");
			logvo.setLog_date(new UFDate(new Date()));
			logvo.setSuccess(new UFBoolean("N"));
			logvo.setContent(e.getMessage());
			logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_SALEDAYSUM);
			dao.insertVO(logvo);
			return e.getMessage();
		}
		return null;
	}

}

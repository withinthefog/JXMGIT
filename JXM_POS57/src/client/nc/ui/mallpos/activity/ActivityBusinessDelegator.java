package nc.ui.mallpos.activity;

import java.util.Hashtable;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.ui.trade.bsdelegate.BDBusinessDelegator;
import nc.vo.mallpos.activity.MPOS_ActivityCustVO;
import nc.vo.mallpos.activity.MPOS_ActivityRuleVO;
import nc.vo.pub.SuperVO;

public class ActivityBusinessDelegator extends BDBusinessDelegator {

	@Override
	public Hashtable loadChildDataAry(String[] tableCodes, String key)
			throws Exception {
		IUifService service = NCLocator.getInstance().lookup(IUifService.class);
		Hashtable<String, SuperVO[]> childtalbe = new Hashtable<String, SuperVO[]>();
		for (int i = 0; i < tableCodes.length; i++) {
			Class voClass = getVoClass(tableCodes[i]);
			SuperVO vo = (SuperVO) voClass.newInstance();
			String strWhere = (new StringBuilder())
					.append(vo.getParentPKFieldName()).append("='").append(key)
					.append("' and isnull(dr,0)=0 ").toString();
			SuperVO vos[] = service.queryByCondition(voClass, strWhere);
			childtalbe.put(tableCodes[i], vos);
		}

		return childtalbe;
	}

	private Class getVoClass(String tableCode) {
		if ("mpos_activity_rules".equals(tableCode)) {
			return MPOS_ActivityRuleVO.class;
		}
		if ("mpos_activity_custs".equals(tableCode)) {
			return MPOS_ActivityCustVO.class;
		}
		return null;
	}

}

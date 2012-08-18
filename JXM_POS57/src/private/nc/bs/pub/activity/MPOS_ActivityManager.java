package nc.bs.pub.activity;

import nc.bs.trade.business.HYSuperDMO;
import nc.vo.mallpos.activity.MPOS_ActivityTypeHeadVO;
import nc.vo.mallpos.pub.MPOS_IActivityExecuter;

public class MPOS_ActivityManager {
	
	private static MPOS_ActivityManager am;
	
	private MPOS_ActivityManager() {
		
	}
	
	public static MPOS_ActivityManager getInstance() {
		if(am == null) {
			am = new MPOS_ActivityManager();
		}
		return am;
	}
	
	public MPOS_IActivityExecuter getActivityHandler(String pk_activity_type)
			throws Exception {
		HYSuperDMO dmo = new HYSuperDMO();
		MPOS_ActivityTypeHeadVO head = (MPOS_ActivityTypeHeadVO) dmo
				.queryByPrimaryKey(MPOS_ActivityTypeHeadVO.class,
						pk_activity_type);
		return (MPOS_IActivityExecuter) Class.forName(head.getAdapter())
				.newInstance();
	}

}

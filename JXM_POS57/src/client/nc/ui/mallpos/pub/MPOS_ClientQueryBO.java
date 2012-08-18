package nc.ui.mallpos.pub;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.mallpos.invexchange.MPOS_InvActivityBodyVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeItemVO;

public class MPOS_ClientQueryBO {
	
	private IUAPQueryBS service;

	public IUAPQueryBS getService() {
		if(service == null) {
			service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return service;
	}
	
	@SuppressWarnings("unchecked")
	public MPOS_InvActivityBodyVO getActivityInvCust(String pk_activity, String pk_invdoc)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.* ");
		sql.append("   from mpos_activity_inv_h h, mpos_activity_inv_b b ");
		sql.append("  where h.pk_activity_inv_h = b.pk_activity_inv_h ");
		sql.append("    and b.pk_invdoc = '"+pk_invdoc+"' ");
		sql.append("    and h.pk_activity = '"+pk_activity+"' ");
		sql.append("    and b.dr = 0 ");
		sql.append("    and h.dr = 0 ");

		List<MPOS_InvActivityBodyVO> data = (List<MPOS_InvActivityBodyVO>) getService()
				.executeQuery(sql.toString(),
						new BeanListProcessor(MPOS_InvActivityBodyVO.class));
		if(data == null || data.size() <= 0) return null;
		return data.get(0);
	}

}

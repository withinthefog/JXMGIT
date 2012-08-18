package nc.bs.pub.activity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.trade.business.HYPubBO;
import nc.jdbc.framework.processor.MapProcessor;
import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailShareVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_IActivityExecuter;
import nc.vo.mallpos.tools.MPOS_Toolkit;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

public class MPOS_DiscountActivityHandler implements MPOS_IActivityExecuter {

	public void executeActivity(Object[] params) throws Exception {
		MPOS_SaleDetailBillVO[] saleBills = null;
		try {
			saleBills = (MPOS_SaleDetailBillVO[]) params[0];
		} catch (Exception e) {
			throw new IllegalArgumentException("活动参数不合法,折扣活动要求第一个参数为订单聚合VO的数组");
		}

		if (saleBills == null || saleBills.length <= 0)
			return;
		for (int i = 0; i < saleBills.length; i++) {
			handleActivityApportionment(saleBills[i]);
		}
	}

	private void handleActivityApportionment(MPOS_SaleDetailBillVO saleBill)
			throws Exception {
		if (saleBill == null || saleBill.getParentVO() == null
				|| saleBill.getChildrenVO() == null
				|| saleBill.getChildrenVO().length <= 0)
			return;

		MPOS_SaleDetailHeadVO head = (MPOS_SaleDetailHeadVO) saleBill
				.getParentVO();
		MPOS_SaleDetailShareVO[] shares = (MPOS_SaleDetailShareVO[]) saleBill
				.getTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_SHARE);
		if(shares == null || shares.length <= 0) return;
		MPOS_SaleDetailShareVO[] newshares = new MPOS_SaleDetailShareVO[shares.length];
		for (int i = 0; i < newshares.length; i++) {
			newshares[i] = (MPOS_SaleDetailShareVO) shares[i].clone();
			newshares[i].setPk_sale_share(null);
			newshares[i].setPk_sale_share(null);
		}
		
		UFDouble custmny = new UFDouble(0); // 分摊部分中商户承担的金额
		UFDouble mallmny = new UFDouble(0); // 分摊部分中商场承担的金额
		
		// 创建分摊单据
		MPOS_SaleDetailBillVO shareBill = new MPOS_SaleDetailBillVO();
		MPOS_SaleDetailHeadVO shareHead = (MPOS_SaleDetailHeadVO) head.clone();
		shareHead.setBill_date(new UFDate());
		shareHead.setSale_no(MPOS_Toolkit.createBillCode(head.getPk_corp(),
				MPOS_GlobalVariable.BILLTYPE_SALEDETAIL));
		shareHead.setSpk_saledetail_h(head.getPk_saledetail_h());
		shareHead.setSale_type(MPOS_GlobalVariable.SALETYPE_SHARE);
		shareBill.setParentVO(shareHead);
		shareBill.setTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_SHARE, newshares);
		
		// 根据分摊明细计算分摊金额
		for (int i = 0; i < shares.length; i++) {
			UFDouble ApportionmentRate = getApportionmentRate(
					head.getPk_cubasdoc(), shares[i].getPk_activity());

			UFDouble discountmny = shares[i].getDiscount_money();
			// 本条明细中商户应承担的分摊金额
			UFDouble tmpcustmny = discountmny.multiply(ApportionmentRate).div(
					new UFDouble(100), 2, UFDouble.ROUND_HALF_UP);
			custmny = custmny.add(tmpcustmny);
			mallmny = mallmny.add(discountmny.sub(tmpcustmny));
		}

		if (custmny.doubleValue() == 0 && mallmny.doubleValue() == 0)
			return;
		

		MPOS_SaleDetailBodyVO ApportionmentBody = new MPOS_SaleDetailBodyVO();
		ApportionmentBody.setSale_datetime(new UFDateTime(new Date()));
		ApportionmentBody.setCust_money(custmny);
		ApportionmentBody.setMall_money(mallmny);
		ApportionmentBody.setReceived_money(ApportionmentBody.getMall_money());
		shareBill.setTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_DETAIL,
				new MPOS_SaleDetailBodyVO[] { ApportionmentBody });
		
		HYPubBO bo = new HYPubBO();
		bo.saveBill(shareBill);
	}

	@SuppressWarnings("rawtypes")
	private UFDouble getApportionmentRate(String pk_cubasdoc, String pk_activity)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * ");
		sql.append("   from mpos_activity_custs cu ");
		sql.append("  where cu.pk_activity = '" + pk_activity + "' ");
		sql.append("    and cu.pk_cubasdoc = '" + pk_cubasdoc + "' ");
		sql.append("    and cu.dr = 0 ");

		BaseDAO dao = new BaseDAO();
		Map m = (Map) dao.executeQuery(sql.toString(), new MapProcessor());
		if (m == null)
			return new UFDouble(0);
		BigDecimal tmp = (BigDecimal) m.get("apportionmentrate");
		return new UFDouble(tmp);
	}

}

package nc.impl.mallpos.pub;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.pub.para.SysInitBO_Client;
import nc.vo.mallpos.cost.MPOS_CostVO;
import nc.vo.mallpos.pos.MPOS_CustdocVO;
import nc.vo.mallpos.pos.MPOS_DiscountTempVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_LogVO;
import nc.vo.mallpos.sale.MPOS_PaymentVO;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uapbd.bankaccount.BankaccbasVO;

import org.apache.commons.lang.StringUtils;

/**
 * 数据查询接口实现
 * 
 * @author chenjun
 * @date 2011-10-9 上午11:05:15
 */
public class MPOS_PubQueryImpl implements MPOS_PubQueryItf {
	@SuppressWarnings("unchecked")
	public MPOS_SaleDaySumVO[] querySale(String pk_corp,String p_sqlWhere) throws Exception {
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select mpos_sale_daysum.*,");
		sbSql.append("       case mpos_sale_daysum.billstate");
		sbSql.append("         when 0 then");
		sbSql.append("          '未生成付款单'");
		sbSql.append("         when 1 then");
		sbSql.append("          '已生成付款单'");
		sbSql.append("       end as strbillstate,");
		sbSql.append("       case mpos_sale_daysum.voucher");
		sbSql.append("         when 0 then");
		sbSql.append("          '未生成'");
		sbSql.append("         when 1 then");
		sbSql.append("          '已生成'");
		sbSql.append("       end as strvoucher,");
		sbSql.append("       cmp_busibill.djbh,");
		sbSql.append("       case cmp_busibill.zzzt");
		sbSql.append("         when 1 then");
		sbSql.append("          '已付款'");
		sbSql.append("         else");
		sbSql.append("          '未付款'");
		sbSql.append("       end as strpay,");
		sbSql.append("       bd_cubasdoc.custcode,");
		sbSql.append("       bd_cubasdoc.custname,");
		sbSql.append("       bd_balatype.balancode,");
		sbSql.append("       bd_balatype.balanname,");
		sbSql.append("       sm_user.user_code usercode,");
		sbSql.append("       sm_user.user_name username");
		sbSql.append("  from mpos_sale_daysum");
		sbSql.append("  left join bd_cubasdoc on mpos_sale_daysum.pk_cubasdoc =");
		sbSql.append("                           bd_cubasdoc.pk_cubasdoc");
		sbSql.append("  left join bd_balatype on mpos_sale_daysum.pk_balatype =");
		sbSql.append("                           bd_balatype.pk_balatype");
		sbSql.append("  left join sm_user on mpos_sale_daysum.pk_cashier = sm_user.cuserid");
		sbSql.append("  left join cmp_busibill on mpos_sale_daysum.pk_busibill =");
		sbSql.append("                            cmp_busibill.vouchid");
		sbSql.append(" where mpos_sale_daysum.pk_corp ='"+pk_corp+"' ");
		if (StringUtils.isNotEmpty(p_sqlWhere)) {
			sbSql.append(p_sqlWhere);
		}
		sbSql.append(" order by custcode,billdate");
		ArrayList<MPOS_SaleDaySumVO> list = null;

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_SaleDaySumVO>) dao.executeQuery(sbSql.toString(),
					new BeanListProcessor(MPOS_SaleDaySumVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询出错:" + e.getMessage());
		}

		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_SaleDaySumVO[0]);
		}
	}

	/**
	 * 根据客商编码与名称查询基本档案主键
	 * 
	 * @param custcode
	 *            客商编码
	 * @return
	 * @throws Exception
	 */
	public String queryPkcubasdocByCustcode(String custcode) throws Exception {
		String pk_cubasdoc = null;
		try {
			BaseDAO dao = new BaseDAO();
			StringBuffer sql = new StringBuffer("");
			sql.append(" select cb.pk_cubasdoc ");
			sql.append(" from bd_cubasdoc cb ");
			sql.append(" where cb.custcode = '" + custcode + "' ");
			HashMap map = (HashMap) dao.executeQuery(sql.toString(), null,
					new MapProcessor());
			if (map != null)
				pk_cubasdoc = map.get("pk_cubasdoc").toString();
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询商户主键出错:" + e.getMessage());
		}
		return pk_cubasdoc;
	}

	/**
	 * 根据公司主键、单据类型编码查询单据类型主键
	 * 
	 * @param p_pk_corp
	 *            公司主键
	 * @param p_billtypecode
	 *            单据类型编码
	 * @return
	 * @throws Exception
	 */
	public String getArapBillTypePK(String p_pk_corp, String p_billtypecode)
			throws Exception {
		String pk_billtype = null;
		String sql = " select djlxoid from arap_djlx where djlxbm = '"
				+ p_billtypecode + "' and dwbm = '" + p_pk_corp + "'";

		BaseDAO dao = new BaseDAO();

		try {
			HashMap map = (HashMap) dao.executeQuery(sql.toString(), null,
					new MapProcessor());
			if (map == null)
				return null;
			pk_billtype = map.get("djlxoid").toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return pk_billtype;
	}

	@SuppressWarnings("unchecked")
	public MPOS_CostVO[] queryCost(String p_sqlWhere) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from mpos_bussincome s where 1=1 ");
		if (StringUtils.isNotEmpty(p_sqlWhere)) {
			sql.append(p_sqlWhere);
		}
		sql.append(" order by s.custcode,s.billdate");
		ArrayList<MPOS_CostVO> list = null;

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_CostVO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(MPOS_CostVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
		}

		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_CostVO[0]);
		}
	}

	/**
	 * 根据客商基本档案主键查询银行档案
	 * 
	 * @param pk_cubasdoc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public BankaccbasVO queryBankaccbasVO(String pk_cubasdoc) throws Exception {
		BankaccbasVO vo = null;
		try {
			BaseDAO dao = new BaseDAO();
			StringBuffer sql = new StringBuffer();
			sql.append("select bb.*");
			sql.append("  from bd_custbank cb, bd_bankaccbas bb");
			sql.append(" where cb.pk_accbank = bb.pk_bankaccbas");
			sql.append("   and cb.pk_cubasdoc = '" + pk_cubasdoc + "'");
			sql.append("   and isnull(cb.dr, 0) = 0");
			sql.append("   and isnull(bb.dr, 0) = 0");
			sql.append("   and cb.defflag='Y'");
			ArrayList<BankaccbasVO> clist = (ArrayList<BankaccbasVO>) dao
					.executeQuery(sql.toString(), new BeanListProcessor(
							BankaccbasVO.class));
			if (clist != null && clist.size() > 0)
				vo = clist.get(0);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询客商银行账户" + e.getMessage());
		}
		return vo;
	}

	/**
	 * 根据公司编码查询主键
	 * 
	 * @param unitcode
	 * @return
	 * @throws Exception
	 */
	public String queryCorpPK(String unitcode) throws Exception {
		String pk_corp = null;
		String sql = " select c.pk_corp from bd_corp c where c.unitcode = '"
				+ unitcode + "'";
		BaseDAO dao = new BaseDAO();
		try {
			HashMap map = (HashMap) dao.executeQuery(sql.toString(), null,
					new MapProcessor());
			if (map == null)
				return null;
			pk_corp = map.get("pk_corp").toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询公司主键出错：" + e.getMessage());
		}
		return pk_corp;
	}

	@SuppressWarnings("unchecked")
	public MPOS_ArgConfigVO queryFirst() throws Exception {
		MPOS_ArgConfigVO vo = null;
		try {
			BaseDAO dao = new BaseDAO();
			StringBuffer sql = new StringBuffer();
			sql
					.append(" select * from mpos_argconfig where first_paydate is not null");
			ArrayList<MPOS_ArgConfigVO> clist = (ArrayList<MPOS_ArgConfigVO>) dao
					.executeQuery(sql.toString(), new BeanListProcessor(
							MPOS_ArgConfigVO.class));
			if (clist != null && clist.size() > 0)
				vo = clist.get(0);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询配置信息出错：" + e.getMessage());
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	public MPOS_ArgConfigVO[] queryAllPrePayDate() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select * from mpos_argconfig where paydate is not null order by paydate desc");
		ArrayList<MPOS_ArgConfigVO> list = null;

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_ArgConfigVO>) dao.executeQuery(sql
					.toString(), new BeanListProcessor(MPOS_ArgConfigVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询所有上次付款日期信息出错：" + e.getMessage());
		}

		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_ArgConfigVO[0]);
		}
	}

	@SuppressWarnings("unchecked")
	public MPOS_ReceivablesVO[] queryReceByWhere(String p_sqlWhere)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select r.*,");
		sql.append("       case r.billstatus");
		sql.append("         when '0' then");
		sql.append("          '未处理'");
		sql.append("         when '1' then");
		sql.append("          '已处理'");
		sql.append("       end as strbillstatus,");
		sql.append("       c.custcode,");
		sql.append("       c.custname");
		sql.append("  from mpos_receivables r");
		sql
				.append("  left join bd_cubasdoc c on r.pk_cubasdoc = c.pk_cubasdoc");
		sql.append("  where 1=1");
		if (StringUtils.isNotEmpty(p_sqlWhere)) {
			sql.append(p_sqlWhere);
		}
		sql.append(" order by r.billdate desc");
		ArrayList<MPOS_ReceivablesVO> list = null;

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_ReceivablesVO>) dao.executeQuery(sql
					.toString(),
					new BeanListProcessor(MPOS_ReceivablesVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询商户应收款项出错：" + e.getMessage());
		}

		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_ReceivablesVO[0]);
		}
	}

	/**
	 * 根据商户主键查询应收商户款项
	 * 
	 * @param pk_cubasdoc
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public MPOS_ReceivablesVO[] queryReceByCust(String pk_cubasdoc)
			throws Exception {
		ArrayList<MPOS_ReceivablesVO> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select *");
		sql.append("  from mpos_receivables r");
		sql.append(" where r.pk_cubasdoc = '" + pk_cubasdoc + "'");
		sql.append("   and r.billstatus = 0");
		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_ReceivablesVO>) dao.executeQuery(sql
					.toString(),
					new BeanListProcessor(MPOS_ReceivablesVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询商户应收款项出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_ReceivablesVO[0]);
		}
	}

	public UFDate queryPrePayDate() throws Exception {
		UFDate maxpaydate = null;
		String sql = " select max(paydate) paydate from mpos_argconfig where paydate is not null ";
		BaseDAO dao = new BaseDAO();
		try {
			HashMap map = (HashMap) dao.executeQuery(sql.toString(), null,
					new MapProcessor());
			if (map != null && map.get("paydate") != null
					&& StringUtils.isNotEmpty(map.get("paydate").toString()))
				maxpaydate = new UFDate(map.get("paydate").toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("查询最大付款日期出错" + e.getMessage());
		}
		return maxpaydate;
	}

	@SuppressWarnings("unchecked")
	public MPOS_LogVO[] queryLogByWhere(String p_sqlWhere) throws Exception {
		ArrayList<MPOS_LogVO> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from mpos_log where 1=1 ");
		if (StringUtils.isNotEmpty(p_sqlWhere)) {
			sql.append(p_sqlWhere);
		}
		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_LogVO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(MPOS_LogVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询失败日志出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_LogVO[0]);
		}
	}

	@SuppressWarnings("unchecked")
	public MPOS_PaymentVO[] queryPayBillByWhere(String p_sqlWhere)
			throws Exception {
		ArrayList<MPOS_PaymentVO> list = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select b.vouchid,b.djrq paydate, c.custcode, c.custname, b.bbje nmoney,b.djbh,");
		sql.append("       case b.zyx2");
		sql.append("         when 'Y' then '已生成'");
		sql.append("        else '未生成'");
		sql.append("       end as zyx2");
		sql.append("  from cmp_busibill b");
		sql.append(" inner join cmp_busibill_b bb on b.vouchid = bb.vouchid");
		sql.append("  left join bd_cubasdoc c on bb.hbbm = c.pk_cubasdoc");
		sql.append(" where b.dr = 0");
		sql.append("   and b.qcbz = 'N'");
		sql.append("   and b.pzglh = 2");
		sql.append("   and bb.dr = 0");
		sql.append("   and b.dwbm = '"
				+ InvocationInfoProxy.getInstance().getCorpCode() + "'");
		sql.append("   and b.djlxbm = 'D5'");
		if (StringUtils.isNotEmpty(p_sqlWhere)) {
			sql.append(p_sqlWhere);
		}
		sql.append("  order by c.custcode");
		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_PaymentVO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(MPOS_PaymentVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询付款结算单出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_PaymentVO[0]);
		}
	}

	@SuppressWarnings("unchecked")
	public MPOS_VoucherConfigVO[] queryAllVouConfigVO() throws Exception {
		ArrayList<MPOS_VoucherConfigVO> list = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from mpos_voucherconfig c");
		sql.append("  order by c.itemcode");
		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_VoucherConfigVO>) dao.executeQuery(sql
					.toString(), new BeanListProcessor(
					MPOS_VoucherConfigVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询凭证配置出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_VoucherConfigVO[0]);
		}
	}

	@SuppressWarnings("unchecked")
	public MPOS_CustdocVO queryByCode(String custcode) throws Exception {
		ArrayList<MPOS_CustdocVO> list = null;
		MPOS_CustdocVO vo = null;
		StringBuffer sql = new StringBuffer();
		sql
				.append("select distinct b.pk_cubasdoc, b.custcode, b.custname, b.pk_areacl,b.def1 bunkno");
		sql.append("  from bd_cubasdoc b, bd_cumandoc m");
		sql.append(" where b.pk_cubasdoc = m.pk_cubasdoc");
		sql.append("   and b.custcode = '" + custcode + "'");
		sql.append("   and b.dr = 0");
		sql.append("   and isnull(b.sealflag, 'N') = 'N'");
		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_CustdocVO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(MPOS_CustdocVO.class));
			if (list != null && list.size() > 0) {
				vo = list.get(0);
			}
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询商户信息出错：" + e.getMessage());
		}
		return vo;
	}


	/**
	 * 按商户识别号、销售日期、支付方式、收银员统计一天的汇总数（退货本身及原始销售的数据单独列出不进行汇总）
	 * 
	 * @param pk_corp 公司主键
	 * @param date
	 *            销售日期
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public MPOS_SaleDaySumVO[] querySaleDaySum(String pk_corp, UFDate date) throws Exception {
		ArrayList<MPOS_SaleDaySumVO> list = null;
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select mpos_saledetail_h.bill_date billdate,");
		sbSql.append("       mpos_saledetail_h.pk_corp,");
		sbSql.append("       mpos_saledetail_h.pk_cubasdoc,");
		sbSql.append("       mpos_saledetail_h.pk_cashier,");
		sbSql.append("       mpos_saledetail_b.pk_balatype,");
		sbSql.append("       sum(mpos_saledetail_b.received_money) nmoney,");
		sbSql.append("       isnull(sum(mpos_saledetail_b.nfee), 0) nfee,");
		sbSql.append("       0 billstate,");
		sbSql.append("       0 voucher");
		sbSql.append("  from mpos_saledetail_h");
		sbSql.append("  left join mpos_saledetail_b on mpos_saledetail_h.pk_saledetail_h =");
		sbSql.append("                                 mpos_saledetail_b.pk_saledetail_h");
		sbSql.append(" where not exists");
		sbSql.append(" (select dd.pk_saledetail_b");
		sbSql.append("          from mpos_saledetail_b dd");
		sbSql.append("         where dd.rt_pk_saledetail = mpos_saledetail_b.pk_saledetail_b");
		sbSql.append("           and dd.rt_pk_saledetail is not null)");
		sbSql.append("   and isnull(mpos_saledetail_h.dr, 0) = 0");
		sbSql.append("   and mpos_saledetail_h.pk_corp = '"+pk_corp+"'");
		sbSql.append("   and mpos_saledetail_h.issend is null");
		sbSql.append("   and mpos_saledetail_h.bill_date <= '"+date+"'");
		sbSql.append("   and mpos_saledetail_b.rt_pk_saledetail is null");
		sbSql.append(" group by mpos_saledetail_h.pk_corp,");
		sbSql.append("      	mpos_saledetail_h.pk_cubasdoc,");
		sbSql.append("          mpos_saledetail_h.bill_date,");
		sbSql.append("          mpos_saledetail_h.pk_cashier,");
		sbSql.append("          mpos_saledetail_b.pk_balatype");
		sbSql.append(" union all ");
		sbSql.append(" select mpos_saledetail_h.bill_date  billdate,");
		sbSql.append("       mpos_saledetail_h.pk_corp,");
		sbSql.append("       mpos_saledetail_h.pk_cubasdoc,");
		sbSql.append("       mpos_saledetail_h.pk_cashier,");
		sbSql.append("       mpos_saledetail_b.pk_balatype,");
		sbSql.append("       mpos_saledetail_b.received_money nmoney,");
		sbSql.append("       isnull(mpos_saledetail_b.nfee, 0) nfee,");
		sbSql.append("       0 billstate,");
		sbSql.append("       0 voucher");
		sbSql.append("  from mpos_saledetail_h");
		sbSql.append("  left join mpos_saledetail_b on mpos_saledetail_h.pk_saledetail_h =");
		sbSql.append("                                 mpos_saledetail_b.pk_saledetail_h");
		sbSql.append(" where (exists");
		sbSql.append("        (select dd.pk_saledetail_b");
		sbSql.append("           from mpos_saledetail_b dd");
		sbSql.append("          where dd.rt_pk_saledetail = mpos_saledetail_b.pk_saledetail_b");
		sbSql.append("            and dd.rt_pk_saledetail is not null) or");
		sbSql.append("        mpos_saledetail_b.rt_pk_saledetail is not null)");
		sbSql.append("   and mpos_saledetail_h.pk_corp = '"+pk_corp+"'");
		sbSql.append("   and isnull(mpos_saledetail_h.dr, 0) = 0");
		sbSql.append("   and mpos_saledetail_h.issend is null");
		sbSql.append("   and mpos_saledetail_h.bill_date <= '"+date+"'");
		sbSql.append(" order by billdate");

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_SaleDaySumVO>) dao.executeQuery(sbSql.toString(),
					new BeanListProcessor(MPOS_SaleDaySumVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询商户销售天汇总出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_SaleDaySumVO[0]);
		}
	}

	public boolean canrRturnGoods(String pk_cubasdoc, UFDouble money)
			throws Exception {
		if (money == null)
			return false;
		String paraValue = this.findParaValue(InvocationInfoProxy.getInstance()
				.getCorpCode(), MPOS_GlobalVariable.PARAM_CODE_ISCTRL);
		if (StringUtils.isNotEmpty(paraValue)
				&& new UFBoolean(paraValue).booleanValue()) {
			String paraPKValue = this.findParaPKValue(InvocationInfoProxy
					.getInstance().getCorpCode(),
					MPOS_GlobalVariable.PARAM_CODE_NOCTRLOP);
			// 如果退货不受控制的操作员参数进行了设置并等于当前操作员则能够进行退货，否则需要检验商户可用余额
			if (StringUtils.isNotEmpty(paraPKValue)
					&& InvocationInfoProxy.getInstance().getUserCode().equals(
							paraPKValue)) {
				return true;
			}
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(summoney) summoney");
			// sql.append(" from (select isnull(sum(salemoney), 0) -
			// isnull(sum(nfee), 0) summoney");
			sql.append("  from (select isnull(sum(salemoney), 0) summoney");// 2012-03-13
																			// 陈军
																			// 修改：不扣除手续费
			sql.append("          from knpos_saledetail");
			sql.append("         where isnull(dr, 0) = 0");
			sql.append("           and issend is null");
			sql.append("           and pk_cubasdoc = '" + pk_cubasdoc + "'");
			sql.append("        union all");
			// sql.append(" select isnull(sum(kn_saleincome.nmoney), 0) -
			// isnull(sum(kn_saleincome.nfee), 0) summoney");
			sql
					.append("        select isnull(sum(kn_saleincome.nmoney), 0) summoney");// 2012-03-13
																							// 陈军
																							// 修改：不扣除手续费
			sql.append("          from kn_saleincome");
			sql
					.append("          left join bd_cubasdoc on kn_saleincome.custcode = bd_cubasdoc.custcode");
			sql.append("         where isnull(kn_saleincome.billstate, 0) = 0");
			sql.append("           and bd_cubasdoc.pk_cubasdoc = '"
					+ pk_cubasdoc + "') t");
			UFDouble balance = null;
			try {
				BaseDAO dao = new BaseDAO();
				HashMap map = (HashMap) dao.executeQuery(sql.toString(), null,
						new MapProcessor());
				if (map != null
						&& map.get("summoney") != null
						&& StringUtils.isNotEmpty(map.get("summoney")
								.toString())) {
					balance = new UFDouble(map.get("summoney").toString());
				} else {
					balance = new UFDouble(0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("验证能否退货出错：" + e.getMessage());
			}
			if (money.compareTo(balance) > 0) {
				throw new Exception("该商户没有足够的余额来进行该笔退货！可用余额："
						+ balance.setScale(2, UFDouble.ROUND_HALF_UP) + "元！");
			}
		}
		return true;
	}

	/**
	 * 取公司参数PK值
	 * 
	 * @param p_pkcorp
	 *            公司主键
	 * @param p_paracode
	 *            参数编码
	 * @return
	 * @throws Exception
	 */
	private String findParaPKValue(String p_pkcorp, String p_paracode)
			throws Exception {
		String para_value = null;
		try {
			para_value = SysInitBO_Client.getPkValue(p_pkcorp, p_paracode);
		} catch (Exception e) {
			throw new Exception("查询控制参数出错：" + e.getMessage());
		}
		return para_value;
	}

	/**
	 * 取公司参数值
	 * 
	 * @param p_pkcorp
	 *            公司主键
	 * @param p_paracode
	 *            参数编码
	 * @return
	 * @throws Exception
	 */
	private String findParaValue(String p_pkcorp, String p_paracode)
			throws Exception {
		String para_value = null;
		try {
			para_value = SysInitBO_Client.getParaString(p_pkcorp, p_paracode);
		} catch (Exception e) {
			throw new Exception("查询控制参数出错：" + e.getMessage());
		}
		return para_value;
	}

	@SuppressWarnings("unchecked")
	public MPOS_SaleDetailBodyVO[] queryReturnGoodsByHPK(String pk_saledetail_h)
			throws Exception {
		ArrayList<MPOS_SaleDetailBodyVO> list = null;
		StringBuffer sbSql = new StringBuffer();
		
		sbSql.append("select mpos_saledetail_b.*,");
		sbSql.append("       bd_balatype.balanname,");
		sbSql.append("       mpos_saledetail_b.received_money -");
		sbSql.append("       isnull((select sum(abs(dd.received_money))");
		sbSql.append("             from mpos_saledetail_b dd");
		sbSql.append("            where dd.rt_pk_saledetail = mpos_saledetail_b.pk_saledetail_b),");
		sbSql.append("           0) canreturnmoney");
		sbSql.append("  from mpos_saledetail_b");
		sbSql.append("  left join mpos_balatype_config on mpos_saledetail_b.pk_balatype =");
		sbSql.append("                                    mpos_balatype_config.pk_balatype");
		sbSql.append("  left join bd_balatype on mpos_saledetail_b.pk_balatype =");
		sbSql.append("                           bd_balatype.pk_balatype");
		sbSql.append(" where mpos_saledetail_b.pk_saledetail_h = '"+pk_saledetail_h+"'");
		sbSql.append("   and mpos_balatype_config.isenable_return = 'Y'");
		sbSql.append("   and mpos_saledetail_b.received_money >0");
		sbSql.append("   and not exists");//--排除已经全额退货的记录
		sbSql.append(" (select dd.rt_pk_saledetail ");
		sbSql.append("          from mpos_saledetail_b dd");
		sbSql.append("         where dd.rt_pk_saledetail = mpos_saledetail_b.pk_saledetail_b");
		sbSql.append("           and abs(dd.received_money) =");
		sbSql.append("               abs(mpos_saledetail_b.received_money))");

		try {
			BaseDAO dao = new BaseDAO();
			list = (ArrayList<MPOS_SaleDetailBodyVO>) dao.executeQuery(sbSql
					.toString(), new BeanListProcessor(
					MPOS_SaleDetailBodyVO.class));
		} catch (DAOException e) {
			e.printStackTrace();
			throw new Exception("查询明细出错：" + e.getMessage());
		}
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_SaleDetailBodyVO[0]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public MPOS_BalaTypeConfigVO[] queryAllBalaTypeConfigVO() throws Exception {
		ArrayList<MPOS_BalaTypeConfigVO> list = null;
		StringBuffer sbSql = new StringBuffer();
		sbSql.append(" select * from mpos_balatype_config where isnull(dr,0)=0");
		BaseDAO dao = new BaseDAO();
		list = (ArrayList<MPOS_BalaTypeConfigVO>) dao
				.executeQuery(sbSql.toString(), new BeanListProcessor(
						MPOS_BalaTypeConfigVO.class));
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_BalaTypeConfigVO[list.size()]);
		}
	}
	
	@SuppressWarnings("unchecked")
	public MPOS_DiscountTempVO[] queryDiscountTempVO(String pk_cubasdoc)throws Exception
	{
		UFDate date = new UFDate(new Date());
		ArrayList<MPOS_DiscountTempVO> list = null;
		StringBuffer sbSql = new StringBuffer();
		sbSql.append("select mpos_activity.activityname,");
		sbSql.append("       mpos_activity.pk_activity,");
		sbSql.append("       mpos_activity_rules.discounttype,");
		sbSql.append("       mpos_activity_rules.pk_activity_type,");
		sbSql.append("	(case mpos_activity_rules.discounttype");
		sbSql.append("         when '"+MPOS_GlobalVariable.DISCOUNT_TYPE_CUT+"' then");
		sbSql.append("          '满减'");
		sbSql.append("         when '"+MPOS_GlobalVariable.DISCOUNT_TYPE_DISCOUNT+"' then");
		sbSql.append("          '满折'");
		sbSql.append("       end) discounttype_name,");
		sbSql.append("       mpos_activity_rules.beginmny,");
		sbSql.append("       mpos_activity_rules.endmny,");
		sbSql.append("       mpos_activity_rules.discountnum");
		sbSql.append("  from mpos_activity, mpos_activity_rules, mpos_activity_custs");
		sbSql.append(" where mpos_activity.pk_activity = mpos_activity_rules.pk_activity");
		sbSql.append("   and mpos_activity.pk_activity = mpos_activity_custs.pk_activity");
		sbSql.append("   and mpos_activity.statusflag = "+MPOS_GlobalVariable.ACTIVITY_STATUS_ACTIVE);
		sbSql.append("   and mpos_activity.pk_activity_type = '"+MPOS_GlobalVariable.PK_ACTIVITY_TYPE+"'");
		sbSql.append("	 and '"+date+"'>=mpos_activity.startdate");
		sbSql.append("   and '"+date+"'<=mpos_activity.enddate");
		sbSql.append("   and mpos_activity_custs.pk_cubasdoc = '"+pk_cubasdoc+"'");
		sbSql.append("   and isnull(mpos_activity.dr,0) = 0");
		sbSql.append("   and isnull(mpos_activity_rules.dr,0) = 0");
		sbSql.append("   and isnull(mpos_activity_custs.dr,0) = 0");

		BaseDAO dao = new BaseDAO();
		list = (ArrayList<MPOS_DiscountTempVO>) dao
				.executeQuery(sbSql.toString(), new BeanListProcessor(
						MPOS_DiscountTempVO.class));
		if (list == null) {
			return null;
		} else {
			return list.toArray(new MPOS_DiscountTempVO[list.size()]);
		}
	}
}

package nc.impl.mallpos.pub;

import java.util.ArrayList;
import java.util.Date;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.pub.pf.PfUtilBO;
import nc.bs.trade.business.HYPubBO;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.jdbc.framework.JdbcSession;
import nc.jdbc.framework.PersistenceManager;
import nc.jdbc.framework.exception.DbException;
import nc.vo.ep.dj.DJZBHeaderVO;
import nc.vo.ep.dj.DJZBItemVO;
import nc.vo.ep.dj.DJZBVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeBillVO;
import nc.vo.mallpos.invexchange.MPOS_InvExchangeItemVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailShareVO;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_LogVO;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.uapbd.bankaccount.BankaccbasVO;

import org.apache.commons.lang.StringUtils;

/**
 * 数据管理接口实现
 * 
 * @author chenjun
 * @date 2011-10-9 上午11:04:50
 */
public class MPOS_PubManageImpl implements MPOS_PubManageItf {
	public void createCMPBill(String llr, MPOS_SaleDaySumVO[] vos, String pk_corp,
			UFDate prePaydate) throws Exception {
		MPOS_PubQueryImpl queryImpl = new MPOS_PubQueryImpl();

		// ====================2012-03-21 陈军
		// 添加：主要解决通过界面点击生成付款单，如果数据库上次付款日期加上付款周期等于当前日期则写配置表
		UFDate dbprePaydate = queryImpl.queryPrePayDate();
		MPOS_ArgConfigVO cvoo = queryImpl.queryFirst();
		if (dbprePaydate.getDateAfter(cvoo.getPay_cycle()).compareTo(
				new UFDate(new Date())) <= 0) {
			// 写入付款日期到配置表中(不管有没有付款数据，只要到了付款日期都记录，不然下次付款时vos查不到数据)
			MPOS_ArgConfigVO cvo = new MPOS_ArgConfigVO();
			cvo.setPaydate(dbprePaydate.getDateAfter(cvoo.getPay_cycle()));// 2012-05-18
			// 陈军
			// 修改：如果到达付款日期任务没有执行，事后运行的话也可以正确定写入付款日期
			this.saveArgConfig(cvo);
		}
		// ====================

		if (vos == null || vos.length <= 0) {
			return;
		}
		String key = vos[0].getCustcode();
		UFDateTime currdatetime = new UFDateTime(new Date());
		// 付款
		String djtype = "D5";
		DJZBVO billvo = new DJZBVO();
		DJZBHeaderVO headvo = new DJZBHeaderVO();
		ArrayList<DJZBItemVO> itemvos = new ArrayList<DJZBItemVO>();

		UFDouble amount = new UFDouble();
		BankaccbasVO vo = null;
		BaseDAO dao = new BaseDAO();
		MPOS_LogVO logvo = null;
		ArrayList<MPOS_SaleDaySumVO> salevos = new ArrayList<MPOS_SaleDaySumVO>();
		String pk_cubasdoc = "";
		String notExistsCust = "";
		UFDouble nfee = null;
		for (int i = 0; i < vos.length; i++) {
			nfee = vos[i].getNfee().setScale(2, UFDouble.ROUND_HALF_UP);// 先对手续费进行四舍五入到两位小数后再计算
			if (i == 0) {
				pk_cubasdoc = queryImpl.queryPkcubasdocByCustcode(vos[i]
						.getCustcode());
				if (pk_cubasdoc == null || "".equals(pk_cubasdoc)) {
					notExistsCust = vos[i].getCustcode();// +
					// vos[i].getCustname();
					logvo = new MPOS_LogVO();
					logvo.setStage("生成付款单");
					logvo.setLog_date(new UFDate(new Date()));
					logvo.setSuccess(new UFBoolean("N"));
					logvo.setContent("找不到客商【" + vos[i].getCustcode()
							+ vos[i].getCustname() + "】");
					logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
					dao.insertVO(logvo);
					continue;
				}
				// 付款
				headvo.setDjdl("fj");
				headvo.setDjlxbm(djtype);
				headvo.setYwbm(queryImpl.getArapBillTypePK(pk_corp, djtype));
				headvo.setEffectdate(new UFDate(new Date()));
				headvo.setDjkjnd(new UFDate(new Date()).getYear() + "");
				headvo.setDjkjqj(new UFDate(new Date()).getStrMonth());
				headvo.setDjrq(new UFDate(new Date()));
				headvo.setDjzt(1);
				headvo.setDwbm(pk_corp);
				headvo.setFbje(new UFDouble(0));
				headvo.setIsjszxzf(new UFBoolean(false));
				headvo.setIsreded(new UFBoolean(false));
				// headvo.setJszxzf(0);
				headvo.setLrr(llr);
				headvo.setLybz(2);
				headvo.setPzglh(2);
				headvo.setQcbz(new UFBoolean(false));
				// headvo.setShkjnd(dt.getDate().getYear()+"");
				// headvo.setShkjqj(dt.getDate().getStrMonth());
				headvo.setSxbz(0);
				// headvo.setSxkjnd(dt.getDate().getYear()+"");
				// headvo.setSxkjqj(dt.getDate().getStrMonth());
				// headvo.setSxrq(dt.getDate());
				headvo.setXslxbm("00014710000000000E5G");
				headvo.setZgyf(0);
				headvo.setZzzt(0);

				vo = queryImpl.queryBankaccbasVO(pk_cubasdoc);
			}

			if (!vos[i].getCustcode().equals(key)) {
				if (itemvos.size() > 0) {
					saveCMPbill(currdatetime, djtype, billvo, headvo, itemvos,
							amount, dao, salevos);
				}
				billvo = new DJZBVO();
				headvo = new DJZBHeaderVO();
				itemvos.clear();
				amount = new UFDouble();
				salevos.clear();

				key = vos[i].getCustcode();

				pk_cubasdoc = queryImpl.queryPkcubasdocByCustcode(vos[i]
						.getCustcode());
				if (pk_cubasdoc == null || "".equals(pk_cubasdoc)) {
					notExistsCust = vos[i].getCustcode();// +
					// vos[i].getCustname();
					logvo = new MPOS_LogVO();
					logvo.setStage("生成付款单");
					logvo.setLog_date(new UFDate(new Date()));
					logvo.setSuccess(new UFBoolean("N"));
					logvo.setContent("找不到客商【" + vos[i].getCustcode()
							+ vos[i].getCustname() + "】");
					logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
					dao.insertVO(logvo);
					continue;
				}

				// 付款
				headvo.setDjdl("fj");
				headvo.setDjlxbm(djtype);
				headvo.setYwbm(queryImpl.getArapBillTypePK(pk_corp, djtype));
				headvo.setEffectdate(new UFDate(new Date()));
				headvo.setDjkjnd(new UFDate(new Date()).getYear() + "");
				headvo.setDjkjqj(new UFDate(new Date()).getStrMonth());
				headvo.setDjrq(new UFDate(new Date()));
				headvo.setDjzt(1);
				headvo.setDwbm(pk_corp);
				headvo.setFbje(new UFDouble(0));
				headvo.setIsjszxzf(new UFBoolean(false));
				headvo.setIsreded(new UFBoolean(false));
				// headvo.setJszxzf(0);
				headvo.setLrr(llr);
				headvo.setLybz(2);
				headvo.setPzglh(2);
				headvo.setQcbz(new UFBoolean(false));
				// headvo.setShkjnd(dt.getDate().getYear()+"");
				// headvo.setShkjqj(dt.getDate().getStrMonth());
				headvo.setSxbz(0);
				// headvo.setSxkjnd(dt.getDate().getYear()+"");
				// headvo.setSxkjqj(dt.getDate().getStrMonth());
				// headvo.setSxrq(dt.getDate());
				headvo.setXslxbm("00014710000000000E5G");
				headvo.setZgyf(0);
				headvo.setZzzt(0);

				vo = queryImpl.queryBankaccbasVO(pk_cubasdoc);
			}

			if (notExistsCust.equals(vos[i].getCustcode()))
				continue;

			salevos.add(vos[i]);

			if (vos[i].getNmoney().sub(nfee).compareTo(new UFDouble(0)) == 0) {
				vos[i].setBillstate(MPOS_GlobalVariable.SALEDAYSUM_BILLSTATE_HANDLED);
				dao.updateVO(vos[i]);
				continue;
			}
			amount = amount.add(vos[i].getNmoney().sub(nfee));
			DJZBItemVO bvo = new DJZBItemVO();

			bvo.setBbhl(new UFDouble(1));
			bvo.setBbye(vos[i].getNmoney().sub(nfee));
			bvo.setBilldate(vos[i].getBilldate());
			bvo.setBjdwhsdj(new UFDouble(0));
			bvo.setBjdwsl(new UFDouble(0));
			bvo.setBjdwwsdj(new UFDouble(0));
			bvo.setBzbm(MPOS_GlobalVariable.PK_CNY);
			bvo.setAttributeValue("checkflag", "0");

			// 付款
			bvo.setDjdl("fj");
			bvo.setDjlxbm(djtype);
			bvo.setYwbm(queryImpl.getArapBillTypePK(pk_corp, djtype));

			bvo.setDfbbje(new UFDouble(0));
			bvo.setDfbbsj(new UFDouble(0));
			bvo.setDfbbwsje(new UFDouble(0));
			bvo.setDffbje(new UFDouble(0));
			bvo.setDffbsj(new UFDouble(0));
			// bvo.setDfjs(new UFDouble(0));
			bvo.setDfshl(new UFDouble(0));
			bvo.setDfybje(new UFDouble(0));
			bvo.setDfybsj(new UFDouble(0));
			bvo.setDfybwsje(new UFDouble(0));
			bvo.setDj(new UFDouble(0));

			bvo.setJfbbje(vos[i].getNmoney().sub(nfee));
			bvo.setJfbbsj(new UFDouble(0));
			bvo.setJffbje(new UFDouble(0));
			bvo.setJffbsj(new UFDouble(0));
			// bvo.setJfjs(new UFDouble(0));
			// bvo.setAttributeValue("jfjs", new UFDouble(0));
			bvo.setJfshl(new UFDouble(0));
			bvo.setJfybje(vos[i].getNmoney().sub(nfee));
			bvo.setJfybsj(new UFDouble(0));
			bvo.setJfybwsje(vos[i].getNmoney().sub(nfee));

			bvo.setWbfbbje(vos[i].getNmoney().sub(nfee));
			bvo.setWbffbje(new UFDouble(0));
			bvo.setWbfybje(new UFDouble(0));

			bvo.setFx(1);
			bvo.setPayflag(0);
			bvo.setQxrq(new UFDate(new Date()));

			bvo.setDjxtflag(0);
			bvo.setDwbm(pk_corp);
			bvo.setFbhl(new UFDouble(0));
			bvo.setFbye(new UFDouble(0));
			bvo.setFlbh(0);
			bvo.setHbbm(pk_cubasdoc);
			bvo.setHsdj(new UFDouble(0));
			bvo.setKslb(1);
			bvo.setPausetransact(new UFBoolean(false));
			bvo.setpjdirection("none");
			bvo.setSfbz("3");
			bvo.setShlye(new UFDouble(0));
			bvo.setSl(new UFDouble(0));
			bvo.setTradertype(0);
			bvo.setTxlx_bbje(new UFDouble(0));
			bvo.setTxlx_fbje(new UFDouble(0));
			bvo.setTxlx_ybje(new UFDouble(0));
			bvo.setVerifyfinisheddate(new UFDate("3000-12-31"));
			bvo.setXgbh(-1);
			bvo.setYbye(vos[i].getNmoney().sub(nfee));

			bvo.setBfyhzh(MPOS_GlobalVariable.PK_PAYACCOUNT);
			bvo.setDfyhzh(vo == null ? null : vo.getPk_bankaccbas());
			bvo.setPj_jsfs(MPOS_GlobalVariable.PK_BALATYPE);

			bvo.setZy("付货款");

			itemvos.add(bvo);

			if (i == vos.length - 1) {
				if (itemvos.size() <= 0)
					continue;
				saveCMPbill(currdatetime, djtype, billvo, headvo, itemvos,
						amount, dao, salevos);
			}
		}
	}

	/**
	 * @param currdatetime
	 * @param djtype
	 * @param billvo
	 * @param headvo
	 * @param itemvos
	 * @param amount
	 * @param dao
	 * @param salevos
	 * @throws DAOException
	 */
	private void saveCMPbill(UFDateTime currdatetime, String djtype,
			DJZBVO billvo, DJZBHeaderVO headvo, ArrayList<DJZBItemVO> itemvos,
			UFDouble amount, BaseDAO dao, ArrayList<MPOS_SaleDaySumVO> salevos) {
		// 如果付款金额小于0则写入应收商户款项
		if (amount.compareTo(new UFDouble(0)) < 0) {
			try {
				MPOS_ReceivablesVO recevo = new MPOS_ReceivablesVO();
				recevo.setPk_cubasdoc(itemvos.get(0).getHbbm());
				recevo.setAmount(amount);
				recevo.setBilldate(new UFDate(new Date()));
				recevo.setBillstatus("0");
				dao.insertVO(recevo);
				for (MPOS_SaleDaySumVO saleVO : salevos) {
					saleVO.setBillstate(MPOS_GlobalVariable.SALEDAYSUM_BILLSTATE_HANDLED);
					dao.updateVO(saleVO);
				}
			} catch (Exception e) {
				e.printStackTrace();
				MPOS_LogVO logvo = new MPOS_LogVO();
				logvo.setStage("生成付款单");
				logvo.setLog_date(new UFDate(new Date()));
				logvo.setSuccess(new UFBoolean("N"));
				logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
				logvo.setContent("生成应收商户款项失败！商户编码："
						+ salevos.get(0).getCustcode() + ",商户名称："
						+ salevos.get(0).getCustname() + ",金额：" + amount
						+ e.getMessage());
				try {
					dao.insertVO(logvo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else {
			MPOS_PubQueryImpl queryImpl = new MPOS_PubQueryImpl();

			try {
				// 如果付款金额等于0，直接回写中间表处理状态,否则生成付款单
				if (amount.compareTo(new UFDouble(0)) == 0) {
					for (MPOS_SaleDaySumVO saleVO : salevos) {
						saleVO.setBillstate(MPOS_GlobalVariable.SALEDAYSUM_BILLSTATE_HANDLED);
						dao.updateVO(saleVO);
					}
					// 写日志
					MPOS_LogVO logvo = new MPOS_LogVO();
					logvo.setStage("生成付款单");
					logvo.setLog_date(new UFDate(new Date()));
					logvo.setSuccess(new UFBoolean("N"));
					logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
					logvo.setContent("商户编码：" + salevos.get(0).getCustcode()
							+ ",商户名称：" + salevos.get(0).getCustname() + ",金额："
							+ amount);
					try {
						dao.insertVO(logvo);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					// =====================================表体合并成一行，为了下面好扣减应收商户款项
					if (itemvos.size() > 0) {
						DJZBItemVO itemvo = new DJZBItemVO();
						UFDouble sumItemAmount = new UFDouble();
						for (DJZBItemVO tempvo : itemvos) {
							sumItemAmount = sumItemAmount.add(tempvo.getBbye());
						}
						itemvo = (DJZBItemVO) itemvos.get(0).clone();
						itemvo.setBbye(sumItemAmount);
						itemvo.setJfbbje(sumItemAmount);
						itemvo.setJfybje(sumItemAmount);
						itemvo.setJfybwsje(sumItemAmount);
						itemvo.setWbfbbje(sumItemAmount);
						itemvo.setYbye(sumItemAmount);
						itemvos.clear();
						itemvos.add(itemvo);
					}

					// =====================================

					// 查询商户是否有应收款
					MPOS_ReceivablesVO[] recevos = queryImpl
							.queryReceByCust(itemvos.get(0).getHbbm());
					if (recevos != null && recevos.length > 0) {
						UFDouble tempAmount = new UFDouble(amount.getDouble());// 不直接用amount运算主要是为了下面算已扣除应收款项
						for (MPOS_ReceivablesVO receivablesVO : recevos) {
							tempAmount = tempAmount.add(receivablesVO
									.getAmount());
						}
						if (tempAmount.compareTo(new UFDouble(0)) <= 0)// 如果扣去应收款项还是小于等于0
						{
							this.saveCMPbill(currdatetime, djtype, billvo,
									headvo, itemvos, tempAmount, dao, salevos);
							for (MPOS_ReceivablesVO receivablesVO : recevos) {
								receivablesVO.setBillstatus("1");
								dao.updateVO(receivablesVO);
							}
							return;
						} else {
							headvo.setScomment("已扣除应收款项"
									+ amount.sub(tempAmount) + "元");
							amount = tempAmount;

							// =====================================表体也相应扣减
							itemvos.get(0).setBbye(amount);
							itemvos.get(0).setJfbbje(amount);
							itemvos.get(0).setJfybje(amount);
							itemvos.get(0).setJfybwsje(amount);
							itemvos.get(0).setWbfbbje(amount);
							itemvos.get(0).setYbye(amount);
							// =====================================
						}
					}

					// 设置表头信息
					headvo.setBbje(amount);
					headvo.setYbje(amount);
					billvo.setParentVO(headvo);
					billvo.setChildrenVO(itemvos.toArray(new DJZBItemVO[0]));
					billvo.setParam_Ext_Save();
					Object obj = (new PfUtilBO()).processAction("SAVE", djtype,
							currdatetime.getDate().toString(), null, billvo,
							null);
					// 回写中间库状态
					if (obj != null) {
						String pk_busibill = ((ArrayList) obj).get(0)
								.toString();
						for (MPOS_SaleDaySumVO saleVO : salevos) {
							saleVO.setBillstate(MPOS_GlobalVariable.SALEDAYSUM_BILLSTATE_HANDLED);
							saleVO.setPk_busibill(pk_busibill);
							dao.updateVO(saleVO);
						}
						if (recevos != null && recevos.length > 0) {
							for (MPOS_ReceivablesVO receivablesVO : recevos) {
								receivablesVO.setBillstatus("1");
								receivablesVO.setPk_busibill(pk_busibill);
								dao.updateVO(receivablesVO);
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				MPOS_LogVO logvo = new MPOS_LogVO();
				logvo.setStage("生成付款单");
				logvo.setLog_date(new UFDate(new Date()));
				logvo.setSuccess(new UFBoolean("N"));
				logvo.setLog_type(MPOS_GlobalVariable.LOGTYPE_PAYMANT);
				logvo.setContent("商户编码：" + salevos.get(0).getCustcode()
						+ ",商户名称：" + salevos.get(0).getCustname() + ",金额："
						+ amount + e.getMessage());
				try {
					dao.insertVO(logvo);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	public void saveArgConfig(MPOS_ArgConfigVO p_cvo) throws Exception {
		try {
			BaseDAO dao = new BaseDAO();
			if (StringUtils.isEmpty(p_cvo.getPk_argconfig())) {
				dao.insertVO(p_cvo);
			} else {
				dao.updateVO(p_cvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存配置出错！" + e.getMessage());
		}
	}

	/**
	 * 调POS系统库的存储过程,重算参数(包括)以后所有未处理的数据。
	 * 
	 * @param date
	 *            日期，YYYY-MM-DD(计算商户销售表)，不计算则传空
	 * @param accperiod
	 *            会计期，YYYY-MM(计算商户费用表)，不计算则传空
	 * @throws Exception
	 */
	// public void callProc(String date, String accperiod) throws Exception
	// {
	// PersistenceManager sessionManager =
	// PersistenceManager.getInstance(KNPOS_GlobalVariable.DS);
	// JdbcSession session = sessionManager.getJdbcSession();
	// Connection conn = session.getConnection();
	//
	// CallableStatement cs = conn.prepareCall("{call KN_SALE_PROCE(?,?)}");
	// cs.setString(1, date);
	// cs.setString(2, accperiod);
	//
	// cs.executeUpdate();
	// sessionManager.release();
	// }
	public void saveVoucherConfig(MPOS_VoucherConfigVO[] p_vos)
			throws Exception {
		try {
			BaseDAO dao = new BaseDAO();
			for (MPOS_VoucherConfigVO configVO : p_vos) {
				if (StringUtils.isEmpty(configVO.getPk_voucherconfig())) {
					dao.insertVO(configVO);
				} else {
					dao.updateVO(configVO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存配置出错！" + e.getMessage());
		}
	}

	public void saveSaleVO(MPOS_SaleDaySumVO[] vos) throws Exception {
		try {
			BaseDAO dao = new BaseDAO();
			for (int i = 0; i < vos.length; i++) {
				if (StringUtils.isEmpty(vos[i].getPk_sale_daysum())) {
					dao.insertVO(vos[i]);
				} else {
					dao.updateVO(vos[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存商户销售天汇总出错！" + e.getMessage());
		}
	}

	/**
	 * 保存商户销售天汇总(后台任务执行)
	 * 
	 * @param vos
	 * @param date
	 *            执行日期
	 * @throws Exception
	 */
	public void saveSaleVO(MPOS_SaleDaySumVO[] vos, UFDate date) throws Exception {
		try {
			BaseDAO dao = new BaseDAO();
			for (int i = 0; i < vos.length; i++) {
				if (StringUtils.isEmpty(vos[i].getPk_sale_daysum())) {
					dao.insertVO(vos[i]);
				} else {
					dao.updateVO(vos[i]);
				}
			}
			// this.writeBackSaleDetail(date);
			StringBuffer sql = new StringBuffer();
			sql.append("   update knpos_saledetail set issend = 'Y' where ");
			sql.append("    isnull(DR, 0) = 0");
			sql.append("    and issend is null");
			sql.append("   and substring(sale_datetime, 0, 11) <= '" + date
					+ "'");
			dao.executeUpdate(sql.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("保存商户销售天汇总出错！" + e.getMessage());
		}
	}

	public void writeBackCMPbill(String vouchid) throws Exception {
		String sql = "update cmp_busibill set zyx2 = 'Y' where vouchid = '"
				+ vouchid + "'";
		try {
			BaseDAO dao = new BaseDAO();
			dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("回写付款结算单生成凭证标识出错！" + e.getMessage());
		}
	}

	
	public void saveSaleDetailAggExVO(MPOS_SaleDetailBillVO aggexvo)throws Exception
	{
		if (aggexvo == null || aggexvo.getParentVO() == null)
			return;
		BaseDAO dao = new BaseDAO();
		String pk_head = ((MPOS_SaleDetailHeadVO)aggexvo.getParentVO()).getPk_saledetail_h();
		MPOS_SaleDetailBodyVO[] bodyvos = (MPOS_SaleDetailBodyVO[])aggexvo.getTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_DETAIL);
		MPOS_SaleDetailShareVO[] sharevos = (MPOS_SaleDetailShareVO[])aggexvo.getTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_SHARE);
		if (StringUtils.isEmpty(pk_head)) {
			pk_head = dao.insertVO((MPOS_SaleDetailHeadVO)aggexvo.getParentVO());
			if (bodyvos != null) {
				for (int loop = 0; loop < bodyvos.length; loop++) {
					bodyvos[loop].setPk_saledetail_h(pk_head);
					dao.insertVO(bodyvos[loop]);
				}
			}
			if (sharevos != null) {
				for (int xx = 0; xx < sharevos.length; xx++) {
					sharevos[xx].setPk_saledetail_h(pk_head);
					dao.insertVO(sharevos[xx]);
				}
			}
		} else {
			dao.updateVO((MPOS_SaleDetailHeadVO)aggexvo.getParentVO());
			if (bodyvos != null) {
				for (int iii = 0; iii < bodyvos.length; iii++) {
					if (StringUtils.isEmpty(bodyvos[iii].getPk_saledetail_b())) {
						bodyvos[iii].setPk_saledetail_h(pk_head);
						dao.insertVO(bodyvos[iii]);
					} else {
						dao.updateVO(bodyvos[iii]);
					}
				}
			}
			if (sharevos != null) {
				for (int iii = 0; iii < sharevos.length; iii++) {
					if (StringUtils.isEmpty(sharevos[iii].getPk_sale_share())) {
						sharevos[iii].setPk_saledetail_h(pk_head);
						dao.insertVO(sharevos[iii]);
					} else {
						dao.updateVO(sharevos[iii]);
					}
				}
			}
		}
	}

	/**
	 * 回写销售明细表生成天汇总数据标识
	 * 
	 * @param date
	 * @throws Exception
	 */
	public void writeBackSaleDetail(UFDate date) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("   update knpos_saledetail set issend = 'Y' where ");
		sql.append("    isnull(DR, 0) = 0");
		sql.append("    and issend is null");
		sql.append("   and substring(sale_datetime, 0, 11) <= '" + date + "'");

		PersistenceManager sessionManager = null;
		try {
			sessionManager = PersistenceManager.getInstance();
			JdbcSession session = sessionManager.getJdbcSession();
			session.executeUpdate(sql.toString());
		} catch (DbException e) {
			e.printStackTrace();
			throw new Exception("回写销售明细表生成天汇总数据标识出错！" + e.getMessage());
		} finally {
			if (sessionManager != null)
				sessionManager.release();
		}
	}

	public void saveBalaTypeConfig(MPOS_BalaTypeConfigVO[] vos)
			throws Exception {
		BaseDAO dao = new BaseDAO();
		for (int i = 0; i < vos.length; i++) {
			if (StringUtils.isEmpty(vos[i].getPk_balatype_config())) {
				dao.insertVO(vos[i]);
			} else {
				dao.updateVO(vos[i]);
			}
		}
	}

	public void deleteBalaTypeConfig(String pk_balatype_config)
			throws Exception {
		BaseDAO dao = new BaseDAO();
		dao.deleteByPK(MPOS_BalaTypeConfigVO.class, pk_balatype_config);
	}

	public AggregatedValueObject saveExchangeBill(AggregatedValueObject billvo,
			Object userObj) throws Exception {
		HYPubBO bo = new HYPubBO();
		AggregatedValueObject retVo = bo.saveBD(billvo, userObj);

		// 处理兑换时消耗的积分
		MPOS_InvExchangeBillVO newBill = (MPOS_InvExchangeBillVO) retVo;
		MPOS_PubVipImpl vip = new MPOS_PubVipImpl();
		String pk_vip_card = (String) billvo.getParentVO().getAttributeValue(
				"pk_vipdoc_card");
		for (int i = 0; i < newBill
				.getTableVO(MPOS_InvExchangeBillVO.CHILD_TABLE_DETAIL).length; i++) {
			MPOS_InvExchangeItemVO item = (MPOS_InvExchangeItemVO) newBill
					.getTableVO(MPOS_InvExchangeBillVO.CHILD_TABLE_DETAIL)[i];
			if (item.getExchange_grade() == null)
				continue;
			vip.subGrade(pk_vip_card, item.getExchange_grade());
		}

		return retVo;
	}
}

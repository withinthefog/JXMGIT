/**
 * 
 */
package nc.impl.mallpos.pub;

import java.util.ArrayList;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubVipItf;
import nc.itf.uif.pub.IUifService;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;
import nc.vo.mallpos.vipcardgrade.MPOS_VipcardgradeVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocCardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author chenguogang
 *
 */
public class MPOS_PubVipImpl implements MPOS_PubVipItf {

	/**
	 * 
	 */
	public MPOS_PubVipImpl() {
		// TODO Auto-generated constructor stub
	}
	private IUifService service = null;

	private IUifService getService() {
		if (service == null) {
			service = (IUifService) NCLocator.getInstance().lookup(
					nc.itf.uif.pub.IUifService.class.getName());
		}
		return service;
	}
	/**
	 * ���ݻ�Ա����Ϣ��ѯ��Ա��Ϣ
	 * @param pk_vip_card
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocVO queryVipDocByPKVipCard(String pk_vip_card)throws Exception{
		MPOS_VipcardVO vipCardVO = (MPOS_VipcardVO)getService().queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
		//���ݻ�Ա����ѯ����Ա
		String vipDocCondition=" isnull(dr,0)=0 and pk_vipdoc in (select mpos_vipdoc_card.pk_vipdoc from mpos_vipdoc_card where mpos_vipdoc_card.pk_vip_card='"+pk_vip_card+"') ";
		MPOS_VipdocVO[] vipDocVO = (MPOS_VipdocVO[])getService().queryByCondition(MPOS_VipdocVO.class, vipDocCondition);
		if(vipDocVO==null||vipDocVO.length<=0){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}else if (vipDocVO.length>1){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա����ѯ���˶����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}
		return vipDocVO[0];
	}
	/**
	 * ���ݻ�Ա������ָ������
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void addGrade(String pk_vip_card,UFDouble grade)throws Exception{
		MPOS_VipcardVO vipCardVO = (MPOS_VipcardVO)getService().queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
		//���ݻ�Ա����ѯ����Ա
		String vipDocCondition=" isnull(dr,0)=0 and pk_vipdoc in (select mpos_vipdoc_card.pk_vipdoc from mpos_vipdoc_card where mpos_vipdoc_card.pk_vip_card='"+pk_vip_card+"') ";
		MPOS_VipdocVO[] vipDocVO = (MPOS_VipdocVO[])getService().queryByCondition(MPOS_VipdocVO.class, vipDocCondition);
		if(vipDocVO==null||vipDocVO.length<=0){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}else if (vipDocVO.length>1){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա����ѯ���˶����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}
		//��д��Ա������Ϣ
		if(vipDocVO[0].getGrade()==null){
			vipDocVO[0].setGrade(new UFDouble(0));
		}
		vipDocVO[0].setGrade(vipDocVO[0].getGrade().add(grade));
		getService().update(vipDocVO[0]);
	}
	/**
	 * ���ݻ�Ա���ۼ�ָ������
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void subGrade(String pk_vip_card,UFDouble grade)throws Exception{
		MPOS_VipcardVO vipCardVO = (MPOS_VipcardVO)getService().queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
		//���ݻ�Ա����ѯ����Ա
		String vipDocCondition=" isnull(dr,0)=0 and pk_vipdoc in (select mpos_vipdoc_card.pk_vipdoc from mpos_vipdoc_card where mpos_vipdoc_card.pk_vip_card='"+pk_vip_card+"') ";
		MPOS_VipdocVO[] vipDocVO = (MPOS_VipdocVO[])getService().queryByCondition(MPOS_VipdocVO.class, vipDocCondition);
		if(vipDocVO==null||vipDocVO.length<=0){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}else if (vipDocVO.length>1){
			throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա����ѯ���˶����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
		}
		//��д��Ա������Ϣ
		if(vipDocVO[0].getGrade()==null){
			vipDocVO[0].setGrade(new UFDouble(0));
		}
		vipDocVO[0].setGrade(vipDocVO[0].getGrade().sub(grade));
		getService().update(vipDocVO[0]);
	}
	/**
	 * ��Ա��������ʾ�����Ļ�Ա����ϸ��Ϣ
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocCardVO[] queryForBD(String condition) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select mpos_vipdoc_card.*,mpos_vip_card.inner_code not_pk_vip_card,mpos_vip_card.inner_code,mpos_vip_card.print_code,mpos_vip_card.wstatus,mpos_vipcard_grade.grade_name from mpos_vipdoc_card " +
				"left join mpos_vip_card on mpos_vip_card.pk_vip_card=mpos_vipdoc_card.pk_vip_card " +
				" left join mpos_vipcard_grade on mpos_vipcard_grade.pk_vipcard_grade=mpos_vip_card.pk_vipcard_grade where 1=1 ");
		sql.append(condition != null ? " and " + condition : " and 1<>1 ");
		BaseDAO dao = new BaseDAO();
		ArrayList<MPOS_VipdocCardVO> list = (ArrayList<MPOS_VipdocCardVO>) dao
				.executeQuery(sql.toString(), new BeanListProcessor(
						MPOS_VipdocCardVO.class));
		if (list != null && list.size() > 0) {
			return (MPOS_VipdocCardVO[]) list
					.toArray(new MPOS_VipdocCardVO[] {});
		} else {
			return null;
		}
	}
	/**
	 * ���ݴ����������ϸbillvo�������ӻ���
	 * @return �������ӵĻ��֣���Ա���ܻ���
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO addGrade(MPOS_SaleDetailBillVO saleDetailBillVo)
			throws Exception {
		// TODO Auto-generated method stub
		if(saleDetailBillVo!=null&&saleDetailBillVo.getTableVO("mpos_saledetail_b")!=null&&saleDetailBillVo.getTableVO("mpos_saledetail_b").length>0){
			MPOS_SaleDetailHeadVO saleDetailHeadVO = (MPOS_SaleDetailHeadVO)saleDetailBillVo.getParentVO();
			MPOS_SaleDetailBodyVO[] saleDetailBodyVOS = (MPOS_SaleDetailBodyVO[])saleDetailBillVo.getTableVO("mpos_saledetail_b");
			//��Ա������
			String pk_vip_card = saleDetailHeadVO.getPk_vip_card();
			MPOS_VipcardVO vipCardVO = (MPOS_VipcardVO)getService().queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
			//���ݻ�Ա����ѯ����Ա
			String vipDocCondition=" isnull(dr,0)=0 and pk_vipdoc in (select mpos_vipdoc_card.pk_vipdoc from mpos_vipdoc_card where mpos_vipdoc_card.pk_vip_card='"+pk_vip_card+"') ";
			MPOS_VipdocVO[] vipDocVO = (MPOS_VipdocVO[])getService().queryByCondition(MPOS_VipdocVO.class, vipDocCondition);
			if(vipDocVO==null||vipDocVO.length<=0){
				throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
			}else if (vipDocVO.length>1){
				throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա����ѯ���˶����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
			}
			//�����Ա����Ա�ȼ��ó�����ϵ��
			String condition=" Pk_Vipcard_Grade in (select Mpos_Vip_Card.Pk_Vipcard_Grade from Mpos_Vip_Card where Mpos_Vip_Card.Pk_Vip_Card='"+pk_vip_card+"')";
			MPOS_VipcardgradeVO[] vipGradeVO = (MPOS_VipcardgradeVO[])getService().queryByCondition(MPOS_VipcardgradeVO.class, condition);
			if(vipGradeVO!=null&&vipGradeVO.length>0){
				//����ϵ��
				UFDouble inl_rule = (vipGradeVO[0].getInl_rule()!=null?vipGradeVO[0].getInl_rule():new UFDouble(0));
				UFDouble addGrades = new UFDouble(0);
				UFDouble zero = new UFDouble(0);
				for(MPOS_SaleDetailBodyVO saleDetailBodyVO:saleDetailBodyVOS){
					if(saleDetailBodyVO.getDiscash_grade()!=null&&
							saleDetailBodyVO.getDiscash_grade().compareTo(zero)!=0){
						addGrades=addGrades.sub(saleDetailBodyVO.getDiscash_grade());
						saleDetailBodyVO.setExp_grade(saleDetailBodyVO.getDiscash_grade());
					}else{
						UFDouble received_money = saleDetailBodyVO.getReceived_money();
						if(inl_rule==null){
							throw new Exception ("δ�ҵ�����ϵ����Ϣ����ȷ�ϸû�Ա�Ļ�Ա�ȼ�ά���˻���ϵ����Ϣ��");
						}
						UFDouble grades = inl_rule.multiply(received_money);
						saleDetailBodyVO.setExp_grade(grades);
						saleDetailBodyVO.setGrade_cofit(inl_rule);
						addGrades=addGrades.add(grades);
					}
				}
				//��д��������
				saleDetailHeadVO.setBill_grade(addGrades);
				//��д��Ա������Ϣ
				if(vipDocVO[0].getGrade()==null){
					vipDocVO[0].setGrade(new UFDouble(0));
				}
				vipDocVO[0].setGrade(vipDocVO[0].getGrade().add(addGrades));
				getService().update(vipDocVO[0]);
			}else{
				throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա���ȼ���Ϣ����鿴��Ա���Ƿ�����˻�Ա���ȼ���Ϣ��");
			}
		}
		return saleDetailBillVo;
	}
	/**
	 * ���ݴ����������ϸbillvo�ۼ�����
	 * @return ���οۼ��Ļ��֣���Ա���ܻ���
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO subGrade(MPOS_SaleDetailBillVO saleDetailBillVo)
			throws Exception {
		// TODO Auto-generated method stub
		if(saleDetailBillVo!=null&&saleDetailBillVo.getTableVO("mpos_saledetail_b")!=null&&saleDetailBillVo.getTableVO("mpos_saledetail_b").length>0){
			MPOS_SaleDetailHeadVO saleDetailHeadVO = (MPOS_SaleDetailHeadVO)saleDetailBillVo.getParentVO();
			MPOS_SaleDetailBodyVO[] saleDetailBodyVOS = (MPOS_SaleDetailBodyVO[])saleDetailBillVo.getTableVO("mpos_saledetail_b");
					//��Ա������
			String pk_vip_card = saleDetailHeadVO.getPk_vip_card();
			MPOS_VipcardVO vipCardVO = (MPOS_VipcardVO)getService().queryByPrimaryKey(MPOS_VipcardVO.class, pk_vip_card);
			//���ݻ�Ա����ѯ����Ա
			String vipDocCondition=" isnull(dr,0)=0 and pk_vipdoc in (select mpos_vipdoc_card.pk_vipdoc from mpos_vipdoc_card where mpos_vipdoc_card.pk_vip_card='"+pk_vip_card+"') ";
			MPOS_VipdocVO[] vipDocVO = (MPOS_VipdocVO[])getService().queryByCondition(MPOS_VipdocVO.class, vipDocCondition);
			if(vipDocVO==null||vipDocVO.length<=0){
				throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա��δ��ѯ����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
			}else if (vipDocVO.length>1){
				throw new Exception("�ڲ����Ϊ"+vipCardVO.getInner_code()+"�Ļ�Ա����ѯ���˶����Ա��Ϣ����ȷ�ϸû�Ա���Ƿ���ȷ����!");
			}
			UFDouble addGrades = new UFDouble(0);
			UFDouble negativeOne = new UFDouble(-1);
			UFDouble zero = new UFDouble(0);
			for(MPOS_SaleDetailBodyVO saleDetailBodyVO:saleDetailBodyVOS){
				if(saleDetailBodyVO.getDiscash_grade()!=null&&
						saleDetailBodyVO.getDiscash_grade().compareTo(zero)!=0){
					UFDouble _grade = saleDetailBodyVO.getDiscash_grade().multiply(negativeOne);
					addGrades=addGrades.add(_grade);
					saleDetailBodyVO.setExp_grade(_grade);
				}else if(saleDetailBodyVO.getGrade_cofit()!=null&&saleDetailBodyVO.getReceived_money()!=null){
					UFDouble grade_cofit = saleDetailBodyVO.getGrade_cofit();//ϵ��
					UFDouble received_monney = saleDetailBodyVO.getReceived_money();
					UFDouble _grade = grade_cofit.multiply(received_monney).multiply(negativeOne);
					saleDetailBodyVO.setExp_grade(_grade);
					addGrades.add(_grade);
				}
			}
			//��д��������
			saleDetailHeadVO.setBill_grade(addGrades);
			//��д��Ա������Ϣ
			if(vipDocVO[0].getGrade()==null){
				vipDocVO[0].setGrade(new UFDouble(0));
			}
			vipDocVO[0].setGrade(vipDocVO[0].getGrade().add(addGrades));
			getService().update(vipDocVO[0]);
		}
		return saleDetailBillVo;
	}
}

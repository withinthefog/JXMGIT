package nc.vo.mallpos.activity;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_ActivityRuleVO extends SuperVO {
	
	private static final long serialVersionUID = -617718338109528125L;
	
	private String pk_activity_type;
	private String pk_activity;
	@BillTempletParams(@Param(key = "defaultName", value = "折扣类型"))
	private String discounttype;
	@BillTempletParams(@Param(key = "defaultName", value = "开始金额"))
	private UFDouble beginmny;
	@BillTempletParams(@Param(key = "defaultName", value = "结束金额"))
	private UFDouble endmny;
	@BillTempletParams(@Param(key = "defaultName", value = "折扣值"))
	private UFDouble discountnum;
	@BillTempletParams(@Param(key = "defaultName", value = "备注"))
	private String extendedrule;

	@Override
	public String getPKFieldName() {
		return "pk_activity_type";
	}

	@Override
	public String getParentPKFieldName() {
		return "pk_activity";
	}

	@Override
	public String getTableName() {
		return "mpos_activity_rules";
	}

	public String getPk_activity_type() {
		return pk_activity_type;
	}

	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

	public String getPk_activity() {
		return pk_activity;
	}

	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	public UFDouble getBeginmny() {
		return beginmny;
	}

	public void setBeginmny(UFDouble beginmny) {
		this.beginmny = beginmny;
	}

	public UFDouble getEndmny() {
		return endmny;
	}

	public void setEndmny(UFDouble endmny) {
		this.endmny = endmny;
	}

	public UFDouble getDiscountnum() {
		return discountnum;
	}

	public void setDiscountnum(UFDouble discountnum) {
		this.discountnum = discountnum;
	}

	public String getExtendedrule() {
		return extendedrule;
	}

	public void setExtendedrule(String extendedrule) {
		this.extendedrule = extendedrule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDiscounttype() {
		return discounttype;
	}

	public void setDiscounttype(String discounttype) {
		this.discounttype = discounttype;
	}

}

package com.glitter.helloworld;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/** * ��ҳ���� **  */
public class Pagination extends JdbcDaoSupport {
	public static final int PAGE_SIZE = 10;
	// һҳ��ʾ�ļ�¼��
	private int pageSize;
	// ��¼����
	private int totalRows;
	// ��ҳ��
	private int totalPages;
	// ��ǰҳ��
	private int pageNum;
	// ��ʼ����
	private int startIndex;
	// ��������
	private int lastIndex;
	// ��������List
	private List<?> resultList;
	// JdbcTemplate jTemplate
	private JdbcTemplate jTemplate;

	/**
	 * ÿҳ��ʾ10����¼�Ĺ��캯��,ʹ�øú��������ȸ�Pagination����pageNum��jTemplate��ֵ
	 * 
	 * @param sql
	 *            oracle���
	 */
	public Pagination(String sql) {
		if (jTemplate == null) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
		} else if (sql.equals("")) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
		}
		new Pagination(sql, pageNum, PAGE_SIZE, jTemplate);
	}

	/**
	 * ��ҳ���캯��
	 * 
	 * @param sql
	 *            ���ݴ����sql���õ�һЩ������ҳ��Ϣ
	 * @param pageNum
	 *            ��ǰҳ
	 * @param pageSize
	 *            ÿҳ��¼��
	 * @param jTemplate
	 *            JdbcTemplateʵ��
	 */
	@SuppressWarnings("deprecation")
	public Pagination(String sql, int pageNum, int pageSize,JdbcTemplate jTemplate) {
		if (jTemplate == null) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.jTemplate is null,please initial it first. ");
		} else if (sql == null || sql.equals("")) {
			throw new IllegalArgumentException(
					"com.deity.ranking.util.Pagination.sql is empty,please initial it first. ");
		}
		// ����ÿҳ��ʾ��¼��
		this.setPageSize(pageSize);
		// ����Ҫ��ʾ��ҳ��
		this.setPageNum(pageNum);
		// �����ܼ�¼��
		StringBuffer totalSQL = new StringBuffer(" SELECT count(*) FROM ( ");
		totalSQL.append(sql);
		totalSQL.append(" ) totalTable ");
		// ��JdbcTemplate��ֵ
		this.setJdbcTemplate(jTemplate);
		// �ܼ�¼��
		this.setTotalRows(getJdbcTemplate().queryForInt(totalSQL.toString()));
		// ������ҳ��
		this.setTotalPages();
		// ������ʼ����
		this.setStartIndex();
		// �����������
		this.setLastIndex();
		System.out.println("lastIndex=" + lastIndex);
		// ����oracle���ݿ�ķ�ҳ���
		StringBuffer paginationSQL = new StringBuffer(" SELECT * FROM ( ");
		paginationSQL.append(" SELECT temp.* ,ROWNUM num FROM ( ");
		paginationSQL.append(sql);
		paginationSQL.append(") temp where ROWNUM <= " + lastIndex);
		paginationSQL.append(" ) WHERE num > " + startIndex);
		// װ������
		setResultList(getJdbcTemplate().queryForList(paginationSQL.toString()));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	// ������ҳ��
	public void setTotalPages() {
		if (totalRows % pageSize == 0) {
			this.totalPages = totalRows / pageSize;
		} else {
			this.totalPages = (totalRows / pageSize) + 1;
		}
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex() {
		this.startIndex = (pageNum - 1) * pageSize;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public JdbcTemplate getJTemplate() {
		return jTemplate;
	}

	public void setJTemplate(JdbcTemplate template) {
		jTemplate = template;
	}

	// �������ʱ�������
	public void setLastIndex() {
		System.out.println("totalRows=" + totalRows);
		System.out.println("pageSize=" + pageSize);
		if (totalRows < pageSize) {
			this.lastIndex = totalRows;
		} else if ((totalRows % pageSize == 0) || (totalRows % pageSize != 0 && pageNum < totalPages)) {
			this.lastIndex = pageNum * pageSize;
		} else if (totalRows % pageSize != 0 && pageNum == totalPages) {// ���һҳ
			this.lastIndex = totalRows;
		}
	}

	// ���ҵ�ҵ���߼������У�
	/**
	 * find season ranking list from DC
	 * 
	 * @param areaId
	 *            ѡ������id
	 * @param rankDate
	 *            ����
	 * @param category
	 *            ���
	 * @param characterName
	 *            ��ɫ��
	 * @return List
	 */
	public List<?> findSeasonRankingList(Long areaId, int rankYear, int rankMonth,
			Long categoryId, String characterName) {
		StringBuffer sql = new StringBuffer(
				" SELECT C.USERID userid,D.POSNAME posname,C.GAMEID gameid,C.AMOUNT amount,C.RANK rank FROM ");
		// ��sql.append(" (SELECT B.USERID USERID,");
		sql.append(" B.POSID POSID,");
		sql.append(" A.DISTRICT_CODE DISTRICTCODE,");
		sql.append(" A.GAMEID GAMEID,");
		sql.append(" AMOUNT AMOUNT,");
		sql.append(" RANK RANK ");
		sql.append(" FROM TB_FS_RANK A ");
		sql.append(" LEFT JOIN TB_CHARACTER_INFO B ");
		sql.append(" ON A.DISTRICT_CODE = B.DISTRICT_CODE ");
		sql.append(" AND A.GAMEID = B.GAMEID ");
		// ��������
		if (areaId != null && areaId.intValue() != 0) {
			sql.append(" and A.DISTRICT_CODE = " + areaId.intValue());
		}
		if (rankYear > 1970 && rankMonth > 0) {
			// hql.append(" and sas.id.dt >= to_date('" + rankYear + "-" +
			// rankMonth + "-01 00:00:00'," + "YYYY-MM-DD HH24:MI:SS");
			// hql.append(" and sas.id.dt <= to_date('" + rankYear + "-" +
			// rankMonth + "-" + TimeTool.findMaxDateInMonth(rankYear,rankMonth)
			// + " 23:59:59'," + "YYYY-MM-DD HH24:MI:SS");
			sql.append(" and A.DT = fn_time_convert(to_date('" + rankYear + "-"
					+ rankMonth + "'," + "'YYYY-MM')) ");
		}
		if (categoryId != null && categoryId.intValue() != 0) {
			sql.append(" and A.CID = " + categoryId.intValue());
		}
		if (characterName != null && !characterName.trim().equals("")) {
			sql.append(" and A.GAMEID = '" + characterName.trim() + "' ");
		}
		sql.append(" ORDER BY RANK ASC) C ");
		sql.append(" LEFT JOIN TB_FS_POSITION D ");
		sql.append(" ON C.POSID = D.POSID ");
		sql.append(" ORDER BY C.RANK ");
		System.out.println("hql=" + sql.toString());// //////////////
		// ʹ���Լ��ķ�ҳ������ƽ����
		Pagination pageInfo = new Pagination(sql.toString(), 1, 10,
				getJdbcTemplate());
		return pageInfo.getResultList();
		// return getJdbcTemplate().queryForList(sql.toString());
	}
}
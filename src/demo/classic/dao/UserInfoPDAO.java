package demo.classic.dao;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ecside.common.QueryResult;
import org.ecside.common.log.LogHandler;
import org.ecside.easyda.DataAccessUtil;
import org.ecside.easydataaccess.ConnectionUtils;
import org.ecside.util.ECSideUtils;


public class UserInfoPDAO extends BaseDAO {
	
	private Log logger = LogFactory.getLog(BaseDAO.class);

	public List getAllUserInfo(){
		StringBuffer bufSql = new StringBuffer();

		bufSql.append("SELECT * FROM USER_INFO WHERE 1=1 ");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		List userList=null;
		
		
//		int testNum=10;
//		PreparedStatement[] pstms = new PreparedStatement[testNum];

		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());

//			for (int jj=0;jj<testNum;jj++){
//				pstms[jj]=ConnectionUtils.prepareStatement(conn,"SELECT * FROM USER_INFO WHERE "+jj+"="+jj+" ");
//			}
//			
			rest = pstmt.executeQuery();
			String[] columnName=getColumnName(rest);
			userList=new ArrayList();
			Map userInfo=null;
			while (rest.next()) {
				userInfo=new HashMap();
				buildRecord(rest,columnName,userInfo);
				userList.add(userInfo);
			}
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			userList=null;
		}finally{
//			close(rest, pstmt, conn);
			close(conn);
//			try {
//				for (int jj=0;jj<testNum;jj++){
//					System.out.println(jj+" : "+pstms[jj].isClosed());
//				}
//				System.out.println("conn : "+conn.isClosed());
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		
		return userList;
	}
	
	
	public int doUpdateUserInfo(Map userInfo){
		int opresult=-1;
		
		StringBuffer bufSql = new StringBuffer();
		bufSql.append("UPDATE USER_INFO SET PASSWD=? , USERROLE=? ,EMAIL=?, GENDER=?  WHERE USERID=?");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());
			pstmt.setString(1,((String[])userInfo.get("PASSWD"))[0] );
			pstmt.setString(2,((String[])userInfo.get("USERROLE"))[0] );
			pstmt.setString(3,((String[])userInfo.get("EMAIL"))[0] );
			pstmt.setString(4,((String[])userInfo.get("GENDER"))[0] );
			pstmt.setString(5,((String[])userInfo.get("recordKey"))[0] );
			opresult = pstmt.executeUpdate();
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			opresult=-1;
		}finally{
			close( pstmt, conn);
		}
		
		
		return opresult;
	}
	
	
	public int[] doUpdateUsers(List userInfoList){
		int[] opresult=null;
		
		StringBuffer bufSql = new StringBuffer();
		bufSql.append("UPDATE USER_INFO SET PASSWD=? , USERROLE=? ,EMAIL=?, GENDER=?  WHERE USERID=?");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());
			
			for (int i=0;i<userInfoList.size();i++){
				try{
					Map userInfo = (Map)userInfoList.get(i);
					pstmt.setString(1,(String)userInfo.get("PASSWD") );
					pstmt.setString(2,(String)userInfo.get("USERROLE") );
					pstmt.setString(3,(String)userInfo.get("EMAIL") );
					pstmt.setString(4,(String)userInfo.get("GENDER") );
					pstmt.setString(5,(String)userInfo.get("recordKey") );
					pstmt.addBatch();
				} catch (SQLException e) {
					
				}
			}

			opresult = pstmt.executeBatch();

		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			opresult=null;
		}finally{
			close( pstmt, conn);
		}
	
		return opresult;
	}
	
	
	
	public int getUserInfoNumber(Map filterPropertyMap){
		StringBuffer bufSql = new StringBuffer();

		bufSql.append("SELECT COUNT(USERID) FROM ");
		
		StringBuffer whereSql = new StringBuffer(" WHERE 1=1 ");
		
		
		if (filterPropertyMap!=null && !filterPropertyMap.isEmpty()){
			// 根据过滤条件进行sql语句的拼装.
			
			// 在本例中,只有 USERROLE USERNAME GENDER 是可以进行过滤的.
			// 在这里偷下懒,就不用 PreparedStatement 的方式了,而是直接把过滤项拼装进sql语句内.
			String filterProperty;
			String filterValue;
			
			filterProperty="USERROLE";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" = '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}

			filterProperty="USERNAME";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" like '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}
			
			filterProperty="GENDER";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" = '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}
		}
			bufSql.append(" USER_INFO ");
			bufSql.append(whereSql);

		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		int number=-1;
		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());
			rest = pstmt.executeQuery();
			if (rest.next()) {
				number=rest.getInt(1);
			}
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			number=-1;
		}finally{
			close(rest, pstmt, conn);
		}
		
		return number;
	}
	
	public List getSomeUserInfo(int startRow,int endRow,Map sortValueMap,Map filterPropertyMap){
		StringBuffer bufSql = new StringBuffer();
		int size=endRow-startRow;
		
		// 使用传统JDBC时,根据不同条件拼装不同的SQL一向是非常恼人的事情.
		// ECSide当然不能够帮助您解决这个问题.
		// 再次重申一遍,当翻页 过滤 排序 均基于数据库时,ECSide只是能够帮助开发者在后台更方便的取得操作相关的数据.
		// 而如何使用他们,仍然需要开发人员自己来决定.
		
		bufSql.append("SELECT * FROM ");
		
		StringBuffer whereSql = new StringBuffer(" WHERE 1=1 ");
		
		
		if (filterPropertyMap!=null && !filterPropertyMap.isEmpty()){
			// 根据过滤条件进行sql语句的拼装.
			
			// 在本例中,只有 USERROLE USERNAME GENDER 是可以进行过滤的.
			// 在这里偷下懒,就不用 PreparedStatement 的方式了,而是直接把过滤项拼装进sql语句内.
			String filterProperty;
			String filterValue;
			
			filterProperty="USERROLE";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" = '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}

			filterProperty="USERNAME";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" like '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}
			
			filterProperty="GENDER";
			filterValue=(String)filterPropertyMap.get(filterProperty);
			if (filterValue!=null){
				whereSql.append(" AND ").append(filterProperty).append(" = '").append(StringEscapeUtils.escapeSql(filterValue)).append("' ");
			}
		}
		
		if (sortValueMap!=null && !sortValueMap.isEmpty()){
			bufSql.append("( SELECT * FROM USER_INFO  ");
			bufSql.append(whereSql);
			bufSql.append(ECSideUtils.getDefaultSortSQL(sortValueMap));
			bufSql.append(" ) "); 
		}else{
			bufSql.append(" USER_INFO ");
			bufSql.append(whereSql);
		}
		
		bufSql.append(" LIMIT ? OFFSET ?");
		
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
		List userList=null;
		
		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());
			int prarameterIndex=0;
			
			
			pstmt.setInt(++prarameterIndex, size);
			pstmt.setInt(++prarameterIndex, startRow);
			
			rest = pstmt.executeQuery();
			String[] columnName=getColumnName(rest);
			userList=new ArrayList();
			Map userInfo=null;
			while (rest.next()) {
				userInfo=new HashMap();
				buildRecord(rest,columnName,userInfo);
				userList.add(userInfo);
			}
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
			userList=null;
		}finally{
			close(rest, pstmt, conn);
		}
		
		return userList;
	}

	
	public void exportUserListText(Map parameterMap,Map mappingItems,OutputStream outputStream){
		StringBuffer bufSql = new StringBuffer();

		bufSql.append("SELECT * FROM USER_INFO WHERE 1=1 ");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;

		try {
			conn = getConnection();
			pstmt = ConnectionUtils.prepareStatement(conn,bufSql.toString());

			rest = pstmt.executeQuery();
			
			DataAccessUtil.outputText(rest, outputStream, mappingItems);

			
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);

		}finally{
			close(rest, pstmt, conn);
		}

	}
	
	
	public QueryResult getUserInfoQueryResult(){
		QueryResult queryResult=new QueryResult();
		StringBuffer bufSql = new StringBuffer();

		bufSql.append("SELECT * FROM USER_INFO WHERE 1=1 ");
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rest = null;
	
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(bufSql.toString());

			rest = pstmt.executeQuery();
//			
			queryResult.setResultSet(rest);
			queryResult.setStatement(pstmt);
			queryResult.setConnection(conn);
			queryResult.setDataSource(getDataSource());
			
		} catch (Exception e) {
			LogHandler.errorLog(logger, e);
		}
		
		return queryResult;
	}
	

}

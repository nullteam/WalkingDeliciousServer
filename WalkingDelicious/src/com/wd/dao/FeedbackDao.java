package com.wd.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.wd.util.DBUtil;

public class FeedbackDao {

	private final String FEEDBACK_TABLE_NAME = "t_feedback";
	
	//private final String ID_TABLE = "id";
	//private final String USER_ID_TABLE = "user_id";
	//private final String CONTENT_TABLE = "content";
	
	private final String FEEDBACK_ADD_STRING  ="INSERT INTO "+FEEDBACK_TABLE_NAME+" VALUES(NULL,?,?)";
	public FeedbackDao() {
		// TODO Auto-generated constructor stub
	}

	public Boolean addFeedback(String userid,String content){
		Boolean flag = false;
		if(userid==null||content==null) return flag;
		UserDao userdao =new UserDao();
		if(userdao.getUserByName(userid)==null) return flag;
		if(content.length()>256) return flag;
		try {
			PreparedStatement ps = DBUtil.getInstance().getConnection().prepareStatement(FEEDBACK_ADD_STRING);
			ps.setString(1, userid);
			ps.setString(2, content);
			int result = -1;
			result = ps.executeUpdate();
			flag = result>0?true:false;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("create PreparedStatement failed!!!");
		}
		
		return flag;
	}
}

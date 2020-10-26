package com.wj.client.model.DAO;

public class AsAnswerDAO {
	private static AsAnswerDAO instance = null;
	private AsAnswerDAO() {}

	public static AsAnswerDAO getInstance() {
		if(instance==null) {
			synchronized (AsAnswerDAO.class) {
				instance = new AsAnswerDAO();
			}
		}
		return instance;
	}
	
	
	//-------------------------------------- AsAnswer table Query--------------------------------------//
	
	
	
	
}

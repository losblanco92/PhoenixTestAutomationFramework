package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.database.DataBaseManager;
import com.database.model.MapJobProblemModel;

public class MapJobProblemDao {

	private MapJobProblemDao() {

	}

	private final static String JOB_PROBLEM_QUERY = """
			Select * FROM map_job_problem where tr_job_head_id= ?
			""";

	public static List<MapJobProblemModel> getProblemInfo(int tr_job_head_id) {

		List<MapJobProblemModel> mapJobProblemModelList = new ArrayList<MapJobProblemModel>();
		MapJobProblemModel mapJobProblemModel = null;
		try {

			Connection conn = DataBaseManager.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(JOB_PROBLEM_QUERY);

			preparedStatement.setInt(1, tr_job_head_id);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				mapJobProblemModel =

						new MapJobProblemModel(resultSet.getInt("id"), resultSet.getInt("tr_job_head_id"),
								resultSet.getInt("mst_problem_id"), resultSet.getString("remark"));

				mapJobProblemModelList.add(mapJobProblemModel);

			}

		}

		catch (SQLException e) {

			System.err.println(e.getMessage());
		}

		return mapJobProblemModelList;

	}

}

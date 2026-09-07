package com.api.utils;

import java.util.List;

import org.testng.Assert;

import com.api.request.model.Problems;
import com.database.model.MapJobProblemModel;

public class AssertionUtility {

	public static void assertProblemDetails(List<MapJobProblemModel> problemDataFromDB,List<Problems> problems ) {

		for (int i = 0; i < problems.size(); i++) {

			Assert.assertEquals(problemDataFromDB.get(i).getMst_problem_id(), problems.get(i).id());

			Assert.assertEquals(problemDataFromDB.get(i).getRemark(), problems.get(i).remark());
		}
	}
}

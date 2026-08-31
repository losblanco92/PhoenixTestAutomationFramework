package com.database.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.api.request.model.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	public static void main(String[] args) {

		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();

		Iterator<CreateJobBean> iterator = beanList.iterator();

		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		List<CreateJobPayload> list = new ArrayList<CreateJobPayload>();

		while (iterator.hasNext()) {
			tempBean = iterator.next();

			tempPayload = CreateJobBeanMapper.mapper(tempBean);

			list.add(tempPayload);

		}

		list.iterator();

	}

}

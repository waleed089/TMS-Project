package com.demo.tms.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.tms.controller.TMSController;
import com.demo.tms.dto.TaskDTO;
import com.demo.tms.util.CommonUtil;

@Component
public class TMSScheduler {
	
	@Autowired
	private TaskDTO taskDTO;
	
	@Autowired
	private TMSController tmsController;
	
	@Scheduled(cron = "0 0/3 * * * *")
	public void cronJobSch() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println("Java cron job expression:: " + strDate);
		
		String[] arr={"Low","Medium","High"};
      	Random r = new Random();        
  		int randomNumber = r.nextInt(arr.length);
  		
  		int randomNumForDate = 30;
  		int randomNumberDate = r.nextInt(randomNumForDate);
  		
		taskDTO.setTitle("title_"+CommonUtil.getTodayDate());
		taskDTO.setDescription("This Task has been schedule randomly via Scheduler");
		taskDTO.setCreatedAt(new Date());
		taskDTO.setDueDate(CommonUtil.randomDueDate(randomNumberDate));
		taskDTO.setStatus(true);
		taskDTO.setPriority(arr[randomNumber]);
		
		tmsController.addTask(taskDTO);
	}
	
}
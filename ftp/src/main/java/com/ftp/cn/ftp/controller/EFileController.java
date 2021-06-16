package com.ftp.cn.ftp.controller;


import com.ftp.cn.ftp.service.FileSystemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataCL/eFileController")
public class EFileController {

	

    //ftp配置
    @Value("${ftp.host}")
    private String ftpIp;
    @Value("${ftp.port}")
    private int ftpPort;
    @Value("${ftp.username}")
    private String ftpUser;
    @Value("${ftp.password}")
    private String ftpPw;
    @Value("${ftp.filepath}")
    private String filepath;
    @Value("${savepath}")
    private String savepath;
    //ftp配置

	@Autowired
	private FileSystemService fileSystemServiceImpl;

    @RequestMapping("/sftpDown")
    @Scheduled(cron = "0 0 19 * * ?")
	public void sftpDown () throws Exception{
		fileSystemServiceImpl.downloadFile(filepath);
		
	}
	
}

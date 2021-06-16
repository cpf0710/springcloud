package com.ftp.cn.ftp.service.impl;

import java.io.File;
import java.util.Vector;

import com.ftp.cn.ftp.bo.SftpProperties;
import com.ftp.cn.ftp.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;


@Service
public class FileSystemServiceImpl implements FileSystemService {
    @Value("${sftp.savepath}")
    private String vsave;
    @Value("${sftp.client.root}")
    private String pathM;
    @Autowired
    private SftpProperties config;
 
    // 设置第一次登陆的时候提示，可选值：(ask | yes | no)
    private static final String SESSION_CONFIG_STRICT_HOST_KEY_CHECKING = "StrictHostKeyChecking";
 
    /**
     * 创建SFTP连接
     * @return
     * @throws Exception
     */
    private ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();
 
        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(config.getSessionConnectTimeout());
 
 
        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());
 
 
        return (ChannelSftp) channel;
    }
 
    /**
     * 创建session
     * @param jsch
     * @param host
     * @param username
     * @param port
     * @return
     * @throws Exception
     */
    private Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;
 
        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }
 
        if (session == null) {
            throw new Exception(host + " session is null");
        }
 
        session.setConfig(SESSION_CONFIG_STRICT_HOST_KEY_CHECKING, config.getSessionStrictHostKeyChecking());
        return session;
    }
 
    /**
     * 关闭连接
     * @param sftp
     */
    private void disconnect(ChannelSftp sftp) {
        try {
            if (sftp != null) {
                if (sftp.isConnected()) {
                    sftp.disconnect();
                } else if (sftp.isClosed()) {
                }
                if (null != sftp.getSession()) {
                    sftp.getSession().disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadFile(String targetPath) throws Exception {
        ChannelSftp sftp = this.createSftp();
        try {
            sftp.cd(config.getRoot());     
            File file = new File(targetPath.substring(targetPath.lastIndexOf("/") + 1));
     
            //outputStream = new FileOutputStream(file);
           // sftp.get(targetPath, "D://cpfEE");
            //Vector<?> list=sftp.ls(targetPath);
            test(targetPath,sftp);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            /*if (outputStream != null) {
                outputStream.close();
            }*/
            this.disconnect(sftp);
        }
    }
    public  void test(String directory,ChannelSftp sftp) throws Exception {
        if (isDirectory(directory,sftp)){
            Vector<ChannelSftp.LsEntry> sftpFile = sftp.ls(directory);

            for(ChannelSftp.LsEntry lsEntry:sftpFile){
                if (lsEntry.getFilename().length()>2){
//                      System.out.println("这是目录"+lsEntry.getFilename());

                    System.out.println(directory +"/"+ lsEntry.getFilename());
                test(directory +"/"+ lsEntry.getFilename(), sftp);
                }
            }
        }else {
            File file = new File(vsave+directory.substring(pathM.length())).getParentFile();
            if(!file.exists()){
                file.mkdirs();
            }
            try {
                sftp.get(directory, vsave+directory.substring(pathM.length()));
            }catch (Exception e){
                System.out.println("下载出现异常》》》》》》》》》》》》即将退出程序");
                return;
            }
            /*try{
                sftp.rm(directory);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("删除"+directory+"出现异常");

            }*/

        }

//        Vector<ChannelSftp.LsEntry> sftpFile = sftp.ls(directory);
//        for(ChannelSftp.LsEntry lsEntry:sftpFile){

    }
    public boolean isDirectory(String directory,ChannelSftp sftp) throws Exception {
        boolean isDirExistFlag = false;
        try{
            SftpATTRS sftpATTRS = sftp.lstat(directory);  //lstat()检索文件或目录的文件属性
            isDirExistFlag = true;
//            System.out.println(sftpATTRS.isDir());
            return sftpATTRS.isDir();  //isDir()检查此文件是否为目录
        }catch (Exception e){
           throw new Exception("检查目录不存在");
        }

    }
}

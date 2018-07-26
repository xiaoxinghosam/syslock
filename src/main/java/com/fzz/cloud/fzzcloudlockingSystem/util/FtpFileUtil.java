package com.fzz.cloud.fzzcloudlockingSystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpFileUtil {

    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "120.79.15.123";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "ftpuser";
    //密码
    private static final String FTP_PASSWORD = "123456";
    //图片路径
    private static final String FTP_BASEPATH = "/home/ftpuser/www/images";

    public  static boolean uploadFile(String originFileName,InputStream input){
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("UTF-8");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(FTP_BASEPATH );
            ftp.enterLocalPassiveMode();
            ftp.changeWorkingDirectory(FTP_BASEPATH );
            boolean storeFile = ftp.storeFile(new String(originFileName.getBytes("UTF-8"),"iso-8859-1"),input);
            if(storeFile == true)
            	input.close();
            	ftp.logout();
            	success = true;
            return false;
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return success;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	File file = new File("D:\\750FB8094C377A907554E6D560D9386C.jpg");
		InputStream fileInputStream = new FileInputStream(file);
		boolean uploadFile = uploadFile("750FB8094C377A907554E6D560D9386C.jpg", fileInputStream);
		System.out.println(uploadFile);
	}
}